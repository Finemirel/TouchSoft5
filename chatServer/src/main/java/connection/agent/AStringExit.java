package connection.agent;

import connection.IStringCommand;
import connection.client.ConnectionClient;
import org.apache.log4j.Logger;
import register.MappingClientWithAgent;
import register.RegisterUsers;

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
