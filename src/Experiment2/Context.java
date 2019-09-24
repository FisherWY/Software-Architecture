package Experiment2;

/**
 * @Author Fisher
 * @Date 2019/9/10 10:24
 **/

class Context {
    SortAlgorithm alg;

    // Constructor
    public Context(SortAlgorithm alg) {
        this.alg = alg;
    }

    public int[] sortIntArray(int[] a) {
        return this.alg.sort(a);
    }
}
