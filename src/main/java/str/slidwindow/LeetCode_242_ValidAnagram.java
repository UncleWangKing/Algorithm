package str.slidwindow;

/**
 * @author ZhangDaPang 285296372@qq.com
 * @date 2018/11/14 18:24
 */
public class LeetCode_242_ValidAnagram {
    public static void main(String[] args) {
        System.out.println(isAnagram("rat", "tar"));
    }

    /**
     * 核心思路，检测t是否和s的字母种类以及相应个数完全一样。记录s各类字母的个数，然后在t中匹配。
     * 记录的结构可以用map，不过因为字母全小写且仅有26个，那么可以用数组代替。
     * 这个数组代替map的操作，是字符串类题目的基操。
     */
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
