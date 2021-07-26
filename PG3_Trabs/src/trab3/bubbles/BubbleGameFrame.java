package trab3.bubbles;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
import javax.swing.Timer;

import trab3.bubbles.pieces.Bubble;

public class BubbleGameFrame extends JFrame implements GameListener {
	public static final int GRID_WIDTH = 35;
	public static final Color HOLE_COLOR = Color.CYAN;
	protected final JPanel board;
	protected Game game;

	private final Map<String, Player> players = new HashMap<>();
	private Player currPlayer = new Player();
	private final JButton b = new JButton("start");

	private final ValuesPanel currPanel;
	private final ValuesPanel bestPanel;

	JFileChooser fileChooser = new JFileChooser( );

	public static class Itens extends
			java.util.AbstractMap.SimpleEntry<String, ActionListener> {
		public Itens(String title, ActionListener action) { super(title,action); }
		public String getText()           { return getKey();   }
		public ActionListener getAction() { return getValue(); }
	}
	public final  Itens[] actionsMenus = {
			new Itens("start", e -> game.start() ),
			new Itens("stop", e -> game.stop() ),
			new Itens("how to play", this::howToPlay),
			new Itens("exit", this::saveOnExit ) };

	public final  Itens[] currPlayerMenus = {
			new Itens("new player", this::newPlayer),
			new Itens("statistics", this::statistics),
			new Itens("clear", e -> currPlayer.getStatistics().clear() ) };

	public final  Itens[] PlayersMenus = {
			new Itens("save",  this::save),
			new Itens("load", this::load),
			new Itens("top 10", this::showTop10 ) };


	public final  Itens[] strategyMenus = {
			new Itens("gravitational", e -> game.start()),
			new Itens("shifter", e -> game.start()) } ;

	public BubbleGameFrame(String title, Game g, Strategy s ) {
		super(title);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		Container cp = this.getContentPane();

		game = g;
		game.setStrategy( s );
		game.addListener(this );
		addWindowListener( new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				ActionEvent windowActionEvent = new ActionEvent(e.getWindow(), 1, "Exit");
				saveOnExit(windowActionEvent);
			}
		} );

		cp.add( board = createBoard( g ) );

		JMenuBar mb= new JMenuBar();
		mb.add( addItens("Game", actionsMenus) );
		mb.add( addItens("Current player", currPlayerMenus) );
		mb.add( addItens("Players", PlayersMenus) );
		mb.add( addItens("Strategy", strategyMenus) );
		setJMenuBar( mb );

		JPanel north = new JPanel(new GridLayout(1, 2));
		north.add( currPanel = new ValuesPanel("Current game of " + currPlayer.getName()), BorderLayout.NORTH );
		north.add( bestPanel = new ValuesPanel("Best values") );
		cp.add(north, BorderLayout.NORTH);

		b.addActionListener(this::buttonStartStop);
		cp.add(b, BorderLayout.SOUTH);

		pack();
		setResizable(false);
	}

	// <<  Métodos auxiliares >>
	private static JMenu addItens(String title, Itens... itens ){
		JMenu menu = new JMenu(title);
		for ( Itens item: itens) {
			JMenuItem i = new JMenuItem( item.getText() );
			i.addActionListener( item.getAction() );
			menu.add( i );
		}
		return menu;
	}

	private static JPanel createBoard( Game game ) {
		JPanel p = new JPanel( new GridLayout(game.getNumberOfLines(),game.getNumberOfColumns()) );
		for (int i= 0; i < game.getNumberOfLines()*game.getNumberOfColumns(); ++i ) {
			JButton b= new JButton();
			b.setPreferredSize(new Dimension(GRID_WIDTH, GRID_WIDTH));
			b.setBackground(HOLE_COLOR);
			int pos = i;
			b.addActionListener( e -> game.select(pos/game.getNumberOfColumns(), pos%game.getNumberOfColumns()) );
			p.add(b);
		}
		return p;
	}

	// << Implementação da inteface BubbleListener */
	Color[] colors = { HOLE_COLOR, Color.WHITE, Color.BLACK,
	                   Color.RED, Color.GREEN, Color.BLUE, 
	                   Color.YELLOW, Color.PINK, Color.MAGENTA };

	// Obter o componente que se encontra em determinada linha/coluna
	protected JComponent getComponent(int line, int col ) {
		return (JComponent)board.getComponent(line*game.getNumberOfColumns() + col);
	}
	public void selected( Bubble b )
		{ getComponent(b.getLine(), b.getColumn()).setBackground( Color.GRAY );             }
	public void unselected( Bubble p )
		{ getComponent(p.getLine(), p.getColumn()).setBackground(colors[p.getColor()+1]);   }

	// << Implementação da inteface GameListener */
	public void scoreChange(Game g ) {
		Score s = g.getScore();
		currPanel.getScore().setText(String.valueOf(s.score));
		currPanel.getBubbles().setText(String.valueOf(s.bubbles));
	}

	public void gameStart(Game g)    {

		Score s = g.getScore();
		String msg = String.format("Time is %d, start with %d bubbles and %d points", s.time, s.bubbles, s.score);
		JOptionPane.showMessageDialog( this, msg, "START GAME", JOptionPane.PLAIN_MESSAGE);
		// Atualizar os valores
		currPanel.getScore().setText(String.valueOf(s.score));
		currPanel.getBubbles().setText(String.valueOf(s.bubbles));
		this.showBestValues();
		new Timer(1000, timer).start();
		b.setText("stop");
	}

	public void gameStop(Game g)     {

		Score s = g.getScore();
		String msg = String.format("In %d seconds remain %d bubbles with %d points", s.time, s.bubbles, s.score);
		JOptionPane.showMessageDialog( this, msg, "GAME OVER", JOptionPane.INFORMATION_MESSAGE);
		currPlayer.getStatistics().addScore(s, game.getNumberOfLines()*game.getNumberOfColumns());
		if( s.bubbles < currPlayer.getBestScore().bubbles || (s.bubbles == currPlayer.getBestScore().bubbles && s.score >= currPlayer.getBestScore().score) ) { //pode ser time
			currPlayer.setBestScore(s);
			this.showBestValues();
		}
		b.setText("start");
	}

	//Metodo utilizado para atualizar o painel dos melhores valores
	public void showBestValues() {
		bestPanel.getTimer().setText(String.valueOf(String.format("%02d:%02d", (currPlayer.getBestScore().time % 3600) / 60, (currPlayer.getBestScore().time % 60))));
		bestPanel.getBubbles().setText(String.valueOf(currPlayer.getBestScore().bubbles));
		bestPanel.getScore().setText(String.valueOf(currPlayer.getBestScore().score));
	}

	//ActionListener usado para contar o tempo usando o metodo getTime()
	ActionListener timer = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			int elapsedTime = game.getTime();
			String time = String.format("%02d:%02d", (elapsedTime % 3600) / 60, (elapsedTime % 60));
			currPanel.getTimer().setText(time);
		}
	};

	//É criada uma string com as regras contidas no ficheiro bubbles.txt
	private void howToPlay(ActionEvent actionEvent)  {
		try( BufferedReader rd  = new BufferedReader(new FileReader("bubbles.txt")) ) {
			String line;
			StringBuilder sb = new StringBuilder();
			while( (line = rd.readLine()) != null ) {
				sb.append(line).append("\n");
			}
			JOptionPane.showMessageDialog( this, sb.toString(), "HOW TO PLAY", JOptionPane.INFORMATION_MESSAGE);
		} catch(IOException e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
		}
	}

	// Implementação dos metodos do menu current player

	private void newPlayer(ActionEvent actionEvent) {
		String name = JOptionPane.showInputDialog(this, "Name?", "Add name", JOptionPane.QUESTION_MESSAGE);
		if ( name != null && !name.isBlank())
			try {
				currPanel.setBorder( BorderFactory.createTitledBorder("Current game of " + name)); //mudar o titulo do painel current
				Player elem;
				if( (elem = players.get(name)) == null ) {         //se o jogador não existir
					players.put(name, elem = new Player(name));    //é adicionado ao mapa de players
				}
				currPlayer = elem;                                 //o current player passa a ser o inserido
				game.start();                                      //é começado um novo jogo
			} catch (Exception e) {
				JOptionPane.showMessageDialog(this, e.getMessage());
			}
	}

	private void statistics(ActionEvent actionEvent)  {
		JOptionPane.showMessageDialog( this, currPlayer.getStatistics().toString(),
										"STATISTICS", JOptionPane.INFORMATION_MESSAGE);
	}

	private void save(ActionEvent actionEvent) {
		JFileChooser fileChooser = new JFileChooser( );
		fileChooser.setCurrentDirectory(new File("."));
		if (JFileChooser.APPROVE_OPTION == fileChooser.showSaveDialog(null)) {
			Player.saveStatistics(players, fileChooser.getSelectedFile());
		}
	}

	private void saveOnExit(ActionEvent actionEvent) {

		int PromptResult = JOptionPane.showConfirmDialog(null,"Would you like to save your changes?",
				"Warning",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);
		if(PromptResult == JOptionPane.YES_OPTION) {
			save(actionEvent);
			System.exit(0);
		}
		else if( PromptResult == JOptionPane.NO_OPTION ) {
			System.exit(0);
		}

	}

	private void load(ActionEvent actionEvent) {

		fileChooser.setCurrentDirectory(new File("."));
		if ( JFileChooser.APPROVE_OPTION == fileChooser.showOpenDialog(null) ) {
			Player.loadStatistics(players, fileChooser.getSelectedFile());
		}
	}

	private void showTop10(ActionEvent actionEvent) {
		JOptionPane.showMessageDialog( this, Player.getTopX(players, 10),
				"LEADERBOARD", JOptionPane.INFORMATION_MESSAGE);
	}

	private void buttonStartStop(ActionEvent actionEvent) {
		if( b.getText().compareTo("start") == 0 ) {
			game.start();
			b.setText("stop");
		} else if( b.getText().compareTo("stop") == 0) {
			game.stop();
			b.setText("start");
		}
	}

	public static void main(String[] args) {
		new BubbleGameFrame("Bubbles",
				                 new BubbleGame(14,12),
				                 new StrategyGravitational() ).setVisible(true);
	}
}