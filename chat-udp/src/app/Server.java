package app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class Server {
	public static void main(String[] args) throws SocketException, IOException {
		DatagramSocket serverSocket = new DatagramSocket(3000);
		while (true)
		{
			byte[] receivebuffer = new byte[1024];
			byte[] sendbuffer = new byte[1024];
			DatagramPacket recievePacket = new DatagramPacket(receivebuffer, receivebuffer.length);
			serverSocket.receive(recievePacket);
			InetAddress IP = recievePacket.getAddress();
			int portno = recievePacket.getPort();
			String clientdata = new String(recievePacket.getData());
			System.out.println("Client : " + clientdata);
			System.out.print("Server : ");
			BufferedReader serverRead = new BufferedReader(new InputStreamReader(System.in));
			String serverdata = serverRead.readLine();
			sendbuffer = serverdata.getBytes();
			DatagramPacket sendPacket = new DatagramPacket(sendbuffer, sendbuffer.length, IP, portno);
			serverSocket.send(sendPacket);
			if (serverdata.equalsIgnoreCase("bye")) {
				System.out.println("connection ended by server");
				break;
			}
		}
		serverSocket.close();
	}
}
