package trab2.grupo1;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Writer;
import java.util.*;


public class NamesFrame extends JFrame {

    public static final int WIDTH = 200;
    public static final int HEIGHT = 200;

    //Set<String> surnames = Set.of("Sousa", "Veiga");
    Set<String> surnames = new TreeSet<>();

    private final JTextArea listArea = new JTextArea( 15, 40 );
    private final JTextField tfn = new JTextField("", 30 );
    private final JTextField tfs = new JTextField("", 20 );

    public NamesFrame() {
        super("Names");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);

        Container cp = this.getContentPane();
        /*********** north ***************/

        JPanel north = new JPanel(new BorderLayout());
        north.setBorder( new TitledBorder("pathname") );

        north.add(tfn);

        /*********** BUTTON ***************/
        // list names
        JButton bn = new JButton("list names");
        bn.addActionListener(this::listNames);

        north.add(bn, BorderLayout.EAST);

        cp.add(north, BorderLayout.NORTH);

        /*********** CENTER ***************/

        listArea.setBorder( new TitledBorder("information") );
        JScrollPane sp = new JScrollPane(listArea);
        cp.add(sp, BorderLayout.CENTER);

        /*********** SOUTH ***************/

        JPanel south = new JPanel();
        ((FlowLayout) south.getLayout()).setAlignment(FlowLayout.LEFT);
        south.setBorder( new TitledBorder("surnames management") );

        // surname
        tfs.setBorder( new TitledBorder("surname") );
        south.add(tfs);

        /*********** BUTTONS ***************/
        JPanel buttons = new JPanel();
        // add
        JButton bs = new JButton("add");
        bs.addActionListener(this::add);
        buttons.add(bs);
        // remove
        bs = new JButton("remove");
        bs.addActionListener(this::remove);
        buttons.add(bs);
        // list
        bs = new JButton("list");
        bs.addActionListener(this::list);
        buttons.add(bs);

        south.add(buttons);
        cp.add(south, BorderLayout.SOUTH);

        pack();
    }


    /***************************************************
     *  Métodos associados aos itens do surname management
     *
     ***************************************************/
    //C:\Users\Berna\OneDrive - Instituto Superior de Engenharia de Lisboa\Old PC\Documents\Faculdade\PG3\PG3\PG3_Trabs/Names.txt
    private void listNames(ActionEvent actionEvent)  {
        try {
            String filename = tfn.getText();
            Names.forEachIf(filename, (name, integer) -> { listArea.append( integer + name.getFullName() ); }, surnames );
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error file: " + e.getMessage());
        }
    }

    private void add(ActionEvent actionEvent) {
        String surname = tfs.getText();
        surnames.add(surname);
        tfs.setText("");
    }

    private void remove(ActionEvent actionEvent) {
        String surname = tfs.getText();
        surnames.remove(surname);
        tfs.setText("");
    }

    private void list(ActionEvent actionEvent) {
        listArea.setText("");
        surnames.forEach( (surname) -> { listArea.append(surname + "\n"); } );
    }

    public static void main(String[] args) {
        new NamesFrame().setVisible( true);
    }
}


