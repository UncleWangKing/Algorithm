package str.nested;

import java.util.LinkedList;
import java.util.List;

/**
 * @author ZhangDaPang 285296372@qq.com
 * @date 2018/10/31 15:48
 */
public class LeetCode_385_MiniParser {
    public static void main(String[] args) {
        NestedInteger deserialize = deserialize("[123,[456,[789]]]");
        System.out.println(deserialize);
    }

    public static NestedInteger deserialize(String s) {
        if (s.isEmpty()) return new NestedInteger();
        if (s.charAt(0) != '[') return new NestedInteger(Integer.valueOf(s));
        if (s.length() <= 2) return new NestedInteger();
        NestedInteger res = new NestedInteger();
        int start = 1, cnt = 0;
        for (int i = 1; i < s.length(); ++i) {
            if (cnt == 0 && (s.charAt(i) == ',' || i == s.length() - 1)) {
                res.add(deserialize(s.substring(start, i)));
                start = i + 1;
            } else if (s.charAt(i) == '[') ++cnt;
            else if (s.charAt(i) == ']') --cnt;
        }
        return res;
    }

    public static class NestedInteger {
        List<NestedInteger> childList;
        int val;
        // Constructor initializes an empty nested list.
        public NestedInteger(){
            this.childList = new LinkedList<>();
        }
        // Constructor initializes a single integer.
        public NestedInteger(int value){
            this.val = value;
        }
        // @return true if this NestedInteger holds a single integer, rather than a nested list.
        public boolean isInteger(){
            return 0 == childList.size();
        }
        // @return the single integer that this NestedInteger holds, if it holds a single integer
        // Return null if this NestedInteger holds a nested list
        public Integer getInteger(){
            return val;
        }

        // Set this NestedInteger to hold a single integer.
        public void setInteger(int value){
            this.val = value;
        }

        // Set this NestedInteger to hold a nested list and adds a nested integer to it.
        public void add(NestedInteger ni){
            childList.add(ni);
        }

        // @return the nested list that this NestedInteger holds, if it holds a nested list
        // Return null if this NestedInteger holds a single integer
        public List<NestedInteger> getList(){
            return childList;
        }
  }
}
