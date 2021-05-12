package testes.test1;

/**
 * Created by msousa on 4/23/2018.
 */
public class MultQuest extends IntQuest {
    private String[] options;
    public MultQuest( String inquiry, int resp, String ... options ) throws QueryException{
        super( inquiry, options.length, resp );
        if ( resp < 0 || resp >= options.length )
            throw new QueryException("Error: Alternativa correta inválida");
        this.options = options;
        /*StringBuilder sb = new StringBuilder();
        int n= 0;
        for(String s : options )
            sb.append(n++).append(": ").append( s ).append(' ');
        sb.append( "\n\tQual a alternativa correta [0.."+ (options.length-1)+ "]? ");
        this.options = sb.toString();*/
    }
    public String getInquiry() {
        StringBuilder sb = new StringBuilder(super.getInquiry() + "\n\t" );
        int n= 0;
        for(String s : options )
            sb.append(n++).append(": ").append( s ).append(' ');
        sb.append( "\n\tQual a alternativa correta [0.."+ (options.length-1)+ "]? ");

        return  sb.toString();
    }

    public static void main(String[] args)  {
        try {
            Question q = new MultQuest("Qual o espaço ocupado por uma variável do tipo int",
                    2,"2 bytes","8 bits","32 bits","8 bytes");
            System.out.println( q.getInquiry() );
            q = new MultQuest("Qual … ", 2, "…","…");
        }
        catch ( QueryException e ) {
            System.out.println( e.getMessage());
        }
    }
}
