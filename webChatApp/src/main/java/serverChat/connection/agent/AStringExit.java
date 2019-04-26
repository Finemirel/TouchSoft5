package serverChat.connection.agent;

import serverChat.connection.IStringCommand;
import serverChat.connection.client.ConnectionClient;
import org.apache.log4j.Logger;
import serverChat.register.MappingClientWithAgent;
import serverChat.register.RegisterUsers;

public class AStringExit implements IStringCommand {

    final static Logger log = Logger.getLogger(AStringExit.class);

    public AStringExit() {

    }

    @Override
    public void execute(ConnectionClient client, ConnectionAgent agent, boolean needConnection) {
        log.info("Agent exit");
        deleteSome(agent);
        agent.setClient(null);
        client.setAgent(null);
        client.setNeedConnection(true);
        needConnection = false;
    }

    synchronized private void deleteSome(ConnectionAgent agent) {
        RegisterUsers.deleteConnectionAgent(agent);
        for (MappingClientWithAgent map : RegisterUsers.getMappingUser()) {
            if (map.getAgent().equals(agent)) {
                RegisterUsers.deleteMappingUser(map);
            }
        }
    }
}
