package str.dp;

public class LeetCode_115_DistinctSubsequences {
    public static void main(String[] args) {
        String S = "babgbag", T = "bag";//5
        System.out.println(numDistinct2(S, T));
    }

    /**
     *     Ø r a b b b i t
     *   Ø 1 1 1 1 1 1 1 1
     *   r 0 1 1 1 1 1 1 1
     *   a 0 0 1 1 1 1 1 1
     *   b 0 0 0 1 2 3 3 3
     *   b 0 0 0 0 1 3 3 3
     *   i 0 0 0 0 0 0 3 3
     *   t 0 0 0 0 0 0 0 3
     *   dp[i][j] = dp[i][j - 1] + (t.charAt(i - 1) == s.charAt(j - 1) ? dp[i - 1][j - 1] : 0);
     *   字符相等等于左方加左上方 不然等于左方
     *   什么意思？
     *   横向扫描到dp[i][j]了
     *
     *   一个s2[j - 1]进来问，我新增的值是否和s1当前的最后值s1[i - 1]相等
     *   不相等：
     *   由于s2新增进来的只可能当最后一个值，所以如果不等，那么完全不起任何作用，
     *   就和没有s2[j - 1]的时候情况一样，也就是等于左方值。
     *   相等：
     *   除了当没有我的情况左方外，我还能作为“新的”s1尾巴，所以把s1中没有[i - 1]情况下的所有情况也算上
     */
    public static int numDistinct(String s, String t) {
        int [][]dp = new int[t.length() + 1][s.length() + 1];
        for (int i = 0; i <= s.length(); ++i) dp[0][i] = 1;
        for (int i = 1; i <= t.length(); ++i) dp[i][0] = 0;
        for (int i = 1; i <= t.length(); ++i) {
            for (int j = 1; j <= s.length(); ++j) {
                dp[i][j] = dp[i][j - 1] + (t.charAt(i - 1) == s.charAt(j - 1) ? dp[i - 1][j - 1] : 0);
            }
        }
        return dp[t.length()][s.length()];
    }

    /**
     * 最快
     */
    public static int numDistinct2(String s, String t) {
        int[][] hash = new int[256][t.length() + 1];
        int[] cnt = new int[t.length() + 1];
        cnt[0] = 1;
        for (int i = 0; i < t.length();) {
            char c = t.charAt(i);
            hash[c][++hash[c][0]] = ++i;
        }
        for(char c : s.toCharArray()) {
            for(int i = hash[c][0]; i > 0; i--) {
                cnt[hash[c][i]] += cnt[hash[c][i] - 1];
            }
        }
        return cnt[t.length()];
    }
}
