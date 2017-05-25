package com.pszt_organism;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
/**
 * Created by konrad on 14.05.2017.
 */
//class MiLambda dziedziczy po class MiPlusLambda, bo roznia sie tylko jednym krokiem algorytmu
public class MiLambda extends MiPlusLambda{

    private int max ;
    private int lambda;
    private int potegaDwojki;
    private int m;
    private int mi;
    private String wyborKrzyzowania;
    public MiLambda(int n_tmp, int m_tmp, int mi_tmp, int lambda_tmp, String krzyzowanie){

        potegaDwojki=n_tmp;
        m=m_tmp;
        mi=mi_tmp;
        lambda=lambda_tmp;
        max = 2*potegaDwojki*m-1;
        wyborKrzyzowania=krzyzowanie;
    }
        /*
        public ArrayList<int[]> miZpotomstwa(Map<int[],Double> mapVectFunc_tmp)
        {
            ArrayList<int[]> listaMi = new ArrayList<>();
            ArrayList<int[]> klucz_t = new ArrayList<>(mapVectFunc_tmp.keySet());

            for(int i=0; i<mi; i++)
            {
                listaMi.add(klucz_t.get(i));
            }

            return listaMi;
        }
        */
}
