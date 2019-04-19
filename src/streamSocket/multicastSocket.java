package streamSocket;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class multicastSocket {
		
	private MulticastSocket socket = null;
	private InetAddress address = null;
	private int portnum;
	private String hostname;
	
	public multicastSocket(int num, String name) throws IOException
	{
		portnum = num;
		hostname = name;
		socket = new MulticastSocket(portnum);
		address = InetAddress.getByName(hostname);
	}
	
	//Client
	public void sendData()
	{
		try{
		// Join the multicast group so that datagram packets can be received.
		socket.joinGroup(address);
		
		//Read several datagram packets from the server program
		for(int i=0; i<10; i++)
		{
			byte[] buffer = new byte[256];
			
			DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
			
			//receive a datagram packet
			socket.receive(packet);
			
			// Create a second byte array with a length that matches
	        // the length of the sent data.
			byte[] buffer2 = new byte[packet.getLength()];
			
			//copy the sent data to the second byte array
			System.arraycopy(packet.getData(), 0, buffer2, 0, packet.getLength());
			
			// print the contents of the second byte array.
			System.out.println(new String(buffer2));
			
		}
		socket.leaveGroup(address);

		}catch(IOException e)
		{
			  System.out.println(e.toString());
		}
		//leave the multicast group
	}
	
	//Server
	public void receiveData()
	{
	     System.out.println ("Server starting...\n");    

	     try{
	      // Create a MulticastSocket not bound to any port.

	      socket = new MulticastSocket();

	      // Because MulticastSocket subclasses DatagramSocket, it is
	      // legal to replace MulticastSocket s = new MulticastSocket ();
	      // with DatagramSocket s = new DatagramSocket ();
	      
	      byte[] buffer = new byte[256];
			
		  DatagramPacket packet = new DatagramPacket(buffer, 0, address, portnum);
		  
		  //send 30000 Strings to the port
		  for(int i=0; i< 30000; i++)
		  {
			// Create an array of bytes from a String. The platform's
	           // default character set is used to convert from Unicode
	           // characters to bytes.

	           byte [] buffer2 = ("Video line " + i).getBytes ();

	           // Establish the byte array as the datagram packet's
	           // buffer.

	           packet.setData (buffer2);

	           // Establish the byte array's length as the length of the
	           // datagram packet's buffer.

	           packet.setLength (buffer2.length);

	           // Send the datagram to all members of the multicast group
	           // that listen on port 10000.

	           socket.send (packet);
		  }
	      
	     }catch(IOException e)
			{
				  System.out.println(e.toString());
			}
	}
	
	public void close()
	  {
		  if(socket!= null)
			  socket.close();
	  }
}

