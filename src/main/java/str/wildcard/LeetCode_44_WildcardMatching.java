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
