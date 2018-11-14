package str.slidwindow;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ZhangDaPang 285296372@qq.com
 * @date 2018/11/14 18:22
 */
public class LeetCode_438_FindAllAnagramsInAString {
    public static void main(String[] args) {
        String s = "cbaebabacd";
        String p = "abc";
        System.out.println(findAnagrams(s, p));
    }

    /**
     * 类似76 但是这是紧挨着的 也就是子串而不是子序列
     */
    public static List<Integer> findAnagrams(String s, String p) {
        List<Integer> res = new ArrayList<>();
        if (s.isEmpty()) return res;
        int []map = new int[256];
        for (char c : p.toCharArray()) ++map[c];
        return res;
    }
}
