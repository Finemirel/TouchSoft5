package serverChat.connection.agent;

import serverChat.connection.IStringCommand;
import serverChat.connection.client.ConnectionClient;
import org.apache.log4j.Logger;

import java.util.HashMap;

public class AMessageDispetcher {

    final static Logger log = Logger.getLogger(AMessageDispetcher.class);

    private static final String EXIT = "/exit";

    private IStringCommand exit = new AStringExit();

    private final HashMap<String, IStringCommand> messageCommand = new HashMap<>();

    ConnectionClient client;
    ConnectionAgent agent;

    public AMessageDispetcher(ConnectionClient client, ConnectionAgent agent, String msg) {
        messageCommand.put(EXIT, exit);
        if (!messageCommand.containsKey(msg)) {
            messageCommand.put(msg, new AStringMessage(msg));
        }
        this.client = client;
        this.agent = agent;
    }

    public void executeMessage(String msg, boolean needConnection) {
        log.info("in AMessageDispetcher");
        messageCommand.get(this.getCMDString(msg)).execute(client, agent, needConnection);
    }

    private String getCMDString(String str) {
        if (str.startsWith(EXIT)) {
            return EXIT;
        }
        return str;
    }
}
