package streams.examples;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
public class ExampleCopyCom {

	public static void main(String[] args) {
		// Escrita no standard output
		PrintWriter pw= new PrintWriter( System.out, true );
		String pathname = "src\\streams\\examples\\ExampleCopyCom.java";
		// Colocando como recurso do try o BufferedReader o close é automático
		try (BufferedReader br = new BufferedReader(new FileReader(pathname))){
			StreamsUtils.copyCom(br, pw);
		} catch (FileNotFoundException e) {
			System.out.print("Erro a abrir o ficheiro " + pathname);
		}
		catch (IOException e) {
			System.out.print("Erro na leitura do ficheiro " + pathname);
		}
		pw.flush();  // Forçar a escrita no console output
		System.out.println("FIM do Teste"); // Assinalar o fim
	}
}