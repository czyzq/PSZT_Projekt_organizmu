package com.pszt_organism;

import java.util.*;
import java.awt.Point;
import java.lang.System;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by konrad on 14.05.2017.
 */
public class Organisms {


    //karolina 14.05


    private List<HashSet<Point>> listMPoints; // moze nazwac to genotyp?
    private int n,m,mi;
    private List<int[][]> orgnisms;
    private List<int[]> listBitVectors;
    private Map<int[],Double> mapVectFunc;



    public Organisms(int n_tmp, int m_tmp, int mi_tmp){


        n= (int) Math.pow(2,n_tmp);
        m=m_tmp;
        mi=mi_tmp;
        listMPoints=new ArrayList<>();
        mapVectFunc = new LinkedHashMap<>();
        orgnisms= new ArrayList<>();
        listBitVectors = new ArrayList<>();
        newEmptyArrays();
        randomValues();



    }


    public void init()
    {
        int liczbaCzesci;
        double momentBez;
        for(int i=0;i<orgnisms.size();i++)
        {
            liczbaCzesci=grupowanieCzesci(selectOne(orgnisms.get(i)));
            momentBez=momBezwl(orgnisms.get(i), srodekCiezkosci(orgnisms.get(i),m));
            System.out.println("f przyst: "+ fPrzyst(momentBez,liczbaCzesci));
            listBitVectors.add( polozenieNaWektor(selectOne(orgnisms.get(i)),m, (int) (Math.log(n)/Math.log(2)),orgnisms.get(i)));
          //  mapVectFunc.put(polozenieNaWektor(selectOne(orgnisms.get(i)),m, (int) (Math.log(n)/Math.log(2)),orgnisms.get(i)),fPrzyst(momentBez,liczbaCzesci));

        }
        System.out.println("posrotwana mapa" + sortByValue(mapVectFunc).size());
    }
    private void newEmptyArrays(){
        for(int i=0;i<mi;i++)   // dodanie mi razy do listy
            orgnisms.add(new int[n][n]);
    }
    private void randomValues(){
        Random rand =new Random();
        HashSet<Point> mPoints = new HashSet<>();
        int randomX;
        int randomY;
        for(int[][] table : orgnisms)                       //foreach dla kazdej tablicy z organisms
        {
            mPoints.clear();
            while (mPoints.size() != m)                        //losowanie dopóki zbior sie nie zapelni
            {
                randomX = rand.nextInt(n);                  // losowanie polozenia do n
                randomY = rand.nextInt(n);
                mPoints.add(new Point(randomX, randomY));
            }
            listMPoints.add(mPoints);                          // dodanie nowego wektora pktów do listy

            for(Point p : mPoints)                            //dodanie 1 do tablic kolejno z zbioru
               // orgnisms.get(i)[p.x][p.y]=1;              //opcja dla typowego fora
                table[p.x][p.y]+=1;
        }
    }

    //z tablicy wpisuje do listy punkty ktorych wartosc jest rowna 1
    private ArrayList<Point>  selectOne(int[][]envi)
    {
        ArrayList<Point> list = new ArrayList<>();
        for (int i=0; i<envi.length;i++)
        {
            //tak znalazlam ze envi[i] i dopiero dlugosc ale dlaczego nie moze byc bez [i]?
                //to chyba długość i-tej tablicy tzn. np. jeśli wcześniej była szerokość to teraz jest wysokość
            for(int j=0; j<envi[i].length;j++)
            {
                if(envi[i][j]!=0) //18.05 zm
                {
                    list.add(new Point(i,j));
                }
            }
        }
        return list;
    }


    //na podstawie listy zawierajacej jedynki tworzy listy spojnych czesci organizmu
    private  int grupowanieCzesci(ArrayList<Point> listaOne)
    {

        ArrayList<ArrayList<Point>> listaCzesci =new ArrayList<ArrayList<Point>>();
        int iter=1;
        while(!listaOne.isEmpty())
        {
            ArrayList<Point> czesc =new ArrayList<Point>();
            //pobieram pierwszy element
            Point pktPocz = listaOne.get(0);
            czesc.add(pktPocz);
            listaOne.remove(0);

            for(int t=0; t<czesc.size(); t++ )
            {
                Point p1=czesc.get(t);
                for(int ii=0; ii<listaOne.size(); ii++)
                {
                    Point p2=listaOne.get(ii);
                    //sprawdza czy kolejne elementy z listy sa w sasiedztwie pierwszego wzietego
                    //jezeli sa t dopisuje ten punkt do org1 i usuwa z listy
                    if( (Math.sqrt(Math.pow((p2.x-p1.x),2) + Math.pow((p2.y-p1.y),2))) <2 )
                    {
                        czesc.add(p2);
                        listaOne.remove(ii);
                    }
                }
            }
           /* System.out.print("\n Czesc "+iter+" : [");
            czesc.stream().forEach((p)-> System.out.print("[" + p.x +"," + p.y + "]"));
            System.out.print("] ");*/
            listaCzesci.add(czesc);
            iter++;
        }
        return listaCzesci.size();
    }

    //wyznacza srodek ciezkosci organizmu envi o ilosci jedynek rownej mm
    //za srodek czyli os odniesienia przyjmuje srodek tablicy
    private double srodekCiezkosci (int[][]envi, int mm)
    {
        double mkr=0;
        double os = (double)(envi.length+1)/2;
        for (int i=0; i<envi.length;i++)
        {
            //tak znalazlam ze envi[i] i dopiero dlugosc ale dlaczego nie moze byc bez [i]?
            for(int j=0; j<envi[i].length;j++)
            {
                if(envi[i][j]!=0) //18.05 zm
                {
                    mkr=mkr+Math.sqrt(Math.pow(i+1-os, 2)+Math.pow(j+1-os, 2))*envi[i][j]; //mniozenie odleglosci przez mase (licznik wzoru na srodek ciezkosci)
                }
            }
        }
        return mkr/mm; //mianowniekiem wzoru na srodek ciezkosci jest suma mas a u nas zawsze bedzie to tyle ile ustalimy m
    }

    // oblicza moment bezwladnosci liczony wzgledem srodka ciezkosi
    private double momBezwl(int[][]envi, double sr)
    {
        double moment=0;
        // sr to srodek ciezkosci rowny mkr/mm
        for (int i=0; i<envi.length;i++)
        {
            //tak znalazlam ze envi[i] i dopiero dlugosc ale dlaczego nie moze byc bez [i]?
            for(int j=0; j<envi[i].length;j++)
            {
                if(envi[i][j]!=0) //18.05 zm
                {
                    moment = moment+(Math.pow(i+1-sr, 2)+Math.pow(j+1-sr, 2))*envi[i][j]; //mnozenie kwadratu odleglosci przez mase
                }
            }
        }

        return moment;
    }

    //funkcja przystosowania to moment bezwladnosci dzielony przez liczbe spojnych czesi organizmu
    private double fPrzyst(double Mbezwl, int iloscCzesci)
    {
        double f = Mbezwl/iloscCzesci;
        return f;
    }
    //

    //dec na bin
    //liczba 20 np bedzie w tablicy wpisana jako 00101,  nalezy czytac od konca , potem jednak latwiej zamieniac taka konwencje na dec
    //wystarczy   podniesc 2 do indeksu tab jezeli jesto to 1 i zsumowac np : 0+0+2^2+0+2^4=20 :)
    public  int[]  decToBin(int liczbaDec, int potegaDwojki)
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
    public int binToDec(int[] tablica)
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
    private int[] polozenieNaWektor(ArrayList<Point> listaJedynek, int mm, int potegaDwojki, int[][]envi)
    {
        int [] tab = new int [mm*potegaDwojki*2];
        int [] tabX =new int [potegaDwojki];
        int [] tabY =new int [potegaDwojki];
        int index =0;

        for(int in=0; in<listaJedynek.size();in++)
        {
            Point pkt = listaJedynek.get(in);
            for(int w=0; w< envi[pkt.x][pkt.y] ;w++) //musi tyle razy wpisac ten punkt jako bity ile byla jego wartosc 18.05 zm
            {
                tabX= decToBin(pkt.x, potegaDwojki); //teraz sa tablice
                //dla spr
                //System.out.print( "\n wspolrzedna x punktu "+in+" : " +tabX[2]+tabX[1]+tabX[0]);
                java.lang.System.arraycopy(tabX,0,tab,index,potegaDwojki); //tablice tabX od indeksu 0 kopiuje do tablicy tab od indeksu index  , kopiuje potegaDwojki elementow
                index+=potegaDwojki;
                tabY= decToBin(pkt.y, potegaDwojki);
                // dla spr
                //System.out.print( "\n wspolrzedna y punktu "+in+" : " +tabY[2]+tabY[1]+tabY[0]);
                java.lang.System.arraycopy(tabY,0,tab,index,potegaDwojki);  // jak pozbyc sie przodu, bo import nie dziala
                index+=potegaDwojki;
            }

        }

        //to wypisuje pkt0.x, pkt0.y, pkt1.x, pkt1.y itd (ale w odwrotniej kolejnosci tzn, jezeli np x=1, y=3 to wypisze: 100, 110)
        //ta konwencja co w zamianie napisalam
        System.out.print( "\n  polozenie na bity o kolei jak w tab wpisane: ");
        for(int j=0;j<mm*potegaDwojki*2;j++)
            System.out.print(  +tab[j]);

        return  tab;
    }

    private  ArrayList<Point> wektorNaPolozenie(int[] WektorBitow, int PotegaDwojki)
    {
        ArrayList<Point> ListaPunktow = new ArrayList<>();
        int index =0;
        int x;
        int y;
        while(index!=WektorBitow.length)
        {
            x=0;
            y=0;

            for(int i=0; i<PotegaDwojki; i++) //dziele sobie caly wektor na czesci opisujace x-sy i nizej y-ki
            {
                if(WektorBitow[index]==1)
                {
                    x+=Math.pow(2, i);
                    //System.out.print("\n index w x: "+index);
                }
                index++;
            }

            for(int i=0; i<PotegaDwojki; i++)
            {
                if(WektorBitow[index]==1)
                {
                    y+=Math.pow(2, i);
                    //System.out.print("\n index w y: "+index);
                }
                index++;
            }
        	/*test
        	System.out.print("\n x: "+x);
        	System.out.print("\n y: "+y);
        	*/
            ListaPunktow.add(new Point(x,y));
        }
        return ListaPunktow;
    }
    public void print_deb()
    {
        for(int[][] o : orgnisms) {
            for (int[] ii : o)
            {
                for (int i : ii)
                    System.out.print(i);
                System.out.println();
            }
            System.out.println("\n");
        }
    }
    public List<int[]> getListBitVectors() {
        return listBitVectors;
    }
    private  <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map) {
        return map.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Collections.reverseOrder()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new
                ));
    }
    public void listToMap(List<int[]>list_temp)
    {
        int liczbaCzesci;
        double momentBez;

        orgnisms.clear(); // czysczenie listy organizmow
        for (int i =0; i<list_temp.size();i++)
        {
            orgnisms.add(new int[n][n]);// tworzenie psutej tablicy
            List<Point> listPoint_temp= wektorNaPolozenie(list_temp.get(i), (int) (Math.log(n)/Math.log(2))); // stworzenie listy pkt z wektora bitowego
            for(Point p : listPoint_temp)
            {
                orgnisms.get(i)[p.x][p.y]+=1; // dodanie wartosci do przedchwilo stworzonego organizmu
            }
            liczbaCzesci=grupowanieCzesci(selectOne(orgnisms.get(i))); // count part in organism
            momentBez=momBezwl(orgnisms.get(i), srodekCiezkosci(orgnisms.get(i),m));// count moment of inertia
            mapVectFunc.put(polozenieNaWektor(selectOne(orgnisms.get(i)),m, (int) (Math.log(n)/Math.log(2)),orgnisms.get(i)),fPrzyst(momentBez,liczbaCzesci));
        }

    }


}
