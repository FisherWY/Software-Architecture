package Experiment2;

/**
 * @Author Fisher
 * @Date 2019/9/10 10:20
 **/

public class ResultVerification{
    static boolean flag = true;

    public static boolean isResultCorrect(int[] arr){
        for(int k=0; k<arr.length-1; k++){
            if(arr[k] > arr[k+1]){
                flag=false;
                System.out.println("error  "+ k);
                //break;
            }
        }
        return flag;
    }
}

