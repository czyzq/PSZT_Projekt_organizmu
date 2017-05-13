package com.pszt_organism;


import javax.swing.*;
import java.awt.*;

public class Main {

    public static Object[][] getMatrixOrg() {
        return matrixOrg;
    }
    public static Object valueMatrix(int i, int j)
    {
        return matrixOrg[i][j];
    }

    private static Object [][] matrixOrg = new Object [8][8];
    private int [][] mixedOrganisms; // komórki które posiadają sąsiadów (x,y)
    private int [][][] sortedOrganisms; // z,x,y (z-> nr organizmu, (x,y) -> jego polozenie na macierzy)
    private static void addValues()
    {
        matrixOrg = new Object[][]{
                {0, 1, 1, 1, 0, 1, 0, 1},
                {0, 0, 0, 1, 0, 0, 0, 0},
                {0, 1, 1, 0, 0, 0, 0, 0},
                {0, 1, 0, 0, 0, 1, 0, 1},
                {0, 0, 0, 1, 0, 0, 1, 1},
                {0, 1, 1, 1, 0, 0, 0, 1},
                {0, 1, 0, 1, 0, 0, 0, 1},
                {0, 1, 1, 1, 0, 1, 0, 1}};
    }
   /* private void findOrganism(int N) //algoryzm szukania organizmu
    {
        //TODO: skonczyc algorytm
       //i,j -> x,y
        int n=0;
        for(int j=0;j<N;j++)
        {
            for (int i=0;i<N;i++)
            {
                if(matrixOrg[i][j]==1)
                {
                    mixedOrganisms[n][0]=i;
                    mixedOrganisms[n][1]=j;
                    n++;
                }
            }
        }

    }*/
    private void sorting() // odpowiedzialna za posortownaie organizmów
    {

    }
    public static void main( String [] args)
    {
        addValues();
        JFrame frame = new JFrame("PSZT projekt organizmu");
        GUI gui = new GUI();
        frame.add(gui);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(600,500));
        frame.setVisible(true);
        frame.setResizable(false);





    }
}
