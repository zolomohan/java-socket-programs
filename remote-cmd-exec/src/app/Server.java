package app;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server{
  public static void main(String[] args) throws Exception{
    ServerSocket server = new ServerSocket(3000);
    Socket socket = server.accept();
    Scanner scan = new Scanner(socket.getInputStream());
    Runtime runtime = Runtime.getRuntime();
    Process process = runtime.exec(scan.nextLine());
    BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
    String str;
    while((str = reader.readLine()) != null)
      System.out.println(str);
    scan.close();
    server.close();
  }
}