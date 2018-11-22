package dp.classic;

public class LeetCode_877_StoneGame {
    public static void main(String[] args) {
        int piles[] = {5,3,4};
        System.out.println(stoneGame(piles));
    }

    /**
     * 正常dp 初始化对角线 往右上的dp
     * 设dp[i][j]为piles[i]~piles[j]Alex最多可以赢Lee的分数
     * 每次取石头堆只能从两端取
     * 因此:dp[i][j] = max(piles[i] - dp[i+1][j], piles[j] - dp[i][j-1])
     * piles[i] - dp[i+1][j]表示Alex取走i上的石头堆
     * piles[j] - dp[i][j-1]表示Alex取走的是j上的石头堆
     *
     * 注意，为什么dp[i+1][j]表示piles[i+1]~piles[j]之间Alex最多可以赢Lee的分数，
     * 而piles[i]要减去该值而不是加上该值呢？
     * 由于我们的要求是每一步Alex和Lee采取的都是最优策略，
     * 当取piles[i]时，piles[i+1]~piles[j]中Alex和Lee的走法会调换。
     * 意即Lee走Alex的走法，Alex走Lee的走法，因此这里要做减法。
     */
    public static boolean stoneGame(int[] piles) {
        int n = piles.length;
        int [][]dp = new int[n][n];
        for(int i = 0; i < n; i++) {
            dp[i][i] = piles[i]; //初始化只有i一个石头堆的情形
        }
        for(int dis = 1; dis < n; dis++) {//依次计算相邻2个石头堆到n个石头堆的情形
            for(int i = 0; i < n - dis; i++) {
                dp[i][i + dis] = Math.max(piles[i] - dp[i + 1][i + dis], piles[i + dis] - dp[i][i + dis - 1]);
            }
        }
        return dp[0][n - 1] > 0;
    }

    /**
     * 由上可知 先手总能赢
     */
    public static boolean stoneGame2(int[] piles) {
        return true;
    }
}
