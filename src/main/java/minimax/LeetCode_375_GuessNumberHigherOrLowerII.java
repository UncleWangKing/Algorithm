package minimax;

public class LeetCode_375_GuessNumberHigherOrLowerII {
    public static void main(String[] args) {
        System.out.println(getMoneyAmount(10));
    }

    public static int getMoneyAmount(int n) {
        int[][] dp = new int[n + 1][n + 1];
        for (int i = 2; i <= n; ++i) {
            for (int j = i - 1; j > 0; --j) {
                int global_min = Integer.MAX_VALUE;
                for (int k = j + 1; k < i; ++k) {
                    /**
                     * [j,i]范围内猜值 最坏情况
                     */
                    int local_max = k + Math.max(dp[j][k - 1], dp[k + 1][i]);
                    /**
                     * 所有最坏的情况中 取最小
                     */
                    global_min = Math.min(global_min, local_max);
                }
                dp[j][i] = j + 1 == i ? j : global_min;
            }
        }
        return dp[1][n];
    }
}
