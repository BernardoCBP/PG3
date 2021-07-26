package trab3.bubbles;

import java.util.*;

import trab3.bubbles.pieces.Bubble;

public class StrategyGravitational implements Strategy {

	protected final List<Bubble> bubbles = new ArrayList<Bubble>();		// Conjunto de bolhas selecionadas
	private final int minimum;										// Numero de bolhas minimas do conjunto

	public StrategyGravitational(int min) 	{ this.minimum = min;	}
	public StrategyGravitational() 			{ this(2);			}

	public int numberOfBubbles() { return bubbles.size(); 	}
	public void add(Bubble p)    { bubbles.add(p); 			}

	/**
	 * todo - explicar o algoritmo e comentar o código *** DONE
	 * O metodo recebe uma bolha de origem, atraves da qual serão selecionadas novas bolhas.
	 * Caso já existam bolhas selecionadas, então essas são descelecionadas para se poder
	 * selecionar um novo conjunto de bolhas.
	 * A partir da bolhas origem sao selecionadas novas bolhas (isto depende do tipo de bolha).
	 * Caso o numero de bolhas selecionadas seja inferior ao minimo permitido, então as bolhas
	 * são descelecionadas.
	 * Retorna o numero de bolhas que foram selecionadas.
	 *
	 * @param origin bolha que foi selecionada primeiro
	 */
	public int select( Bubble origin ) {
		if( !bubbles.isEmpty() ) 		{ unselect(); }	 // caso o array bubblues nao esteja vazio, entao desceleciona as bolhas
		origin.select( );								 // seleciona as bolhas a partir de uma bolha origem
		if(numberOfBubbles() < minimum) { unselect(); }	 // caso o numero de bolhas selecionadas seja menor que o minimo, desceleciona
		return numberOfBubbles();						 // retorna o numero de bolhas selecionadas
	}

	/**
	 * todo - explicar o algoritmo e comentar o código *** DONE
	 * É guardado o número de bolhas selecionadas.
	 * De seguida é construido um comparador para posteriormente
	 * se fazer a comparação entre as bubbles.
	 * A lista de bubbles é ordenada segundo o comparador:
	 * - as bolhas são comparadas em função da coluna em que encontram
	 * - caso se encontrem na mesma coluna, sao comparadas em funçao da
	 * 	 linha em que se encontrem.
	 * No final é aplicada a estrategia de jogo ao tabuleiro e
	 * no final é retornado o numero de bolhas selecionadas.
	 *
	 * @param board tabuleiro
	 */
	public int removeSelected(Board board ) {
		int n = numberOfBubbles();
		Comparator<Bubble> cmp = ( b1, b2 ) -> {	// Ordena nas colunas e para a mesma coluna das linhas maiores para as menores
			int res = b1.getColumn()-b2.getColumn();	// res é a diferença entre as colunas
			if( res == 0 ) {							// caso estejam na mesma coluna
				res = b2.getLine()-b1.getLine();		// res é a diferença entre as linhas
			}
			return res;
		};
		Collections.sort( bubbles, cmp );				// ordena a lista de bolhas
		strategy( board );								// é aplicada a estrategia de jogo ao tabuleiro
		return n;
	}

	/**
	 * todo - explicar o algoritmo e comentar o código *** DONE
	 * É guardado o numero de bolhas selecionadas. A lista de bolhas selecionadas é percorrida
	 * A linha da bolha selecionada é guardada. São percorridas todas as linhas acima da bolha
	 * selecionada. Para cada linha acima a bolha acima da selecionada é guardada.
	 * Caso a bolha acima nao esteja selecionada, entao essa é movida para a posiçao abaixo,
	 * substituindo a bolha selecionada. Caso a bolha acima esteja selecionada é entao descelecionada.
	 * São percorridas todas as linhas dessa mesma coluna e são susbtituidas as bolhas selecionadas por um buraco.
	 * No final a lista de bolhas selecionadas é limpa.
	 *
	 * @param board tabuleiro
	 */
	protected void strategy(Board board ) {
		int n = bubbles.size();													// guardar o numero de bubbles
		for( Bubble bubbleRemove: bubbles) {									// percorrer a lista de bubbles selecionadas
			if(bubbleRemove.isSelected()) {										// caso a bolha esteja selecionada
				int putLine = bubbleRemove.getLine(), line;						// obter a linha da bolha selecionada
				for(line = putLine - 1; line >= 0; --line) {					// percorre as linhas acima da bolha selecionada
					Bubble bubbleMove = board.getBubble(line, bubbleRemove.getColumn());	// guarda a bolha acima da selecionada
					if (!bubbleMove.isSelected()) {								// caso a bolha acima nao esteja selecionada
						if(bubbleMove.moveTo(putLine, bubbleRemove.getColumn()))			// move a bolha para a posição abaixo
							--putLine;
						else break;
					} else														// caso a bolha acima esteja selecionada
						bubbleMove.unselect();									// entao desceleciona
				}
				for(++line; line <= putLine; ++line) {							// percorre as linhas em ordem reversa
					board.putHole(line, bubbleRemove.getColumn());				// e coloca um buraco nas bolhas dessa coluna
				}
			}
		}
		bubbles.clear(); 														// limpa a lista de bolhas selecioandas
	}

	/**
	 * todo - explicar o algoritmo e comentar o código *** DONE
	 * O metodo percorre todas as linhas de uma coluna e para cada linha, move a bolha
	 * da coluna seguinte para a coluna atual. No inicio é criado um buraco na posicao
	 * atual e no final esse buraco é movido para a ultima coluna.
	 *
	 * @param board tabuleiro
	 * @param column coluna a libertar
	 */
	public void freeColumn(Board board, int column ) {
		if( column != board.getNumberOfColumns() - 1 ) {			// verifica se não é a ultima coluna
			for(int l = 0; l < board.getNumberOfLines() ; ++l) {	// percorrer as linhas de uma coluna
				Bubble hole  = board.getBubble(l, column);			// guardar uma bolha
				int c = column;
				while( board.getBubble(l, c+1).moveTo( l, c) ) {	// move a bolha da coluna seguinte para a coluna atual
					++c;											// enquanto houver coluna seguinte
				}
				hole.moveTo( l, c );								// coloca um buraco na ultima coluna
			}
		}
	}

	/**
	 * Desmarca o conjunto de bolhas selecionadas.
	 * @return o número de bolhas que foram desmarcadas
	 */
	private int unselect() {
														// Guarda o número de bolhas para poder retornar
		int numberSelected = numberOfBubbles();			// no fim o número de bolhas desmarcadas

		bubbles.forEach( Bubble::unselect );			// Desmarca cada uma das bolhas do conjunto evocando
														// o método unselect sobre cada bolha

		bubbles.clear();								// Assinala que o conjunto está vazio
		return numberSelected;
	}

	// Identificação desta estratégia
	public String toString() { return "gravitational"; }

}
