import java.net.*;
import java.util.Scanner;

public class UDPSender {
	public static int PACKETSIZE = 100;
	
	public static void main(String[] args) 
   {
	      // Check the arguments
	      if( args.length != 3 )
	      {
	         System.out.println( "usage: java UDPSender host port" ) ;
	         return ;
	      }
	      DatagramSocket socket = null ;
	      try
	      {
	         // Convert the arguments first, to ensure that they are valid
	         InetAddress host = InetAddress.getByName( args[0] ) ;
	         int port         = Integer.parseInt( args[1] ) ;
	         int nMessages = Integer.parseInt( args[2] ) ;
	         socket = new DatagramSocket() ;
     
	         Scanner in;
	         in = new Scanner (System.in);
	         String message = null;
	         int count = 1;
	         
	         while (count <= nMessages)
	         {
	        		 System.out.println("Message " + count);
	        		 message = in.nextLine();
	        		 if (message.length()==0) break;
	        		 byte [] data = message.getBytes() ;
	        		 DatagramPacket packet1 = new DatagramPacket( data, data.length, host, port ) ;
	        		 socket.send( packet1 ) ;
	        		 count++;
	        		 
	        		 DatagramPacket packet2 = new DatagramPacket( new byte[PACKETSIZE], PACKETSIZE ) ;
	 	             socket.receive( packet2 ) ;
	 	             System.out.println( packet2.getAddress() + " " + packet2.getPort() + ": " + new String(packet2.getData()).trim() ) ;
	         } 
	         System.out.println ("Closing down");
	      }
	      catch( Exception e )
	      {
	         System.out.println( e ) ;
	      }
	      finally
	      {
	         if( socket != null )
	            socket.close() ;
      }
   }
}