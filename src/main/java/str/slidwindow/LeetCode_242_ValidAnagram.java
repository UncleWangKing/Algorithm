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
        if (s.length() != t.length())
            return false;

        int[] sIntArray = new int[26];
        int[] tIntArray = new int[26];

        for (char c : s.toCharArray()) {
            sIntArray[c - 'a']++;
        }

        for (char c : t.toCharArray()) {
            tIntArray[c - 'a']++;
        }

        for (int i = 0; i < tIntArray.length; i++) {
            if(tIntArray[i] != sIntArray[i]) {
                return false;
            }
        }
        return true;
    }
}
