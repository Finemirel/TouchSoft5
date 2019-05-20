package servlets;

import org.apache.log4j.Logger;
import serverChat.root.ServerStarter;
import utils.InputMessage;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

@ServerEndpoint(value = "/chat")
public class AndPointServlet {
    final static Logger log = Logger.getLogger(AndPointServlet.class);

    public boolean needExit = false;
    private Session session;
    private Socket socket;
    private PrintWriter out;

    @OnOpen
    public void onOpen(Session session) {
        log.info("In AndPointServlet onOpen");
        this.session = session;
        if(ServerStarter.onStart==false) {
            ServerStarter.startServer();
            ServerStarter.onStart = true;
        }
        try {
            this.socket = new Socket("localhost", 8085);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Thread tr = new Thread(new InputMessage(session, socket, needExit));
        tr.start();
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        log.info("In AndPointServlet onError");
        try {
            session.getBasicRemote().sendText("Sorry, error!");
        } catch (IOException e) {
            e.printStackTrace();
        }
        throwable.printStackTrace();
    }

    @OnMessage
    public void onMessage(String msg) {
        log.info("In AndPointServlet onMessage");
        out.println(msg);
        if(msg.equals("/exit")) {
            needExit = true;
        }
    }

    @OnClose
    public void onClose(Session session) {
        log.info("In AndPointServlet onClose");
        try {
            session.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
