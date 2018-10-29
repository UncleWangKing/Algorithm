package str.simulation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LeetCode_890_FindAndReplacePattern {
    public static void main(String[] args) {
        String[] words  = {"abc","deq","mee","aqq","dkd","ccc"};
        String pattern = "abb";
        System.out.println(findAndReplacePattern(words, pattern));
    }

    public static List<String> findAndReplacePattern(String[] words, String pattern) {
        List<String> result = new ArrayList<>();

        Map<Character, Character> map = new HashMap<>();
        Map<Character, Character> reverseMap = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            if(words[i].length() != pattern.length())
                continue;
            int j = 0;
            for (; j < words[i].length(); j++) {
                if(! map.containsKey(words[i].charAt(j))) {
                    if(reverseMap.containsKey(pattern.charAt(j)))
                        break;
                    map.put(words[i].charAt(j), pattern.charAt(j));
                    reverseMap.put(pattern.charAt(j), words[i].charAt(j));
                }
                else if(map.get(words[i].charAt(j)) != pattern.charAt(j))
                    break;
            }
            if(j == words[i].length())
                result.add(words[i]);
            map.clear();
            reverseMap.clear();
        }

        return result;
    }
}
