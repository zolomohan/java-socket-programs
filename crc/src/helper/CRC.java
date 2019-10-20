package helper;

public class CRC{

  private static String generator = "1011";

  public static String xor(String message){
    StringBuilder encoded = new StringBuilder(message);
    for(int i=0; i <= encoded.length() - generator.length();){
      for(int j = 0; j < generator.length(); j++)
        encoded.setCharAt(i+j, encoded.charAt(i+j) == generator.charAt(j) ? '0' : '1');
      for(; i < encoded.length() && encoded.charAt(i) != '1'; i++);
    }
    return encoded.toString();
  }

  public static String appendZeros(String message){
    for(int i=0; i < generator.length()-1; i++)
      message+='0';
    return message;
  }

  public static String getCRCBits(String message){
     return message.substring(message.length() - generator.length()+1);
  }
}