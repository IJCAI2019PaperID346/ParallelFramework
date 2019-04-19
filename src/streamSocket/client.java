package streamSocket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;


public class client {

	private BufferedReader bReader = null;
	private PrintWriter pWriter = null;
	private Socket socket = null;
	private String hostName;
	private int portNum;
	private InputStreamReader reader;
	private BufferedReader br = null;
	private PrintWriter pw = null;
	
	public client(String host, int port) throws UnknownHostException, IOException {
		hostName = host;
		portNum  = port;
		socket = new Socket(hostName, portNum);
		reader = new InputStreamReader(socket.getInputStream());
	}
	
	public int getPortNum()
	{
		return portNum;
	}
	
	public String getHostName()
	{
		return hostName;
	}
	
	// Get a buffered reader that chains to the input stream
    // reader. The buffered reader supplies a convenient method
    // for reading entire lines of text.
	public BufferedReader getBufferedReader()
	{
		br = new BufferedReader(reader);
		return br;
	}
	
	// Get a print writer that chains to the socket's byte-
    // oriented output stream. The print writer creates an
    // intermediate output stream writer that converts
    // characters sent to the socket to bytes. The conversion
    // is based on the platform's default character set.
	public PrintWriter getPrintWriter() throws IOException
	{
		pw = new PrintWriter(socket.getOutputStream(), true);
		return pw;
	}
	
	// Send the data command to the server.
	// Obtain and print the current data.
	public void sendData(String data) throws IOException
	{
		pw.println(data);
		System.out.println(br.readLine());
	}
	
	// Send the PAUSE command to the server. This allows several
    // clients to start and verifies that the server is spawning
    // multiple threads.
	public void sendPause()
	{
		pw.println("PAUSE");
	}
	
	public void close()
	{
		System.out.println ("Closing Client Connection...\n");

		try {
				if(br != null)
					br.close();
				if(pw != null)
					pw.close();
				if(socket != null)
					socket.close();
		} catch (IOException e) 
		{
			System.out.println(e.toString());
		}
	}
}
