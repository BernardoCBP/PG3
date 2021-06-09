package streams.demos;

import java.io.*;
import java.util.Scanner;

public class FileClassDemo {
  public static void main(String[] args) {
    System.out.println("I will show the first line in a text file you name.");
    System.out.println("The text file must contain one or mor lines.");

    System.out.print("Enter file name: ");
    Scanner keyboard = new Scanner( System.in );
    String name = keyboard.next();

    File fileObject = new File( name );
    while(!fileObject.exists() || ! fileObject.canRead()) {
      if(!fileObject.exists() )
        System.out.println("No such file");
      else
        System.out.println("That file is not readable");
      System.out.print("Enter file name again: ");
      name = keyboard.next();
      fileObject = new File( name );
    }

    try {
      BufferedReader fileInput =
         new BufferedReader( new FileReader( fileObject ) );

      System.out.println("The first line in the file is:");
      String firstLine = fileInput.readLine();
      System.out.println( firstLine);
      fileInput.close();
    }
    catch (FileNotFoundException e ) {
        System.out.println("File \"" + fileObject.getName() + "\" could not be open");
    }
    catch (IOException e ) {
      System.out.println("Error reading from file " + name);
    }
  }
}
