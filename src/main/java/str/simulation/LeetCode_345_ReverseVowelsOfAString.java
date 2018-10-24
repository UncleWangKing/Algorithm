package str.simulation;

/**
 * @author ZhangDaPang 285296372@qq.com
 * @date 2018/10/24 16:02
 */
public class LeetCode_345_ReverseVowelsOfAString {
    public static void main(String[] args) {
        System.out.println(reverseVowels("leetcode"));
    }

    public static String reverseVowels(String s) {
        int left = 0, right = s.length() - 1;
        String t = "aeiouAEIOU";
        while (left < right) {
            if (-1 == t.indexOf(s.charAt(left))) ++left;
            else if (-1 == t.indexOf(s.charAt(right))) --right;
            else swap(s[left++], s[right--]);
        }
        return s;
    }
}
