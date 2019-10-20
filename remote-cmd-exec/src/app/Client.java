package app;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client{
  public static void main(String[] args) throws Exception{
    Socket socket = new Socket("localhost", 3000);
    Scanner scanner = new Scanner(System.in);
    PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
    System.out.print("Enter Command: ");
    output.println(scanner.nextLine());
    scanner.close();
    socket.close();
  }
}