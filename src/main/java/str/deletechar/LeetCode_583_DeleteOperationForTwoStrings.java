package str.deletechar;

public class LeetCode_583_DeleteOperationForTwoStrings {
    public static void main(String[] args) {
        System.out.println(minDistance("sea", "eat"));
    }

    public static int minDistance(String word1, String word2) {
        int lcs = longestCS_dp_betterlook(word1, word2);
        return word1.length() + word2.length() - 2 * lcs;
    }

    public static int longestCS_dp_betterlook(String x, String y){
        char[] xList = x.toCharArray();
        char[] yList = y.toCharArray();
        int m = xList.length;
        int n = yList.length;
        int dp[][] = new int[m + 1][n + 1];

        /**
         * 开始根据方程递推
         */
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if(xList[i - 1] == yList[j - 1])
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                else
                    dp[i][j] = Math.max(dp[i - 1][j],dp[i][j - 1]);
            }
        }

        return dp[m][n];
    }
}
