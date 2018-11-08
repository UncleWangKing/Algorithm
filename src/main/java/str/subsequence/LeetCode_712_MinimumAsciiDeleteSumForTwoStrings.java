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
     * 和我一开始思路一样 求最大ascii值的 公共川
     * 注意这里不是LCS 不是长度最长
     * 有没有普通走楼梯变成了带权值走楼梯的意思
     */
    public static int minimumDeleteSum2(String s1, String s2) {
        int m = s1.length(), n = s2.length();
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 1; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) dp[i][j] = dp[i - 1][j - 1] + s1.charAt(i - 1);
                else dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
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
