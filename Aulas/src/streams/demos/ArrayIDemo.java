package streams.demos;

import java.io.*;

public class ArrayIDemo {
  public static void main(String[] args) {
    Species[] anotherArray;
    try {
      ObjectInputStream inputStream =
         new ObjectInputStream( new FileInputStream("array.file") );
      anotherArray = (Species[]) inputStream.readObject();
      inputStream.close();
      System.out.println("The following were read from the file array.file:");
      for(Species s: anotherArray )
        System.out.println( s + "\n");
      System.out.println("End of Program.");
   }
    catch (FileNotFoundException e ) {
        System.out.println("File array.file could not be open");
    }
    catch (IOException e ) {
      System.out.println("Error reading to file array.file");
    }
    catch (ClassNotFoundException e) {
      System.out.println("Error class " + e.getMessage() + " not exist");
    }
  }
}
