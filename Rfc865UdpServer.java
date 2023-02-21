package ce3005;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class Rfc865UdpServer {
	 public static void main(String[] argv) {
	 //
	 // 1. Open UDP socket at well-known port
	 //
     byte [] qotd = "QOTD".getBytes(); // A test message to sent to the client after it ha recieved the data
	 DatagramSocket socket = null;
	 try {
	 socket = new DatagramSocket(5205);
	 } 
	 catch (SocketException e) {System.out.println(" Socket Exception");}
	 
	 byte[] recieve = new byte[512]; // testing out with a buffer of 512 bytes
	 while (true) {
		 
	 try {
	 //
	 // 2. Listen for UDP request from client
	 //
		 // here we are only interested in receiving data from the client
		 //so we need to choose a constructor that only Constructs a DatagramPacket for receiving packets of length length. 
		 // that is why we using this DatagramPacket(byte[] buf, int length)
		 
	 DatagramPacket request = new DatagramPacket(recieve,recieve.length); 
	 socket.receive(request);
	 
	 
	 String datastring = new String(request.getData(),0,request.getLength());
	 System.out.println("The recieved data is "+datastring);
	 
	 //
	 // 3. Send UDP reply to client
	 //
	 
	 // now we want to send the data to a client
	 // so we need the constructor to Constructs a datagram packet for sending packets of length length to the specified port number on the specified host.
	 // that is why we are using this constructor DatagramPacket(byte[] buf, int length, InetAddress address, int port)
	 // to get the inet address we can get it from the request that we have recieved and the port as well
	 // but the first 2 variables are needed, so similar to the request we will create a buffer at the start when we are creating a socket that is why we have the qotd variable 
	 InetAddress ip = request.getAddress();
	 DatagramPacket reply = new DatagramPacket(qotd,qotd.length,ip,request.getPort());
	 socket.send(reply);

	 
	 System.out.println("reply sent from server");
	 } catch (IOException e) {}
	 }
	 }
	}
