package app;

import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
	public static void main(String[] args) throws Exception {
		Socket socket = new ServerSocket(3000).accept(); 
		Scanner socketScanner = new Scanner(socket.getInputStream());
		System.out.println("Connection Established");
		while(true){
			String string = socketScanner.nextLine();
			System.out.println("Client: "+string);
			PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
			writer.println("Server: "+ string);
		}
	}
}