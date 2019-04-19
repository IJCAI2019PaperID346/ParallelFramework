package streamSocket;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class datagramSocket 
{
  private byte[] buffer = new byte[256];
  private DatagramPacket packet = null;
  private InetAddress address = null;
  private DatagramSocket socket = null;
  private int portnum;
  
  public datagramSocket(byte[] msg, String hostname) throws UnknownHostException 
  {
       buffer = msg;
       packet = new DatagramPacket(buffer, buffer.length);
       address = InetAddress.getByName(hostname);
  }
  
  public datagramSocket(byte[] msg, String hostname, int num) throws UnknownHostException 
  {
       buffer = msg;
       address = InetAddress.getByName(hostname);
       portnum = num;
       packet = new DatagramPacket(buffer, buffer.length, address, portnum);
  }
  
  public void setPort(int num)
  {
	  packet.setPort(num); //Send datagram packet to port num
  }
  
  public void setAddress()
  {
	  packet.setAddress(address);
  }
  
  //Client
  public void sentData()
  {
	  try{
		  socket = new DatagramSocket();
		  //send the datagram packet over the socket
		  socket.send(packet);
		  
		  // Create a byte array to hold the response from the server.
		  byte[] buffer2 = new byte [256];
          packet = new DatagramPacket(buffer2, buffer2.length, address, portnum);
          
          //receive a datagram packet over the socket
          socket.receive(packet);
          
          // Print the data returned from the server program and stored
          // in the datagram packet.
          System.out.println(new String (packet.getData()));
          
	  }catch(IOException e)
	  {
		  System.out.println(e.toString());
	  }
  }
  
  //Server
  public void receiveData()
  {
	  try{
		  socket = new DatagramSocket(portnum);
		  byte[] data = new byte[256];
		  
		  packet = new DatagramPacket(data, data.length);
		  
		  //Enter an infinite loop. 
		  while(true)
		  {
			  //Receive a datagram packet from the client program
			  socket.receive(packet);
			  
			  //DIsplay contents of datagram packet
			  System.out.println(new String(data));
			  
			  //Echo datagram packet back to client program
			  socket.send(packet);
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
