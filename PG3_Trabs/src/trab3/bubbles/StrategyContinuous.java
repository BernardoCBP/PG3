package trab3.bubbles;

import trab3.bubbles.pieces.Bubble;
/**
 * OPCIONAL tem que adicionar ao Game o método addColumn
 *          Sempre que uma coluna é libertada é adicionada uma nova
 *          coluna à direita.
 **/

public class StrategyContinuous implements Strategy {

	private final Strategy strategy;

	public StrategyContinuous(Strategy s)       { strategy = s;                       }
	public void add(Bubble b)                   { strategy.add(b);                    }
	public int select(Bubble origin)            { return strategy.select(origin);     }
	public int removeSelected(Board b)          { return strategy.removeSelected(b);  }

	public void freeColumn(Board board, int column) {
		strategy.freeColumn(board,column);
//		board.addColumn();
	}
	public String toString() { return "mega" + strategy; }

}
