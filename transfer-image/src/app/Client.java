package app;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.net.Socket;

import javax.imageio.ImageIO;

public class Client{
  public static void main(String[] args) throws Exception{
    Socket socket = new Socket("localhost", 4000);
    System.out.println("Client is Running");
    try{
      BufferedImage image = ImageIO.read(new File("D:/Pictures/Camera Roll/IMG_20180913_164515.jpg"));
      ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
      ImageIO.write(image, "jpg", byteArrayOutputStream);
      byteArrayOutputStream.flush();
      byte[] data = byteArrayOutputStream.toByteArray();
      byteArrayOutputStream.close();

      DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
      dataOutputStream.writeInt(data.length);
      dataOutputStream.write(data, 0, data.length);
      System.out.println("Image Sent to Server");
      dataOutputStream.close();
    }
    catch(Exception exception){
      System.out.println("Exception" + exception.getMessage());
    }
    finally{
      socket.close();
    }
  }
}