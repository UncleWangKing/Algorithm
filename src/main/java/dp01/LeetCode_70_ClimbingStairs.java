package dp01;

/**
 * @author ZhangDaPang 285296372@qq.com
 * @date 2018/7/3 10:11
 */
public class LeetCode_70_ClimbingStairs {
    static long count;
    public static void main(String[] args) {
        int n = 40;
        long[] dp = new long[n+1];
        System.out.println(f_dp(n, dp));
        System.out.println(count);
        count = 0;
        System.out.println(f(n));
        System.out.println(count);
    }
    public static long f_dp(int n, long[] dp){
        ++count;
        if(1 == n)
            return 1;
        else if(2 == n)
            return 2;
        if(0 == dp[n])
            dp[n] = f_dp(n-1, dp) + f_dp(n-2, dp);
        return dp[n];
    }
    public static long f(int n){
        ++count;
        if(1 == n)
            return 1;
        else if(2 == n)
            return 2;
        return f(n-1) + f(n-2);
    }
}
