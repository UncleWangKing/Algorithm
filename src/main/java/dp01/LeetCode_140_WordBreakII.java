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
    /**
     *
     */
    public static List<String> helper(String s, List<String> wordDict, HashMap<String, List<String>> m) {
        if (m.containsKey(s))
            return m.get(s);
        if (0 == s.length())
            return Arrays.asList("");
        List<String> res = new ArrayList<String>();
        for (String word : wordDict) {
            if (s.length() < word.length() || ! s.substring(0, word.length()).equals(word))
                continue;
            List<String> rem = helper(s.substring(word.length()), wordDict, m);
            for (String str : rem) {
                res.add(word + (0 == str.length() ? "" : " ") + str);
            }
        }
        m.put(s, res);
        return res;
    }
}
