package com.pszt_organism;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by konrad on 14.05.2017.
 */
public class MiPlusLambda {


    public static int[]mutacja(int[] Wektor, int min, int max)
    {
        //tak wlasciwie min to zawsze 0 a max o bedzie 2*potegaDwojki*m-1
        int[]TabZmutowana = Wektor;
        int randomNum = ThreadLocalRandom.current().nextInt(min, max);
        System.out.print("\n numer random: "+randomNum);
        if(TabZmutowana[randomNum]==1)
            TabZmutowana[randomNum]=0;
        else
            TabZmutowana[randomNum]=1;

        return TabZmutowana;
    }

    public static ArrayList<int[]> krzyzowanie (int[] Wektor1, int[] Wektor2) {
        //krzyżowanie na zasadzie ab cd => ad cb
        ArrayList<int[]> Wektory = new ArrayList<>();
        int[] Temp = Wektor1;
        for (int i = (Wektor1.length / 2) + 1; i <= Wektor1.length; i++)
        {
            Wektor1[i] = Wektor2[i];
            Wektor2[i] = Temp[i];
        }
        Wektory.add(Wektor1);
        Wektory.add(Wektor2);
        return Wektory;
    }



}
