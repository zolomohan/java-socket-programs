package app;

import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.FileOutputStream;
import java.net.Socket;

public class Client {
  public static void main(String[] args) throws Exception {
    Socket socket = new Socket("localhost", 3000);
    BufferedOutputStream output = new BufferedOutputStream(new FileOutputStream("D:/download.txt"));
    DataInputStream socketInputStream = new DataInputStream(socket.getInputStream());
    byte[] data = new byte[socketInputStream.readInt()];
    socketInputStream.read(data, 0, data.length);
    output.write(data, 0, data.length);
    output.flush();
    output.close();
    socket.close();
  }
}
