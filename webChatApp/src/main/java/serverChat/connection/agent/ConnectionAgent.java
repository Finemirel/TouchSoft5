package serverChat.connection.agent;

import serverChat.connection.client.ConnectionClient;
import org.apache.log4j.Logger;
import serverChat.register.MappingClientWithAgent;
import serverChat.register.RegisterUsers;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;


public class ConnectionAgent{

    final static Logger log = Logger.getLogger(ConnectionAgent.class);

    private boolean needConnection;
    private ConnectionClient client = null;
    private Socket socket;
    private String name;
    private boolean free = true;

    public ConnectionAgent(Socket socket, String name) {
        this.socket = socket;
        this.name = name;
    }

    public void startChat() {
        log.info("In connection agent");
        needConnection = true;
        while (needConnection) {
            try {
                if (client == null) {
                    client = searchClient(client);
                }
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                while (in.ready()) {
                    String msg = in.readLine();
                    AMessageDispetcher disp = new AMessageDispetcher(client, this, msg);
                    disp.executeMessage(msg, needConnection);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    private ConnectionClient searchClient(ConnectionClient client) {
        while (true) {
            client = searchClientSynch();
            if (client != null) {
                return client;
            }
        }
    }

    synchronized private ConnectionClient searchClientSynch() {
        if (!RegisterUsers.getMappingUser().isEmpty()) {
            for (MappingClientWithAgent map : RegisterUsers.getMappingUser()) {
                if (map.getAgent().equals(this)) {
                    log.info("Client call");
                    return map.getClient();
                }
            }
        }
        return null;
    }

    public void setNeedConnection(boolean needConnection) {
        this.needConnection = needConnection;
    }

    public void setClient(ConnectionClient client) {
        this.client = client;
    }

    public boolean isFree() {
        return free;
    }

    public void setFree(boolean free) {
        this.free = free;
    }

    public Socket getSocket() {
        return socket;
    }

    public String getName() {
        return name;
    }

}
