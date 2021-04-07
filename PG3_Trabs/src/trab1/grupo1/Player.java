package trab1.grupo1;

import java.util.Arrays;

public class Player implements Comparable<Player> {

    // CONSTANTS
    public static final int MIN_GAMES = 0;
    public static final int MIN_POINTS = 0;

    // VARIABLES
    private final String name;
    private int numQuizzes, totalQuizPoints;

    // CONSTRUCTORS
    public Player(String name, int numQuizzes, int totalQuizPoints) {
        this.name = name;
        setNumQuizzes( numQuizzes );
        setTotalQuizPoints( totalQuizPoints );
    }

    public Player(String toString){
        setNumQuizzes( toString.substring(0, toString.indexOf(':')) );
        setTotalQuizPoints( toString.substring(toString.indexOf(':')+1, toString.indexOf('-')) );
        name = toString.substring( toString.indexOf('-')+1 ).trim();
    }

    // SETTERS
    public void setNumQuizzes( int numQuizzes ) {
        if( numQuizzes >= MIN_GAMES)
            this.numQuizzes = numQuizzes;
    }
    public void setTotalQuizPoints( int totalQuizPoints ) {
        if( totalQuizPoints >= MIN_POINTS )
            this.totalQuizPoints = totalQuizPoints;
    }
    public void setNumQuizzes( String str ) {
        setNumQuizzes( Integer.parseInt(str.trim()) );
    }
    public void setTotalQuizPoints( String str ) {
        setTotalQuizPoints( Integer.parseInt(str.trim()) );
    }

    // GETTERS
    public final String getName() {
        return name;
    }
    public final int getNumQuizzes() {
        return numQuizzes;
    }
    public final int getTotalQuizPoints() {
        return totalQuizPoints;
    }

    // METHODS
    @Override
    public boolean equals( Object o ) {
        if( this == o ) return true;
        if( !(o instanceof Player) ) return false;
        Player player = (Player) o;
        return numQuizzes == player.numQuizzes && totalQuizPoints == player.totalQuizPoints && name.equals(player.name);
    }

    @Override
    public String toString() {
        return numQuizzes + ":" + totalQuizPoints + " - " + name;
    }

    public int getPoints() {
        return (numQuizzes > 0) ? (totalQuizPoints/numQuizzes) : 0;
    }

    public void addQuiz( int quizPoints ) {
        numQuizzes++;
        totalQuizPoints += quizPoints;
    }

    public int compareTo( Player p ) {
        return this.getPoints() - p.getPoints();
    }

    public static Player getPlayer( Player[] pArray, String name ) {
        for(Player player : pArray) {                           // find the players with the specified name
            if( player.name.equals(name) ) {
                return player;
            }
        }
        return new Player( name, 0, 0);   // if player not found, creates a new player
    }

    public static Player minimum( Player ... p ) {
        if( p != null && p.length > 0 ) {            // check if there is at least one non null player
            Player min = p[0];                       // at the beginning the first player has the least points
            for( int i = 0; i < p.length - 1; i++ ) { // go through the players and find the player with the least points
                if( min.compareTo(p[i + 1]) > 0 ) {
                    min = p[i + 1];
                }
            }
            return min;
        }
        return null;
    }

    public static Player[] getTop10( Player[] pArray ) {
        Player[] top10Array = new Player[10];

        Arrays.sort( pArray );

        for( int i=0; i < 10 && i < pArray.length; i++ ) {    // the top10Array is sorted in descending order
            top10Array[i] = pArray[pArray.length-i-1];
        }

        top10Array = Arrays.copyOf( top10Array, Math.min(pArray.length, 10) );  // check the length of the array
                                                                                // to see if it has less than 10 elem
        return top10Array;
    }

}