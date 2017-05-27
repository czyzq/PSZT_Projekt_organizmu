package com.pszt_organism;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
/**
 * Created by konrad on 14.05.2017.
 */
//class MiLambda dziedziczy po class MiPlusLambda, bo roznia sie tylko jednym krokiem algorytmu
//musialem nadpisac kilka metod, bo be tego nie chcialo dzialac
public class MiLambda extends MiPlusLambda{

    private ArrayList <int[]> listaPopulacjaR = new ArrayList<>();
    private ArrayList <int[]> listaPopulacjaMi = new ArrayList<>();
    private int max ;
    private int lambda;
    private int potegaDwojki;
    private int m;
    private int mi;
    private double theChosenOne;
    private int iterationStop;
    private String wyborKrzyzowania;
    public MiLambda(int n_tmp, int m_tmp, int mi_tmp, int lambda_tmp, String krzyzowanie){
        potegaDwojki=n_tmp;
        m=m_tmp;
        mi=mi_tmp;
        lambda=lambda_tmp;
        max = 2*potegaDwojki*m-1;
        wyborKrzyzowania=krzyzowanie;
        theChosenOne=0;
        max = 2*potegaDwojki*m-1;
    }


    public ArrayList<int[]> getListaPopulacjaR() {
        return listaPopulacjaR;
    }

    public void setListaPopulacjaMi(ArrayList<int[]> listaPopulacjaMi) {
        this.listaPopulacjaMi = listaPopulacjaMi;
    }

    public ArrayList<int[]> losujLambda()
    {
        ArrayList<int[]> listaLambda = new ArrayList<>();
        //z listy populacji mi losowanych lambda osobnikow do rozmnozenia
        for(int i=0; i<lambda; i++)
        {
            int rnd = ThreadLocalRandom.current().nextInt(0, listaPopulacjaMi.size());
            listaLambda.add(listaPopulacjaMi.get(rnd));
        }
        return listaLambda;
    }


    public  void dodajPotomstwoR(ArrayList <int[]> listaLambda)
    {

        int i=0;
        while(i!=listaLambda.size())
        {
            //dla kolejno wylosowanych par lambda tworzonych jest 2 dzieci , ktore sa mutowane i dodawane do listy potomstwa
            if(wyborKrzyzowania.equals("Jednopunktowe")) {
                listaPopulacjaR.add(mutacja(krzyzowanie_jednopunktowe(listaLambda.get(i), listaLambda.get(i + 1)).get(0), 0, max));
                listaPopulacjaR.add(mutacja(krzyzowanie_jednopunktowe(listaLambda.get(i), listaLambda.get(i + 1)).get(1), 0, max));
            }
            else{
                listaPopulacjaR.add(mutacja(krzyzowanie_dwupunktowe(listaLambda.get(i), listaLambda.get(i + 1)).get(0), 0, max));
                listaPopulacjaR.add(mutacja(krzyzowanie_dwupunktowe(listaLambda.get(i), listaLambda.get(i + 1)).get(1), 0, max));
            }
            i+=2;
        }

    }

    public ArrayList<int[]> miZrodzicowIpotomstwa(Map<int[],Double> mapVectFunc_tmp)
    {
        ArrayList<int[]> listaMi = new ArrayList<>();
        ArrayList<int[]> klucz_t = new ArrayList<>(mapVectFunc_tmp.keySet());

        for(int i=0; i<mi; i++)
        {
            listaMi.add(klucz_t.get(i));
        }

        return listaMi;
    }

    public int stop(double firstValueFromMap)
    {
        if(theChosenOne<firstValueFromMap)
        {
            theChosenOne=firstValueFromMap;
            iterationStop=0;
        }
        else {
            iterationStop++;
            //System.out.println(iterationStop);
        }

        return iterationStop;
    }
}
