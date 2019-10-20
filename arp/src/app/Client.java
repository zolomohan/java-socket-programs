package app;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client{
  public static void main(String[] args) throws Exception{
    Socket socket = new Socket("localhost", 3000);
    Scanner keyboardScanner = new Scanner(System.in);
    Scanner socketScanner = new Scanner(socket.getInputStream());

    PrintWriter output = new PrintWriter(socket.getOutputStream(), true);

    System.out.print("Enter IP: ");
    output.println(keyboardScanner.nextLine());
    while(true)
      try{
        System.out.println(socketScanner.nextLine());
      }
      catch(Exception e){
        break;
      }
    socket.close();
    keyboardScanner.close();
    socketScanner.close();
  }
}