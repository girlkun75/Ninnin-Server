package com.girlkun.ninnin.entities.map;

import com.girlkun.network.io.Message;
import com.girlkun.ninnin.entities.player.Player;
import com.girlkun.ninnin.interfaces.IUpdate;
import com.girlkun.ninnin.server.Cmd;
import com.girlkun.ninnin.services.CoreService;
import com.girlkun.ninnin.services.MapService;
import com.girlkun.ninnin.services.PlayerService;
import com.girlkun.ninnin.utils.Utils;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import lombok.Data;

/**
 *
 * @author ❤Girlkun75❤
 * @copyright ❤Trần Lại❤
 */
@Data
public class Zone implements IUpdate {

    private int id;
    private Map map;

    private List<Player> players;

    public Zone(int id, Map map) {
        this.id = id;
        this.map = map;
        this.players = new ArrayList<>();
    }

    @Override
    public void update() {
        for (Player player : this.players) {
            player.update();
        }
    }

    public void playerJoinZone(Player player) {
        if (this.players.contains(player)) {
            return;
        }
        player.setZone(this);
        MapService.gI().sendInfoMap(player);
        PlayerService.gI().sendPlayerToPlayersInMap(player);
        PlayerService.gI().sendPlayersInMapToPlayer(player);
        this.players.add(player);
    }

    public void playerExitZone(Player player) {
        if (!this.players.contains(player)) {
            return;
        }
        PlayerService.gI().sendPlayerExitZone(player);
        this.players.remove(player);
    }

}

/**
 * Vui lòng không sao chép mã nguồn này dưới mọi hình thức. Hãy tôn trọng tác
 * giả của mã nguồn này. Xin cảm ơn! - Girlkun75
 */
