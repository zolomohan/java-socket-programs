package app;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.net.*;
import java.io.*;
import java.awt.image.*;

public class Server {
	public static void main(String[] args) throws Exception {
		ServerSocket server = new ServerSocket(4000);
		System.out.println("Waiting For Client");
		Socket socket = server.accept();
		System.out.println("Client Connected");
		InputStream inputStream = socket.getInputStream();
		DataInputStream dataInputStream = new DataInputStream(inputStream);
		int length = dataInputStream.readInt();
		System.out.println("Image Size " + length / 1024 + "KB");
		byte[] data = new byte[length];
		dataInputStream.readFully(data);
		dataInputStream.close();
		inputStream.close();

		InputStream ian = new ByteArrayInputStream(data);
		BufferedImage image = ImageIO.read(ian);

		JFrame frame = new JFrame("Server");
		ImageIcon icon = new ImageIcon(image);
		JLabel label = new JLabel();
		label.setIcon(icon);
		frame.add(label);
		frame.pack();
		frame.setVisible(true);
		socket.close();
		server.close();
	}
}