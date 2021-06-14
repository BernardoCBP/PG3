package trab2.grupo2;

import trab2.grupo1.Name;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.io.*;
import java.util.*;
import java.util.function.*;

/**
 * Interface gráfica para visualizar e modificar a agenda.
 */
public class FamiliesFrame extends JFrame {
    private final JFileChooser fileChooser = new JFileChooser( );
    // Os elementos devem ser iterados pela ordem em que são adicionados
    private HashMap<String, SortedSet<Name>> namesPerFamily = new HashMap<>();

    private final JTextArea listArea = new JTextArea( 15, 40 );

    public static class Itens extends
        AbstractMap.SimpleEntry<String, ActionListener> {
        public Itens(String s, ActionListener l){ super(s,l);}
    }

    public final  Itens[] fileMenus = {
            new Itens("load", this::load),
            new Itens("save", this::save),
            new Itens("exit", this::exit)};

    public final  Itens[] namesMenus = {
            new Itens("Add", this::addName),
            new Itens("Remove", this::removeName),
            new Itens("List all", this::listNames)};

    public final  Itens[] listMenus = {
            new Itens("All surnames", this::listAllSurnames),
            new Itens("All names per surname", this::listAllNamesPerSurname),
            new Itens("Names with surname", this::listNamesWithSurname),
            new Itens("Surnames most numerous", this::listFamiliesMostNumerous)};

     public FamiliesFrame(){
        super("Families");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Container cp = getContentPane();

        // Adicionar a TextArea para a listagem, com barra de scroll
        listArea.setBorder( new TitledBorder("list") );
        JScrollPane sp = new JScrollPane(listArea);
        sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        cp.add(sp, BorderLayout.CENTER);

        // << Adicionar os butões >>
        JPanel buttons = new JPanel();
        ((FlowLayout) buttons.getLayout()).setAlignment(FlowLayout.RIGHT);
        // add name
         JButton b = new JButton("add name");
         b.addActionListener(this::addName);
         buttons.add(b);
         // remove name
         b = new JButton("remove name");
         b.addActionListener(this::removeName);
         buttons.add(b);
        //Adicionar os restants botões (adicionar e remover)
        // todo - usar o create para adicionar os três botões.
        cp.add(buttons, BorderLayout.SOUTH);

        //<< Adicionar os menus >>
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(create(new JMenu( "File"), JMenuItem::new, fileMenus));
        menuBar.add(create(new JMenu( "Names"), JMenuItem::new, namesMenus));
        menuBar.add(create(new JMenu( "List"), JMenuItem::new, listMenus));

        setJMenuBar( menuBar );
        pack();
    }

    /**
     * Adiciona ao container os itens descritos no array de itens
     * @param container container
     * @param itens array contendo a descrição dos itens (nome e ação a efetuar).
     * @return o menu instanciado
     */
    protected static <C extends Container> C create(C container, Supplier<? extends AbstractButton> s, Itens ... itens ){
        for ( Itens item: itens)  {
            AbstractButton ab = s.get();
            ab.setText( item.getKey() );
            ab.addActionListener( item.getValue() );
            container.add( ab );
        }
        return container;
    }

    /**
     * Lista a sequencia de elementos  na area de texto, o formato da
     * escrita de cada elemento é dado pela função toList.
     * @param title - titulo a colocar na cercadura da area de texto
     * @param seq sequência de Elementos.
     * @param toList - Função para obter a string a listar
     * @param <E> tipo de cada elemento da sequência
     */
    private <E> void list(String title, Iterable<E> seq, Function<E, String> toList ){
        ((TitledBorder)listArea.getBorder()).setTitle( title );
        seq.forEach( n -> listArea.append( toList.apply(n) + "\n") );
     }

    /***************************************************
     *  Métodos associados aos itens do menu "Names"
     *
     ***************************************************/

    /**
     * Método chamado quando é selecionado o item "List names".
     * Lista os nomes completos que constam no contentor associativo namesPerFamilies.
     * @param actionEvent
     */
    private void listNames(ActionEvent actionEvent) {
        //this.list("List of names:", namesPerFamily.keySet(), Name::getFullName );
    }

    /**
     * Método chamado quando é premido o botão "add name".
     * Coloca visivel uma janela de dialogo correspondente à inserção do nome.
     * Adiciona ao contentor associativo namesPerFamily o nome.
     * Lista todos os nomes após a adição
     * @param actionEvent evento do action listener.
     */
    private void addName(ActionEvent actionEvent) {
        String name = JOptionPane.showInputDialog(this, "Name?", "Add name", JOptionPane.QUESTION_MESSAGE);
        if ( name != null && !name.isBlank())
            try {
                //todo - adicionar o name ao contentor associativo namesPerFamily  - usar o método da alinea 2.

            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
            }
    }

    /**
     * Método chamado quando é premido o botão "clear name".
     * Remove do contentor associativo namesPerFamily todos os nomes.
     * @param actionEvent evento do action listener.
     */
    private void clearNames(ActionEvent actionEvent) {
        namesPerFamily.clear();
    }

    /**
     * Método chamado quando é premido o botão "add name".
     * Coloca visivel uma janela de dialogo correspondente à inserção do nome.
     * Remove do contentor associativo namesPerFamily o nome, caso a familia só
     * contenha este membro, remove o apelido do contentor.
     * Lista todos os nomes após a remoção.
     * @param actionEvent evento do action listener.
     */
    private void removeName(ActionEvent actionEvent) {
        //todo
    }

    /***************************************************
     *  Métodos associados aos itens do menu "File"
     *
     ***************************************************/
   /**
     * Coloca visivel a janela de dialogo para que seja selecionado
     * o ficheiro. Abre o ficheiro selecionado e escreve no ficheiro
     * os nomes completos que constam no contentor associativo namesPerFamilies.
     * */
    private void save(ActionEvent actionEvent) {
        fileChooser.setCurrentDirectory(new File("."));
        if ( JFileChooser.APPROVE_OPTION == fileChooser.showSaveDialog(this) )
            try ( PrintWriter pw = new PrintWriter(fileChooser.getSelectedFile())) {
                namesPerFamily.values().forEach( tree -> tree.forEach( name -> pw.println( name.getFullName())));
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Error file: " + e.getMessage());
            }
    }

    /**
     * Coloca visivel a janela de dialogo para que seja selecionado
     * o ficheiro. Abre o ficheiro selecionado e evoca o método families
     * para acrescentar ao contentor associativo namesPerFamilies os
     * nomes que constam no ficheiro.
     * @param actionEvent
     */
    private void load(ActionEvent actionEvent) {
        fileChooser.setCurrentDirectory(new File("."));
        if ( JFileChooser.APPROVE_OPTION == fileChooser.showOpenDialog(this) )
            try ( BufferedReader rd = new BufferedReader( new FileReader(fileChooser.getSelectedFile()) ) ) {
                //namesPerFamily = Families.families(rd, TreeMap::new, TreeSet::new);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Error file: " + e.getMessage());
            }
    }

    /**
     * Pergunta ao utilizador se quer salvar em ficheiro os nomes.
     * Caso a resposta seja afirmativa evoca o método save.
     * @param actionEvent
     */
    private void exit( ActionEvent actionEvent ) {
        //todo
    }

    /***************************************************
     *  Métodos associados aos itens do menu "List"
     *
     ***************************************************/
    /**
     * Método chamado quando é selecionado o item "all surnames".
     * Lista os apelidos que constam no contentor associativo namesPerFamilies.
     * @param actionEvent
     */
    private void listAllSurnames(ActionEvent actionEvent) {
        //todo - usar o método list
    }

    /**
     * Método chamado quando é selecionado o item "All names per surname".
     * Lista todos as familias. Por cada família a primeira linha contém 
     * o apelido e o número de membros, as linhas seguintes contêm os nomes
     * dos membros antecedidos do carácter tab.
     * @param actionEvent
     */
    private void listAllNamesPerSurname( ActionEvent actionEvent ) {
        //todo - USAR como auxiliar o método printFamilies
    }

    /**
     * Método chamado quando é selecionado o item "Names with surname".
     * Coloca visivel uma janela de dialogo para a inserção do apelido.
     * Após ter o apelido, lista os primeiros nomes dos nomes que contêm
     * este apelido.
     * @param actionEvent
     */
    private void listNamesWithSurname(ActionEvent actionEvent) {
        //todo - usar o método list
    }

    /**
     * Método chamado quando é selecionado o item "Families most numerous".
     * Lista os apelidos das familis que são mais numerosas.
     * @param actionEvent
     */
    private void listFamiliesMostNumerous(ActionEvent actionEvent) {
        //todo - usar o método greaterFamilies e list
    }

    public static void main(String[] args) {
        new FamiliesFrame().setVisible( true );
    }
}
