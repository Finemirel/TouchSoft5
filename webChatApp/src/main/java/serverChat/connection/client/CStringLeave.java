package serverChat.connection.client;

import serverChat.connection.IStringCommand;
import serverChat.connection.agent.ConnectionAgent;
import org.apache.log4j.Logger;
import serverChat.register.MappingClientWithAgent;
import serverChat.register.RegisterUsers;

public class CStringLeave implements IStringCommand{

    final static Logger log = Logger.getLogger(CStringLeave.class);

    public CStringLeave() {

    }

    @Override
    public void execute(ConnectionClient client, ConnectionAgent agent, boolean needConnection) {
        log.info("Client leave");
        // Synchronize
        for (MappingClientWithAgent map : RegisterUsers.getMappingUser()) {
            if (map.getClient().equals(client)) {
                RegisterUsers.deleteMappingUser(map);
            }
        }
        client.setAgent(null);
        agent.setClient(null);
        agent.setFree(true);
        agent.setNeedConnection(true);
        needConnection = true;

    }

}
