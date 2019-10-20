package app;

import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import helper.CRC;

public class Server{
  public static void main(String[] args) throws Exception{
    ServerSocket serverSocket = new ServerSocket(3000);
    Socket socket = serverSocket.accept();
    Scanner keyScan = new Scanner(System.in);
    PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
    System.out.print("Enter Message: ");
    String message = keyScan.nextLine();
    String encoded = CRC.xor(CRC.appendZeros(message));
    output.println(message + CRC.getCRCBits(encoded));
    keyScan.close();
    socket.close();
    serverSocket.close();
  }
}