package com.pszt_organism;

import java.awt.*;
import java.util.HashSet;
import java.awt.Point;
import java.util.ArrayList;
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
    public static void grupowanieCzesci(ArrayList<Point> list, ArrayList<Point> cz)
    {
        //pobieram pierwszy element
        Point pktPocz = list.get(0);
        cz.add(pktPocz);
        list.remove(0);

        for(int t=0; t<cz.size(); t++ )
        {
            Point p1=cz.get(t);
            for(int ii=0; ii<list.size(); ii++)
            {
                Point p2=list.get(ii);
                //sprawdza czy kolejne elementy z listy sa w sasiedztwie pierwszego wzietego
                //jezeli sa t dopisuje ten punkt do org1 i usuwa z listy
                if( (Math.sqrt(Math.pow((p2.x-p1.x),2) + Math.pow((p2.y-p1.y),2))) <2 )
                {
                    cz.add(p2);
                    list.remove(ii);
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
    private HashSet<Point> mPoints;
    private int cosdopusha;

    public Organisms(){};
}
