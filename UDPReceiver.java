import java.net.*;

public class UDPReceiver {

	private final static int PACKETSIZE = 100;

	public static void main(String args[]) {
		// Check the arguments
		if (args.length != 1) {
			System.out.println("usage: UDPReceiver port");
			return;
		}
		try {
			// Convert the argument to ensure that is it valid
			int port = Integer.parseInt(args[0]);

			// Construct the socket
			DatagramSocket socket = new DatagramSocket(port);

			for (;;) {
				System.out.println("Receiving on port " + port);
				DatagramPacket packet1 = new DatagramPacket(new byte[PACKETSIZE], PACKETSIZE);
				socket.receive(packet1);

				System.out.println(
						packet1.getAddress() + " " + packet1.getPort() + ": " + new String(packet1.getData()).trim());
				
				System.out.println("Echoing back");				
				byte[] data = packet1.getData();
				DatagramPacket packet2 = new DatagramPacket(data, packet1.getData().length,
						packet1.getAddress(), packet1.getPort());
				socket.send(packet2);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
