package com.girlkun.ninnin.core;

import com.girlkun.database.GirlkunDB;
import com.girlkun.network.session.ISession;
import com.girlkun.ninnin.consts.ConstDB;
import com.girlkun.ninnin.consts.ConstEventPopup;
import com.girlkun.ninnin.entities.player.Player;
import com.girlkun.ninnin.server.ClientManager;
import com.girlkun.ninnin.server.ManagerService;
import com.girlkun.ninnin.server.io.MySession;
import com.girlkun.ninnin.services.CoreService;
import com.girlkun.result.GirlkunResultSet;
import org.json.simple.JSONArray;
import org.json.simple.JSONValue;

/**
 *
 * @author ❤Girlkun75❤
 * @copyright ❤Trần Lại❤
 */
public class Authentication {

    public static boolean register(ISession is, String username, String password) {
        try {
            GirlkunResultSet rs = GirlkunDB.executeQuery(ConstDB.NINNIN_RES.baseName, "select * from account where username = ? limit 1", username);
            if (rs.first()) {
                CoreService.gI().sendNotifyPopup(is, "Tài khoản đã tồn tại!", ConstEventPopup.SWITCH_TO_REGISTER_SCREEN);
                return false;
            }
            GirlkunDB.executeUpdate(ConstDB.NINNIN_RES.baseName, "insert into account (username, password) values ()", username, password);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static Player login(ISession is, String username, String password) {
        MySession mySession = (MySession) is;
        Player player = null;
        try {
            GirlkunResultSet rs = GirlkunDB.executeQuery(ConstDB.NINNIN_RES.baseName,
                    "select * from account where username = ? and password = ? limit 1", username, password);
            if (!rs.first()) {
                CoreService.gI().sendNotifyPopup(is, "Thông tin tài khoản hoặc mật khẩu không chính xác!", ConstEventPopup.SWITCH_TO_MENU_SCREEN);
                return player;
            }
            int accountId = rs.getInt("id");
            Player pl = ClientManager.getPlayerByAccountId(accountId);
            if (pl != null) {
                pl.getSession().disconnect();
                return null;
            }
            mySession.id = accountId;
            mySession.username = username;
            mySession.password = password;
            rs.dispose();
            rs = GirlkunDB.executeQuery(ConstDB.NINNIN_RES.baseName, "select * from player where account_id = ? limit 1", accountId);
            if (rs.first()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int mapId = 0;
                float x = 0;
                float y = 0;
                JSONArray dataArr = (JSONArray) JSONValue.parse(rs.getString("data_location"));
                mapId = Integer.parseInt(String.valueOf(dataArr.get(0)));
                x = Float.parseFloat(String.valueOf(dataArr.get(1)));
                y = Float.parseFloat(String.valueOf(dataArr.get(2)));
                player = new Player();
                player.setId(accountId);
                player.setName(name);
                player.getLocation().set(x, y);
                player.setSession(is);
                ((MySession) is).player = player;
                player.setZone(ManagerService.gI().findZoneByMapIdAndZoneId(mapId, 0));
            } else {
                CoreService.gI().switchToCreatePlayerScreen(is);
            }
        } catch (Exception e) {
        }
        return player;
    }

}

/**
 * Vui lòng không sao chép mã nguồn này dưới mọi hình thức. Hãy tôn trọng tác
 * giả của mã nguồn này. Xin cảm ơn! - Girlkun75
 */
