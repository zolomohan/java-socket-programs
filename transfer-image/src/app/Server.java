package app;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Server {
	public static void main(String[] args) throws Exception {
		ServerSocket server = new ServerSocket(4000);
		System.out.println("Waiting For Client");
		Socket socket = server.accept();
		System.out.println("Client Connected");
		DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
		int length = dataInputStream.readInt();
		System.out.println("Image Size " + length / 1024 + "KB");
		byte[] data = new byte[length];
		dataInputStream.readFully(data);
		dataInputStream.close();

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