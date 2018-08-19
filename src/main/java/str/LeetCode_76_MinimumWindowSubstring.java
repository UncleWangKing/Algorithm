package str;

import java.util.HashMap;
import java.util.Map;

public class LeetCode_76_MinimumWindowSubstring {
    public static void main(String[] args) {
        System.out.println(minWindow("ADOBECODEBANC", "ABC"));
    }

    public static String minWindow(String s, String t) {
        String result = "";
        char[] list = s.toCharArray();
        char[] tempList = t.toCharArray();
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < tempList.length; i++) {
            if(map.containsKey(tempList[i]))
                map.put(tempList[i], map.get(tempList[i]) + 1);
            else
                map.put(tempList[i], 1);
        }
        int count = map.size();
        int left = 0, right = 0, minLength = list.length + 1;

        for (; right < list.length; right++) {
            //凑到某字符一个
            if(map.containsKey(list[right])){
                int tempNum = map.get(list[right]);
                map.put(list[right], tempNum - 1);
                //刚好凑到某个字符 0 == tempNum - 1 而不是 0 >= tempNum - 1 因为count代表还有多少"种"字符要凑
                if(0 == tempNum - 1)
                    count--;
                //凑到所有字符所需所有
                while(0 == count){
                    //若是更短的值 取出来
                    if(right - left + 1 < minLength) {
                        minLength = right - left + 1;
                        result = s.substring(left, left + minLength);
                    }
                    //如果接下来左移要排除的是t中的字符，要更新count
                    if(map.containsKey(list[left])){
                        int tempLeft = map.get(list[left]);
                        map.put(list[left], tempLeft + 1);
                        if(tempLeft + 1 > 0) ++count;
                    }
                    //上面已经保存了当前的最小值，可以left++直接假定至少丢一个开头再继续，后面找不出补位的结束就是正确结果
                    left++;
                }
            }
        }

        return result;
    }
}
