
package trab3.bubbles;

public interface Game {
	void setStrategy(Strategy s);
	void addListener(GameListener v);
	
	Strategy select(int l, int c);
	void start();
	void stop();
	
	int getNumberOfLines();
	int getNumberOfColumns();
	Score getScore();
	int getTime();
}
