package streams.demos;

import java.io.*;

public class ArrayODemo {
  public static void main(String[] args) {
    Species[] onArray = new Species[2];
    onArray[0] = new Species("Calif. Condor", 27, 0.02);
    onArray[1] = new Species("Black Rhino", 100, 1.0);
    try {
      ObjectOutputStream outputStream =
         new ObjectOutputStream( new FileOutputStream("array.file") );
      outputStream.writeObject( onArray );
      outputStream.close();
      System.out.println("Array sent to file array.file");
    }
    catch (FileNotFoundException e ) {
        System.out.println("File array.txt could not be open");
    }
    catch (IOException e ) {
      System.out.println("Error writing to file array.file");
    }
  }
}
