package serverChat.register;

import serverChat.connection.client.ConnectionClient;
import org.apache.log4j.Logger;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientRegistration implements UserRegistration {

    final static Logger log = Logger.getLogger(ClientRegistration.class);

    public ClientRegistration() {

    }

    @Override
    public void registerUser(Socket socket, String helloMsg, NeedConnectedUser need) {
        try {
            need.setNeedConnectedUser(false);
            PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())),
                    true);
            log.info("Register client");
            String name = getNameString(helloMsg);
            ConnectionClient con = new ConnectionClient(socket, name);
            out.println("Hello " + name + "! You client");
            con.startChat();//TODO
        } catch (IOException e) {
            need.setNeedConnectedUser(true);
            e.printStackTrace();
        }

    }

    private String getNameString(String str) {
        String[] strArray = str.split(" ");
        String name = strArray[2];
        return name;
    }


}
