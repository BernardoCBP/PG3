package streams.examples;

import java.io.File;

/**
 * Created by msousa on 4/23/2020.
 */
public class FileUtils {
    /**
     * Se for diretoria retorna o número de ficheiros  na diretoria e subdiretrias,
     * caso contrário, retorna 1 ou 0 conforme exista ou não.
     * @param filename nome do ficheiro ou diretoria
     * @return numero de ficheiros existentes
     */
    public static int numberFiles( String filename ){
        File f = new File ( filename );
        if ( f.isDirectory() ) {
            String[] fn = f.list();
            int c= 0;
            for ( String s : fn ) {
                c+= numberFiles( filename +"\\" +s );
            }
            return c;
        } else
            return f.exists() ? 1 : 0;
    }

    /**
     * Escreve no standard output a informação de uma File.
     * @param f - file a escrever a informação
     */
    private static void print( File f ) {
        System.out.print("[" + (f.isDirectory() ? 'D' : 'F') +  "] " + f.getName());
        if ( !f.isDirectory())
            System.out.print((int)Math.ceil( f.length()/1024d ) + " kB(" + f.length() + "" +
                             (f.canRead()? " RD": "" ) +
                             (f.canWrite()? " WR" : "") );
        System.out.println();
    }

    /**
     * Se for diretoria escreve no standard output a informação das entradas,
     * caso contrário, escreve a informação do ficheiro.
     * @param filename - nome de ficheiro ou diretoria
     */
    public static void list( String filename ){
        File f = new File( filename );
        if ( f.isDirectory() ) {
            File[] fn = f.listFiles();
            for ( File s: fn )
                print( s );
        }
        else if ( f.exists()) print( f );
        else System.out.println( "Nao existe um ficheiro com name " + filename );

    }

    /**
     * Método auxiliar de dirAll para que o os ficheiros nas subdiretorias fiquem tabelados.
     * @param prefix caracteres que têm que ser colocados no inicio
     * @param f objeto do tito File a listar
     */
    private static void dirAll(String prefix,  File f ) {
        System.out.print( prefix ); print( f );
        if ( f.isDirectory() ) {
            File[] fn = f.listFiles();
            for ( File fs: fn )
                // por cada diretoria são adicionados dois espaços ao prefixo
                dirAll( prefix+"  ", fs );
        }
    }

    /**
     * Caso seja diretoria lista todos os ficheiros, as subdiretorias e respetivos ficheiros
     * @param pathname
     */
    public static void dirAll( String pathname ) {
        File f = new File( pathname );
        if ( f.exists() )
            dirAll("", f );
        else
            System.out.println(pathname + " not exist.");
    }
}
