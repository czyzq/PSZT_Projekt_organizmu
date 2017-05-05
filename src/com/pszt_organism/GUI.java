package com.pszt_organism;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;

import static java.lang.Integer.parseInt;

/**
 * Created by konra on 02.05.2017.
 */
public class GUI extends JPanel{

    private JTable jt;
    private JScrollPane jsp;
    private JButton buttonStart;
    private JLabel labelAlg,labelN, labelM;
    private JFormattedTextField paramM, paramN;
    private JComboBox algEvo;
    private  String[] algoritmList = {" ","λ+ μ","λ, μ"};
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

        labelN = new JLabel("N");
        labelN.setBounds(440,90,120,20);
        labelN.setHorizontalAlignment(SwingConstants.CENTER);
        add(labelN);


        NumberFormat format = NumberFormat.getInstance();
        NumberFormatter formatter = new NumberFormatter(format);
        formatter.setValueClass(Integer.class);
        formatter.setMinimum(0);
        formatter.setMaximum(Integer.MAX_VALUE);
        formatter.setAllowsInvalid(false);
        paramN = new JFormattedTextField(formatter);
        paramN.setBounds(440,110,120,20);
        paramN.setHorizontalAlignment(SwingConstants.CENTER);
        add(paramN);

        labelM = new JLabel("M");
        labelM.setBounds(440,180,120,20);
        labelM.setHorizontalAlignment(SwingConstants.CENTER);
        add(labelM);

        paramM = new JFormattedTextField(formatter);
        paramM.setBounds(440,200,120,20);
        paramM.setHorizontalAlignment(SwingConstants.CENTER);
        add(paramM);



        buttonStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

               int n_temp =parseInt(paramN.getText());

                modelMethods(n_temp);
                jt.setModel(model); // te metody sa ze sobą powiązane

                //model.setValueAt(n_temp,1,1); ustawianie danej wartosci




                System.out.println(jt.getColumnCount());

            }
        });
    }
    private void modelMethods(int temp)
    {
        int n_temp= temp;
        tableData= new Object[n_temp][n_temp];
        colNames = new String[n_temp]; // domyślnie podłączona smienna przez button
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

}
