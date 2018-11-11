package str.subsequence;

public class LeetCode_730_CountDifferentPalindromicSubsequences {
    public static void main(String[] args) {
        System.out.println(countPalindromicSubsequences("bccb"));
    }

    public static int countPalindromicSubsequences(String S) {
        int n = S.length(), M = 1000000007;
        int [][]dp = new int[n][n];
        for (int i = 0; i < n; ++i) dp[i][i] = 1;
        for (int len = 1; len < n; ++len) {
            for (int i = 0; i < n - len; ++i) {
                int j = i + len;
                if (S.charAt(i) == S.charAt(j)) {
                    int left = i + 1, right = j - 1;
                    while (left <= right && S.charAt(left) != S.charAt(i)) ++left;
                    while (left <= right && S.charAt(right) != S.charAt(i)) --right;
                    if (left > right) {
                        dp[i][j] = dp[i + 1][j - 1] * 2 + 2;
                    } else if (left == right) {
                        dp[i][j] = dp[i + 1][j - 1] * 2 + 1;
                    } else {
                        dp[i][j] = dp[i + 1][j - 1] * 2 - dp[left + 1][right - 1];
                    }
                } else {
                    dp[i][j] = dp[i][j - 1] + dp[i + 1][j] - dp[i + 1][j - 1];
                }
                dp[i][j] = (dp[i][j] < 0) ? dp[i][j] + M : dp[i][j] % M;
            }
        }
        return dp[0][n - 1];
    }
}
