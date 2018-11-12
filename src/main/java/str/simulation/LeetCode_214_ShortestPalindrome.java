package str.simulation;

public class LeetCode_214_ShortestPalindrome {
    public static void main(String[] args) {
        System.out.println(shortestPalindrome3("adcba"));
    }

    /**
     * 只能从前面加 双指针拼呗 遇到缺的 补上 完事
     */
    public static String shortestPalindrome(String s) {
        String r = new StringBuilder(s).reverse().toString();
        String t = s + "#" + r;
        int[] next = new int[t.length()];
        for (int i = 1; i < t.length(); ++i) {
            int j = next[i - 1];
            while (j > 0 && t.charAt(i) != t.charAt(j)) j = next[j - 1];
            j += (t.charAt(i) == t.charAt(j)) ? 1 : 0;
            next[i] = j;
        }
        return r.substring(0, s.length() - next[t.length() - 1]) + s;
    }

    /**
     * 只算头部部分 求出0开头的最大回文子串 然后右方剩余部分翻转就是要补在头部的
     */
    public static String shortestPalindrome2(String s) {
        int i = 0, end = s.length() - 1, j = end;
        char []arr = s.toCharArray();
        while (i < j) {
            if (arr[i] == arr[j]) {
                ++i; --j;
            } else {
                i = 0; --end; j = end;
            }
        }
        return new StringBuilder(s.substring(end + 1)).reverse().toString() + s;
    }

    /**
     * 漂亮的递归
     */
    public static String shortestPalindrome3(String s) {
        int j = 0;
        // 找出s对于s.reverse的子串
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == s.charAt(j)) {
                j++;
            }
        }
        // 递归边界: s本身就是回文子串
        if (j == s.length()) {
            return s;
        }
        String suffix = s.substring(j);

        return new StringBuffer(suffix).reverse().
                append(shortestPalindrome(s.substring(0, j))).
                append(suffix).
                toString();
    }
}
