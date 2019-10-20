package app;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
	public static void main(String[] args) throws Exception {
		ServerSocket server = new ServerSocket(3000);
		Socket socket = server.accept();

		Scanner socketScanner = new Scanner(socket.getInputStream());
		PrintWriter output = new PrintWriter(socket.getOutputStream(), true);

		Runtime runtime = Runtime.getRuntime();
		Process process = runtime.exec("arp -a " + socketScanner.nextLine());

		BufferedReader processReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
		String str;
		while ((str = processReader.readLine()) != null)
			output.println(str);
		server.close();
		socket.close();
		socketScanner.close();
	}
}
