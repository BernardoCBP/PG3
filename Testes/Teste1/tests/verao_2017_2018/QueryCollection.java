package testes.test1;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.function.Predicate;

/**
 * Created by msousa on 4/23/2018.
 */
public abstract class QueryCollection {
    public final String title;
    protected final ArrayList<Query> queries= new ArrayList<>();
    private int pointsTotal;
    protected QueryCollection( String title ) { this.title = title; }
    public int getPoints()                    { return pointsTotal; }
    public QueryCollection append( Query q ) {
        queries.add( q );
        pointsTotal += q.getPoints();
        return this;
    }

    public abstract String getHeader();

    public int ask(Scanner in, String prefix ) {
        System.out.print(prefix + getHeader() +'\n');
        int sumResp = 0, queryNumber = 1;
        for ( Query q : queries )
            sumResp += q.ask( in, prefix + queryNumber++ + ".");
        return sumResp;
    }

    public Query find( Predicate<Query> p ) {
        for ( Query q: queries )
            if ( p.test( q )) return q;
        return null;
    }
}
