package Experiment2;

/**
 * @Author Fisher
 * @Date 2019/9/10 10:27
 **/

public class BubbleSort implements SortAlgorithm {
    public int[] sort(int[] nums){
        for(int i = nums.length; --i >= 0;)
            for(int j = 0; j < i; j++){
                if(nums[j] > nums[j + 1]){

                    //exchange nums[j+1] with nums[j]
                    int T = nums[j];
                    nums[j] = nums[j + 1];
                    nums[j + 1] = T;
                }
            }
        return nums;
    }
}
