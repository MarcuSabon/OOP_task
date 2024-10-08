package hello.greetings;

import java.io.InputStream;
import java.io.PrintStream;

public class HalGreetings {
  public static void main(String[] args) {
	  String[] fullname;
	    if (args==null || args.length==0) {
	      System.out.println("Hello, I am Hal, and you are?");
	      fullname = readFullName(System.in);
	    } else {
	      System.out.println("Hello, I am Hal.");
	      fullname = args;
	    }
	    echoGreetings(System.out,fullname);
  }
  private static String[] readFullName(InputStream in) {
	  byte[] inputBuffer = new byte[20];
      int bytesRead = 0;
      try {//permet de tester sans erreur
    	  
    	  
          bytesRead = in.read(inputBuffer);
          //stock le nombre de caractere dans bytesread
          //stock les caracteres dans inputBuffer
          //read lit ce que l'on tape
          
          
      } catch (Exception e) {}
      
      String fullName = new String(inputBuffer, 0, bytesRead).trim();
      //crée un string avec toute les info d'avant
      return fullName.split(" ");
      //decoupe ce string par espace
  }
  
  private static void echoGreetings(PrintStream ps, String[] names) {
	ps.print("Greetings ");  
    for(int i=0;i<names.length;i++) {
    	ps.printf("%s ",names[i]);
    }
	ps.print("!");
  }
}