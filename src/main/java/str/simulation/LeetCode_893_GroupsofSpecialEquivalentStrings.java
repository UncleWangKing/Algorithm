package str.simulation;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LeetCode_893_GroupsofSpecialEquivalentStrings {
    public static void main(String[] args) {
        String[] list = {"a","b","c","a","c","c"};
        System.out.println(numSpecialEquivGroups(list));
    }

    public static Map<Character, Integer> countChar(String str){
        Map<Character, Integer> charCounter = new HashMap<>();
        // 统计偶数位字符
        for(int i = 0; i < str.length(); i += 2)
            charCounter.put(str.charAt(i), charCounter.getOrDefault(str.charAt(i), 0) + 1);
        // 统计奇数位字符
        for(int i = 1; i < str.length(); i += 2)
            charCounter.put((char)-str.charAt(i), charCounter.getOrDefault((char)-str.charAt(i), 0) + 1);
        return charCounter;
    }

    public static int numSpecialEquivGroups(String[] A) {
        Set<Map<Character, Integer>> ans = new HashSet<>();
        for(String S : A)
            ans.add(countChar(S));

        return ans.size();
    }
}
