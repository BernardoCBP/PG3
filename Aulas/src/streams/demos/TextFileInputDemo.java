package streams.demos;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class TextFileInputDemo {
  public static void main(String[] args) {
    try {
      BufferedReader inputStream =
         new BufferedReader( new FileReader("data.txt")) ;

      System.out.println("Lines in data.txt:");

      int count = 1;

      String line= inputStream.readLine();
      while ( line != null ) {
        System.out.println(count++ + " " + line);
        line = inputStream.readLine();
      }

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
