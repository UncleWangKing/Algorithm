package dp.classic;

/**
 * @author ZhangDaPang 285296372@qq.com
 * @date 2018/11/23 19:09
 */
public class LeetCode_343_IntegerBreak {
    public static void main(String[] args) {
        System.out.println(integerBreak3(9));
    }

    /**
     * 老实数数
     */
    public static int integerBreak(int n) {
        if (n == 2 || n == 3) return n - 1;
        int res = 1;

        while (4 < n){
            res *= 3;
            n -= 3;
        }

        return res * n;
    }

    /**
     * 每多三个数就多一个3 dp
     */
    public static int integerBreak2(int n) {
        int[] dp = new int[58];
        dp[2] = 1;
        dp[3] = 2;
        dp[4] = 4;
        dp[5] = 6;
        dp[6] = 9;
        if(6 >= n) return dp[n];
        for (int i = 7; i <= n; ++i) {
            dp[i] = 3 * dp[i - 3];
        }
        return dp[n];
    }

    /**
     * 直接拆分出3的个数和余数
     * 余数只有2和4的情况 如何体现很巧妙
     * n -= 2 这个操作
     * 然后减去的2都在下方式子加了回来 确避免了 n % 3 == 0的情况。
     * 非常精彩
     * 将 [0,1,2]余数的可能 变成了[2,3,4]妙不可言
     */
    public static int integerBreak3(int n) {
        if (n == 2 || n == 3) return n - 1;
        if (n == 4) return 4;
        n -= 2;
        return (int)Math.pow(3, (n / 3)) * (n % 3 + 2);
    }
}
