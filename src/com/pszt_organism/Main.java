package com.pszt_organism;


import javax.swing.*;
import java.awt.*;
import java.util.HashSet;
import java.util.Random;

public class Main {


    public static void main( String [] args)
    {


        JFrame frame = new JFrame("PSZT projekt organizmu");
        GUI gui = new GUI();
        frame.add(gui);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(600,500));
        frame.setVisible(true);
        frame.setResizable(false);

    }
}
