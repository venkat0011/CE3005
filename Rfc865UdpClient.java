package ce3005;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
// name : venkat Subramanian 
// class : A34
// my client ip: 172.21.145.108

public class Rfc865UdpClient {

	 public static void main(String[] argv) {
	 //
	 // 1. Open UDP socket
	 // notice for the server we do not have to specify the port number, they only want us to open the UDP socket 
	 DatagramSocket socket = null;
	 InetAddress ip = null;
	 try {
		  ip = InetAddress.getByName("hwlab1.scse.ntu.edu.sg"); // since we do not know the ip address we can get the ip using the get by name method
		 
	 } catch (UnknownHostException e) {System.out.println("Unable to resolve host name");}
	 
	 try {
		 socket = new DatagramSocket();
	 }
	 catch(SocketException e) {System.out.println("Socket Excpetion");}
	 
	 
	 
//	 byte[] message = "This is a test message".getBytes();
	 // the message sent to the random quote is name, group and ipaddress
	 
	 byte [] message = (" Venkat, A34," + ip.getHostAddress()).getBytes() ;
	 byte[] buffer = new byte[65535]; // the maximum payload of the UDP is 65,507 for the first part we are sending to the local server
	 try {
	 //
	 // 2. Send UDP request to server
	 //
	// we will be sending the request to the server which is located in the port 5222
	// similar to server sending to client, since we are sending we need to know the port and the ip address 

//	 DatagramPacket request = new DatagramPacket(message,message.length,InetAddress.getLocalHost(),5205);
	 // now we are going to	send the message to the TA's server to get the random message, to do so we need to get the ip address 
	DatagramPacket request = new DatagramPacket(message,message.length,ip,17);
	socket.send(request);
	 System.out.println("Message sent from client");
	 //
	 // 3. Receive UDP reply from server
	 //
	 DatagramPacket reply = new DatagramPacket(buffer,buffer.length); 
	 socket.receive(reply);
	 String reply_message = new String(reply.getData(),0,reply.getLength());
	 System.out.println("the message recieved from server is " + reply_message);
	 System.out.println("Reply recieved at Client ");
	 } catch (IOException e) {}
	 }
	}
// take note that since the server is always listening, we need the server to run first before the client runs

