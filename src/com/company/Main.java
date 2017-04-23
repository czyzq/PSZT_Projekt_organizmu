package com.company;


public class Main {

    private int [][] matrix = new int [8][8];
    private int [][] organism;
    private void addValues()
    {
        matrix = new int[][]{
                {0, 1, 1, 1, 0, 1, 0, 1},
                {0, 0, 0, 1, 0, 0, 0, 0},
                {0, 1, 1, 0, 0, 0, 0, 0},
                {0, 1, 0, 0, 0, 1, 0, 1},
                {0, 0, 0, 1, 0, 0, 1, 1},
                {0, 1, 1, 1, 0, 0, 0, 1},
                {0, 1, 0, 1, 0, 0, 0, 1},
                {0, 1, 1, 1, 0, 1, 0, 1}};
    }
    private void findOrganism() //algoryzm szukania organizmu
    {
        //TODO: skonczyc algorytm
       //i,j -> x,y
        int n=0;
        for(int i =0;i<8;i++)
        {
            for (int j=0;j<8;j++)
            {
                if(matrix[i][j]==1 && (matrix[i+1][j]==1 || matrix [i+1][j+1]==1 || matrix[i][j+1]==1 ||matrix[i-1][j]==1))//znajduje poczatek organizmu - musi miec sąsiadów
                {
                    organism[n][0]= i;
                    organism[n][1]=j;
                    n++;
                }
            }
        }

    }
    public void main()
    {
        addValues();

    }
}
