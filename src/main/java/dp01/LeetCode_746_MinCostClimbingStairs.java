package dp01;

/**
 * @author ZhangDaPang 285296372@qq.com
 * @date 2018/7/3 10:11
 */
public class LeetCode_746_MinCostClimbingStairs {
    public static void main(String[] args) {
        int [] list = {3,1,5,8};

        System.out.println(minCostClimbingStairs(list));
    }

    /**
     * dp[i] = min(dp[i- 2] + cost[i - 2], dp[i - 1] + cost[i - 1])
     */
    public static int minCostClimbingStairs(int[] cost) {
        int n = cost.length;
        int[] dp = new int[cost.length+1];
        for (int i = 2; i < n + 1; ++i) {
            dp[i] = Math.min(dp[i- 2] + cost[i - 2], dp[i - 1] + cost[i - 1]);
        }
        return dp[cost.length];
    }
}
