package trab1.grupo1;

import java.util.Objects;

public class Player {
    // Constante
    public static final int MIN_GAMES = 0;
    public static final int MIN_POINTS = 0;
    //Variaveis de instancia
    private final String name;
    private int numQuizzes, totalQuizPoints;

    //Construtores
    public Player(String nm, int nq, int tqs){
        this.name = nm;
        this.numQuizzes = nq;
        this.totalQuizPoints = tqs;
    }

    public Player(String toString){
        setNumQuizzes( toString.substring(0, toString.indexOf(':') ) );
        setPoints( toString.substring(toString.indexOf(':')+1, toString.indexOf('-') ) );
        name = toString.substring( toString.indexOf('-')+1 ).trim();
    }

    //Getters
    public String getName() { return name; }
    public int getNumQuizzes() { return numQuizzes; }
    public int getTotalPoints() { return totalQuizPoints; }


    //Setters
    public void setNumQuizzes( int numQuizzes ) {
        if( numQuizzes >= MIN_GAMES)
            this.numQuizzes = numQuizzes;
    }
    public void setPoints( int totalQuizPoints ) {
        if( totalQuizPoints >= MIN_POINTS)
            this.totalQuizPoints = totalQuizPoints;
    }
    public void setNumQuizzes( String str) {
        setNumQuizzes(Integer.parseInt( str.trim() ));
    }
    public void setPoints( String str) {
        setPoints(Integer.parseInt( str.trim() ));
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
        
    }

    //Metodos estaticos

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

