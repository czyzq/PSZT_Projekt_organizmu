package com.pszt_organism;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;

import static java.lang.Integer.parseInt;

/**
 * Created by konrad on 02.05.2017.
 */
public class GUI extends JPanel{

    private JTable jt;
    private JScrollPane jsp;
    private JButton buttonStart;
    private JLabel labelAlg,labelN, labelM, labelMi,labelLambda;
    private JFormattedTextField paramM;
    private JComboBox algEvo,paramN, paramMi,paramLambda;
    private  String[] algoritmList = {" ","λ+ μ","λ, μ"};
    private String[] nList ={"2","3","4","5"};
    private String[] miList={"2","10","20","50","100"};
    private String[] lambdaList={"5","10"};
    private Object[][] tableData;
    private String[] colNames;
    private TableModel model;

    public GUI()
    {
        setLayout(null);



        modelMethods(8);
        jt = new JTable(model);

        setBackground(Color.white);
        //setSize(new Dimension(500,500));
       // jt = new JTable();
        jt.setBackground(Color.white);
        jt.setPreferredScrollableViewportSize(new Dimension(400, 300));
        jt.setFillsViewportHeight(true);
        jsp = new JScrollPane(jt);


        jsp.setBounds(0,0,400,400);
        add(jsp);

        buttonStart = new JButton("Start");
        buttonStart.setBounds(460,425,70,20);
        add(buttonStart);

        algEvo=new JComboBox(algoritmList);
        algEvo.setSelectedIndex(0);
        ((JLabel)algEvo.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER); // zcentrowanie stringa
        algEvo.setBounds(440,25,120,20);
        add(algEvo);

        labelAlg = new JLabel("Algorytmy");
        labelAlg.setBounds(440,3,120,20);
        labelAlg.setHorizontalAlignment(SwingConstants.CENTER);
        add(labelAlg);

        labelN = new JLabel("Parametr N (2^x)");
        labelN.setBounds(440,50,120,20);
        labelN.setHorizontalAlignment(SwingConstants.CENTER);
        add(labelN);


        NumberFormat format = NumberFormat.getInstance();
        NumberFormatter formatter = new NumberFormatter(format);
        formatter.setValueClass(Integer.class);
        formatter.setMinimum(0);
        formatter.setMaximum(Integer.MAX_VALUE);
        formatter.setAllowsInvalid(false);
        //paramN = new JFormattedTextField(formatter);
        //
        //paramN.setHorizontalAlignment(SwingConstants.CENTER);
        //add(paramN);
        paramN=new JComboBox(nList);
        paramN.setSelectedIndex(0);
        ((JLabel)paramN.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER); // zcentrowanie stringa
        paramN.setBounds(440,75,120,20);
        add(paramN);

        labelM = new JLabel("M");
        labelM.setBounds(440,100,120,20);
        labelM.setHorizontalAlignment(SwingConstants.CENTER);
        add(labelM);

        paramM = new JFormattedTextField(formatter);
        paramM.setBounds(440,125,120,20);
        paramM.setHorizontalAlignment(SwingConstants.CENTER);
        paramM.setText("1");
        add(paramM);

        labelMi = new JLabel("μ");
        labelMi.setBounds(440,150,120,20);
        labelMi.setHorizontalAlignment(SwingConstants.CENTER);
        add(labelMi);

        paramMi = new JComboBox(miList);
        paramMi.setSelectedIndex(0);
        ((JLabel)paramMi.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER); // zcentrowanie stringa
        paramMi.setBounds(440,175,120,20);
        add(paramMi);

        labelLambda = new JLabel("Lambda");
        labelLambda.setBounds(440,200,120,20);
        labelLambda.setHorizontalAlignment(SwingConstants.CENTER);
        add(labelLambda);

        paramLambda = new JComboBox(lambdaList);
        paramLambda.setSelectedIndex(0);
        ((JLabel)paramLambda.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER); // zcentrowanie stringa
        paramLambda.setBounds(440,225,120,20);
        add(paramLambda);



        buttonStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

               int n_temp =parseInt(String.valueOf(paramN.getSelectedItem()));
                modelMethods((int) Math.pow(2,n_temp));
                jt.setModel(model); // te metody sa ze sobą powiązane


               // model.setValueAt(n_temp,1,1); //ustawianie danej wartosci
            Organisms org = new Organisms(getN(),getM(),getMi());
            org.print_deb();
            org.init();
            MiPlusLambda ag = new MiPlusLambda(getN(), getM(), getMi(), getLambda());
            /*
            While(ag.stop()==100) // 100 iteracji bez lepszego osobnia
            {
            ag(org.getListBitVectors); // np 100
            org.setListBitVectors(ag.getListBitVectorWithLambda);
            ag.setMap(org.getMapVectFunc);

            }
             */


               // System.out.println(jt.getColumnCount());

            }
        });
    }
    public void modelMethods(int temp)
    {
        int n_temp= temp;
        tableData= new Object[n_temp][n_temp];
        colNames = new String[n_temp]; // domyślnie podłączona zmienna przez button
        for( int i=0;i<n_temp;i++)
        {
            colNames[i]=Integer.toString(i+1);
        }
        for(int i =0;i<n_temp;i++)
            for(int j =0;j<n_temp;j++)
                tableData[i][j]=0;

        model = new AbstractTableModel() {

            public int getRowCount() {
                return tableData.length;
            }

            public int getColumnCount() {
                return colNames.length;
            }


            public Object getValueAt(int rowIndex, int columnIndex) {
                return tableData[rowIndex][columnIndex];
            }

            public String getColumnName(int column){
                return colNames[column];
            }

            public Class getColumnClass(int column){
                return (getValueAt(0,column).getClass());
            }
            public void setValueAt(Object value, int row, int col) {
                tableData[row][col] = value;
                fireTableCellUpdated(row, col);
            }
        };
       // jt.setModel(model);
    }
    public void set1inTable( int row, int col){ // bo model to zmienna prywatna wiec trzeba metode publiczna do niej
        model.setValueAt(1,row,col);
    }

    ////////// GET NA PARAMETRY/////////////
    public int getN(){return parseInt(String.valueOf(paramN.getSelectedItem()));}
    public int getM(){return parseInt(paramM.getText().toString());}
    public int getMi(){return parseInt(String.valueOf(paramMi.getSelectedItem()));}
    public int getLambda(){return parseInt(String.valueOf(paramLambda.getSelectedItem()));}

}
