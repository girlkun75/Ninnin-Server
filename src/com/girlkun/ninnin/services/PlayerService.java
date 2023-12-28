package com.girlkun.ninnin.services;

import com.girlkun.network.io.IMessage;
import com.girlkun.network.io.Message;
import com.girlkun.network.session.ISession;
import com.girlkun.ninnin.core.PlayerDAO;
import com.girlkun.ninnin.entities.player.Player;
import com.girlkun.ninnin.server.Cmd;
import com.girlkun.ninnin.server.io.MySession;
import com.girlkun.ninnin.utils.Utils;
import java.io.IOException;

/**
 *
 * @author ❤Girlkun75❤
 * @copyright ❤Trần Lại❤
 */
public class PlayerService {

    private static PlayerService I;

    public static PlayerService gI() {
        if (PlayerService.I == null) {
            PlayerService.I = new PlayerService();
        }
        return PlayerService.I;
    }

    public final void createNewPlayer(ISession is, IMessage msg) throws IOException, Exception {
        String name = msg.readUTF();
        MySession session = (MySession) is;
        if (PlayerDAO.createPlayer(is, name)) {
            AuthenticationService.gI().login(is, session.username, session.password);
        }
    }

    public final void sendMyPlayer(ISession is, Player player) {
        Message msg = new Message(Cmd.MY_PLAYER);
        try {
            msg.writeInt(player.getId());
            msg.writeUTF(player.getName());
            msg.writeFloat(player.getLocation().getX());
            msg.writeFloat(player.getLocation().getY());
            is.sendMessage(msg);
            msg.cleanup();
        } catch (Exception e) {
        }
    }

    public final void playerMove(Player player, IMessage message) throws IOException {
        float x = message.readFloat();
        float y = message.readFloat();
        player.getLocation().set(x, y);
        Message msg = new Message(Cmd.PLAYER_MOVE);
        msg.writeInt(player.getId());
        msg.writeFloat(x);
        msg.writeFloat(y);
        CoreService.gI().sendMessageAllPlayersInZoneIgnoreMe(player, msg);
        msg.cleanup();
    }

    public final void sendPlayerToPlayersInMap(Player player) {
        Message msg = new Message(Cmd.PLAYER_JOIN_MAP);
        try {
            msg.writeInt(player.getId());
            msg.writeUTF(player.getName());
            msg.writeFloat(player.getLocation().getX());
            msg.writeFloat(player.getLocation().getY());
            for (Player pl : player.getZone().getPlayers()) {
                pl.sendMessage(msg);
            }
            msg.cleanup();
        } catch (Exception e) {
        }
    }

    public final void sendPlayerExitZone(Player player) {
        Message msg = new Message(Cmd.PLAYER_EXIT_MAP);
        try {
            msg.writeInt(player.getId());
            CoreService.gI().sendMessageAllPlayersInZoneIgnoreMe(player, msg);
            msg.cleanup();
        } catch (Exception e) {
        }
    }

    public final void sendPlayersInMapToPlayer(Player player) {
        System.out.println("send map to me " + player.getZone().getPlayers().size());
        for (Player pl : player.getZone().getPlayers()) {
            Message msg = new Message(Cmd.PLAYER_JOIN_MAP);
            try {
                msg.writeInt(pl.getId());
                msg.writeUTF(pl.getName());
                msg.writeFloat(pl.getLocation().getX());
                msg.writeFloat(pl.getLocation().getY());
                player.sendMessage(msg);
                msg.cleanup();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public final void playerChat(Player player, String text) {
        Message msg = new Message(Cmd.PLAYER_CHAT);
        try {
            msg.writeInt(player.getId());
            msg.writeUTF(text);
            CoreService.gI().sendMessageAllPlayersInZone(player, msg);
            msg.cleanup();
        } catch (Exception e) {
        }
    }

    public final void setPos(Player player, float x, float y) {
        player.getLocation().set(x, y);
        Message msg = new Message(Cmd.SET_POS);
        try {
            msg.writeInt(player.getId());
            msg.writeFloat(x);
            msg.writeFloat(y);
            CoreService.gI().sendMessageAllPlayersInZone(player, msg);
            msg.cleanup();
        } catch (Exception e) {
        }
    }
}

/**
 * Vui lòng không sao chép mã nguồn này dưới mọi hình thức. Hãy tôn trọng tác
 * giả của mã nguồn này. Xin cảm ơn! - Girlkun75
 */
