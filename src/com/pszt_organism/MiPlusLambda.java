package com.pszt_organism;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by konrad on 14.05.2017.
 */
public class MiPlusLambda {


    public static int[]mutacja(int[] Wektor, int min, int max)
    {
        //tak wlasciwie min to zawsze 0 a max o bedzie 2*potegaDwojki*m-1

        //tablica prawdopodobienstwa takiej dlugosci jak wektor, bo prawdopodobienstwo zanegowania bitu ma by 1/dl_wektora
        int[] prawdopodobienstwo = new int[Wektor.length];
        for (int i = 0; i < prawdopodobienstwo.length; i++)
        {
            prawdopodobienstwo[i] = 0;
        }
        //gdzies bedzie 1 i jezeli ja sie wylosuje to znaczy ze gen ma byc negowany
        int randomZmiana = ThreadLocalRandom.current().nextInt(0, max);
        prawdopodobienstwo[randomZmiana]=1;

        int[]WektorZmutowany = Wektor;
        for(int w=0; w<WektorZmutowany.length; w++)
        {
            int randomNum = ThreadLocalRandom.current().nextInt(min, max);
            //System.out.print("\n numer random: "+randomNum);
            //jezeli losowy index z prawdopodobienstwa jest 1 to gen o indeksie w mutuje
            if(prawdopodobienstwo[randomNum ]==1)
            {
                //jezli byl 1 to mutuje na 0
                if(WektorZmutowany[w]==1)
                    WektorZmutowany[w]=0;
                    //jezeli byl 0 to mutuje na 1
                else
                    WektorZmutowany[w]=1;
            }
        }


        return WektorZmutowany;
    }
}
