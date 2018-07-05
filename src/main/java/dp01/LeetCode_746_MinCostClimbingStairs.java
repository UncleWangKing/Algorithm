package dp01;

/**
 * @author ZhangDaPang 285296372@qq.com
 * @date 2018/7/3 10:11
 */
public class LeetCode_746_MinCostClimbingStairs {
    public static void main(String[] args) {
        int [] list = {1, 100, 1, 1, 1, 100, 1, 1, 100, 1};

        System.out.println(minCostClimbingStairs_dp_recursion(list.length, list));
        System.out.println(minCostClimbingStairs_dp_loop(list));
    }

    /**
     * 关键:可以从0 或者 1位置为起始点
     * dp[i] = min(dp[i- 2] + cost[i - 2], dp[i - 1] + cost[i - 1])
     */
    public static int minCostClimbingStairs_dp_recursion(int n, int[] cost) {
        if(0 == n || 1 == n)
            return 0;
        else if(2 == n)
            return Math.min(cost[0], cost[1]);


        return Math.min(minCostClimbingStairs_dp_recursion(n - 2, cost) + cost[n - 2], minCostClimbingStairs_dp_recursion(n - 1, cost) + cost[n - 1]);
    }

    /**
     * 关键:可以从0 或者 1位置为起始点
     * dp[i] = min(dp[i- 2] + cost[i - 2], dp[i - 1] + cost[i - 1])
     */
    public static int minCostClimbingStairs_dp_loop(int[] cost) {
        int[] dp = new int[cost.length+1];
        for (int i = 2; i < cost.length + 1; ++i) {
            dp[i] = Math.min(dp[i- 2] + cost[i - 2], dp[i - 1] + cost[i - 1]);
        }
        return dp[cost.length];
    }
}
