package Experiment2;

/**
 * @Author Fisher
 * @Date 2019/9/10 10:28
 **/

public class InsertSort implements SortAlgorithm {
    public int[] sort(int[] nums){
        for (int i = 1; i < nums.length; i++){
            int j = i;
            int numToBeInserted = nums[i];

            while ((j > 0) && (nums[j-1] > numToBeInserted) ) {
                nums[j] = nums[j-1];
                j--;
            }
            nums[j] = numToBeInserted;
        }
        return nums;
    }
}

