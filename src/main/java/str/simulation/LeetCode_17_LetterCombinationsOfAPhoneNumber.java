package str.simulation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LeetCode_17_LetterCombinationsOfAPhoneNumber {
    public static void main(String[] args) {
        System.out.println(letterCombinations("23"));
    }

    public static List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList<>();
        if(1 > digits.length())
            return res;
        Map<Integer, String> map = new HashMap<>();
        map.put(2, "abc");
        map.put(3, "def");
        map.put(4, "ghi");
        map.put(5, "jkl");
        map.put(6, "mno");
        map.put(7, "pqrs");
        map.put(8, "tuv");
        map.put(9, "wxyz");
        helper(digits, "", map, res);
        return res;
    }
    public static void helper(String digits, String temp, Map<Integer, String> map, List<String> res){
        for (int i = 0; i < map.get(digits.charAt(temp.length()) - '0').length(); i++) {
            String str = temp + map.get(digits.charAt(temp.length()) - '0').charAt(i);
            if(temp.length() == digits.length() - 1)
                res.add(str);
            else
                helper(digits, str, map, res);
        }
    }
}
