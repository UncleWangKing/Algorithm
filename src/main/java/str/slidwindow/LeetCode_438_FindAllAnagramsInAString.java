package str.slidwindow;

import java.util.LinkedList;
import java.util.List;

/**
 * @author ZhangDaPang 285296372@qq.com
 * @date 2018/11/14 18:22
 */
public class LeetCode_438_FindAllAnagramsInAString {
    public static void main(String[] args) {
        String s = "cbaebabacd"; String p = "abc";//[0, 6]
//        String s = "abab"; String p = "ab";//[0, 1, 2]
        System.out.println(findAnagrams(s, p));
    }

    /**
     * 上一题只找一个，这题找所有。双指针的思路不变，只是中途不return而已。
     * 同时注意 s: "abab" p: "ab" 这个用例，不要找到一个串就跳过整个串。
     */
    public static List<Integer> findAnagrams(String s, String p) {
        List<Integer> res = new LinkedList<>();
        if (s.isEmpty()) return res;
        int[] m = new int[128];
        int left = 0, right = 0, cnt = p.length(), n = s.length();
        for (char c : p.toCharArray()) ++m[c];
        while (right < n) {
            if (m[s.charAt(right++)]-- > 0) --cnt;
            if (cnt == 0) res.add(left);
            /**
             * 利用了惰性判断 right - left == p.length() 不成立时
             * 右边不会执行，left也就不会递增。
             */
            if (right - left == p.length() && m[s.charAt(left++)]++ >= 0) ++cnt;
        }
        return res;
    }

}
