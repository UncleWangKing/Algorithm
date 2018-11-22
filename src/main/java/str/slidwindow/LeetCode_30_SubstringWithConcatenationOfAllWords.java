package str.slidwindow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ZhangDaPang 285296372@qq.com
 * @date 2018/11/14 17:31
 */
public class LeetCode_30_SubstringWithConcatenationOfAllWords {
    public static void main(String[] args) {
        String s = "barfoothefoobarman";
        String[] words = {"foo","bar"};
        System.out.println(findSubstring2(s, words));
    }

    /**
     * words中单词长度相同的 这是个重要条件 让单词匹配变得简单了不少
     * 只需要截取固定长度然后 否则要用字典树 就麻烦了
     * 暴力法
     */
    public static List<Integer> findSubstring(String s, String[] words) {
        List<Integer> res = new ArrayList<>();
        if (s.isEmpty() || 0 == words.length) return res;
        int n = words.length, m = words[0].length();
        Map<String, Integer> m1 = new HashMap<>();
        for (String a : words) m1.put(a, m1.getOrDefault(a, 0) + 1);//++m1[a]
        for (int i = 0; i <= s.length() - n * m; ++i) {
            Map<String, Integer> m2 = new HashMap<>();
            int j = 0;
            for (j = 0; j < n; ++j) {
                String t = s.substring(i + j * m, i + j * m + m);
                if (! m1.containsKey(t)) break;
                    m2.put(t, m2.getOrDefault(t, 0) + 1);
                if (m2.get(t) > m1.get(t)) break;
            }
            if (j == n) res.add(i);
        }
        return res;
    }

    /**
     * 字母变单词了怎么办。首先，本题有个重要条件，所有单词长度相等。
     * 那么假设长度为k
     * 可以将s 划分为s[0] s[1] s[2] .. s[k - 2]开头的，以固定k长度为分割的"单词串"。
     * 再将words中单词存入hashmap便于查找，就变成了第三题了。
     */
    public static List<Integer> findSubstring2(String s, String[] words) {
        /**
         * 边界条件检测
         */
        List<Integer> res = new ArrayList<>();
        if (s.isEmpty() || 0 == words.length) return res;
        int n = s.length(), cnt = words.length, len = words[0].length();
        Map<String, Integer> m1 = new HashMap<>();
        /**
         * words单词放入map里 就和之前数组存字符一个道理
         */
        for (String a : words) m1.put(a, m1.getOrDefault(a, 0) + 1);//++m1[a]
        /**
         * 外层循环构建了 s[0] s[1] s[2] .. s[k - 2]开头的，以固定k长度为分割的"单词串"。
         */
        for (int i = 0; i < len; ++i) {
            /**
             * 内层就是和上一题一个道理了 双指针走起
             */
            int left = i, count = 0;
            Map<String, Integer> m2 = new HashMap<>();
            for (int j = i; j <= n - len; j += len) {
                String t = s.substring(j, j + len);
                if (m1.containsKey(t)) {
                    m2.put(t, m2.getOrDefault(t, 0) + 1);
                    if (m2.get(t) <= m1.get(t)) {
                        ++count;
                    } else {
                        while (m2.get(t) > m1.get(t)) {
                            String t1 = s.substring(left, left + len);
                            m2.put(t1, m2.get(t1) - 1);
                            if (m2.get(t1) < m1.get(t1)) --count;
                            left += len;
                        }
                    }
                    if (count == cnt) {
                        res.add(left);
                        String sub = s.substring(left, left + len);
                        m2.put(sub, m2.get(sub) - 1);
                        --count;
                        left += len;
                    }
                } else {
                    m2.clear();
                    count = 0;
                    left = j + len;
                }
            }
        }
        return res;
    }
}
