package trab2.grupo1;

import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

public class TestDigitsExtense {
    @Test
    public void testOneNumber() throws IOException {
        StringReader sr = new StringReader("O 1º teste");
        StringWriter sw = new StringWriter( );
        DigitsExtense.digitsConsecutivesPerExtense(sr, new PrintWriter(sw) );
        assertEquals("O 1(um)º teste", sw.toString());
    }

    @Test
    public void testTwoNumber() throws IOException {
        StringReader sr = new StringReader("O 1º teste foi dia 13 de Maio");
        StringWriter sw = new StringWriter( );
        DigitsExtense.digitsConsecutivesPerExtense(sr, new PrintWriter(sw) );
        assertEquals("O 1(um)º teste foi dia 13(um, três) de Maio", sw.toString());
    }

    @Test
    public void testTwoLines() throws IOException {
        StringReader sr = new StringReader("O 2º teste e o exame de época norma é a 30 de Junho.\n" +
                                             "O exame de época de recurso é a 19 de Julho.\n");
        StringWriter sw = new StringWriter( );
        DigitsExtense.digitsConsecutivesPerExtense(sr, new PrintWriter(sw) );
        assertEquals("O 2(dois)º teste e o exame de época norma é a 30(três, zero) de Junho.\n" +
                             "O exame de época de recurso é a 19(um, nove) de Julho.\n", sw.toString());
    }

    @Test
    public void testInEnd() throws IOException {
        StringReader sr = new StringReader("O 2º teste e o exame de época norma é a 30-06-2021\n" +
                                             "O exame de época de recurso é a 19-07-2021");
        StringWriter sw = new StringWriter( );
        DigitsExtense.digitsConsecutivesPerExtense(sr, new PrintWriter(sw) );
        assertEquals("O 2(dois)º teste e o exame de época norma é a 30(três, zero)-06(zero, seis)-2021(dois, zero, dois, um)\n" +
                "O exame de época de recurso é a 19(um, nove)-07(zero, sete)-2021(dois, zero, dois, um)", sw.toString());
    }

    @Test
    public void testInBegin() throws IOException {
        StringReader sr = new StringReader("1º e 2º teste podem ser repetidos no exame de época de recurso");
        StringWriter sw = new StringWriter( );
        DigitsExtense.digitsConsecutivesPerExtense(sr, new PrintWriter(sw) );
        assertEquals("1(um)º e 2(dois)º teste podem ser repetidos no exame de época de recurso", sw.toString());
    }


}
