package dp01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * @author ZhangDaPang 285296372@qq.com
 * @date 2018/7/3 10:11
 */
public class LeetCode_140_WordBreakII {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("cat", "cats", "and", "sand", "dog");
        String str = "catsanddog";
        List<String> resultList = wordBreak(str, list);
        System.out.println(resultList);
    }

    public static List<String> wordBreak(String s, List<String> wordDict) {
        HashMap<String, List<String>> m = new HashMap();
        return helper(s, wordDict, m);
    }

    public static List<String> helper(String s, List<String> wordDict, HashMap<String, List<String>> m) {
        /**
         * m作为dp的重复子问题缓存
         */
        if (m.containsKey(s))
            return m.get(s);
        /**
         * 0 == s 代表分完 用Arrays.asList("")返回是一个巧法 给上层判断是否最后一个字符串
         */
        if (0 == s.length())
            return Arrays.asList("");
        List<String> res = new ArrayList<String>();
        /**
         * 以单词为单位拆分
         */
        for (String word : wordDict) {
            /**
             * 字符串长度小于匹配串 则不用截取匹配 直接跳过
             */
            if (s.length() < word.length() || ! s.substring(0, word.length()).equals(word))
                continue;
            /**
             * 递归出子串的拆分
             */
            List<String> rem = helper(s.substring(word.length()), wordDict, m);
            /**
             * 将子串加上自身 组成自身的拆分串
             */
            for (String str : rem) {
                res.add(word + (0 == str.length() ? "" : " ") + str);
            }
        }
        m.put(s, res);
        return res;
    }
}
