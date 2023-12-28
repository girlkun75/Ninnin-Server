package com.girlkun.ninnin.services;

import com.girlkun.network.io.IMessage;
import com.girlkun.network.io.Message;
import com.girlkun.network.session.ISession;
import com.girlkun.ninnin.Resources;
import com.girlkun.ninnin.consts.ConstEventPopup;
import com.girlkun.ninnin.entities.map.Zone;
import com.girlkun.ninnin.entities.player.Player;
import com.girlkun.ninnin.server.Cmd;

/**
 *
 * @author ❤Girlkun75❤
 * @copyright ❤Trần Lại❤
 */
public class CoreService {

    private static CoreService I;

    public static CoreService gI() {
        if (CoreService.I == null) {
            CoreService.I = new CoreService();
        }
        return CoreService.I;
    }

    public final void sendMessageAllPlayersInZoneIgnoreMe(Player player, IMessage msg) {
        if (player == null || player.getZone() == null) {
            return;
        }
        for (Player pl : player.getZone().getPlayers()) {
            if (pl.equals(player)) {
                continue;
            }
            pl.sendMessage(msg);
        }
    }

    public final void sendMessageAllPlayersInZone(Player player, IMessage msg) {
        if (player == null || player.getZone() == null) {
            return;
        }
        for (Player pl : player.getZone().getPlayers()) {
            pl.sendMessage(msg);
        }
    }

    public final void sendDoneLoadGame(ISession is) {
        is.sendMessage(new Message(Cmd.DONE_LOAD_GAME));
    }

    public final void sendNotifyPopup(ISession is, String text, ConstEventPopup event) {
        Message msg = new Message(Cmd.NOTIFY_POPUP);
        try {
            msg.writeUTF(Resources.TITLE_NOTIFY_POPUP);
            msg.writeUTF(text);
            msg.writeByte(event.id);
            is.sendMessage(msg);
            msg.cleanup();
        } catch (Exception e) {
        }
    }

    public final void switchToCreatePlayerScreen(ISession is) {
        is.sendMessage(new Message(Cmd.SWITCH_TO_CREATE_PLAYER_SCREEN));
    }

}

/**
 * Vui lòng không sao chép mã nguồn này dưới mọi hình thức. Hãy tôn trọng tác
 * giả của mã nguồn này. Xin cảm ơn! - Girlkun75
 */
