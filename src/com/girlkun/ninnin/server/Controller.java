package com.girlkun.ninnin.server;

import com.girlkun.network.handler.IMessageHandler;
import com.girlkun.network.io.Message;
import com.girlkun.network.session.ISession;
import com.girlkun.ninnin.entities.player.Player;
import com.girlkun.ninnin.server.io.MySession;
import com.girlkun.ninnin.services.AuthenticationService;
import com.girlkun.ninnin.services.DataService;
import com.girlkun.ninnin.services.MapService;
import com.girlkun.ninnin.services.PlayerService;
import com.girlkun.ninnin.services.SkillService;

/**
 *
 * @author ❤Girlkun75❤
 * @copyright ❤Trần Lại❤
 */
public class Controller implements IMessageHandler {
    
    private static Controller I;
    
    public static Controller gI() {
        if (Controller.I == null) {
            Controller.I = new Controller();
        }
        return Controller.I;
    }
    
    @Override
    public void onMessage(ISession is, Message msg) {
        try {
            Player player = ((MySession) is).getPlayer();
            if (player != null) {
                this.handleMessageRequirePlayer(player, msg);
            } else {
                this.handleMessageNotRequirePlayer(is, msg);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void handleMessageRequirePlayer(Player player, Message msg) throws Exception {
        switch (msg.command) {
            case Cmd.ICON:
                DataService.gI().sendIcon(player.getSession(), msg.readInt());
                break;
            case Cmd.PLAYER_MOVE:
                PlayerService.gI().playerMove(player, msg);
                break;
            case Cmd.TILE_SET:
                DataService.gI().sendTileSet(player, msg);
                break;
            case Cmd.TILE_MAP:
                DataService.gI().sendTileMap(player, msg);
                break;
            case Cmd.PLAYER_CHAT:
                PlayerService.gI().playerChat(player, msg.readUTF());
                break;
            case Cmd.REQUEST_CHANGE_MAP:
                MapService.gI().requestChangeMap(player, msg.readByte());
                break;
            case Cmd.ZONES_INFO:
                MapService.gI().sendZonesInfo(player);
                break;
            case Cmd.REQUEST_CHANGE_ZONE:
                MapService.gI().requestChangeZone(player, msg.readByte());
                break;
            case Cmd.PLAYER_ATTACK:
                SkillService.gI().playerUseSkill(player);
                break;
        }
    }
    
    private void handleMessageNotRequirePlayer(ISession is, Message msg) throws Exception {
        switch (msg.command) {
            case Cmd.LOGIN:
                AuthenticationService.gI().login(is, msg.readUTF(), msg.readUTF());
                break;
            case Cmd.CREATE_NEW_PLAYER:
                PlayerService.gI().createNewPlayer(is, msg);
                break;
            case Cmd.REGISTER:
                AuthenticationService.gI().register(is, msg);
                break;
        }
    }
    
}

/**
 * Vui lòng không sao chép mã nguồn này dưới mọi hình thức. Hãy tôn trọng tác
 * giả của mã nguồn này. Xin cảm ơn! - Girlkun75
 */
