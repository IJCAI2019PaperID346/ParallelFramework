package streamSocket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


public class server extends Thread{
	private Socket socket;
	private BufferedReader bReader = null;
	private PrintWriter pWriter = null;
	
	public server(Socket s)
	{
		socket = s;
	}

	public void run()
	{
		try{
			  // Create an input stream reader that chains to the socket's
	          // byte-oriented input stream. The input stream reader
	          // converts bytes read from the socket to characters. The
	          // conversion is based on the platform's default character
	          // set.
			 InputStreamReader iReader;
			 iReader = new InputStreamReader(socket.getInputStream());
			  // Create a buffered reader that chains to the input stream
	          // reader. The buffered reader supplies a convenient method
	          // for reading entire lines of text.
			 bReader = new BufferedReader(iReader);
			  // Create a print writer that chains to the socket's byte-
	          // oriented output stream. The print writer creates an
	          // intermediate output stream writer that converts
	          // characters sent to the socket to bytes. The conversion
	          // is based on the platform's default character set.
			 pWriter = new PrintWriter(socket.getOutputStream(), true);
			 
			 do {
				// Obtain the client program's next command.
				 String cmd = bReader.readLine();
				 
				// Exit if client program has closed its output stream.
				 if (cmd == null)
	                  break;
				 
				// If client program sends BYE command, terminate.
				 if (cmd.startsWith ("BYE"))
	                  break;
				 
				// If client program sends PAUSE command, sleep for three seconds.
				 if (cmd.startsWith ("PAUSE"))
	                  try
	                  {
	                      Thread.sleep (3000);
	                  }
	                  catch (InterruptedException e)
	                  {
	                  }
				 
			 }while(true);
			 
		} catch(IOException e)
		{
			System.out.println(e.toString());
		}	
	}
	
	public void close()
	{
		System.out.println ("Closing Sever Connection...\n");
		try {
				if( bReader!= null)
					bReader.close();
				if( pWriter != null)
					pWriter.close();
				if(socket != null)
					socket.close();
			} catch (IOException e) {
				
			}
	}
}
