package app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class Client {
	public static void main(String[] args) throws SocketException, IOException {
		BufferedReader scanner = new BufferedReader(new InputStreamReader(System.in));
		InetAddress IP = InetAddress.getByName("127.0.0.1");
		DatagramSocket clientSocket = new DatagramSocket();
		while (true) // true
		{
			byte[] sendbuffer = new byte[1024];
			byte[] receivebuffer = new byte[1024];
			System.out.print("Client: ");
			String clientData = scanner.readLine();
			sendbuffer = clientData.getBytes();
			DatagramPacket sendPacket = new DatagramPacket(sendbuffer, sendbuffer.length, IP, 3000);
			clientSocket.send(sendPacket);
			if (clientData.equalsIgnoreCase("bye")) {
				System.out.println("Connection ended by client");
				break;
			}
			DatagramPacket receivePacket = new DatagramPacket(receivebuffer, receivebuffer.length);
			clientSocket.receive(receivePacket);
			String serverData = new String(receivePacket.getData());
			System.out.println("Server: " + serverData);
		}
		clientSocket.close();
	}

}
