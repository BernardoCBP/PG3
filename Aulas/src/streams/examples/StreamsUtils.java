package streams.examples;

import java.io.*;

public class StreamsUtils {

	public static void copy( Reader rd, PrintWriter pw  ) throws IOException {
		int car;
		while ( (car= rd.read()) != -1 )
				pw.write( car );
	}
	
	public static void copy( String filenameInput, String filenameOutput ) {
		try ( PrintWriter pw = new PrintWriter( filenameOutput ) ) { 
			try ( BufferedReader br = new BufferedReader(new FileReader( filenameInput )) ){
				copy( br, pw );
			}
			catch ( FileNotFoundException ex ) {
				System.out.println( "Não é possível abrir o ficheiro para leitura");
			}
			catch ( IOException ex ) {
				System.out.println ( "Erro no acesso ao ficheiro " + filenameInput );
			}
		}
		catch ( FileNotFoundException ex ) {
			System.out.println( "Não é possível abrir o ficheiro para escrita"  );
		}
	}

	// Copia os comentários de linha. Assume que nem os literais do tipo string
	// nem os comentários de bloco contêm a sequencia de caracteres que
	// define o início de um comentário de linha ("//").
	public static void copyCom( BufferedReader rd, PrintWriter pw ) throws IOException {
		String line;
		while ((line= rd.readLine())!= null) {
			int indexCom = line.indexOf("//");
			if ( indexCom != -1 )
				pw.println( line.substring(indexCom) );
		}
	}

	/* Copia as linhas dos alunos com nota superior ou igual a 10.
	 * O formato das linhas é:
	 * 	<numero> <name> <nota>
	 * As linhas onde são detetados erros no formato não são consideradas.
	 * @param br input stream
	 * @param pw output stream
	 * @return o número de linhas copiadas.
	 * @throws IOException exita erro na leitura
	 */
	public static int copyApproved(BufferedReader br, PrintWriter pw) throws IOException {
		int numberApproved = 0;
		String line;
		while ((line = br.readLine()) != null) {
			try {
				String strNota = line.trim();
				int indexNota = strNota.lastIndexOf(' ');
				if (indexNota != -1) {
					strNota = strNota.substring(indexNota + 1);
					double nota = Double.parseDouble(strNota);
					if (nota >= 9.5) {
						pw.println(line);
						++numberApproved;
					}
				}
			}
			// Caso não exista nota no fim o aluno não é considerado aprovado
			catch ( NumberFormatException e ) { }
		}
		return numberApproved;
	}
}
