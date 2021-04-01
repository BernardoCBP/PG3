package trab1.grupo1;

import java.util.Arrays;
import java.util.Objects;

public class Player implements Comparable<Player> {
    // Constante
    public static final int MIN_GAMES = 0;
    public static final int MIN_POINTS = 0;
    //Variaveis de instancia
    private final String name;
    private int numQuizzes, totalQuizPoints;

    //Construtores
    public Player(String name, int numQuizzes, int totalQuizPoints){
        this.name = name;
        setNumQuizzes(numQuizzes);
        setTotalPoints(totalQuizPoints);
    }

    public Player(String toString){
        setNumQuizzes( toString.substring(0, toString.indexOf(':') ) );
        setTotalPoints( toString.substring(toString.indexOf(':')+1, toString.indexOf('-') ) );
        name = toString.substring( toString.indexOf('-')+1 ).trim();
    }

    //Getters
    public String getName() { return name; }
    public int getNumQuizzes() { return numQuizzes; }
    public int getTotalQuizPoints() { return totalQuizPoints; }


    //Setters
    public void setNumQuizzes( int numQuizzes ) {
        if( numQuizzes >= MIN_GAMES)
            this.numQuizzes = numQuizzes;
    }
    public void setTotalPoints( int totalQuizPoints ) {
        if( totalQuizPoints >= MIN_POINTS)
            this.totalQuizPoints = totalQuizPoints;
    }
    public void setNumQuizzes( String str) {
        setNumQuizzes(Integer.parseInt( str.trim() ));
    }
    public void setTotalPoints( String str) {
        setTotalPoints(Integer.parseInt( str.trim() ));
    }

    //Metodos de instancia
    public int getPoints() {
        return (numQuizzes == 0) ? 0 : (totalQuizPoints / numQuizzes);
    }
    public void addQuiz(int quizPoints) {
        numQuizzes++;
        totalQuizPoints += quizPoints;
    }
    public int compareTo(Player p) {
        return this.totalQuizPoints - p.totalQuizPoints;
    }

    //Metodos estaticos
    public static Player getPlayer(Player[] pArray, String nm) {
        for (Player player : pArray)
            if (player.name.equals(nm))
                return player;

        return new Player("0:0 - " + nm);
    }

    public static Player minimum( Player ... p ) {
        if(p == null || p.length == 0)
            return null;
        Player min = p[0];
        for(int i=0; i < p.length - 1 ; i++) {
            if( min.compareTo( p[i+1] ) > 0 ) {
                min = p[i+1];
            }
        }
        return min;
    }

    public static Player[] getTop10(Player[] pArray) {
        Player[] top10Array = new Player[10];

        Arrays.sort( pArray );

        for(int i = 0; i < 10 && i < pArray.length; i++) {
            top10Array[i] = pArray[pArray.length-1-i];
        }

        top10Array = Arrays.copyOf( top10Array, Math.min(pArray.length, 10) );

        return top10Array;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;                                  //verificar se os objetos referenciam o mesmo objeto
        if (o == null || getClass() != o.getClass()) return false;   //verificar se são da mesma classe
        Player player = (Player) o;                                  //como sabemos que são da mesma classe realizamos um cast
        return numQuizzes == player.numQuizzes && totalQuizPoints == player.totalQuizPoints && name.equals(player.name);
    }
    @Override
    public String toString() {
        return numQuizzes + ":" + totalQuizPoints + " - " + name;
    }

}
















