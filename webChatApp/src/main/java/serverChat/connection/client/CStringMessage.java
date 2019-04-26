package serverChat.connection.client;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import serverChat.connection.IStringCommand;
import serverChat.connection.agent.ConnectionAgent;

public class CStringMessage implements IStringCommand{

    String msg;

    public CStringMessage(String msg) {
        this.msg = msg;
    }

    @Override
    public void execute(ConnectionClient client, ConnectionAgent agent, boolean needConnection) {

        try {
            PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(agent.getSocket().getOutputStream())), true);
            out.println(client.getName() + "(client): " + msg);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}
