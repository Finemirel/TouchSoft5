package user;

import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class InputMessage{

	final static Logger log = Logger.getLogger(InputMessage.class);

	Socket socket;
	Thread tr;

	public InputMessage(Socket socket, Thread tr) {
		this.socket = socket;
		this.tr = tr;
	}

	public void startInput() {
		log.info("You can receive messages");
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			do {
				if (in.ready()) {
					String response = in.readLine();
					System.out.println(response);
				}
			} while (!Exit.isNeedExit());

		} catch (IOException e) {
			log.info("I can not receive messages");
			log.error("Error receive " + e.getStackTrace());
		} finally {
			try {
				if (!socket.isClosed()) {
					socket.close();
				}
			} catch (IOException e) {
				log.error("Error close socket " + e.getStackTrace());
			}
		}

	}

}
