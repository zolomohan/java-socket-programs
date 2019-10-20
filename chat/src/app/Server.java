package app;

import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
	public static void main(String[] args) throws Exception {
		Socket socket = new ServerSocket(3000).accept();
		Scanner keyboardScanner = new Scanner(System.in);
		Scanner socketScanner = new Scanner(socket.getInputStream());
		PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
		while(true){
			System.out.println(socketScanner.nextLine());
			System.out.print("Server: ");
			writer.println("Server:" +  keyboardScanner.nextLine());
		}
	}
}