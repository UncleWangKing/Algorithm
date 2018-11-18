package str.wildcard;

public class LeetCode_44_WildcardMatching {
    public static void main(String[] args) {
//        String s = "acdcb", p = "a*c?b";//false
        String s = "acdcb", p = "a*?b";//true
        System.out.println(isMatch(s, p));
    }

    /**
     * O(m*n) 贪心 回溯
     * 回溯部分:
     * 本质就是遇到*就挨个尝试让*从空串开始用，之后继续，
     * 如果之后配不上，可能是*用错了，让*多配一个，sp分别回溯再试
     * 贪心部分:
     * 只用关心最后一个*
     * 如果之前的*和最后一个*之间没有非*字母，也就是连续的全是*，那么可以当成一个*，也就成了关心最后一个*
     * 如果之前的*和最后一个*之间有非*字母，也就是*(非*，可能是普通字母a，也可能是?)*，配到最后一个*，表示之前的非*都被消耗了，是最理想情况，绝对不用回溯。
     */
    public static boolean isMatch(String s, String p) {
        int sIndex = 0, pIndex = 0, match = 0, starIdx = -1;
        while (sIndex < s.length()){
            if (pIndex < p.length()  && (p.charAt(pIndex) == '?' || s.charAt(sIndex) == p.charAt(pIndex))){
                sIndex++;
                pIndex++;
            }
            else if (pIndex < p.length() && p.charAt(pIndex) == '*'){
                /**
                 * 保存p中最后出现的*位置
                 */
                starIdx = pIndex;
                /**
                 * 保存s中遇到最后*时候的字符位置
                 */
                match = sIndex;
                /**
                 * 只让p位置++因为*可以当空串，所以s的不能被跳过一个
                 */
                pIndex++;
            }
            /**
             * 没配上，如果之前p出现过*，那么可能是*没用对，回溯再试
             */
            else if (starIdx != -1){
                /**
                 * p的最后一个*的上一次配法不行 要尝试躲让*配一个
                 * 也就是匹配回溯了
                 */
                match++;
                /**
                 * s p位置进行相应回溯 s回溯到match位置 + 1 p回溯到最后一个*后方
                 */
                pIndex = starIdx + 1;
                sIndex = match;
            }
            /**
             * p不是*也没配上 GG
             */
            else return false;
        }

        /**
         * 上方保证了s被配完
         * 因为*可以被当空串，所以s配完后 如果p后面有多余的*是依然可以配上的
         */
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
