package register;

import connection.agent.ConnectionAgent;
import org.apache.log4j.Logger;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class AgentRegistration implements UserRegistration {

    final static Logger log = Logger.getLogger(AgentRegistration.class);

    public AgentRegistration() {

    }

    @Override
    public void registerUser(Socket socket, String helloMsg, NeedConnectedUser need) {
        try {
            need.setNeedConnectedUser(false);
            PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())),
                    true);
            log.info("Register agent");
            // expected input format: REG_AGENT_CMD + name
            String name = getNameString(helloMsg);
            ConnectionAgent con = new ConnectionAgent(socket, name);
            synchRegisterAgent(con);
            out.println("Hello: " + name + "! You agent");
            con.startChat();//TODO
        } catch (IOException e) {
            need.setNeedConnectedUser(true);
            e.printStackTrace();
        }
    }

    synchronized private void synchRegisterAgent(ConnectionAgent con) {
        RegisterUsers.addConnectionAgent(con);
    }

    private String getNameString(String str) {
        String[] strArray = str.split(" ");
        String name = strArray[2];
        return name;
    }

}