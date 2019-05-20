package utils;

import org.apache.log4j.Logger;

import javax.websocket.Session;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class InputMessage implements Runnable{
    final static Logger log = Logger.getLogger(InputMessage.class);

    private Session session;
    private Socket socket;
    private boolean needExit;

    public InputMessage(Session session, Socket socket, boolean needExit) {
        this.session = session;
        this.socket = socket;
        this.needExit = needExit;
    }


    @Override
    public void run() {
        log.info("You can receive messages");
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            do {
                if (in.ready()) {
                    String response = in.readLine();
                    session.getBasicRemote().sendText(response);
                }
            } while (!needExit);
        } catch (IOException e) {
            log.info("I can not receive messages");
            log.error("Error receive " + e.getStackTrace());
        } finally {
            try {
                if (!socket.isClosed()) {
                    socket.close();
                }
            } catch (IOException e) {
                log.error("Error close socket " + e.getStackTrace());
            }
        }
    }
}
