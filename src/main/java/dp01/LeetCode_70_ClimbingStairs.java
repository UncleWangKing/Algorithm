package dp01;

/**
 * @author ZhangDaPang 285296372@qq.com
 * @date 2018/7/3 10:11
 */

/**
 * -Xss180k 栈空间 3000次爆栈
 */
public class LeetCode_70_ClimbingStairs {
    public static void main(String[] args) {
        int n = 30;
        int[] dp = new int[n+1];
//        System.out.println("climbStairs_recursion");
//        System.out.println(climbStairs_recursion(n));
        System.out.println("climbStairs_dp_recursion");
        System.out.println(climbStairs_dp_recursion(n, dp));
        System.out.println("climbStairs_dp_loop");
        System.out.println(climbStairs_dp_loop(n));
        System.out.println("climbStairs_dp_loop_lessMemory");
        System.out.println(climbStairs_dp_loop_lessMemory(n));
    }

    /**
     * 状态转换方程:
     * dp[i] = dp[i-2] + dp[i-1] (dp[i]代表有多少种方法可到达第i层)
     * 自顶向下(top-down) OR 自底向上(bottom-up) ？
     * 答案:自底向上(bottom-up)
     * 看似递归是自顶向下 但是只是将问题划分出来
     * 真正的结果是弹栈时从dp[1]->dp[n]的结果出现
     */
    public static int climbStairs_recursion(int n){
        if(1 == n)
            return 1;
        else if(2 == n)
            return 2;
        return climbStairs_recursion(n-2) + climbStairs_recursion(n-1);
    }

    /**
     * 状态转换方程:
     * dp[i] = dp[i-2] + dp[i-1] (dp[i]代表有多少种方法可到达第i层)
     * 自顶向下(top-down) OR 自底向上(bottom-up) ？
     * 答案:自底向上(bottom-up)
     * 看似递归是自顶向下 但是只是将问题划分出来
     * 真正的结果是弹栈时从dp[1]->dp[n]的结果出现
     * dp消除重叠子问题
     */
    public static int climbStairs_dp_recursion(int n, int[] dp){
        if(1 == n)
            return 1;
        else if(2 == n)
            return 2;
        if(0 == dp[n])
            dp[n] = climbStairs_dp_recursion(n-2, dp) + climbStairs_dp_recursion(n-1, dp);
        return dp[n];
    }

    /**
     * 状态转换方程:
     * dp[i] = dp[i-2] + dp[i-1] (dp[i]代表有多少种方法可到达第i层)
     * 改为循环 更直观的bottom-up
     */
    public static int climbStairs_dp_loop(int n){
        if(1 == n)
            return 1;
        else if(2 == n)
            return 2;

        int[] dp = new int[n+1];
        int res = 0;
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i < n+1; i++) {
            res = dp[i] = dp[i-2] + dp[i-1];
        }
        return res;
    }

    /**
     * 状态转换方程:
     * dp[i] = dp[i-2] + dp[i-1] (dp[i]代表有多少种方法可到达第i层)
     * 改为循环 更直观的bottom-up
     * 优化内存使用(滚动数组---只使用每一轮计算所需的缓存，通常是上一轮或者多轮的结果)
     * 分析可得 只需要两个int变量交替使用即可达到要求
     */
    public static int climbStairs_dp_loop_lessMemory(int n){
        if(1 == n)
            return 1;
        else if(2 == n)
            return 2;

        int res = 0;
        int temp1 = 1;
        int temp2 = 2;
        for (int i = 3; i < n+1; i++) {
            res = temp1 + temp2;
            temp1 = temp2;
            temp2 = res;
        }
        return res;
    }
}
