package streamSocket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class connection {

	//private int number;
	
	public connection()
	{
	}
	
	public void connect(int number) throws IOException
	{
		System.out.println("Server Starting...");
		
		// Create a server socket that listens for incoming connection
	    // requests on port number.
		ServerSocket serverSocket = new ServerSocket(number);
		
		while(true)
		{
			// Listen for incoming connection requests from client
	        // programs, establish a connection, and return a Socket
	        // object that represents this connection.
			Socket socket = serverSocket.accept();
			
			System.out.println("Accepting Connection...");
			
			//Start a thread to handle the connection
			new server(socket).start();
		}
	}
}
