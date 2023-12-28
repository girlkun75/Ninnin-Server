package com.girlkun.ninnin.services;

import com.girlkun.network.io.Message;
import com.girlkun.ninnin.entities.map.Map;
import com.girlkun.ninnin.entities.map.Waypoint;
import com.girlkun.ninnin.entities.map.Zone;
import com.girlkun.ninnin.entities.player.Player;
import com.girlkun.ninnin.managers.MapManager;
import com.girlkun.ninnin.server.Cmd;
import com.girlkun.ninnin.server.Manager;
import com.girlkun.ninnin.templates.MapTemplate;

/**
 *
 * @author ❤Girlkun75❤
 * @copyright ❤Trần Lại❤
 */
public class MapService {

    private static MapService I;

    public static MapService gI() {
        if (MapService.I == null) {
            MapService.I = new MapService();
        }
        return MapService.I;
    }

    public final void sendInfoMap(Player player) {
        Zone zone = player.getZone();
        if (zone == null) {
            return;
        }
        Message msg = new Message(Cmd.INFO_MAP);
        try {
            MapTemplate temp = zone.getMap().getMapTemplate();
            msg.writeInt(temp.getId());
            msg.writeUTF(temp.getName());
            msg.writeByte(zone.getId());
            msg.writeByte(temp.getWaypoints().size());
            for (Waypoint waypoint : temp.getWaypoints()) {
                msg.writeByte(waypoint.getId());
                msg.writeUTF(waypoint.getName());
                msg.writeInt(waypoint.getX());
                msg.writeInt(waypoint.getY());
                msg.writeInt(waypoint.getW());
                msg.writeInt(waypoint.getH());
                msg.writeBoolean(waypoint.isEnter());
            }
            player.sendMessage(msg);
            msg.cleanup();
        } catch (Exception e) {
        }
    }

    public final void requestChangeMap(Player player, int waypointId) {
        MapTemplate temp = player.getZone().getMap().getMapTemplate();
        Waypoint waypoint = null;
        for (Waypoint w : temp.getWaypoints()) {
            if (w.getId() == waypointId) {
                waypoint = w;
                break;
            }
        }
        if (waypoint == null || !waypoint.isOverlap(player.getLocation().getX(), player.getLocation().getY())) {
            return;
        }
        Map map = MapManager.findById(waypoint.getMapIdGo());
        if (map == null) {
            return;
        }
        player.getZone().playerExitZone(player);
        player.getLocation().set(waypoint.getXGo() / Manager.PPM, waypoint.getYGo() / Manager.PPM);
        map.getZones().get(0).playerJoinZone(player);
        PlayerService.gI().synchronizedPosPlayer(player);
        CoreService.gI().sendDoneLoadGame(player.getSession());
    }
    
    public final void changeMap(Player player, Zone zoneJoin){
        player.getZone().playerExitZone(player);
    }

    public final void sendZonesInfo(Player player) {
        System.out.println("zones info");
        Message msg = new Message(Cmd.ZONES_INFO);
        try {
            msg.writeByte(player.getZone().getMap().getZones().size());
            for (Zone zone : player.getZone().getMap().getZones()) {
                msg.writeByte(zone.getId());
                msg.writeByte(zone.getPlayers().size());
                msg.writeByte(10); //max player
            }
            player.sendMessage(msg);
            msg.cleanup();
        } catch (Exception e) {
        }
    }

    public final void requestChangeZone(Player player, byte zoneId) {
        if (zoneId < 0 || zoneId > player.getZone().getMap().getZones().size() - 1) {
            return;
        }
        Zone zone = player.getZone().getMap().getZones().get(zoneId);
        player.getZone().playerExitZone(player);
        zone.playerJoinZone(player);
        CoreService.gI().sendDoneLoadGame(player.getSession());
    }

}

/**
 * Vui lòng không sao chép mã nguồn này dưới mọi hình thức. Hãy tôn trọng tác
 * giả của mã nguồn này. Xin cảm ơn! - Girlkun75
 */
