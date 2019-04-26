package serverChat.connection;

import serverChat.connection.agent.ConnectionAgent;
import serverChat.connection.client.ConnectionClient;

public interface IStringCommand {

    public void execute(ConnectionClient client, ConnectionAgent agent, boolean needConnection);

}
