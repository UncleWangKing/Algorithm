package str.slidwindow;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ZhangDaPang 285296372@qq.com
 * @date 2018/11/14 18:22
 */
public class LeetCode_438_FindAllAnagramsInAString {
    public static void main(String[] args) {
        String s = "cbaebabacd"; String p = "abc";//[0, 6]
//        String s = "abab"; String p = "ab";//[0, 1, 2]
        System.out.println(findAnagrams2(s, p));
    }

    /**
     * 类似30 更像一些
     * 也类似76 但是这是紧挨着的 也就是子串而不是子序列
     * 可以记录字符出现个数，种类和个数都相等就配上了
     */
    public static List<Integer> findAnagrams(String s, String p) {
        List<Integer> res = new ArrayList<>();
        if (s.isEmpty()) return res;
        int []map = new int[256];
        for (char c : p.toCharArray()) ++map[c];
        for (int i = 0; i <= s.length() - p.length(); i++) {
            int count = 0, left = i;
            int []temp = new int[256];
            for (int j = i; j < i + p.length(); j++) {
                char c = s.charAt(j);
                if(0 != map[c]){
                    temp[c]++;
                    if(temp[c] <= map[c])
                        count++;
                    else {
                        while (temp[c] > map[c]){
                            char leftChar = s.charAt(left);
                            temp[leftChar]--;
                            if(temp[leftChar] <= map[leftChar]) count--;
                            left++;
                        }
                    }
                }
                else {
                    i = j;
                    break;
                }
            }
            if(count == p.length()){
                res.add(i);
            }
        }
        return res;
    }

    /**
     * 1.都是小写字母 用差值可以省一些空间
     * 2.不是增 而是减的方式 可以省一个数组
     */
    public static List<Integer> findAnagrams2(String s, String p) {
        // 存放结果
        List<Integer> soln = new ArrayList<>();

        // 数据有效性
        if (s.length() == 0 || p.length() == 0 || s.length() < p.length()) {
            return new ArrayList<>();
        }

        // 初始化p中字符的hash
        int[] chars = new int[26];
        for (Character c : p.toCharArray()) {
            chars[c - 'a']++;
        }

        int left = 0, right = 0, length = s.length(), count = p.length();
        while (right < length) {
            char rightTemp = s.charAt(right);
            if (chars[rightTemp - 'a'] >= 1) { // rightTemp 在 p中的
                count--;
            }
            chars[rightTemp - 'a']--; // 更新rightTemp 字符可以匹配的数量
            right++;
            if (0 == count) {
                soln.add(left); // wulixiwa, 找到了一个
            }
            if (right - left == p.length()) { // 处理left
                char tempLeft = s.charAt(left);
                if (chars[tempLeft - 'a'] >= 0) { // tempLeft字符对count-- 做出过贡献。还回来。 如果没有做过贡献，应该是负数
                    count++;
                }
                chars[tempLeft - 'a']++;
                left++;
            }
        }
        return soln;
    }
}
