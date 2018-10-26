package str.simulation;

import java.util.*;

/**
 * @author ZhangDaPang 285296372@qq.com
 * @date 2018/10/26 18:19
 */
public class LeetCode_819_MostCommonWord {
    public static void main(String[] args) {
        System.out.println(mostCommonWord("Bob hit a ball, the hit BALL flew far after it was hit.", new String[]{"hit"}));
    }

    public static String mostCommonWord(String paragraph, String[] banned) {
        Set<String> hs = new HashSet<>(Arrays.asList(banned));
        paragraph += '.';

        String res = "";
        int count = 0;
        Map<String, Integer> hm = new HashMap<>();

        StringBuilder sb = new StringBuilder();
        for(char c : paragraph.toCharArray()){
            if(Character.isLetter(c)){
                sb.append(Character.toLowerCase(c));
            }else if(sb.length() > 0){
                String s = sb.toString();
                if(!hs.contains(s)){
                    hm.put(s, hm.getOrDefault(s, 0)+1);
                    if(hm.get(s) > count){
                        res = s;
                        count = hm.get(s);
                    }
                }

                sb = new StringBuilder();
            }
        }

        return res;
    }
}
