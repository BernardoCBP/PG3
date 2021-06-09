package streams.examples;

import java.io.*;


public class ExampleApproved {

	public static void main(String[] args) throws IOException{
			StringReader sr = new StringReader(
					"123 Rita Isabel 18.1\n"+
			        "125 Maria Isabel 5\n" +
					"124 Ana Beatriz 18.1\n" +
					"126 Filipe Manuel 9.1\n" +
			        "127 João Pedro 20\n" );
			/* Resultado esperado:
			 123 Rita Isabel 18.1
			 124 Ana Beatriz 18.1
			 127 João Pedro 20
			 Aprovados: 3
			*/
			StringWriter sw = new StringWriter();
			PrintWriter pw =new PrintWriter(sw);
			int i = StreamsUtils.copyApproved(new BufferedReader(sr), pw);
	
			System.out.print( sw.toString() );
			System.out.print( "Aprovados: " +  i );
	}


}