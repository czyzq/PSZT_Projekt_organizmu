package com.pszt_organism;
import javax.swing.*;
import java.awt.*;
import java.util.List;

import static java.lang.Integer.parseInt;


public class Worker extends SwingWorker<Object, Object> {

    private final int N,M,Mi,Lambda;
    private final String Crossing,Algorithms;
    final JLabel labelBestValue, labelIteration;
    final JTable jt;

    public  Worker(final int N,final int M,final int Mi,final int Lambda,final String Crossing,final String Algorithms,
                   final JLabel labelIteration,final JLabel labelBestValue,final JTable jTable){
        this.N=N;
        this.M=M;
        this.Mi=Mi;
        this.Lambda=Lambda;
        this.Crossing=Crossing;
        this.Algorithms=Algorithms;
        this.labelIteration=labelIteration;
        this.labelBestValue=labelBestValue;
        this.jt=jTable;
    }

    protected Object doInBackground() throws Exception {
        Organisms org = new Organisms(N,M,Mi);
        List<int[][]> organizmy=org.doGUI();
        org.print_deb();
        org.init();
        int counter=0;
        double max=0.0;
        if(Algorithms.equals("λ+ μ")) {
            MiPlusLambda ag = new MiPlusLambda(N, M, Mi, Lambda, Crossing);
            //testy
            ag.setListaPopulacjaMi(org.getListBitVectors());
            while (ag.stop(org.getBestValueMap()) != 100) {
                ag.dodajPotomstwoR(ag.losujLambda()); // krzyzowanie i dodwwanie populacji potomnej
                org.listToMap(ag.getListaPopulacjaMi());
                ag.setListaPopulacjaMi(ag.miZrodzicowIpotomstwa(org.sortByValue(org.getMapVectFunc())));
                if(org.getBestValueMap()==max)
                    counter++;
                else
                    counter=0;
                if(org.getBestValueMap()>max)
                    max=org.getBestValueMap();

                labelIteration.setText("Liczba iteracji bez lepszego osobnika: " + counter);
                labelBestValue.setText("Oto najlepsza uzyskana wartość: " + max);
                print_deb(organizmy);
            }
            System.out.println("\n oto pierwsza wartosc " + org.getBestValueMap());
        }
        else{
            MiLambda ag = new MiLambda(N,M,Mi,Lambda,Crossing);
            //testy
            ag.setListaPopulacjaMi(org.getListBitVectors());
            while (ag.stop(org.getBestValueMap()) != 100) {
                ag.dodajPotomstwoR(ag.losujLambda()); // krzyzowanie i dodwwanie populacji potomnej
                org.listToMap(ag.getListaPopulacjaR());
                ag.setListaPopulacjaMi(ag.miZrodzicowIpotomstwa(org.sortByValue(org.getMapVectFunc())));
                if(org.getBestValueMap()==max)
                    counter++;
                else
                    counter=0;
                if(org.getBestValueMap()>max)
                    max=org.getBestValueMap();

                labelIteration.setText("Liczba iteracji bez lepszego osobnika: " + counter);
                labelBestValue.setText("Oto najlepsza uzyskana wartość: " + max);
                print_deb(organizmy);
            }
            System.out.println("\n oto pierwsza wartosc " + org.getBestValueMap());
        }


        /*
        for (int index = 0; index < 100; index++) {
            int progress = Math.round(((float) index / 100f) * 10f);
            setProgress(progress);

            Thread.sleep(10);
        }
        */
        return null;
    }

    public void print_deb(List<int[][]> lista)
    {
        int[][] o= lista.get(0);
            for (int j=0; j<o.length;j++)
        {
            for (int i=0; i<o[j].length;i++) {
                jt.setValueAt(o[i][j],j,i);
                if(parseInt(String.valueOf(jt.getValueAt(i,j)))!=0)
                jt.getCellRenderer(i,j);
                System.out.print(o[i][j]);
            }
            System.out.println();
        }
        System.out.println("\n");

    }
}
