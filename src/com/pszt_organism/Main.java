package com.pszt_organism;


import javax.swing.*;
import java.awt.*;


public class Main {


    public static void main( String [] args)
    {


        JFrame frame = new JFrame("PSZT projekt organizmu");
        GUI gui = new GUI();
        frame.add(gui);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(1100,600));
        frame.setVisible(true);
        frame.setResizable(false);

    }
}
