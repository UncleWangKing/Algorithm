package dp01;

import java.util.Arrays;

/**
 * @author ZhangDaPang 285296372@qq.com
 * @date 2018/7/3 10:11
 */
public class LeetCode_322_CornChange {
    public static void main(String[] args) {
        int []list = {1, 2, 5};
        int amount = 11;
        System.out.println(coinChange(list, amount));
    }

    public static int coinChange(int[] coins, int amount) {
        int dp[] = new int[amount +1];
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;
        for (int i = 1; i <= amount; ++i) {
            for (int j = 0; j < coins.length; ++j) {
                if (coins[j] <= i) {
                    dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
                }
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }
}
