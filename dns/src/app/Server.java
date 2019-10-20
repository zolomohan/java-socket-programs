package app;

import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Server {

	static List<Address> cache = new ArrayList<Address>();

	public static void main(String[] args) throws Exception {
		ServerSocket server = new ServerSocket(3000);
		Socket socket = server.accept();

		Scanner socketScanner = new Scanner(socket.getInputStream());
		PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
		
		while (true) {
			String hostname = socketScanner.nextLine();
			int index = -1;
			for (int i = 0; i < cache.size(); i++)
				if (cache.get(i).getHostname().equals(hostname)) {
					index = i;
					break;
				}
			if (index == -1) {
				try{
					InetAddress inet = InetAddress.getByName(hostname);
					Address newAddress = new Address(inet.getHostName(), inet.getHostAddress());
					cache.add(newAddress);
					index = cache.size() - 1;
					output.println(cache.get(index).getIp());
				}
				catch(UnknownHostException e){
					output.println(e.getLocalizedMessage());
				}
			}
			else output.println(cache.get(index).getIp());
		}
	}
}

class Address {
	private String hostname;
	private String ip;

	Address(String name, String address) {
		hostname = name;
		ip = address;
	}

	public String getHostname() {
		return hostname;
	}

	public String getIp() {
		return ip;
	}
}