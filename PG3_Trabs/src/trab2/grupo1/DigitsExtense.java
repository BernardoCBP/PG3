package trab2.grupo1;

import java.io.*;

public class DigitsExtense {

    public static void digitsConsecutivesPerExtense(Reader rd, PrintWriter pw) throws IOException {

        int ch;
        String[] sDigits = {"zero","um","dois","três","quatro","cinco","seis","sete","oito","nove"};

        while( (ch = rd.read()) != -1 ) {

            if( Character.isDigit( ch ) ) {
                StringBuilder digits = new StringBuilder("(");
                while( ch != -1 && Character.isDigit( ch ) ) {
                    pw.write(ch);
                    digits.append( sDigits[Character.getNumericValue((char)ch)] ).append(", ");
                    ch = rd.read();
                }
                digits.setCharAt(digits.length()-2, ')');
                pw.write( digits.toString().trim() );
            }

            if(ch == -1) break;
            pw.write(ch);
        }
    }

    public static void digitsConsecutivesPerExtense(String filenameIn, PrintWriter pw ) throws IOException {
        try( BufferedReader rd  = new BufferedReader(new FileReader(filenameIn)) ) {
            digitsConsecutivesPerExtense(rd , pw);
        }
    }

    public static void digitsConsecutivesPerExtense(String filenameIn, String filenameOut ) throws IOException {
        try( PrintWriter pw = new PrintWriter( filenameOut ) ) {
            digitsConsecutivesPerExtense(filenameIn, pw);
        }
    }

    public static void digitsConsecutivesPerExtense( String filenameIn ) throws IOException {
            PrintWriter pw = new PrintWriter( System.out );
            digitsConsecutivesPerExtense(filenameIn, pw);
            pw.flush();
    }
}