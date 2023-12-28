package com.girlkun.ninnin.server;

import com.girlkun.network.example.DFSendCollect;
import com.girlkun.network.example.KeyHandler;
import com.girlkun.network.server.ISessionAcceptHandler;
import com.girlkun.network.session.ISession;
import com.girlkun.ninnin.entities.player.Player;
import com.girlkun.ninnin.server.io.MySession;

/**
 *
 * @author ❤Girlkun75❤
 * @copyright ❤Trần Lại❤
 */
public class SessionAcceptHandler implements ISessionAcceptHandler {

    @Override
    public void sessionInit(final ISession session) {
        session.setSendCollect(new DFSendCollect());
        session.setKeyHandler(new KeyHandler());
        session.setMessageHandler(Controller.gI());
        session.start();
    }

    @Override
    public void sessionDisconnect(ISession is) {
        Player player = ((MySession) is).player;
        if(player != null){
            player.dispose();
        }
    }

}

/**
 * Vui lòng không sao chép mã nguồn này dưới mọi hình thức. Hãy tôn trọng tác
 * giả của mã nguồn này. Xin cảm ơn! - Girlkun75
 */
