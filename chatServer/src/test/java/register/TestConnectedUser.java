package register;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.Socket;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TestConnectedUser {
	private static final String LN = System.getProperty("line.separator");
	
	@Test
	public void testRun() throws IOException {
		Socket socket = mock(Socket.class);
		System.out.println("1");
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		System.out.println("3");
		ByteArrayInputStream in = new ByteArrayInputStream(String.format("/register client Artem", LN).getBytes());
		System.out.println("2");
		ConnectedUser connectedUser = new ConnectedUser(socket);
		System.out.println("4");
		when(socket.getInputStream()).thenReturn(in);
		System.out.println("5");
		when(socket.getOutputStream()).thenReturn(out);
		System.out.println("6");
		connectedUser.run();
		System.out.println("9");
		assertThat(out.toString(), is(
					String.format("Hello Artem! You client", LN)
				));
		
	}

}
