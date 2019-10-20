package app;

import java.net.Socket;
import java.util.Scanner;
import helper.CRC;

public class Client{
  public static void main(String[] args) throws Exception{
    Socket socket = new Socket("localhost", 3000);
    Scanner socketScan = new Scanner(socket.getInputStream());
    if(CRC.getCRCBits(CRC.xor(socketScan.nextLine())).equals("000"))
      System.out.println("No Error");
    else System.out.println("Error");
    socket.close();
    socketScan.close();
  }
}