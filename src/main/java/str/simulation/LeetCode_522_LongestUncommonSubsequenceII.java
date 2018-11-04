package str.simulation;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static str.simulation.LeetCode_522_LongestUncommonSubsequenceII.checkSubs;

public class LeetCode_522_LongestUncommonSubsequenceII {
    public static void main(String[] args) {
        String [] strs = {"aba", "cdc", "eae"};
        System.out.println(findLUSlength(strs));
    }

    /**
     * 延续521 暴力 不多看了 时间不留给蠢题
     */
    public static int findLUSlength(String[] strs) {
        int res = -1, j = 0, n = strs.length;
        for (int i = 0; i < n; ++i) {
            for (; j < n; ++j) {
                if (i == j) continue;
                if (checkSubs(strs[i], strs[j])) break;
            }
            if (j == n) res = Math.max(res, strs[i].length());
        }
        return res;
    }
    public static boolean checkSubs(String subs, String str) {
        int i = 0;
        for (char c : str.toCharArray()) {
            if (c == subs.charAt(i)) ++i;
            if (i == subs.length()) break;
        }
        return i == subs.length();
    }
}
