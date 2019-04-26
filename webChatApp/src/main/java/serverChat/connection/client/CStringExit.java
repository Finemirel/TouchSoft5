package serverChat.connection.client;

import serverChat.connection.IStringCommand;
import serverChat.connection.agent.ConnectionAgent;
import org.apache.log4j.Logger;
import serverChat.register.MappingClientWithAgent;
import serverChat.register.RegisterUsers;

public class CStringExit implements IStringCommand {

    final static Logger log = Logger.getLogger(CStringExit.class);

    public CStringExit() {

    }

    @Override
    public void execute(ConnectionClient client, ConnectionAgent agent, boolean needConnection) {
        log.info("Client exit");
        deleteSome(client);
        agent.setClient(null);
        agent.setNeedConnection(true);
        agent.setFree(true);
        client.setAgent(null);
        needConnection = false;
    }

    synchronized private void deleteSome(ConnectionClient client) {
        for (MappingClientWithAgent map : RegisterUsers.getMappingUser()) {
            if (map.getClient().equals(client)) {
                RegisterUsers.deleteMappingUser(map);
            }
        }
    }

}
