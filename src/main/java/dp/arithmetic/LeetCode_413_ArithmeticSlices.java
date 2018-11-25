package dp.arithmetic;

/**
 * @author ZhangDaPang 285296372@qq.com
 * @date 2018/11/23 18:23
 */
public class LeetCode_413_ArithmeticSlices {
    public static void main(String[] args) {
        int []A = {1, 2, 3, 4};
        System.out.println(numberOfArithmeticSlices(A));
    }

    /**
     * [1,2,3,4,5]为例
     *
     * len = 3: [1,2,3], [2,3,4], [3,4,5]
     * len = 4: [1,2,3,4], [2,3,4,5]
     * len = 5: [1,2,3,4,5]
     * 梯形公式
     * (上底 + 下底) * 高 / 2
     * (n - 2 + 1) * (n - 2) / 2
     * (n - 1) * (n - 2) / 2
     */
    public static int numberOfArithmeticSlices(int[] A) {
        int res = 0, len = 2;
        for (int i = 2; i < A.length; ++i) {
            if (A[i] - A[i - 1] == A[i - 1] - A[i - 2]) {
                ++len;
            } else {
                if (len > 2) res += (len - 1) * (len - 2) * 0.5;
                len = 2;
            }
        }
        if (len > 2) res += (len - 1) * (len - 2) * 0.5;
        return res;
    }

    /**
     * dp
     */
    public static int numberOfArithmeticSlices2(int[] A) {
        int res = 0;
        int[]dp = new int[A.length];
        for (int i = 2; i < A.length; ++i) {
            if (A[i] - A[i - 1] == A[i - 1] - A[i - 2]) {
                dp[i] = dp[i - 1] + 1;
            }
            res += dp[i];
        }
        return res;
    }

    /**
     * dp 空间改良
     */
    public static int numberOfArithmeticSlices3(int[] A) {
        int res = 0, cur = 0;
        for (int i = 2; i < A.length; ++i) {
            if (A[i] - A[i - 1] == A[i - 1] - A[i - 2]) {
                cur += 1;
                res += cur;
            } else {
                cur = 0;
            }
        }
        return res;
    }
}
