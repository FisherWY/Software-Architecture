package Experiment2;

/**
 * @Author Fisher
 * @Date 2019/9/10 10:22
 **/

import java.util.Random;
public class IntegerArrGenerator{
    public static int[] generateInput(int len){
        int[] input= new int[len];
        Random r = new Random();
        for(int m=0; m< len; m++){
            input[m] = r.nextInt(len);
        }

        return input;
    }
}
