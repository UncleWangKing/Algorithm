package dp.classic;

public class LeetCode_887_SuperEggDrop {
    public static void main(String[] args) {
        int k = 3, n = 14;
        System.out.println(superEggDrop(k, n));
    }

    /**
     * TLE
     * O(K * N^2)
     * dp[n][k] 表示用k个鸡蛋测n层最少需要多少步。
     * 我们可以枚举第一次在第i层扔鸡蛋，会得到两种结果:

     * 鸡蛋坏掉: 我们接下来需要面对的情形是: 用 k-1 个鸡蛋来测量 i-1 层，所以最少需要 dp[i-1][k-1] 步。
     * 鸡蛋没坏: 我们接下来要面对的情形是: 用 k 个鸡蛋来测量 n-i 层，所以最少需要 dp[n-i][k] 步。
     * 因为我们总会面对最坏情况，所以，在第i层扔，会用 max(dp[i-1][k-1], dp[n-i][k]) + 1 步。
     * dp[n][k] = min{ max(dp[i-1][k-1], dp[n-i][k]) + 1 } (1 <= i <= n)
     */
    public static int superEggDrop(int K, int N) {
        int[][] dp = new int[K + 1][N + 1];
        return helper(K, N, dp);
    }
    private static int helper(int K, int N, int[][] dp) {
        if (N <= 1) {
            return N;
        }
        if (K == 1) {
            return N;
        }
        if (dp[K][N] > 0) {
            return dp[K][N];
        }
        int min = N;
        for (int i = 1; i <= N; i++) {
            int left = helper(K - 1, i - 1, dp);
            int right = helper(K, N - i, dp);
            min = Math.min(min, Math.max(left, right) + 1);
        }
        dp[K][N] = min;
        return min;
    }

    /**
     * O(K * logN)
     * 令dp[k][m] 表示k个鸡蛋在m步内最多能测出的层数。
     * 假设我们有k个鸡蛋第m步时，在第X层扔鸡蛋。这时候，会有两种结果，鸡蛋碎了，或者没碎。
     * 如果鸡蛋没碎，我们接下来会在更高的楼层扔，最多能确定 X + dp[k][m-1] 层的结果；
     * 如果鸡蛋碎了，我们接下来会在更低的楼层扔，最多能确定 Y + dp[k-1][m-1] 层的结果 (假设在第X层上还有Y层)。
     * 因此，这次扔鸡蛋，我们最多能测出 dp[k-1][m-1] (摔碎时能确定的层数) + dp[k][m-1] (没摔碎时能确定的层数) + 1 (本层) 层的结果。
     * 另外，我们知道一个鸡蛋一次只能测一层，没有鸡蛋一层都不能测出来。
     * dp[k][0] = 0
     * dp[1][m] = m (m > 0)
     * dp[k][m] = dp[k - 1][m - 1] + dp[k][m - 1] + 1 (k > 0, m > 0)
     * 空间压缩
     */
    public static int superEggDrop2(int K, int N) {
        int dp[] = new int[K + 1], m = 0;
        for (m = 0; dp[K] < N; ++m)
            for (int k = K; k > 0; --k)
                dp[k] += dp[k - 1] + 1;
        return m;
    }
}
