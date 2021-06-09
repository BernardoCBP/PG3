package streams.demos;

import java.io.*;

import java.util.Scanner;

public class TextFileInputDemo0 {
  public static void main(String[] args) {
    try {
      BufferedReader inputStream =
         new BufferedReader( new FileReader("data.txt")) ;
      String line;

      line = inputStream.readLine();
      System.out.println("The first line in data.txt:");
      System.out.println( line);

      line = inputStream.readLine();
      System.out.println("The second line in data.txt:");
      System.out.println( line);

      inputStream.close();
    }
    catch (FileNotFoundException e ) {
        System.out.println("File data.txt was not found or could not be open");
    }
    catch (IOException e ) {
      System.out.println("Error reading from file data.txt");
    }

  }
}
