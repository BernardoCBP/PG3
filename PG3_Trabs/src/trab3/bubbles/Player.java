package trab3.bubbles;

import trab2.grupo2.Families;

import javax.swing.*;
import java.io.*;
import java.util.*;

public class Player{

    private final String name;
    private Statistics statistics;
    private Score bestScore;


    public Player() {
        this.name = "UNDEFINED";
        this.statistics = new Statistics();
        this.bestScore = new Score(this.statistics.minTime, this.statistics.minBubbles, this.statistics.maxScore);
    }
    public Player(String name) {
        this.name = name;
        this.statistics = new Statistics();
        this.bestScore = new Score(this.statistics.minTime, this.statistics.minBubbles, this.statistics.maxScore);
    }

    public Player(String name, Statistics statistics) {
        this.name = name;
        this.statistics = statistics;
        this.bestScore = new Score(this.statistics.minTime, this.statistics.minBubbles, this.statistics.maxScore);
    }

    public void setBestScore(Score s) {
        this.bestScore.time = s.time;
        this.bestScore.bubbles = s.bubbles;
        this.bestScore.score = s.score;
    }

    public final String getName()           { return this.name;       }
    public final Score getBestScore()       { return bestScore;       }
    public final Statistics getStatistics() { return this.statistics; }


    private static void printPlayers(PrintWriter pw, Map<String, Player> players ) throws IOException {
        players.forEach( (name, player ) -> { pw.println( name + ":" + player.getStatistics().saveStats()); } );
    }

    public static void saveStatistics( Map<String, Player> players , File file ) {
            try (PrintWriter pw = new PrintWriter(file)) {
                Player.printPlayers(pw, players);
            } catch (IOException e) {
                e.printStackTrace();
            }
    }

    private static Player stringToPlayer(String saved) {

        String[] segments = saved.split(":");           //separar cada elemento
        int[] statsNum = new int[segments.length-1];
        for(int i = 0;i < statsNum.length;i++) {             //buscar todos os valores que percentem as estatisticas
            statsNum[i] = Integer.parseInt(segments[i+1]);   //atribuit a um array de ints que irá ser usado para criar as estatisticas deste jogador
        }
        Statistics statistics = new Statistics( statsNum );
        return new Player(segments[0], statistics);          //criar um novo player com nome dado pelo primeiro elemento do array e com as estatisticas criadas anteriormente
    }

    public static void loadStatistics( Map<String, Player> players, File file ) {
        try ( BufferedReader br = new BufferedReader( new FileReader(file) ) ){
            String line;
            Player player;
            while( (line = br.readLine()) != null ) {              //cada linhas contem um player diferente
                player = Player.stringToPlayer(line);              //com cada string é criado um player
                players.put(player.getName(), player);             //É posto o player no mapa de players
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getTopX(Map<String, Player> players, int max) {


        List<Map.Entry<String, Player>> entryList = new ArrayList<Map.Entry<String, Player>>(players.entrySet());       //criamos uma lista para dar sort posteriormente

        entryList.sort(new Comparator<Map.Entry<String, Player>>() {
            @Override
            public int compare(Map.Entry<String, Player> player1, Map.Entry<String, Player> player2) {

                if(player2.getValue().getStatistics().maxScore != player1.getValue().getStatistics().maxScore)            //se as pontuações forem diferentes
                    return player2.getValue().getStatistics().maxScore - player1.getValue().getStatistics().maxScore;     //retornamos a comparação das pontuações

                else if(player1.getValue().getStatistics().minBubbles != player2.getValue().getStatistics().minBubbles)   //se as pontuações forem iguais, vamos verificar as bubbles
                    return player1.getValue().getStatistics().minBubbles - player2.getValue().getStatistics().minBubbles; //se forem diferentes retornamos a comparação das bubbles

                return player1.getValue().getStatistics().minTime - player2.getValue().getStatistics().minTime;           //se chegamos aqui, so o tempo pode ser diferente
            }
        });

        StringBuilder sb = new StringBuilder("Points | Bubbles | Time  | Player\n");
        sb.append("----------------------------------------------\n");
        int count = 0;
        for (Map.Entry<String, Player> entries: entryList) {
            if (count >= max) break;
            sb.append(String.format("%05d |", entries.getValue().getStatistics().maxScore ));
            sb.append(String.format("%07d |", entries.getValue().getStatistics().minBubbles ));
            sb.append(String.format(" %02d:%02d | ", (entries.getValue().getStatistics().minTime % 3600) / 60, (entries.getValue().getStatistics().minTime % 60)));
            sb.append(entries.getValue().name).append("\n");
            count++;
        }
        return sb.toString();
    }



}
