package trab3.bubbles;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.function.IntUnaryOperator;

import trab3.bubbles.pieces.*;

public class BubbleGame implements Game, Board {

	protected final static Bubble DUMMY_BUBBLE = new Dummy();	// A DUMMY_BUBBLE é uma bolha ficticia que permite que não
																//  existam testes aos limites do tabuleiro.

	private static final Random randomize = new Random();		// Utilizado para preencher aleatóriamente as cores no tabuleiro


	private final int[] positionsShuffle; 		// Posições em que podem ser colocadas bolhas - usado no preenchimento aleatório
												// Depende da dimensão do tabuleiro


	private final int[]  percentagePerColor = { 1, 1, 21, 21, 21, 12, 12, 12 };	// Percentagem de bolhas para cada cor.
	private final int[]  numberPerColor;										// Depende da dimensão do tabuleiro

	private final Bubble [][] board; // Tabuleiro de jogo - existem 2 linhas
	                                 // a mais e por cada linha 2 colunas.


	private final ArrayList<GameListener> listener = new ArrayList<>();	// Objectos que pretendem ser avisados de modificações no jogo
	private Strategy strategy = new StrategyGravitational();			// Estratégia do jogo -

	private int bubbles, score;
	long timeStart, 				// Colocado com a hora currente no start.
		 timeStop;  				// Colocado a 0 no start e atualizado com a hora currente do stop.

	public BubbleGame( int numberLines, int numberColumns )       { 
		board = new Bubble[ numberLines+2 ] [ numberColumns+2 ];

		int numberOfBubbles = numberLines * numberColumns;
		numberPerColor = calculateNumberPerColor(percentagePerColor, numberOfBubbles, 2);
		positionsShuffle = fillPositions(numberOfBubbles, IntUnaryOperator.identity());

		// Preencher o tabuleiro com a bolha fiticia - DUMMY_BUBBLE.
		for ( int line= 0; line < board.length-1; ++line )
			Arrays.fill( board[line], DUMMY_BUBBLE);
		board[board.length-1] = board[0];
	}

	// << Implementação da interface Board >>
	public final int getNumberOfColumns() { return board[0].length-2; }
	public final int getNumberOfLines()   { return board.length-2;    }
	public Bubble getBubble(int l, int c) { return board[l+1][c+1];   }
	public void putBubble(int l, int c, Bubble b) {
		board[ l+1 ][ c+1 ] = b;
		listener.forEach(li -> li.unselected( b ));
	}
	public void putHole(int line, int column) {
		putBubble(line, column, new Hole(this, line, column));
		if( line == getNumberOfLines()-1 )
			strategy.freeColumn(this, column);
	}

	// << Implementação da interface  BubbleListener >>
	public void selected( Bubble b )   { strategy.add( b );	listener.forEach(li-> li.selected( b )); }
	public void unselected( Bubble b ) { listener.forEach(li-> li.unselected( b ));                  }

	// << Implementação da interface Game >>
	public final Score getScore() {
		return new Score(getTime(), bubbles, score);
	}
	public int getTime() {
		long time = (timeStop == 0) ? System.currentTimeMillis() : timeStop;
		return (int)( time-timeStart)/1000;
	}
	public void addListener(GameListener v) { listener.add(v);       }
	public void setStrategy(Strategy s )    { strategy = s; stop();  }

	public Strategy select(int line, int column ) {
		Bubble b = getBubble(line, column);
		if( b.isSelected() ) {
			int n = strategy.removeSelected( this );

			// todo - actualizar o número de bubbles e o score *** DONE
			this.bubbles -= n;
			this.score += b.getColor() * Math.pow(2, n-2);

			listener.forEach(li-> li.scoreChange( this ));		}
		else
			strategy.select( b );
		return strategy;
	}

	public void start() {
		if( timeStop < timeStart ) {
			stop();
		}
		timeStop = 0;
		timeStart = System.currentTimeMillis();
		score = 0;
		bubbles = getNumberOfLines()*getNumberOfColumns();
		fillBoard( positionsShuffle.length, numberPerColor, positionsShuffle ) ;
		listener.forEach( li-> li.gameStart( this ) );
	}
	
	public void stop() {
		if( timeStop < timeStart ) {
			timeStop = System.currentTimeMillis();
			this.score -= this.getTime()*5;  //por cada segundo de jogo são substraidos 5 pontos ao jogador
			if(this.score < 0) {             //o numero de pontos minimo é zero
				this.score = 0;
			}
			listener.forEach(li -> li.gameStop(this));
		}
	}

	// << Métodos auxiliares >>
	private int[] fillPositions(int numberOfBubbles, IntUnaryOperator operation) {
		int[] array = new int[numberOfBubbles];
		for(int pos = 0; pos < numberOfBubbles; ++pos )
			array[pos] = operation.applyAsInt(pos);
		return array;
	}

	/**
	 *  todo - descrição *** DONE
	 * A variavel diff, existe para o caso de a soma das percentagens presentes no array
	 * percentages ser maior do que 100%.Caso isto aconteça, é calculada a diferença entre
	 * esse valor e 100%, retirada a diferença a umas das percentagens do array;
	 * De seguida é criado um array onde  irá ser guardado os numeros de bolhas de cada cor,
	 * e por isso, terá dimensao igual ao array de percentagens;
	 * O numero de bolhas é calculado multiplicando o valor total de bolhas pela respetiva percentagem;
	 * Para as duas primeiras cores, Preto e Branco, é verificado se esse nmero de bolhas é maior do que
	 * o minimum e caso não seja, entao o numero de bolhas pass a ser o minimum.
	 * no final é retornado o array com os numero de bolhas por cor.
	 *
	 * @param percentages - percentagem que deve ter cada cor
	 * @param totalOfBubbles - numero total de bolhas
	 * @return número de bolhas por cor
	 */
	private int[] calculateNumberPerColor(int[] percentages, int totalOfBubbles, int minimum) {
		// todo - calcular o número de peças. Os dois primeiros (While e Black) tem que ter pelo menos minimum. *** DONE
		int diff;
		if( (diff = Arrays.stream(percentages).sum() - 100) > 0 ) {
			percentages[percentages.length-1] -= diff;
		};

		int[] nPerColor = new int[percentages.length];

		if( ( nPerColor[0] = (int)(totalOfBubbles * (percentages[0]/100.0)) ) < minimum ) nPerColor[0] = minimum;
        if( ( nPerColor[1] = (int)(totalOfBubbles * (percentages[0]/100.0)) ) < minimum ) nPerColor[1] = minimum;
		for(int i = 2; i < percentages.length; i++) {
			nPerColor[i] = (int)(totalOfBubbles * (percentages[i]/100.0));
		}
		return nPerColor;
	}

	/**
	 * todo - explicar o algoritmo e comentar o código *** DONE
	 * É percorrido o array com o numero de bolhas por cor. Para cada cor é calculada a posiçao de
	 * cada bolha aleatoriamente. Atraves dessa posçao é obtida a linha e coluna a que a bolha deve
	 * pertencer. Apos ser calculada a posiçao, ser obtida a linha e coluna da bolha a ser inserida,
	 * é feita a inserção da bolha atraves da sua cor. A cor da bolha depende da sua posiçao no array
	 * numberColor:	-> numbersColor[0] - cor Branca
	 * 				-> numbersColor[1] - cor Branca
	 * 				-> numbersColor[2]..numbersColor[4] - cores em Cruz
	 * 				-> numbersColor[5]..numbersColor[7] - cores Diagonais
	 *
	 * @param numberOfBubbles
	 * @param numbersColor número de peças para cada cor
	 * @param positions array que contém todas as posiçoes das bolhas
	 * @return
	 */
	private void fillBoard(int numberOfBubbles, int[] numbersColor, int[] positions) {
		for( int color = 0; color < numbersColor.length; ++color) {									// percorre o array de cores
			for( int numberOfColor = 0; numberOfColor < numbersColor[color] ; ++numberOfColor) {
				int randomIndex = randomize.nextInt(numberOfBubbles--);
				int pos = positions[randomIndex]; 												// Posição a ser ocupada
				positions[ randomIndex ] = positions[ numberOfBubbles ];						// guarda a posiçao final
				positions[ numberOfBubbles ] = pos;												// a posiçaofinal passa a ser a nova posiçao
				int  line = pos/getNumberOfColumns(), column = pos%getNumberOfColumns();  		// calcula a linha e coluna da nova posiçao
				switch( color ) {			// verifica a cor da bolha a ser inserida
					case 0: putBubble( line, column, new White(this, line, column)); break;  // cor Branca - numbersColor[0]
					case 1: putBubble( line, column, new Black(this, line, column)); break;  // cor Preta - numbersColor[1]
					default:
						if( color < numbersColor.length-3)		// Cruz - numbersColor[2]..numbersColor[4]
							putBubble( line, column, new CrossBubble(this, line, column, color));
						else									// Diagonais - numbersColor[5]..numbersColor[7]
							putBubble( line, column, new DiagonalBubble(this, line, column, color));
				}
			}
		}
	}
}
