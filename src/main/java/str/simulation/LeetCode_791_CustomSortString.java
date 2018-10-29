package str.simulation;

import java.util.HashMap;
import java.util.Map;

public class LeetCode_791_CustomSortString {
    public static void main(String[] args) {
        System.out.println(customSortString("cba", "abcd"));//cbad
    }

    public static String customSortString(String S, String T) {
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < T.length(); i++) {
            map.put(T.charAt(i), map.getOrDefault(T.charAt(i), 0) + 1);
        }
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < S.length(); i++) {
            if(map.containsKey(S.charAt(i))) {
                for (int j = 0; j < map.get(S.charAt(i)); j++) {
                    builder.append(S.charAt(i));
                }
                map.remove(S.charAt(i));
            }
        }

        for (Map.Entry<Character, Integer> entry : map.entrySet()){
            for (int i = 0; i < entry.getValue(); i++) {
                builder.append(entry.getKey());
            }
        }

        return builder.toString();
    }
}
