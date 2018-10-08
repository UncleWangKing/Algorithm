package array.indexmagic;

/**
 * @author ZhangDaPang 285296372@qq.com
 * @date 2018/10/8 15:29
 */
public class LeetCode_775_GlobalandLocalInversions {
    public static void main(String[] args) {
//        int list[] = {1,2,0};//false
//        int list[] = {2,0,1};//false
        int list[] = {1,2,0};//false
        System.out.println(isIdealPermutation(list));
    }

    /**
     * 显然 局部倒置一定是全局倒置，而全局倒置不一定是局部倒置。
     * 所以有一个部署局部倒置的全局倒置 即可返回false
     *
     * 然后维护一个 [i, n-1] 范围内的最小值，每次和 A[i - 2] 比较，如果小于 A[i - 2]，说明这是个全局的倒置，并且不是局部倒置
     */
    public static boolean isIdealPermutation(int[] A) {
        int min = Integer.MAX_VALUE;

        for (int i = A.length - 1; i >= 2; i--) {
            min = Math.min(min, A[i]);
            if (A[i - 2] > min) return false;
        }

        return true;
    }

    /**
     * 上题同理 反过来也是一样
     */
    public static boolean isIdealPermutation2(int[] A) {
        int max = Integer.MIN_VALUE;

        for (int i = 0; i < A.length - 2; i++) {
            max = Math.max(max, A[i]);
            if (A[i + 2] < max) return false;
        }

        return true;
    }

    /**
     * 数组下标骚操作 因为是0-A.length - 1的排列， 所以值如果和下标相差大于1 就至少在某个方向上有两个倒置，只能有一个是局部
     */
    public static boolean isIdealPermutation3(int[] A) {
        for (int i = 0; i < A.length; ++i) {
            if (Math.abs(A[i] - i) > 1) return false;
        }
        return true;
    }
}
