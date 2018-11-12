package str.dp;

public class LeetCode_97_InterleavingString {
    public static void main(String[] args) {
        String s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac";//ture
        System.out.println(isInterleave(s1, s2, s3));
    }

    /**
     * 左方或者上方有一条路能通就行
     */
    public static boolean isInterleave(String s1, String s2, String s3) {
        if (s1.length() + s2.length() != s3.length()) return false;
        int n1 = s1.length();
        int n2 = s2.length();
        boolean[][] dp = new boolean[n1 + 1][n2 + 1];
        dp[0][0] = true;
        for (int i = 1; i <= n1; ++i) {
            dp[i][0] = dp[i - 1][0] && (s1.charAt(i - 1) == s3.charAt(i - 1));
        }
        for (int i = 1; i <= n2; ++i) {
            dp[0][i] = dp[0][i - 1] && (s2.charAt(i - 1) == s3.charAt(i - 1));
        }
        for (int i = 1; i <= n1; ++i) {
            for (int j = 1; j <= n2; ++j) {
                dp[i][j] =
                        (dp[i - 1][j] && s1.charAt(i - 1) == s3.charAt(i - 1 + j))
                        ||
                        (dp[i][j - 1] && s2.charAt(j - 1) == s3.charAt(j - 1 + i));
            }
        }
        return dp[n1][n2];
    }
}
