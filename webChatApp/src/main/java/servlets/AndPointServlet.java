package servlets;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint(value = "/chat/{username}")
public class AndPointServlet {

    @OnOpen
    public void onOpen(Session session) {

    }

    @OnError
    public void onError(Session session) {

    }

    @OnMessage
    public void onMessage(Session session) {

    }

    @OnClose
    public void onClose(Session session) {

    }
}
