package com.girlkun.ninnin.services;

import com.girlkun.network.io.IMessage;
import com.girlkun.network.session.ISession;
import com.girlkun.ninnin.consts.ConstEventPopup;
import com.girlkun.ninnin.core.Authentication;
import com.girlkun.ninnin.entities.player.Player;
import com.girlkun.ninnin.server.ClientManager;
import java.io.IOException;

/**
 *
 * @author ❤Girlkun75❤
 * @copyright ❤Trần Lại❤
 */
public class AuthenticationService {

    private static AuthenticationService I;

    public static AuthenticationService gI() {
        if (AuthenticationService.I == null) {
            AuthenticationService.I = new AuthenticationService();
        }
        return AuthenticationService.I;
    }

    public final void login(ISession is, String username, String password) throws Exception {
        Player player = Authentication.login(is, username, password);
        if (player == null) {
            return;
        }
        ClientManager.addPlayer(player);
        player.getZone().playerJoinZone(player);
        PlayerService.gI().sendMyPlayer(is, player);
        CoreService.gI().sendDoneLoadGame(is);
        System.out.println("login");
    }

    public final void register(ISession is, IMessage msg) throws IOException, Exception {
        String username = msg.readUTF();
        String password = msg.readUTF();
        String repassword = msg.readUTF();
        if (username.equals("")) {
            CoreService.gI().sendNotifyPopup(is, "Tên tài khoản không được để trống!", ConstEventPopup.SWITCH_TO_REGISTER_SCREEN);
            return;
        }
        if (password.equals("")) {
            CoreService.gI().sendNotifyPopup(is, "Mật khẩu không được để trống!", ConstEventPopup.SWITCH_TO_REGISTER_SCREEN);
            return;
        }
        if (!password.equals(repassword)) {
            CoreService.gI().sendNotifyPopup(is, "Mật khẩu nhập lại không chính xác!", ConstEventPopup.SWITCH_TO_REGISTER_SCREEN);
            return;
        }
        if (username.length() < 6) {
            CoreService.gI().sendNotifyPopup(is, "Tài khoản ít nhất 6 ký tự!", ConstEventPopup.SWITCH_TO_REGISTER_SCREEN);
            return;
        }
        if (password.length() < 6) {
            CoreService.gI().sendNotifyPopup(is, "Mật khẩu ít nhất 6 ký tự!", ConstEventPopup.SWITCH_TO_REGISTER_SCREEN);
            return;
        }
        if (Authentication.register(is, username, password)) {
            this.login(is, username, password);
        }
    }
}

/**
 * Vui lòng không sao chép mã nguồn này dưới mọi hình thức. Hãy tôn trọng tác
 * giả của mã nguồn này. Xin cảm ơn! - Girlkun75
 */
