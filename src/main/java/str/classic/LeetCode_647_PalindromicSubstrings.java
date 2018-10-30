package str.classic;

public class LeetCode_647_PalindromicSubstrings {
    public static void main(String[] args) {
        System.out.println(countSubstrings("aaa"));
    }

    /**
     * 暴力
     */
    public static int countSubstrings(String s) {
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            for (int j = i; j < s.length(); j++) {
                int left = i, right = j;
                while (left <= right){
                    if(s.charAt(left) == s.charAt(right)){
                        left++;right--;
                    }else
                        break;
                }
                if(left > right)
                    count++;
            }
        }
        return count;
    }
    /**
     * dp
     */
    public static int countSubstrings2(String s) {
        int n = s.length(), res = 0;
        boolean[][] dp = new boolean[n][n];
        for (int i = n - 1; i >= 0; --i) {
            for (int j = i; j < n; ++j) {
                dp[i][j] = (s.charAt(i) == s.charAt(j)) && (j - i <= 2 || dp[i + 1][j - 1]);
                if (dp[i][j]) ++res;
            }
        }
        return res;
    }

    /**
     * 奇数长度回文 和 偶数长度回文数之和
     */
    public static int countSubstrings3(String s) {
        int res = 0;
        for (int i = s.length(); i >= 0; --i) {
            res += getCentersSubstrings(i, i, s)
                    + getCentersSubstrings(i, i + 1, s);
        }
        return res;
    }

    public static int getCentersSubstrings(int l, int r, String s) {
        int res = 0;
        int len = s.length();
        while (l >= 0 && r < len && s.charAt(l--) == s.charAt(r++)) {
            ++res;
        }
        return res;
    }
}
