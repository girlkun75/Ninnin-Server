package com.girlkun.ninnin.server.io;

import com.girlkun.network.session.Session;
import com.girlkun.ninnin.entities.player.Player;
import java.io.IOException;
import java.net.Socket;
import lombok.Data;

/**
 *
 * @author ❤Girlkun75❤
 * @copyright ❤Trần Lại❤
 */
@Data
public class MySession extends Session{
    
    private Player player;
    private int accountId;
    private String username;
    private String password;
    
    public MySession(Socket socket){
        super(socket);
    }

}






















/**
 * Vui lòng không sao chép mã nguồn này dưới mọi hình thức.
 * Hãy tôn trọng tác giả của mã nguồn này.
 * Xin cảm ơn! - Girlkun75
 */