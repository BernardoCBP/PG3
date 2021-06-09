package trab2.grupo1;

import java.io.*;

public class DigitsExtense {

    public static void digitsConsecutivesPerExtense(Reader rd, PrintWriter pw ) throws IOException {
        int ch;
        String[] sDigits = {"zero","um","dois","três","quatro","cinco","seis","sete","oito","nove"};

        while( (ch = rd.read()) != -1 ) {
            StringBuilder digits = new StringBuilder();

            if( Character.isDigit(ch) ){
                digits.append("(");
                while( ch != -1 && Character.isDigit(ch) ) {
                    pw.write( ch );
                    digits.append( sDigits[Character.getNumericValue((char)ch)] ).append(", ");
                    ch = rd.read();
                }
                digits.setCharAt(digits.length()-2, ')');
                digits.setLength(digits.length()-1);
                pw.write( digits.toString() );

                if(ch == -1) break;
            }
            pw.write( ch );
        }
    }

    public static void digitsConsecutivesPerExtense( String filenameIn, PrintWriter pw ) throws IOException {
        try( BufferedReader inputStream = new BufferedReader(new FileReader(filenameIn)) ) {
            digitsConsecutivesPerExtense( inputStream, pw );
        }
    }

    public static void digitsConsecutivesPerExtense( String filenameIn, String filenameOut ) throws IOException {
        try( PrintWriter outStream = new PrintWriter(filenameOut) ) {
            digitsConsecutivesPerExtense(filenameIn, outStream);
        }
    }

    public static void digitsConsecutivesPerExtense( String filenameIn ) throws IOException {
        PrintWriter outStream = new PrintWriter( System.out );
        digitsConsecutivesPerExtense( filenameIn, outStream );
        outStream.flush();
    }

}