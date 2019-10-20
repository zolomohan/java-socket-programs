package app;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
  public static void main(String[] args) throws Exception {
    Socket socket = new Socket("localhost", 3000);
    Scanner keyboardScanner = new Scanner(System.in);
    Scanner socketScanner = new Scanner(socket.getInputStream());
    PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
    while (true) {
      System.out.print("Client: ");
      writer.println("Client: " + keyboardScanner.nextLine());
      System.out.println(socketScanner.nextLine());
    }
  }
}