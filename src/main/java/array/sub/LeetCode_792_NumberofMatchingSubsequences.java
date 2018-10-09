package array.sub;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ZhangDaPang 285296372@qq.com
 * @date 2018/10/9 14:19
 */
public class LeetCode_792_NumberofMatchingSubsequences {
    public static void main(String[] args) {
//        String words[] = {"a", "bb", "acd", "ace"};
//        String str = "abcde";
        String words[] = {"ahjpjau","ja","ahbwzgqnuk","tnmlanowax"};
        String str = "dsahjpjauf";

        System.out.println(numMatchingSubseq3(str, words));
    }

    /**
     * 暴力 TLE 据说去重优化可以过
     */
    public static int numMatchingSubseq(String S, String[] words) {
        int count = 0;
        for (int i = 0; i < words.length; i++)
            if(isMatching(S, words[i])) count++;

        return count;
    }

    public static boolean isMatching(String word, String sub){
        char[] wordList = word.toCharArray();
        char[] subList = sub.toCharArray();
        int sIndex = 0;
        for (int i = 0; i < wordList.length; i++)
            if(wordList[i] == subList[sIndex])
                if(++sIndex >= subList.length)
                    return true;

        return false;
    }

    /**
     * 去重优化
     */
    public static int numMatchingSubseq2(String S, String[] words) {
        HashMap<String, Integer> map = new HashMap<>();
        int index = 0, count = 0;
        boolean sub;
        for (String word : words) {
            if (map.containsKey(word)) {
                count += map.get(word);
            } else {
                index = -1;
                sub = true;
                for (int i = 0; i < word.length(); i++) {
                    index = S.indexOf(word.charAt(i), index + 1);
                    if (index < 0) {
                        sub = false;
                        break;
                    }
                }
                if (sub) {
                    count++;
                    map.put(word, 1);
                } else {
                    map.put(word, 0);
                }
            }
        }
        return count;
    }

    /**
     * 更优秀的"分组处理"方案
     */
    public static int numMatchingSubseq3(String S, String[] words) {
        Map<Character, List<Pair>> all = new HashMap<>();
        int res = 0, n = words.length;
        /**
         * words中字符串首字母 分组
         */
        for (int i = 0; i < n; i++) {
            char key = words[i].toCharArray()[0];
            Pair pair = new Pair(i, 1);
            if(! all.containsKey(key)) {
                List<Pair> list = new ArrayList<>();
                list.add(pair);
                all.put(key, list);
            }else {
                all.get(key).add(pair);
            }
        }

        for (char c : S.toCharArray()) {
            List<Pair> list = all.get(c);
            all.remove(c);
            if(null != list) {
                for (Pair it : list) {
                    if (it.second == words[it.first].length()) ++res;
                    else {
                        char key = words[it.first].toCharArray()[it.second++];
                        if (all.containsKey(key))
                            all.get(key).add(it);
                        else {
                            List<Pair> tempList = new ArrayList<>();
                            tempList.add(it);
                            all.put(key, tempList);
                        }
                    }
                }
            }
        }
        return res;
    }

    private static class Pair{
        int first;
        int second;
        public Pair(int first, int second){
            this.first = first;
            this.second = second;
        }
    }
}
