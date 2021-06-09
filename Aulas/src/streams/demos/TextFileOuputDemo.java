package streams.demos;

import java.io.*;

public class TextFileOuputDemo {
  public static void main(String[] args) {
    try {
      PrintWriter out =
         new PrintWriter( new FileWriter("species.records")) ;
      Species oneRecord =  new Species("Calif. Condor",
                           27, 0.02);
      out.println( oneRecord.toString() );
      out.println( );
      out.println( oneRecord );
      out.close();
      System.out.println("End of Program.");
    }
    catch (IOException e ) {
        System.out.println("Error opening species.records.");
    }
  }
}
