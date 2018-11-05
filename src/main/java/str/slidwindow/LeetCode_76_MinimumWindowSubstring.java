package str.slidwindow;

import java.util.HashMap;
import java.util.Map;

public class LeetCode_76_MinimumWindowSubstring {
    public static void main(String[] args) {
        System.out.println(minWindow("ADOBECODEBANC", "ABC"));
    }

    /**
     * 频繁的使用charAt 不如先转成char数组 效率更高 OJ亲测有效 这里为了好看 就没有那样写
     */
    public static String minWindow(String s, String t) {
        String result = "";
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < t.length(); i++)
            map.put(t.charAt(i), map.getOrDefault(t.charAt(i), 0) + 1);

        int count = map.size();
        int left = 0, right = 0, minLength = s.length() + 1;

        for (; right < s.length(); right++) {
            //凑到某字符一个
            if(map.containsKey(s.charAt(right))){
                int tempNum = map.get(s.charAt(right));
                map.put(s.charAt(right), tempNum - 1);
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
                    if(map.containsKey(s.charAt(left))){
                        int tempLeft = map.get(s.charAt(left));
                        map.put(s.charAt(left), tempLeft + 1);
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
