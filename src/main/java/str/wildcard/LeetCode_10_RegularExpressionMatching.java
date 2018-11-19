package str.wildcard;

public class LeetCode_10_RegularExpressionMatching {
    public static void main(String[] args) {
        String s = "mississippi", p = "mis*is*p*.";//false
//        System.out.println(isMatch(s, p));
//        s = "aab"; p = "c*a*b";//true
//        System.out.println(isMatch(s, p));
        s = "aa"; p = "a*";//true
//        System.out.println(isMatch(s, p));
//        s = "aa"; p = "a";//false
//        System.out.println(isMatch(s, p));
//        s = "ab"; p = ".*";//true
//        System.out.println(isMatch(s, p));
//        s = "aaa"; p = "ab*a*c*a";//true
        System.out.println(isMatch2(s, p));
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

    public static boolean isMatch3(String s, String p) {
        int m = s.length(), n = p.length();
        boolean dp[][] = new boolean[m + 1][n + 1];
        dp[0][0] = true;
        for (int i = 0; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                if (j > 1 && p.charAt(j - 1) == '*') {
                    dp[i][j] = dp[i][j - 2] || (i > 0 && (s.charAt(i - 1) == p.charAt(j - 2) || p.charAt(j - 2) == '.') && dp[i - 1][j]);
                } else {
                    dp[i][j] = i > 0 && dp[i - 1][j - 1] && (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '.');
                }
            }
        }
        return dp[m][n];
    }
}
