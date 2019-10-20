package app;

import java.net.Socket;
import java.util.Scanner;

public class Client{
  public static void main(String[] args) throws Exception{
    Socket socket = new Socket("localhost", 3000);
    Scanner keyScan = new Scanner(System.in);
    Scanner scanner = new Scanner(socket.getInputStream());

    String message = scanner.nextLine();
    System.out.print("Enter CRC: ");
    String crc = keyScan.nextLine();

    StringBuilder encoded = new StringBuilder(message);

    for(int i=0; i <= encoded.length() - crc.length();){
      for(int j = 0; j < crc.length(); j++)
        encoded.setCharAt(i+j, encoded.charAt(i+j) == crc.charAt(j) ? '0' : '1');
      for(; i < encoded.length() && encoded.charAt(i) != '1'; i++);
    }

    if(encoded.substring(encoded.length() - crc.length()+1).equals("000")) System.out.println("No Error");
    else System.out.println("Error");
  }
}