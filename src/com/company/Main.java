package com.company;


public class Main {

    private int [][] matrix = new int [8][8];
    private int [][] mixedOrganisms; // komórki które posiadają sąsiadów (x,y)
    private int [][][] sortedOrganisms; // z,x,y (z-> nr organizmu, (x,y) -> jego polozenie na macierzy)
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
        for(int j=0;j<8;j++)
        {
            for (int i=0;i<8;i++)
            {
                if(j==1)// wiersz pierwszy (rozdzielone dla czytolności)
                {

                    //pierwsza kolumna
                    if (i==1 && matrix[i][j] == 1 && (matrix[i + 1][j] == 1 || matrix[i + 1][j + 1] == 1 || matrix[i][j + 1] == 1 ))//znajduje poczatek organizmu - musi miec sąsiadów
                    {
                        mixedOrganisms[n][0] = i;
                        mixedOrganisms[n][1] = j;
                        n++;
                    }
                    //ostatnia kolumna
                    else if(i==7 && matrix [i][j]==1 && (matrix[i-1][j]==1 || matrix[i-1][j+1]==1 || matrix[i][j+1]==1))
                    {
                        mixedOrganisms[n][0] = i;
                        mixedOrganisms[n][1] = j;
                        n++;
                    }
                    //środek wiersza
                    else if(matrix[i][j] == 1 && (matrix[i - 1][j] == 1 || matrix[i-1][j+1]==1 || matrix[i + 1][j] == 1 || matrix[i + 1][j + 1] == 1 || matrix[i][j + 1] == 1  ))
                    {
                        mixedOrganisms[n][0] = i;
                        mixedOrganisms[n][1] = j;
                        n++;
                    }

                }
                else if (i==7) // ostatni wiersz
                {
                    //pierwszy element wiersza
                    if (i==1 && matrix[i][j] == 1 && (matrix[i + 1][j] == 1 || matrix[i + 1][j - 1] == 1 || matrix[i][j - 1] == 1 ))//znajduje poczatek organizmu - musi miec sąsiadów
                    {
                        mixedOrganisms[n][0] = i;
                        mixedOrganisms[n][1] = j;
                        n++;
                    }
                    //ostatni element wiersza
                    else if(i==7 && matrix [i][j]==1 && (matrix[i-1][j]==1 || matrix[i-1][j-1]==1 || matrix[i][j-1]==1))
                    {
                        mixedOrganisms[n][0] = i;
                        mixedOrganisms[n][1] = j;
                        n++;
                    }
                    //środek wiersza
                    else if(matrix[i][j] == 1 && (matrix[i - 1][j] == 1 || matrix[i-1][j-1]==1 || matrix[i + 1][j] == 1 || matrix[i + 1][j - 1] == 1 || matrix[i][j - 1] == 1  ))
                    {
                        mixedOrganisms[n][0] = i;
                        mixedOrganisms[n][1] = j;
                        n++;
                    }
                    else
                    {

                    }
                }
            }
        }

    }
    private void sorting() // odpowiedzialna za posortownaie organizmów
    {

    }
    public void main()
    {
        addValues();

    }
}
