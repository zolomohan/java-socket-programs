package app;

import java.net.ServerSocket;
import java.net.Socket;
import java.io.BufferedInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;

public class Server {
	public static void main(String[] args) throws Exception {
		ServerSocket server = new ServerSocket(3000);
		Socket socket = server.accept();
		File file = new File("D:/toTransfer.txt");
		byte[] data = new byte[(int) file.length()];
		BufferedInputStream input = new BufferedInputStream(new FileInputStream(file));
		input.read(data, 0, data.length);
		DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());
		outputStream.writeInt(data.length);
		outputStream.write(data, 0, data.length);
		outputStream.flush();
		System.out.println("Done");
		input.close();
		socket.close();
		server.close();
	}
}