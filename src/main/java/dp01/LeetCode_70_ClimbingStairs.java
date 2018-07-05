package dp01;

/**
 * @author ZhangDaPang 285296372@qq.com
 * @date 2018/7/3 10:11
 */
public class LeetCode_70_ClimbingStairs {
    static long count;
    public static void main(String[] args) {
        int n = 40;
        int[] dp = new int[n+1];
        System.out.println(climbStairs_dp_recursion(n, dp));
        System.out.println(count);

        System.out.println(climbStairs_dp_loop_lessMemory(n));
    }

    public static int climbStairs(int n){
        ++count;
        if(1 == n)
            return 1;
        else if(2 == n)
            return 2;
        return climbStairs(n-1) + climbStairs(n-2);
    }

    /**
     * 状态转换方程:
     * dp[i] = dp[i-1] + dp[i-2]
     * 自顶向下(top-down) OR 自底向上(bottom-up) ？
     * 答案:自底向上(bottom-up)
     * 看似递归是自顶向下 但是只是将问题划分出来
     * 真正的结果是弹栈时从dp[1]->dp[n]的结果出现
     */
    public static int climbStairs_dp_recursion(int n, int[] dp){
        ++count;
        if(1 == n)
            return 1;
        else if(2 == n)
            return 2;
        if(0 == dp[n])
            dp[n] = climbStairs_dp_recursion(n-1, dp) + climbStairs_dp_recursion(n-2, dp);
        return dp[n];
    }

    /**
     * 状态转换方程:
     * dp[i] = dp[i-1] + dp[i-2]
     * 改为循环 更直观的bottom-up
     */
    public static int climbStairs_dp_loop(int n){
        ++count;
        if(1 == n)
            return 1;
        else if(2 == n)
            return 2;

        int[] dp = new int[n+1];
        int res = 0;
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i < n+1; i++) {
            res = dp[i] = dp[i - 1] + dp[i - 2];
        }
        return res;
    }

    /**
     * 状态转换方程:
     * dp[i] = dp[i-1] + dp[i-2]
     * 改为循环 更直观的bottom-up
     * 并优化内存使用
     * 分析可得 只需要两个int变量交替使用即可达到要求
     */
    public static int climbStairs_dp_loop_lessMemory(int n){
        ++count;
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
