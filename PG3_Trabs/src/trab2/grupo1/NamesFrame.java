package trab2.grupo1;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class NamesFrame extends JFrame {

    public static final int WIDTH = 200;
    public static final int HEIGHT = 200;
    private final JTextArea listArea = new JTextArea( 15, 40 );

    public NamesFrame() {
        super("Names");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);

        Container cp = this.getContentPane();
        /*********** north ***************/

        JPanel north = new JPanel();
        north.setBorder( new TitledBorder("pathname") );
        JTextField tfn = new JTextField("", 12 );
        north.add(tfn);
        cp.add(north, BorderLayout.NORTH);

        /*********** CENTER ***************/

        JPanel center = new JPanel();
        JTextArea info = new JTextArea();
        center.setBorder( new TitledBorder("information") );
        center.add(info);

        cp.add(center, BorderLayout.CENTER);

        /*********** SOUTH ***************/

        JPanel south = new JPanel(new GridLayout(1,3));
        south.setBorder( new TitledBorder("surnames management") );
        JTextField tfs = new JTextField("", 12 );
        tfs.setBorder( new TitledBorder("surnames") );

        JPanel buttons = new JPanel();
        JButton b1 = new JButton("add");
        buttons.add(b1);

        south.add( tfs );
        south.add( buttons );
        cp.add(south, BorderLayout.SOUTH);

        cp.add(south, BorderLayout.SOUTH);
        pack();
    }

    public static void main(String[] args) {
        new NamesFrame().setVisible( true);
    }
}


