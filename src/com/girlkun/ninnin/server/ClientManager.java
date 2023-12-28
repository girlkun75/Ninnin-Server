package com.girlkun.ninnin.server;

import com.girlkun.ninnin.entities.player.Player;
import com.girlkun.ninnin.server.io.MySession;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author ❤Girlkun75❤
 * @copyright ❤Trần Lại❤
 */
public class ClientManager {

    private static final Map<Integer, Player> IDS_PLAYER = new HashMap<>();
    private static final Map<String, Player> NAMES_PLAYER = new HashMap<>();
    private static final Map<Integer, Player> ACCOUNT_IDS_PLAYER = new HashMap<>();

    public static final void addPlayer(Player player) {
        if (!IDS_PLAYER.containsKey(player.getId())) {
            IDS_PLAYER.put(player.getId(), player);
        }
        if (!NAMES_PLAYER.containsKey(player.getName())) {
            NAMES_PLAYER.put(player.getName(), player);
        }
        if (!ACCOUNT_IDS_PLAYER.containsKey(((MySession) player.getSession()).id)) {
            ACCOUNT_IDS_PLAYER.put(((MySession) player.getSession()).id, player);
        }
    }

    public static final void removePlayer(Player player) {
        if (IDS_PLAYER.containsKey(player.getId())) {
            IDS_PLAYER.remove(player.getId(), player);
        }
        if (NAMES_PLAYER.containsKey(player.getName())) {
            NAMES_PLAYER.remove(player.getName(), player);
        }
        if (ACCOUNT_IDS_PLAYER.containsKey(((MySession) player.getSession()).id)) {
            ACCOUNT_IDS_PLAYER.remove(((MySession) player.getSession()).id, player);
        }
    }

    public static Player getPlayerById(int id) {
        return IDS_PLAYER.get(id);
    }

    public static Player getPlayerByName(String name) {
        return NAMES_PLAYER.get(name);
    }

    public static Player getPlayerByAccountId(int id) {
        return ACCOUNT_IDS_PLAYER.get(id);
    }

}

/**
 * Vui lòng không sao chép mã nguồn này dưới mọi hình thức. Hãy tôn trọng tác
 * giả của mã nguồn này. Xin cảm ơn! - Girlkun75
 */
