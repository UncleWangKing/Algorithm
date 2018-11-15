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
        System.out.println(isMatch(s, p));
    }

    /**
     * 思路:分类讨论
     * 1. *.    aaab a*.b     //如何只让*配一个
     * 2. .*    abcac a.*c   //如何配到第二个c
     * 3. ** 和 *一个意思
     * 4. .. 没啥特别两个任意字符而已
     * 那么关键就在 *和. 的两种组合的情况
     */
    public static boolean isMatch(String s, String p) {
        for (int i = 0; i < s.length(); i++) {
            if(i >= p.length()) {
                return false;
            }else if('*' == p.charAt(i)){
                if(i > 0)
                    return isWildcardMatch(p.charAt(i - 1), s.substring(i, s.length()), p.substring(i, p.length()));
                else //p是*开头 直接去了
                    return isMatch(s.substring(i, s.length()), p.substring(i + 1, p.length()));
            }else if(s.charAt(i) != p.charAt(i) && '.' != p.charAt(i)){//String s = "aab", p = "c*a*b" c可能是0次 看下后面是否为*
                if(i + 1 < p.length() && '*' == p.charAt(i + 1))
                    return isMatch(s.substring(i, s.length()), p.substring(i + 2, p.length()));
                else
                    return false;
            }//'.' == p.charAt(i) 一定能配上 算过 .*情况会被pre拿出来
        }
        return s.length() >= p.length();
    }

    public static boolean isWildcardMatch(char pre, String s, String p) {
        int index = 0;
        for (int i = 0; i < s.length() && (pre == s.charAt(i) || pre == '.'); i++) {
            if(isMatch(s.substring(index, s.length()), p.substring(1, p.length()))){
                return true;
            }

        }
        return false;
    }

    public static boolean isMatch2(String s, String p) {
        if (p.isEmpty()) return s.isEmpty();
        if (1 == p.length()) {
            return (1 == s.length() && (s.charAt(0) == p.charAt(0) || p.charAt(0) == '.'));
        }
        if ('*' != p.charAt(1)) {
            if (s.isEmpty()) return false;
            return (s.charAt(0) == p.charAt(0) || '.' == p.charAt(0)) && isMatch(s.substring(1, s.length()), p.substring(1, p.length()));
        }
        while (!s.isEmpty() && (s.charAt(0) == p.charAt(0) ||  '.' == p.charAt(0))) {
            if (isMatch(s, p.substring(2, p.length()))) return true;
            s = s.substring(1, s.length());
        }
        return isMatch(s, p.substring(2, p.length()));
    }
}
