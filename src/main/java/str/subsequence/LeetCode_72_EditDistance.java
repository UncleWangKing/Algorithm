package str.subsequence;

public class LeetCode_72_EditDistance {
    public static void main(String[] args) {
        String word1 = "intention", word2 = "execution";
        System.out.println(minDistance2(word1, word2));
    }

    /**
     * 本质还是LCS。回忆这三个题走来。
     * 1.最长公共子序列。
     * 2.Ascii值最大公共子序列。
     * 3."消耗"最小公共子序列。
     * dp[i][j] 代表将s1 0 到 i - 1 和 s2 0 到 j - 1 完成"转换"的最小消耗
     * if(s1[i - 1] == s2[j - 1])//相同 无消耗
     *  dp[i][j] = dp[i - 1][j - 1];
     * else
     *  dp[i][j] = min(dp[i - 1][j], dp[i][j - 1], dp[i - 1][j - 1]) + 1;
     * 上方三个式子的意义
     * dp[i - 1][j]左边少一个 要多增1
     * dp[i][j - 1]右边多一个 要多删1
     * dp[i - 1][j - 1]左右都少一个 要多变1
     * 初始项易得
     * dp[0][j] = j;
     * dp[i][0] = i;
     */
    public static int minDistance(String word1, String word2) {
        int n1 = word1.length(), n2 = word2.length();
        int dp[][] = new int[n1 + 1][n2 + 1];
        for (int i = 0; i <= n1; ++i) dp[i][0] = i;
        for (int i = 0; i <= n2; ++i) dp[0][i] = i;
        for (int i = 1; i <= n1; ++i) {
            for (int j = 1; j <= n2; ++j) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1])) + 1;
                }
            }
        }
        return dp[n1][n2];
    }

    /**
     * 递归写法 更容易看出方程中三个项的意义
     */
    public static int minDistance2(String word1, String word2) {
        if (word1.equals("") || word2.equals("")) {
            return Math.max(word1.length(), word2.length());
        }
        int dp[][] = new int[word1.length()][word2.length()];
        for (int i = 0; i < word1.length(); i++) {
            for (int j = 0; j < word2.length(); j++) {
                dp[i][j] = -1;
            }
        }
        minDistance(word1.toCharArray(), 0, word2.toCharArray(), 0, dp);
        return dp[0][0];
    }

    private static int minDistance(char[] word1, int i, char[] word2, int j, int[][] dp) {
        if (i == word1.length || j == word2.length) {
            return Math.max(word1.length - i, word2.length - j);
        }
        if (dp[i][j] >= 0) {
            return dp[i][j];
        }
        int d = 0;
        if (word1[i] == word2[j]) {
            d = minDistance(word1, i + 1, word2, j + 1, dp);

        } else {
            int replace = minDistance(word1, i + 1, word2, j + 1, dp);
            int del = minDistance(word1, i + 1, word2, j, dp);
            int insert = minDistance(word1, i, word2, j + 1, dp);
            d = Math.min(Math.min(replace, del), insert) + 1;
        }
        dp[i][j] = d;
        return d;
    }
}
