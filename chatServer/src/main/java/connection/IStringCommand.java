package connection;

import connection.agent.ConnectionAgent;
import connection.client.ConnectionClient;

public interface IStringCommand {

    public void execute(ConnectionClient client, ConnectionAgent agent, boolean needConnection);

}
