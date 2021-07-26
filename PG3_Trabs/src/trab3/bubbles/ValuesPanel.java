package trab3.bubbles;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class ValuesPanel extends JPanel {

    private JTextField newJTextField(String title ) {
        JTextField tf = new JTextField(5);
        tf.setBorder( new TitledBorder(title) );
        tf.setBackground(UIManager.getColor("Panel.background"));
        tf.setHorizontalAlignment(JTextField.CENTER);
        tf.setEditable(false);
        return tf;
    }

    JTextField tfTime, tfBubbles, tfScore;

    public ValuesPanel(String title) {
        Border border = BorderFactory.createTitledBorder(title);
        this.setBorder(border);
        this.setLayout(new FlowLayout());

        this.add( tfTime = newJTextField("time") );
        this.add( tfBubbles = newJTextField("bubbles") );
        this.add( tfScore = newJTextField("score") );
    }

    public final JTextField getTimer()   { return tfTime;    }
    public final JTextField getBubbles() { return tfBubbles; }
    public final JTextField getScore()   { return tfScore;   }

}
