package dp01;

/**
 * @author ZhangDaPang 285296372@qq.com
 * @date 2018/7/3 10:11
 */
public class LeetCode_746_MinCostClimbingStairs {
    public static void main(String[] args) {
        int [] cost = {1, 100, 1, 1, 1, 100, 1, 1, 100, 100};
//        int [] cost = {10, 15, 20};

        System.out.println("minCostClimbingStairs_dp_loop");
        System.out.println(minCostClimbingStairs_dp_loop(cost));
        int [] dp = new int[cost.length + 1];
        System.out.println("minCostClimbingStairs_dp_recursion");
        System.out.println(minCostClimbingStairs_dp_recursion(cost.length, cost, dp));
        System.out.println("minCostClimbingStairs_recursion");
        System.out.println(minCostClimbingStairs_recursion(cost.length, cost));
        System.out.println("minCostClimbingStairs_dp_loop_lessMemory");
        System.out.println(minCostClimbingStairs_dp_loop_lessMemory(cost));
        System.out.println("minCostClimbingStairs_dp_otherWay_loop_lessMemory");
        System.out.println(minCostClimbingStairs_dp_otherWay_loop_lessMemory(cost));
    }

    /**
     * 关键:
     * 1.可以从0 或者 1位置为起始点！
     * 2.梯顶不是最后一个数 而是最后一个数后一个位置！
     * dp[i] = min(dp[i- 2] + cost[i - 2], dp[i - 1] + cost[i - 1]) (dp[i]代表“到达”第i层所需最小花费)
     * 自底向上(bottom-up)
     */
    public static int minCostClimbingStairs_recursion(int n, int[] cost) {
        if(0 == n || 1 == n)
            return 0;
        else if(2 == n)
            return Math.min(cost[0], cost[1]);

        return Math.min(minCostClimbingStairs_recursion(n - 2, cost) + cost[n - 2], minCostClimbingStairs_recursion(n - 1, cost) + cost[n - 1]);
    }

    /**
     * 关键:
     * 1.可以从0 或者 1位置为起始点！
     * 2.梯顶不是最后一个数 而是最后一个数后一个位置！
     * dp[i] = min(dp[i- 2] + cost[i - 2], dp[i - 1] + cost[i - 1]) (dp[i]代表“到达”第i层所需最小花费)
     * 自底向上(bottom-up)
     */
    public static int minCostClimbingStairs_dp_recursion(int n, int[] cost, int[] dp) {
        if(0 == n || 1 == n)
            return 0;
        else if(2 == n)
            return Math.min(cost[0], cost[1]);
        if(0 == dp[n])
            dp[n] = Math.min(minCostClimbingStairs_dp_recursion(n - 2, cost, dp) + cost[n - 2], minCostClimbingStairs_dp_recursion(n - 1, cost, dp) + cost[n - 1]);
        return dp[n];
    }

    /**
     * 关键:
     * 1.可以从0 或者 1位置为起始点！
     * 2.梯顶不是最后一个数 而是最后一个数后一个位置！
     * dp[i] = min(dp[i- 2] + cost[i - 2], dp[i - 1] + cost[i - 1]) (dp[i]代表“到达”第i层所需最小花费)
     * 自底向上(bottom-up)
     * 改为循环
     */
    public static int minCostClimbingStairs_dp_loop(int[] cost) {
        int[] dp = new int[cost.length + 1];
        for (int i = 2; i < cost.length + 1; ++i) {
            dp[i] = Math.min(dp[i- 2] + cost[i - 2], dp[i - 1] + cost[i - 1]);
        }
        return dp[cost.length];
    }

    /**
     * 关键:
     * 1.可以从0 或者 1位置为起始点！
     * 2.梯顶不是最后一个数 而是最后一个数后一个位置！
     * dp[i] = min(dp[i- 2] + cost[i - 2], dp[i - 1] + cost[i - 1]) (dp[i]代表“到达”第i层所需最小花费)
     * 自底向上(bottom-up)
     * 改为循环
     * 优化内存使用(滚动数组---只使用每一轮计算所需的缓存，通常是上一轮或者多轮的结果)
     * 分析可得 只需要两个int变量交替使用即可达到要求
     */
    public static int minCostClimbingStairs_dp_loop_lessMemory(int[] cost) {
        int temp1 = 0;
        int temp2 = 0;
        int res = 0;
        for (int i = 2; i < cost.length + 1; ++i) {
            res = Math.min(temp1 + cost[i - 2], temp2 + cost[i - 1]);
            temp1 = temp2;
            temp2 = res;
        }
        return res;
    }

    /**
     * 关键:
     * 1.可以从0 或者 1位置为起始点！
     * 2.梯顶不是最后一个数 而是最后一个数后一个位置！
     * dp[i] = cost[i] + min(dp[i- 1], dp[i - 2]) (dp[i]代表“经过”第i层所需最小花费)
     * 自底向上(bottom-up)
     * 改为循环
     * 优化内存使用(滚动数组---只使用每一轮计算所需的缓存，通常是上一轮或者多轮的结果)
     * 分析可得 只需要两个int变量交替使用即可达到要求
     */
    public static int minCostClimbingStairs_dp_otherWay_loop_lessMemory(int[] cost) {
        int temp1 = cost[0];
        int temp2 = cost[1];
        int res = 0;
        for (int i = 2; i < cost.length; ++i) {
            res = cost[i] + Math.min(temp1, temp2);
            temp1 = temp2;
            temp2 = res;
        }
        return Math.min(temp1, temp2);
    }
}
