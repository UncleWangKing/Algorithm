package str.simulation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LeetCode_916_WordSubsets {
    public static void main(String[] args) {
        String[] A = {"amazon","apple","facebook","google","leetcode"};
        String[] B = {"e","o"};
        System.out.println(wordSubsets(A, B));//["facebook","google","leetcode"]
    }

    /**
     * 暴力能过
     */
    public static List<String> wordSubsets(String[] A, String[] B) {
        Map<Character, Integer> map = new HashMap<>();
        Map<Character, Integer> tempMap = new HashMap<>();

        for (int i = 0; i < B.length; i++) {
            for (int j = 0; j < B[i].length(); j++) {
                tempMap.put(B[i].charAt(j), tempMap.getOrDefault(B[i].charAt(j), 0) + 1);
                if(tempMap.get(B[i].charAt(j)) > map.getOrDefault(B[i].charAt(j), 0))
                    map.put(B[i].charAt(j), tempMap.get(B[i].charAt(j)));
            }
            tempMap.clear();
        }

        List<String> res = new ArrayList<>();
        for (int i = 0; i < A.length; i++) {
            tempMap.putAll(map);
            for (int j = 0; j < A[i].length(); j++) {
                if(tempMap.containsKey(A[i].charAt(j))){
                    if(1 == tempMap.get(A[i].charAt(j))){
                        tempMap.remove(A[i].charAt(j));
                        if(0 == tempMap.size()){
                            res.add(A[i]);
                            break;
                        }
                    }else {
                        tempMap.put(A[i].charAt(j), tempMap.get(A[i].charAt(j)) - 1);
                    }
                }
            }
            tempMap.clear();
        }

        return res;
    }

    /**
     * 看看人家！所以存字符串个数别再用hashmap啦！
     */
    public List<String> wordSubsets2(String[] A, String[] B) {
        List<String> list = new ArrayList<>();

        final int maxn = 26;
        int need[] = new int[maxn];
        int tmp[] = new int[maxn];
        for(int i = 0; i < B.length; i++) {
            for(int j = 0; j < B[i].length(); j++) {
                tmp[B[i].charAt(j) - 'a']++;
            }
            for(int j = 0; j < maxn; j++) {
                if(need[j] < tmp[j])
                    need[j] = tmp[j];
                tmp[j] = 0;
            }
        }

        for(int i = 0; i < A.length; i++) {
            boolean f = true;
            for(int j = 0;j < A[i].length(); j++) {
                tmp[A[i].charAt(j) - 'a']++;
            }
            for(int j = 0; j < maxn; j++) {
                if(tmp[j] < need[j]) {
                    f = false;
                }
                tmp[j] = 0;
            }
            if(f) list.add(A[i]);
        }

        return list;
    }
}
