package dp.catalan;

/**
 * @author ZhangDaPang 285296372@qq.com
 * @date 2018/11/23 16:10
 */
public class LeetCode_96_UniqueBinarySearchTrees {
    public static void main(String[] args) {
        System.out.println(numTrees(5));
    }

    /**
     * 卡塔兰数列
     */
    public static int numTrees(int n) {
        int []dp = new int[n + 1];

        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; ++i) {
            for (int j = 0; j < i; ++j) {
                dp[i] += dp[j] * dp[(i - 1) - j];//多余的括号为了便于观察表达式含义
            }
        }

        return dp[n];
    }
}
