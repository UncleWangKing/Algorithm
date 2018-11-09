package str.subsequence;

public class LeetCode_583_DeleteOperationForTwoStrings {
    public static void main(String[] args) {
        System.out.println(minDistance("sea", "eat"));
    }

    /**
     * 一开始发现LeetCode没有LCS原题我是震精的，直接就是变种题，一点都不美丽。
     * 求删除最少，那么删除后留下的最长，也就删除最少，也就是求LCS了。
     * 子序列问题一下扩展到了二维。
     * 其实就像我们的走楼梯换到了二维一样。
     * dp[i][j] 代表s1[i - 1]和s2[j - 1]能取到的LCS。
     * 定义有多种，-1与否是指为了返回或者迭代方便，相信走楼梯过后你已经习惯了。
     * if(s1[i - 1] == s2[j - 1])
     * dp[i][j] = dp[i - 1][j - 1] + 1;
     * else
     * dp[i][j] = max(dp[i - 1][j], dp[i][j - 1]);
     * 之前说了，dp找方程是靠状态划分，其实两个字符串的dp，无非就是三种情况。
     * 1.s1加一个字符
     * 2.s2加一个字符
     * 3.s1,s2都加一个字符
     * 翻译成二维走楼梯对应的就是
     * 1.和左方有关
     * 2.和上方有关
     * 3.和左上方有关
     */
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
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
            }
        }

        return dp[m][n];
    }
}
