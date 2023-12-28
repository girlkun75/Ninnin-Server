package com.girlkun.ninnin.entities.player;

import com.girlkun.network.io.IMessage;
import com.girlkun.network.session.ISession;
import com.girlkun.ninnin.core.PlayerDAO;
import com.girlkun.ninnin.entities.Location;
import com.girlkun.ninnin.entities.map.Zone;
import com.girlkun.ninnin.interfaces.IUpdate;
import com.girlkun.ninnin.server.ClientManager;
import lombok.Data;

/**
 *
 * @author ❤Girlkun75❤
 * @copyright ❤Trần Lại❤
 */
@Data
public class Player implements IUpdate {
    
    private ISession session;
    private int id;
    private String username;
    private String password;
    private String name;
    private Location location;
    private Zone zone;

    public Player() {
        this.init();
    }
    
    private void init(){
        this.location = new Location();
    }
    
    public void sendMessage(IMessage msg){
        if(this.session != null){
            this.session.sendMessage(msg);
        }
    }
    
    @Override
    public void update() {
    }
    
    public void dispose(){
        ClientManager.removePlayer(this);
        if(this.zone != null){
            this.zone.playerExitZone(this);
            PlayerDAO.savePlayer(this);
            this.zone = null;
        }
    }

}






















/**
 * Vui lòng không sao chép mã nguồn này dưới mọi hình thức.
 * Hãy tôn trọng tác giả của mã nguồn này.
 * Xin cảm ơn! - Girlkun75
 */
