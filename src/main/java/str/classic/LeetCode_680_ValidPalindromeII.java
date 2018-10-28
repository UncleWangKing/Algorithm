package str.classic;

public class LeetCode_680_ValidPalindromeII {
    public static void main(String[] args) {
        System.out.println(validPalindrome("ababd"));
    }

    /**
     * 遇到不一样的 左右都试试能不能跳过然后剩下的形成回文
     */
    public static boolean validPalindrome(String s) {
        int left = 0, right = s.length() - 1;
        while (left < right) {
            if (s.charAt(left) == s.charAt(right)) {
                ++left; --right;
            } else {
                int l = left, r = right - 1;
                while (l < r) {
                    if (s.charAt(l) != s.charAt(r)) break;
                    ++l; --r;
                    if (l >= r) return true;
                }
                ++left;
                while (left < right) {
                    if (s.charAt(left) != s.charAt(right)) return false;
                    ++left; --right;
                }
            }
        }
        return true;
    }

    /**
     * 妙不可言
     */
    public static boolean validPalindrome2(String s) {
        return helper(s,0,s.length() - 1,false);
    }

    private static boolean helper(String s,int left, int right, boolean flag){
        if(left >= right) return true;
        if(s.charAt(left) == s.charAt(right)){
            return helper(s,left + 1,right - 1,flag);
        }
        if(flag) return false;

        return helper(s,left+1,right,true) || helper(s,left,right-1,true);
    }
}
