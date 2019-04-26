package register;

import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ConnectedUser implements Runnable {

    final static Logger log = Logger.getLogger(ConnectedUser.class);

    private BuildingConnection buildingConnection;
    private Socket socket;
    private NeedConnectedUser need;

    public ConnectedUser(Socket socket) {
        this.socket = socket;
        need = new NeedConnectedUser();
    }

    @Override
    public void run() {
        log.info("in connected user");
        while (need.isNeedConnectedUser()) {
            try {
                BufferedReader in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
                while (in.ready()) {
                    String helloMsg = in.readLine();
                    log.info(helloMsg);
                    buildingConnection = new BuildingConnection(socket, helloMsg);
                    buildingConnection.build(helloMsg, need);
                }
            } catch (IOException e) {
                //TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

}
