package str.slidwindow;

/**
 * @author ZhangDaPang 285296372@qq.com
 * @date 2018/11/14 18:19
 */
public class LeetCode_567_PermutationInString {
    public static void main(String[] args) {
        String s1 = "ab", s2 = "eidbaooo";
        System.out.println(checkInclusion(s1, s2));
    }

    public static boolean checkInclusion(String s1, String s2) {
        int cnt = s1.length(), left = 0;
        int[] m = new int[128];
        for (char c : s1.toCharArray()) ++m[c];
        for (int right = 0; right < s2.length(); ++right) {
            if (m[s2.charAt(right)]-- > 0) --cnt;
            while (0 == cnt) {
                if (right - left + 1 == s1.length()) return true;
                if (++m[s2.charAt(left++)] > 0) ++cnt;
            }
        }
        return false;
    }
}
