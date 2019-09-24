package Experiment2;

/**
 * @Author Fisher
 * @Date 2019/9/10 10:28
 **/


public class HeapSort implements SortAlgorithm{
    public int[] sort(int nums[ ]) {
        for(int i=nums.length; i>1; i--){
            buildBinaryHeapTree(nums, i - 1);
            swapLeadingNodeWithLastNode(nums, i - 1);
        }
        return nums;
    }

    public void buildBinaryHeapTree(int array[], int arrayBound){
        int leftChild, rightChild, biggerChild, temp;
        int root = (arrayBound-1)/2;

        // Find the bigger child index
        for(int i=root; i>=0; i--) {
            leftChild = (2*i)+1;
            rightChild = (2*i)+2;

            if((leftChild <= arrayBound) && (rightChild <= arrayBound)){
                if(array[rightChild] >= array[leftChild])
                    biggerChild = rightChild;
                else
                    biggerChild = leftChild;
            }
            else{
                if(rightChild > arrayBound)
                    biggerChild = leftChild;
                else
                    biggerChild = rightChild;
            }

            //swap the integer contained in the bigger child index
            //with that in the current parent node
            if(array[i] < array[biggerChild]){
                temp = array[i];
                array[i] = array[biggerChild];
                array[biggerChild] = temp;
            }
        }
        return;
    }

    public static void swapLeadingNodeWithLastNode(int array[], int arrayBound){
        int  temp;
        temp = array[0];
        array[0] = array[arrayBound];
        array[arrayBound] = temp;
        return;
    }
}

