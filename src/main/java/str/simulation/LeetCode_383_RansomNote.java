package str.simulation;

import java.util.HashMap;
import java.util.Map;

public class LeetCode_383_RansomNote {
    public static void main(String[] args) {
        String ransomNote = "aa";
        String magazine = "aab";
        System.out.println(canConstruct(ransomNote, magazine));
    }

    public static boolean canConstruct(String ransomNote, String magazine) {
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < ransomNote.length(); i++)
            map.put(ransomNote.charAt(i), map.getOrDefault(ransomNote.charAt(i), 0) + 1);

        for (int i = 0; i < magazine.length(); i++) {
            if(map.containsKey(magazine.charAt(i))){
                Integer count = map.get(magazine.charAt(i));
                if(1 == count) {
                    if(1 == map.size())
                        return true;
                    map.remove(magazine.charAt(i));
                }
                else
                    map.put(magazine.charAt(i), count - 1);
            }
        }

        return 0 == ransomNote.length();
    }

    public boolean canConstruct2(String ransomNote, String magazine) {
        boolean ans = true;
        int[] marked = new int[256];
        char[] c = magazine.toCharArray();
        char[] x = ransomNote.toCharArray();
        for(int i=0; i<c.length; i++){
            marked[c[i]]++;
        }
        for(int i=0; i<x.length; i++){
            if(marked[x[i]]==0){
                ans=false;
                break;
            }
            else{
                marked[x[i]]--;
            }
        }
        return ans;
    }
}
