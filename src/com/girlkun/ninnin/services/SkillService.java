package com.girlkun.ninnin.services;

import com.girlkun.network.io.Message;
import com.girlkun.ninnin.entities.player.Player;
import com.girlkun.ninnin.server.Cmd;

/**
 *
 * @author ❤Girlkun75❤
 * @copyright ❤Trần Lại❤
 */
public class SkillService {

    private static SkillService I;

    public static SkillService gI() {
        if (SkillService.I == null) {
            SkillService.I = new SkillService();
        }
        return SkillService.I;
    }
    
    public void playerUseSkill(Player player){
        Message msg = new Message(Cmd.PLAYER_ATTACK);
        try {
            msg.writeInt(player.getId());
            CoreService.gI().sendMessageAllPlayersInZone(player, msg);
            msg.cleanup();
        } catch (Exception e) {
        }
    }

}






















/**
 * Vui lòng không sao chép mã nguồn này dưới mọi hình thức.
 * Hãy tôn trọng tác giả của mã nguồn này.
 * Xin cảm ơn! - Girlkun75
 */
