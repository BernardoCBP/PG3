package streams.demos;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;

public class TextFileOuputDemo1 {
  public static void main(String[] args) {
      PrintWriter outputStream = new PrintWriter( System.out , true); //autoflush é só para println
      for (int count = 1; count <= 3; ++count) {
        outputStream.println(count + "º Line");
      }
      //outputStream.flush();
      System.out.println("Those lines were written to out.txt.");
  }
}
