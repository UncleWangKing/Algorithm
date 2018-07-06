package dp.other;

/**
 * @author ZhangDaPang 285296372@qq.com
 * @date 2018/7/6 14:39
 */

/**
 * 经典问题:最长公共子序列 Longest Common Sequence
 * 1:BDCABA
 * 2:ABCBDAB
 * Answer:BCBA 长度4
 * LeetCode上没有
 */
public class LCS {
    public static void main(String[] args) {
        String x = "BDCABA";
        String y = "ABCBDAB";
        System.out.println(longestCS(x, y));
    }

    /**
     * 状态转换方程
     * if(x_m == y_n)
     *  dp[m][n] = dp[m-1][n-1] + 1;
     * else
     *  dp[m][n] = max(dp[m-1][n],dp[m][n-1]) + 1;
     */
    public static int longestCS(String x, String y){
        char[] xList = x.toCharArray();
        char[] yList = y.toCharArray();
        int m = xList.length;
        int n = yList.length;
        int dp[][] = new int[m][n];

        /**
         * 横向扫描 初始化第一行和第一例
         * 默认是0 匹配上为1
         */
        for (int i = 0; i < n; i++) {
            if(xList[0] == yList[i])
                dp[0][i] = 1;
        }
        for (int i = 0; i < m; i++) {
            if(yList[0] == xList[i])
                dp[i][0] = 1;
        }

        /**
         * 开始根据方程递推
         */
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if(xList[i] == yList[j])
                    dp[i][j] = dp[i-1][j-1] + 1;
                else
                    dp[i][j] = Math.max(dp[i-1][j],dp[i][j-1]);
            }
        }

        return dp[m-1][n-1];
    }
}
