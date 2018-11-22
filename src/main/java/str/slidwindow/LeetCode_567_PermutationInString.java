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

    /**
     * 双指针，这个方法也是这个系列的核心方法，之后的题大都使用了这个思想。
     * 用一个cnt记录遍历到的在s中的字符个数。
     * 0 == cnt表示两个指针包含的字符串中，已经找到了s的所有字符。
     * 此刻长度如果等于s，那么找到了。
     * 如果不等于s，那么中间有多余的字母，左边指针往右走，直到0 != cnt。
     * PS:可以用m[26]，当然也可以改成m[128]，多常数级的空间，但是少做很多次减法。
     * 你可以分别尝试一下这m[26]和m[128]在leetcode上的效率排名。
     */
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
