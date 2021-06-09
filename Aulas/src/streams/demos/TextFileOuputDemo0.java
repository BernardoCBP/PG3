package streams.demos;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class TextFileOuputDemo0 {
  public static void main(String[] args) {
    try {
      PrintWriter outputStream =
         new PrintWriter( new FileWriter("out.txt", true ) );
      System.out.println("Enter three lines of text:");
      String line;
      Scanner keyboard = new Scanner( System.in);
      for (int count = 1; count <= 3; ++count) {
        line = keyboard.nextLine();
        outputStream.println(count + " " + line);
      }
      outputStream.close();
      System.out.println("Those lines were written to out.txt.");
    }
    catch (Exception e ) {
        System.out.println("Error opening the file out.txt");
    }
  }
}
