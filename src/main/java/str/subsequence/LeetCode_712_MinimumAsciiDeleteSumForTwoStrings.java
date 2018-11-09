package str.subsequence;

public class LeetCode_712_MinimumAsciiDeleteSumForTwoStrings {
    public static void main(String[] args) {
        String s1 = "delete", s2 = "leet";
        System.out.println(minimumDeleteSum(s1, s2));
    }

    /**
     * dp[i][j] = s1[i - 1] == s2[j - 1] ? : dp[i - 1][j - 1] : min(dp[i - 1][j] + s1[i - 1], dp[i][j - 1] +s2[j - 1])
     * dp[i][j]为删掉的字符的ascii值
     */
    public static int minimumDeleteSum(String s1, String s2) {
        int m = s1.length(), n = s2.length();
        int[][] dp = new int[m + 1][n + 1];
        for (int j = 1; j <= n; ++j) dp[0][j] = dp[0][j - 1] + s2.charAt(j - 1);
        for (int i = 1; i <= m; ++i) {
            dp[i][0] = dp[i - 1][0] + s1.charAt(i - 1);
            for (int j = 1; j <= n; ++j) {
                dp[i][j] = (s1.charAt(i - 1) == s2.charAt(j - 1)) ? dp[i - 1][j - 1]
                        : Math.min(dp[i - 1][j] + s1.charAt(i - 1), dp[i][j - 1] + s2.charAt(j - 1));
            }
        }
        return dp[m][n];
    }

    /**
     * 两个字符串Ascii总值一定，求出Ascii值最大的公共子序列，然后总值减去其2倍即可。
     * 依然是求公共子序列，但是要求不再是“最长”，而是Ascii值最大，dp条件变变即可。
     * 有没有二维走楼梯变二维带权值走楼梯的感觉？
     * dp[i][j] 代表将s1 0 到 i - 1 和 s2 0 到 j - 1 的Ascii值最大的公共子序列
     * if (s1.charAt(i - 1) == s2.charAt(j - 1))//相同 对于Ascii值纯增益 加上就行
     *  dp[i][j] = dp[i - 1][j - 1] + s1.charAt(i - 1);
     * else//不同 选之前最大的
     *  dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
     */
    public static int minimumDeleteSum2(String s1, String s2) {
        int m = s1.length(), n = s2.length();
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 1; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1))
                    dp[i][j] = dp[i - 1][j - 1] + s1.charAt(i - 1);
                else
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
            }
        }

        int sum1 = 0, sum2 = 0;
        for (char s : s1.toCharArray())
            sum1 += s;
        for (char s : s2.toCharArray())
            sum2 += s;

        return sum1 + sum2 - 2 * dp[m][n];
    }
}
