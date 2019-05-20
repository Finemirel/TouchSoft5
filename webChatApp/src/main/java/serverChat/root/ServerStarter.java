package serverChat.root;

public class ServerStarter {
    public static boolean onStart = false;
    private static Server server = new Server();

    public static void startServer() {
        server.start();
    }


}
