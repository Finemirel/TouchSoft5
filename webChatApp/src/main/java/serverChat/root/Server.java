package serverChat.root;

import org.apache.log4j.Logger;
import register.ConnectedUser;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    final static Logger log = Logger.getLogger(Server.class);

    public static final int PORT = 8085;

    private ServerSocket serverSocket;

    public Server() {
        try {
            serverSocket = new ServerSocket(PORT);
            log.info("Server create");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void start() {
        log.info("Server start");
        while (true) {
            try {
                Socket socket = serverSocket.accept();
                new Thread(new ConnectedUser(socket)).start();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

}