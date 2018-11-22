package str.slidwindow;

/**
 * @author ZhangDaPang 285296372@qq.com
 * @date 2018/11/14 18:24
 */
public class LeetCode_242_ValidAnagram {
    public static void main(String[] args) {
        System.out.println(isAnagram("rat", "tar"));
    }

    public static boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) return false;
        int m[] = new int[26];
        for (int i = 0; i < s.length(); ++i) ++m[s.charAt(i) - 'a'];
        for (int i = 0; i < t.length(); ++i) {
            if (--m[t.charAt(i) - 'a'] < 0) return false;
        }
        return true;
    }
}
