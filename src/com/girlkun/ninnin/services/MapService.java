package com.girlkun.ninnin.services;

import com.girlkun.network.io.Message;
import com.girlkun.ninnin.entities.map.Map;
import com.girlkun.ninnin.entities.map.Zone;
import com.girlkun.ninnin.entities.player.Player;
import com.girlkun.ninnin.server.Cmd;
import com.girlkun.ninnin.server.Manager;

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
            msg.writeInt(zone.getMap().getMapTemplate().getId());
            msg.writeUTF(zone.getMap().getMapTemplate().getName());
            msg.writeByte(zone.getId());
            player.sendMessage(msg);
            msg.cleanup();
        } catch (Exception e) {
        }
    }

    public final void requestChangeMap(Player player, int waypointId) {
        Map mapz = player.getZone().getMap();
        Map map = Manager.MAPS.get(mapz.getMapTemplate().getId() == 1? 1 : 0);
        player.getZone().playerExitZone(player);
        map.getZones().get(0).playerJoinZone(player);
        PlayerService.gI().setPos(player, 1, 1);
        CoreService.gI().sendDoneLoadGame(player.getSession());
    }

}

/**
 * Vui lòng không sao chép mã nguồn này dưới mọi hình thức. Hãy tôn trọng tác
 * giả của mã nguồn này. Xin cảm ơn! - Girlkun75
 */
