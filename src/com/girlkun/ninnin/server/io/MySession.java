package com.girlkun.ninnin.server.io;

import com.girlkun.network.session.Session;
import com.girlkun.ninnin.entities.player.Player;
import java.io.IOException;
import java.net.Socket;

/**
 *
 * @author ❤Girlkun75❤
 * @copyright ❤Trần Lại❤
 */
public class MySession extends Session{
    
    public Player player;
    public int id;
    public String username;
    public String password;
    
    public MySession(Socket socket){
        super(socket);
    }

}






















/**
 * Vui lòng không sao chép mã nguồn này dưới mọi hình thức.
 * Hãy tôn trọng tác giả của mã nguồn này.
 * Xin cảm ơn! - Girlkun75
 */