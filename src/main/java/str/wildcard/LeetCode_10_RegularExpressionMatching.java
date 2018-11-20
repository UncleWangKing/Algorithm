package str.wildcard;

public class LeetCode_10_RegularExpressionMatching {
    public static void main(String[] args) {
        String s = "mississippi", p = "mis*is*p*.";//false
//        System.out.println(isMatch(s, p));
//        s = "aab"; p = "c*a*b";//true
//        System.out.println(isMatch(s, p));
//        s = "aa"; p = "a*";//true
//        System.out.println(isMatch(s, p));
//        s = "aa"; p = "a";//false
//        System.out.println(isMatch(s, p));
//        s = "ab"; p = ".*";//true
//        System.out.println(isMatch(s, p));
        s = "aaa"; p = "ab*a*c*a";//true
        System.out.println(isMatch3(s, p));
    }

    public static boolean isMatch(String s, String p) {
        if (p.isEmpty()) return s.isEmpty();
        if (1 == p.length()) {
            return (1 == s.length() && (s.charAt(0) == p.charAt(0) || p.charAt(0) == '.'));
        }
        if ('*' != p.charAt(1)) {
            if (s.isEmpty()) return false;
            return (s.charAt(0) == p.charAt(0) || '.' == p.charAt(0)) && isMatch(s.substring(1, s.length()), p.substring(1, p.length()));
        }
        /**
         * 到这里p(1)一定是* 然后将*前一个数 从0次开始 逐个+1
         * s = s.substring(1, s.length()) s配了一个 p不变
         * 是+1次的成型所在
         */
        while (!s.isEmpty() && (s.charAt(0) == p.charAt(0) ||  '.' == p.charAt(0))) {
            if (isMatch(s, p.substring(2, p.length()))) return true;
            s = s.substring(1, s.length());
        }
        return isMatch(s, p.substring(2, p.length()));
    }

    /**
     * 上一个写法简化版
     */
    public static boolean isMatch2(String s, String p) {
        /**
         * p是空 那么是有s是空能配上
         */
        if (p.isEmpty()) return s.isEmpty();
        /**
         * p(1)是* 与否 直接迭代 反正都递归了 也就将上方的循环写成了递归
         * 本质还是 等于*时 将*前一个数 从0次开始 依次使用
         */
        if (p.length() > 1 && p.charAt(1) == '*') {
            /**
             * 左方是用0次 右方是+1次
             * isMatch2(s.substring(1, s.length()), p) 这个写法 s配了一个 p不变
             * 是+1次的成型所在
             */
            return isMatch2(s, p.substring(2, p.length())) || (! s.isEmpty() && (s.charAt(0) == p.charAt(0) || p.charAt(0) == '.') && isMatch2(s.substring(1, s.length()), p));
        } else {
            return !s.isEmpty() && (s.charAt(0) == p.charAt(0) || p.charAt(0) == '.') && isMatch2(s.substring(1, s.length()), p.substring(1, p.length()));
        }
    }

    /**
     * "aaa"; p = "ab*a*c*a"
     *
     *      a b * a * c * a
     *    T F F F F F F F F
     *  a F T F T F T F T F
     *  a F F F F T T F T T
     *  a F F F F F T F T T
     *
     */

    public static boolean isMatch3(String s, String p) {
        int m = s.length(), n = p.length();
        boolean dp[][] = new boolean[m + 1][n + 1];
        dp[0][0] = true;
        /**
         * 初始项 默认了输入数据不可能以*开头！
         *  s ""
         *  p "a*"
         *  这种情况看初始项 如果为*那么*前可以为0个 所以对应的dp[i][j - 2]位置如果可配 那么一定可配
         */
        for (int i = 1; i <= p.length(); i++) {
            if(p.charAt(i - 1) == '*')
                dp[0][i] = dp[0][i - 2];
        }
        for (int i = 1; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                /**
                 * 不是* 直接看能否配上 这里不是*的方法比较委婉 因为s中没有* 配上一定就不是*了
                 */
                if(s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '.') {
                    dp[i][j] = dp[i - 1][j - 1];
                }else if(p.charAt(j - 1) == '*'){
                    /**
                     * 是* 那么 *当0个还是多个
                     */
                    if(s.charAt(i - 1) != p.charAt(j - 2) && p.charAt(j - 2) != '.'){
                        /**
                         *  s "b"
                         *  p "a*"
                         *  上一个值不等
                         * 只能当0个
                         */
                        dp[i][j] = dp[i][j - 2];
                    }else {
                        /**
                         *  上一个相等
                         * 可能当0或多个
                         *  s "a"
                         *  p "a*"
                         *  0个  dp[i][j - 2]
                         *  1个  dp[i - 1][j]
                         *  多个 dp[i][j - 1]
                         */
                        dp[i][j] = dp[i][j - 2] || dp[i - 1][j] || dp[i][j - 1];
                    }
                }
            }
        }
        return dp[m][n];
    }
}
