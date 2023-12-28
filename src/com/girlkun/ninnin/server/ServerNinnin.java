package com.girlkun.ninnin.server;

import com.girlkun.network.server.GirlkunServer;
import com.girlkun.network.server.IGirlkunServer;
import com.girlkun.ninnin.server.io.MySession;

/**
 *
 * @author ❤Girlkun75❤
 * @copyright ❤Trần Lại❤
 */
public class ServerNinnin {

    private static ServerNinnin I;

    private static ServerNinnin gI() {
        if (ServerNinnin.I == null) {
            ServerNinnin.I = new ServerNinnin();
        }
        return ServerNinnin.I;
    }

    public static void main(String[] args) throws Exception {
        ServerNinnin.gI().run();
    }

    private void run() throws Exception {
        Manager.gI();
        this.openServer();
    }

    private void openServer() throws Exception {
        final IGirlkunServer server = GirlkunServer.gI().init();
        server.setAcceptHandler(new SessionAcceptHandler());
        server.setTypeSessioClone(MySession.class);
        server.start(Manager.PORT);
    }

}

/**
 * Vui lòng không sao chép mã nguồn này dưới mọi hình thức. Hãy tôn trọng tác
 * giả của mã nguồn này. Xin cảm ơn! - Girlkun75
 */
