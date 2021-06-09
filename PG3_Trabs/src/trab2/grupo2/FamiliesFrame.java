package trab2.grupo2;

import javax.swing.*;
import java.awt.*;

public class FamiliesFrame extends JFrame { // TODO: 27/05/2021 -> acabar Class

    public FamiliesFrame() {
        setDefaultCloseOperation( EXIT_ON_CLOSE );
        setSize(300, 200);
        Container cp = getContentPane();
    }

    public static void main(String[] args) {
        new FamiliesFrame().setVisible( true );
        System.out.println("Terminated.");
    }

}
