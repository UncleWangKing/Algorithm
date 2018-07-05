package dp02;

import java.util.Arrays;

/**
 * @author ZhangDaPang 285296372@qq.com
 * @date 2018/7/3 13:50
 */
public class LeetCode_62_UniquePaths {
    public static void main(String[] args) {
        System.out.println("uniquePaths_dp_loop");
        System.out.println(uniquePaths_dp_loop(6,3));
        System.out.println("uniquePaths_dp_loop_lessMemory");
        System.out.println(uniquePaths_dp_loop_lessMemory(6,3));
        System.out.println("uniquePaths_math");
        System.out.println(uniquePaths_math(6,3));
    }

    /**
     * 状态转换方程:
     * dp[i][j] = dp[i-1][j] + dp[i][j-1] (dp[i][j]代表有多少种方法可到达第i行j列)
     *
     * dp数组需要将第一行第一列初始化为1
     * 例:
     * 若m = 4 ， n = 3
     * {{1, 1, 1},
     *  {1, 0, 0},
     *  {1, 0, 0},
     *  {1, 0, 0}}
     *
     *  可选择横向 OR 纵向扫描 （根据题意，无论横纵，从左到右，从上到下不变）
     *
     *  此处选择横向扫描
     */
    public static int uniquePaths_dp_loop(int m, int n) {
        int [][]dp = new int[m][n];
        //初始化为1
        Arrays.fill(dp[0], 1);
        for (int i = 1; i < m; i++) {
            dp[i][0] = 1;
        }
        //dp
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i-1][j] + dp[i][j-1];
            }
        }
        return dp[m-1][n-1];
    }
    /**
     * 例:
     * 若m = 4 ， n = 3
     * {{1, 1, 1},
     *  {1, 0, 0},
     *  {1, 0, 0},
     *  {1, 0, 0}}
     *
     * 优化内存使用
     * 分析：
     * 横向扫描时
     *  每“格”的值只需要 上方 和 左方 的值
     *  --> 每“行”的值只需要前一行 和 当前行第一列(且默认值为1)
     * 1.前一行:已经存在于dp[]中 因为是上一轮的结果 dp[]第一轮初始化为1
     * 2.当前行第一列:(j = 1) dp[j] = dp[1] + 1 = dp[j] + dp[j-1] --- 由于从1开始遍历dp[0]永远等于1
     * --> dp[j] = dp[j] + dp[j-1]; --- 此刻等号右边dp[j]代表上方，dp[j-1]代表左方
     * --> dp[j] += dp[j-1];
     */
    public static int uniquePaths_dp_loop_lessMemory(int m, int n) {
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
