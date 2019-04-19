package streamSocket;

import java.net.*;
import java.net.UnknownHostException;

public class netAddress {
	String host = "localhost";
	String hostName;
	String hostAddress;
	InetAddress iaAddress;
	
	InetAddress getName() throws UnknownHostException
	{
		return InetAddress.getByName(host);
	}
	
	void setHostName()
	{
		hostName = iaAddress.getCanonicalHostName();
	}
	
	String getHostName()
	{
		return hostName;
	}
	
	void setHostAddress()
	{
		hostAddress = iaAddress.getHostAddress();
	}
	
    String getHostAddress()
    {
    	return hostAddress;
    }
    
    boolean isLoopback()
    {
    	return iaAddress.isLoopbackAddress();
    }
    
    
}
