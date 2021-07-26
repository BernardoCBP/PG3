package trab3.bubbles;

import trab1.grupo1.Player;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

public class Statistics {

    protected int tGames;
    protected int wGames;

    protected int maxScore;
    protected int avgScore;

    protected int minTime;
    protected int avgTime;

    protected int maxBubbles;  //numero maximo de bubbles que não foram destruidas
    protected int minBubbles;  //numero minimo de bubbles que não foram destruidas
    protected int avgBubbles;

    public Statistics(  ) {
        this.tGames = 0;
        this.wGames = 0;
        this.maxScore = 0;
        this.avgScore = 0;
        this.minTime = 0;
        this.avgTime = 0;
        this.maxBubbles = 168;
        this.minBubbles = 168;
        this.avgBubbles = 0;
    }

    //construtor utilizado quando é feito load de um player
    public Statistics( int[] stats ) {
        this.tGames = stats[0];
        this.wGames = stats[1];
        this.maxScore = stats[2];
        this.avgScore = stats[3];
        this.minTime = stats[4];
        this.avgTime = stats[5];
        this.maxBubbles = stats[6];
        this.minBubbles = stats[7];
        this.avgBubbles = stats[8];
    }

    public void addScore( Score s, int tBubbles ) {
        if(s.bubbles < (2/3.0)*tBubbles )                          { this.wGames++;               }
        if( s.score > this.maxScore )                              { this.maxScore = s.score;     }
        if( s.bubbles > this.maxBubbles )                          { this.maxBubbles = s.bubbles; }
        if( s.bubbles < this.minBubbles )                          { this.minBubbles = s.bubbles; this.minTime = s.time; }
        else if( s.bubbles == this.minBubbles &&
                (s.time < this.minTime  || this.minTime == 0) )    { this.minTime = s.time;       }

        tGames++;
        avgScore = calcAvg(s.score, tGames, avgScore);
        avgTime = calcAvg(s.time, tGames, avgTime);
        avgBubbles = calcAvg(s.bubbles, tGames, avgBubbles);
    }

    public int calcAvg( int value, int total, int oldAvg ) { return (oldAvg+value)/total; }

    public void clear() {
        this.tGames = 0;
        this.wGames = 0;
        this.maxScore = 0;
        this.avgScore = 0;
        this.minTime = 0;
        this.avgTime = 0;
        this.maxBubbles = 168;
        this.minBubbles = 168;
        this.avgBubbles = 0;
    }

    @Override
    public String toString() {
        return  "\tNumber of games played: " + this.tGames + "\n" + "\tNumber of games won: " + this.wGames + "\n\n" +
                "\tBest score: " + this.maxScore + "\n" + "\tAverage score: " + this.avgScore + "\n\n" +
                "\tBest time: " + String.format("%02d:%02d", (this.minTime % 3600) / 60, (this.minTime % 60)) + "\n" +
                "\tAverage time: " + String.format("%02d:%02d", (this.avgTime % 3600) / 60, (this.avgTime % 60)) + "\n\n" +
                "\tMost amount of bubbles left: " + this.maxBubbles + "\n" +
                "\tLeast amount of bubbles left: " + this.minBubbles + "\n" + "\tAverage bubbles left: " + this.avgBubbles;
    }

    //Metodo utilizado para guardar as estatisticas na classe player
    public String saveStats() {
        return this.tGames + ":" + this.wGames + ":" + this.maxScore + ":" + this.avgScore + ":" +this.minTime + ":" +
                this.avgTime + ":" + this.maxBubbles + ":" + this.minBubbles + ":" + this.avgBubbles;
    }

}
