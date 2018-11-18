package str.wildcard;

public class LeetCode_44_WildcardMatching {
    public static void main(String[] args) {
//        String s = "acdcb", p = "a*c?b";//false
        String s = "acdcb", p = "a*?b";//true
        System.out.println(isMatch(s, p));
    }

    public static boolean isMatch(String s, String p) {
        int sIndex = 0, pIndex = 0, match = 0, starIdx = -1;
        while (sIndex < s.length()){
            // advancing both pointers
            if (pIndex < p.length()  && (p.charAt(pIndex) == '?' || s.charAt(sIndex) == p.charAt(pIndex))){
                sIndex++;
                pIndex++;
            }
            // * found, only advancing pattern pointer
            else if (pIndex < p.length() && p.charAt(pIndex) == '*'){
                starIdx = pIndex;
                match = sIndex;
                pIndex++;
            }
            // last pattern pointer was *, advancing string pointer
            else if (starIdx != -1){
                pIndex = starIdx + 1;
                match++;
                sIndex = match;
            }
            //current pattern pointer is not star, last patter pointer was not *
            //characters do not match
            else return false;
        }

        //check for remaining characters in pattern
        while (pIndex < p.length() && p.charAt(pIndex) == '*')
            pIndex++;

        return pIndex == p.length();
    }

    /**
     * s = "acdcb", p = "a*?b";
     *     a * ? b
     *   T F F F F
     * a F T T F F
     * c F F T T F
     * d F F T T F
     * c F F T T F
     * b F F T T T
     *
     * s内容固定，唯有p中?和*的情况特殊，?其实最简单，直接当匹配即可，*要特殊对待
     * 1.p.charAt(j - 1) != '*'
     *  就简单了 配么一样 要么p是? 且左上相等
     *  dp[i][j] = (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '?') && dp[i - 1][j - 1];
     * 2.p.charAt(j - 1) == '*'
     * s = s[0, i] = "acdcb" p = p[0, j] = "a*"为例
     * 此刻关心 三种情况
     *
     * 2.1.acdc  a*  s少一个      上方   -- 被p以*为结尾匹配上了 s多一个*                    依然可以匹配
     * 2.2.acdcb a   p少一个      左方   -- p在最后一个*之前都匹配上了，那么多个*可以当空    依然可以匹配
     * 2.3.acdc  a   sp各少一个   左上方 -- 之前的都能匹配 s加一个就能被p的星匹配            依然可以匹配
     *
     * 三种情况中 任意一个为true都可以让dp[i][j]匹配
     * 也就是
     * dp[i][j] = dp[i - 1][j] || dp[i][j - 1] || dp[i - 1][j - 1];
     * 这里可以简化一下
     * 因为在这个式子的递推方式下
     * 上方和 左上方 有一些特殊关系
     * 首先上方 也就是j不变，所以p.charAt(j - 1) == '*' 成立
     * dp[i - 1][j] = dp[i - 2][j] || dp[i - 1][j - 1] || dp[i - 2][j - 1];
     * 可以看到 左上方结果dp[i - 1][j - 1] 已经被"或"进了上方结果dp[i - 1][j]中
     * 所以左上方可以忽略 写成
     * dp[i][j] = dp[i - 1][j] || dp[i][j - 1];
     *  PS:左方结果就没有这个关系了，因为左方j已经变了 p.charAt(j - 2) 不一定等于 '*'。
     */
    public static boolean isMatch2(String s, String p) {
        int m = s.length(), n = p.length();
        boolean [][]dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;
        for (int i = 1; i <= n; ++i) {
            if (p.charAt(i - 1) == '*') dp[0][i] = dp[0][i - 1];
        }
        for (int i = 1; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                if (p.charAt(j - 1) == '*') {
                    dp[i][j] = dp[i - 1][j] || dp[i][j - 1];
                } else {
                    dp[i][j] = (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '?') && dp[i - 1][j - 1];
                }
            }
        }
        return dp[m][n];
    }
}
