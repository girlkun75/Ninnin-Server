package com.girlkun.ninnin.services;

import com.girlkun.network.io.IMessage;
import com.girlkun.network.io.Message;
import com.girlkun.network.session.ISession;
import com.girlkun.ninnin.Resources;
import com.girlkun.ninnin.entities.player.Player;
import com.girlkun.ninnin.server.Cmd;
import com.girlkun.ninnin.utils.FileIO;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ❤Girlkun75❤
 * @copyright ❤Trần Lại❤
 */
public class DataService {

    private static DataService I;

    public static DataService gI() {
        if (DataService.I == null) {
            DataService.I = new DataService();
        }
        return DataService.I;
    }

    public final void sendIcon(ISession is, int iconId) {
        byte[] data = FileIO.readFile("data/girlkun/icon/" + iconId + ".png");
        Message msg = new Message(Cmd.ICON);
        try {
            msg.writeInt(iconId);
            msg.writeByteArray(data);
            is.sendMessage(msg);
            msg.cleanup();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public final void sendTileSet(Player player, IMessage message) throws Exception {
        int mapId = message.readInt();
        byte[] data = FileIO.readFile("data/girlkun/map/tilesets/" + 1 + ".png");
        Message msg = new Message(Cmd.TILE_SET);
        msg.writeInt(mapId);
        msg.writeByteArray(data);
        msg.writeByte(16);
        msg.writeByte(16);
        player.sendMessage(msg);
        msg.cleanup();
    }

    public final void sendTileMap(Player player, IMessage message) throws Exception {
        int mapId = message.readInt();
        byte[] data = Resources.map;
        int w = 70;
        int h = 50;
        if (mapId == 2) {
            w = 30;
            h = 30;
            data = Resources.map_2;
        }
        Message msg = new Message(Cmd.TILE_MAP);
        msg.writeInt(mapId);
        msg.writeByteArray(data);
        msg.writeByte(w);
        msg.writeByte(h);
        player.sendMessage(msg);
        msg.cleanup();
    }
}

/**
 * Vui lòng không sao chép mã nguồn này dưới mọi hình thức. Hãy tôn trọng tác
 * giả của mã nguồn này. Xin cảm ơn! - Girlkun75
 */
