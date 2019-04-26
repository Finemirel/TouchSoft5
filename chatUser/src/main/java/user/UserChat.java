package user;

import org.apache.log4j.Logger;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class UserChat {

	final static Logger log = Logger.getLogger(UserChat.class);

	private final static int PORT = 8085;

	Scanner sc = new Scanner(System.in);
	InetAddress addr;
	Socket socket;
	
	
	public UserChat() {
		try {
			log.info("You connected");
			addr = InetAddress.getByName("localhost");
		} catch (UnknownHostException e) {
			log.info("You not connected");
			log.error("Error inetAddress " + e.getStackTrace());
		}
		try {
			socket = new Socket(addr, UserChat.PORT);
		} catch (IOException e) {
			log.info("No connected");
			log.error("Error connected " + e.getStackTrace());
		}
	}

	public void startUser() {
		Exit.setNeedExit(false);
		Thread tr = new Thread(new OutputMessage(socket));
		tr.start();
		InputMessage input = new InputMessage(socket, tr);
		input.startInput();
	}
}
