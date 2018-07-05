package dp02;

import java.util.Arrays;

/**
 * @author ZhangDaPang 285296372@qq.com
 * @date 2018/7/3 13:50
 */
public class LeetCode_62_UniquePaths {
    public static void main(String[] args) {
        System.out.println(uniquePaths_dp(7,3));
    }

    /**
     * 状态转换方程:
     * dp[i][j] = dp[i-1][j] + dp[i][j-1]
     */
    public static int uniquePaths_dp(int m, int n) {
        int [][]dp = new int[m][n];
        //初始化为1
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dp[i][j] = 1;
            }
        }
        //dp
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i-1][j] + dp[i][j-1];
            }
        }
        return dp[m-1][n-1];
    }

    public static int uniquePaths_dp_lessMemory(int m, int n) {
        int []dp = new int[n];
        Arrays.fill(dp, 1);
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[j] += dp[j-1];
            }
        }
        return dp[n-1];
    }

    /**
     * 如果我们仔细的看这个问题背后的数学模型，
     * 其实就是机器人总共走m+n-2步，
     * 其中m-1步往下走，n-1步往右走，
     * 本质上就是一个组合问题，
     * 也就是从m+n-2个不同元素中每次取出m-1个元素的组合数。
     * 根据组合的计算公式，我们可以写代码来求解即可。
     * @param m
     * @param n
     * @return
     */
    public static int uniquePaths_math(int m, int n) {
        //如果不是double 会有越界问题 这是一个阶乘
        double num = 1, denom = 1;
        int small = m > n ? n : m;
        for (int i = 1; i <= small - 1; ++i) {
            num *= m + n - 1 - i;
            denom *= i;
        }
        return (int)(num / denom);
    }
}
