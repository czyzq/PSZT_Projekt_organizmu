package com.pszt_organism;

import java.awt.*;
import java.util.HashSet;
import java.awt.Point;
import java.util.ArrayList;
import java.lang.System;

/**
 * Created by konrad on 14.05.2017.
 */
public class Organisms {


    //karolina 14.05

    //z tablicy wpisuje do listy punkty ktorych wartosc jest rowna 1
    public static   void  selectOne(int[][]envi, ArrayList<Point> list)
    {
        for (int i=0; i<envi.length;i++)
        {
            //tak znalazlam ze envi[i] i dopiero dlugosc ale dlaczego nie moze byc bez [i]?
            for(int j=0; j<envi[i].length;j++)
            {
                if(envi[i][j]==1)
                {
                    list.add(new Point(i,j));
                }
            }
        }
    }

    //na podstawie listy zawierajacej jedynki tworzy listy spojnych czesci organizmu
    public static void grupowanieCzesci(ArrayList<Point> listaJedynek, ArrayList<Point> cz)
    {
        //pobieram pierwszy element
        Point pktPocz = listaJedynek.get(0);
        cz.add(pktPocz);
        listaJedynek.remove(0);

        for(int t=0; t<cz.size(); t++ )
        {
            Point p1=cz.get(t);
            for(int ii=0; ii<listaJedynek.size(); ii++)
            {
                Point p2=listaJedynek.get(ii);
                //sprawdza czy kolejne elementy z listy sa w sasiedztwie pierwszego wzietego
                //jezeli sa t dopisuje ten punkt do org1 i usuwa z listy
                if( (Math.sqrt(Math.pow((p2.x-p1.x),2) + Math.pow((p2.y-p1.y),2))) <2 )
                {
                    cz.add(p2);
                    listaJedynek.remove(ii);
                }
            }
        }
    }

    //wyznacza srodek ciezkosci organizmu envi o ilosci jedynek rownej mm
    //za srodek czyli os odniesienia przyjmuje srodek tablicy
    public static double srodekCiezkosci (int[][]envi, int mm)
    {
        double mkr=0;
        double os = (double)(envi.length+1)/2;
        for (int i=0; i<envi.length;i++)
        {
            //tak znalazlam ze envi[i] i dopiero dlugosc ale dlaczego nie moze byc bez [i]?
            for(int j=0; j<envi[i].length;j++)
            {
                if(envi[i][j]==1)
                {
                    mkr=mkr+Math.sqrt(Math.pow(i+1-os, 2)+Math.pow(j+1-os, 2));
                }
            }
        }
        return mkr/mm;
    }

    // oblicza moment bezwladnosci liczony wzgledem srodka ciezkosi
    public static double momBezwl(int[][]envi, double sr)
    {
        double moment=0;
        // sr to srodek ciezkosci rowny mkr/mm
        for (int i=0; i<envi.length;i++)
        {
            //tak znalazlam ze envi[i] i dopiero dlugosc ale dlaczego nie moze byc bez [i]?
            for(int j=0; j<envi[i].length;j++)
            {
                if(envi[i][j]==1)
                {
                    moment = moment+(Math.pow(i+1-sr, 2)+Math.pow(j+1-sr, 2));
                }
            }
        }

        return moment;
    }

    //funkcja przystosowania to moment bezwladnosci dzielony przez liczbe spojnych czesi organizmu
    public static double fPrzyst(double Mbezwl, ArrayList<ArrayList<Point>> list)
    {
        double f = Mbezwl/list.size();
        return f;
    }
    //

    //dec na bin
    //liczba 20 np bedzie w tablicy wpisana jako 00101,  nalezy czytac od konca , potem jednak latwiej zamieniac taka konwencje na dec
    //wystarczy   podniesc 2 do indeksu tab jezeli jesto to 1 i zsumowac np : 0+0+2^2+0+2^4=20 :)
    public static int[]  decToBin(int liczbaDec, int potegaDwojki)
    {
        int i=0;
        int []tab =  new int [potegaDwojki];

        while(liczbaDec!=0) //dopóki liczba będzie różna od zera
        {
            tab[i++]=liczbaDec%2;
            liczbaDec/=2;
        }
        //for(int j=i-1;j>=0;j--)
        //  System.out.print(  +tab[j]);

        return tab;
    }

    //binarne na decymalne
    public static int binToDec(int[] tablica)
    {
        int dec=0;
        for (int ind=0; ind<tablica.length; ind++)
        {
            if(tablica[ind]==1)
            {
                dec+=Math.pow(2,ind);
            }
        }
        //sprawdzenie
        System.out.print("\n dec: "+dec);

        return dec;
    }

    //zamienianie polozenia na wektor bitowy
    public static int[] polozenieNaBity(ArrayList<Point> listaJedynek, int mm, int potegaDwojki )
    {
        int [] tab = new int [mm*potegaDwojki*2];
        int [] tabX =new int [potegaDwojki];
        int [] tabY =new int [potegaDwojki];
        int index =0;

        for(int in=0; in<listaJedynek.size();in++)
        {
            Point pkt = listaJedynek.get(in);
            tabX= decToBin(pkt.x, potegaDwojki); //teraz sa tablice
            //dla spr
            //System.out.print( "\n wspolrzedna x punktu "+in+" : " +tabX[2]+tabX[1]+tabX[0]);
            java.lang.System.arraycopy(tabX,0,tab,index,potegaDwojki); //tablice tabX od indeksu 0 kopiuje do tablicy tab od indeksu index  , kopiuje potegaDwojki elementow
            index+=3;
            tabY= decToBin(pkt.y, potegaDwojki);
            // dla spr
            //System.out.print( "\n wspolrzedna y punktu "+in+" : " +tabY[2]+tabY[1]+tabY[0]);
            java.lang.System.arraycopy(tabY,0,tab,index,potegaDwojki);  // jak pozbyc sie przodu, bo import nie dziala
            index+=3;
        }

        //to wypisuje pkt0.x, pkt0.y, pkt1.x, pkt1.y itd (ale w odwrotniej kolejnosci tzn, jezeli np x=1, y=3 to wypisze: 100, 110)
        //ta konwencja co w zamianie napisalam
        System.out.print( "\n  polozenie na bity o kolei jak w tab wpisane: ");
        for(int j=0;j<mm*potegaDwojki*2;j++)
            System.out.print(  +tab[j]);

        return  tab;
    }


    private HashSet<Point> mPoints;
    private int cosdopusha;

    public Organisms(){};
}
