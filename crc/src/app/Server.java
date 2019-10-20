package app;

import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server{
  public static void main(String[] args) throws Exception{
    ServerSocket serverSocket = new ServerSocket(3000);
    Socket socket = serverSocket.accept();

    Scanner keyScan = new Scanner(System.in);
    Scanner socketScan = new Scanner(socket.getInputStream());
    PrintWriter output = new PrintWriter(socket.getOutputStream(), true);

    String message, crc;
    System.out.print("Enter Message: ");
    message = keyScan.nextLine();
    System.out.print("Enter CRC: ");
    crc = keyScan.nextLine();

    StringBuilder encoded = new StringBuilder(message);
    for(int i=0; i < crc.length()-1; i++)
      encoded.append('0');

    for(int i=0; i <= encoded.length() - crc.length();){
      for(int j = 0; j < crc.length(); j++)
        encoded.setCharAt(i+j, encoded.charAt(i+j) == crc.charAt(j) ? '0' : '1');
      for(; i < encoded.length() && encoded.charAt(i) != '1'; i++);
    }
    output.println(message + encoded.substring(encoded.length() - crc.length()+1));
    // System.out.println(message + encoded.substring(encoded.length() - crc.length()+1));
  }
}