package connection.agent;

import connection.IStringCommand;
import connection.client.ConnectionClient;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class AStringMessage implements IStringCommand {

    String msg;

    public AStringMessage(String msg) {
        this.msg = msg;
    }

    @Override
    public void execute(ConnectionClient client, ConnectionAgent agent, boolean needConnection) {
        try {
            PrintWriter out = new PrintWriter(
                    new BufferedWriter(new OutputStreamWriter(client.getSocket().getOutputStream())), true);
            out.println(agent.getName() + "(agent): " + msg);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
