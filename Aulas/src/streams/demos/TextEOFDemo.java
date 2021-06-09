package streams.demos;

import java.io.*;

public class TextEOFDemo {
  public static void main(String[] args) {
    try {
      BufferedReader inputStream =
         new BufferedReader( new FileReader("story.txt")) ;
      PrintWriter outputStream =
              new PrintWriter( new FileWriter("storylines.txt" ) );

      int count = 0;

      String line= inputStream.readLine();
      while ( line != null ) {
        count++;
        outputStream.println(count + " " + line);
        line = inputStream.readLine();
      }
      System.out.println(count + " lines written to storylines.txt.");

      outputStream.close();
      inputStream.close();
    }
    catch (FileNotFoundException e ) {
      System.out.println("File opening problem");
    }
    catch (IOException e ) {
      System.out.println("Error reading from file story.txt");
    }
  }
}
