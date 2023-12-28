package com.girlkun.ninnin.core;

import com.girlkun.database.GirlkunDB;
import com.girlkun.network.session.ISession;
import com.girlkun.ninnin.consts.ConstDB;
import com.girlkun.ninnin.consts.ConstEventPopup;
import com.girlkun.ninnin.entities.player.Player;
import com.girlkun.ninnin.server.io.MySession;
import com.girlkun.ninnin.services.CoreService;
import com.girlkun.result.GirlkunResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONArray;

/**
 *
 * @author ❤Girlkun75❤
 * @copyright ❤Trần Lại❤
 */
public class PlayerDAO {

    public static final void savePlayer(Player player) {
        JSONArray dataLocation = new JSONArray();
        dataLocation.add(player.getZone().getMap().getMapTemplate().getId());
        dataLocation.add(player.getLocation().getX());
        dataLocation.add(player.getLocation().getY());
        try {
            GirlkunDB.executeUpdate(ConstDB.NINNIN_RES.baseName,
                    "update player set name = ?, data_location = ? where id = ?",
                    player.getName(), dataLocation.toJSONString(), player.getId());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static final boolean createPlayer(ISession is, String name) {
        try {
            GirlkunResultSet rs = null;
            rs = GirlkunDB.executeQuery(ConstDB.NINNIN_RES.baseName, "select * from player where account_id = ? limit 1", ((MySession) is).id);
            if (rs.first()) {
                CoreService.gI().sendNotifyPopup(is, "Tài khoản đã tạo nhân vật!", ConstEventPopup.SWITCH_TO_MENU_SCREEN);
                return false;
            }
            rs.dispose();
            rs = GirlkunDB.executeQuery(ConstDB.NINNIN_RES.baseName, "select * from player where name = ? limit 1", name);
            if (rs.first()) {
                CoreService.gI().sendNotifyPopup(is, "Tên nhân vật đã tồn tại!", ConstEventPopup.SWITCH_TO_CREATE_PLAYER_SCREEN);
                return false;
            }
            rs.dispose();
            GirlkunDB.executeUpdate(ConstDB.NINNIN_RES.baseName, "insert into player (account_id, name, data_location) values ()",
                    ((MySession) is).id, name, "[1,1,1]");
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

}

/**
 * Vui lòng không sao chép mã nguồn này dưới mọi hình thức. Hãy tôn trọng tác
 * giả của mã nguồn này. Xin cảm ơn! - Girlkun75
 */
