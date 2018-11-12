package str.classic;

import java.util.Stack;

public class LeetCode_32_LongestValidParentheses {
    public static void main(String[] args) {
//        String s = ")()())";//4
//        String s = "()";//2
//        String s = "(()";//2
        String s = "()(()";//2
        System.out.println(longestValidParentheses(s));
    }

    /**
     * 栈
     */
    public static int longestValidParentheses(String s) {
        int res = 0, start = 0;
        Stack<Integer> m = new Stack<>();
        for (int i = 0; i < s.length(); ++i) {
            if (s.charAt(i) == '(') m.push(i);
            else if (s.charAt(i) == ')') {
                if (m.empty()) start = i + 1;
                else {
                    m.pop();
                    res = m.empty() ? Math.max(res, i - start + 1) : Math.max(res, i - m.peek());
                }
            }
        }
        return res;
    }

    public static int longestValidParentheses2(String s) {
        /**
         * 动态规划，dp[i]为以s[i]为结尾的最长有效字串长度
         * s[i]=='('，dp[i]为零
         * s[i]=')',看对称位置是否为'(',注意()(()),这种情况，前面也得加上
         * 和我想法一样 但是没有写下去 凸(艹皿艹 )
         */
        char[] chas = s.toCharArray();
        int[] dp = new int[chas.length];
        int res = 0;
        for(int i = 1; i < chas.length; i++){
            if(chas[i] == ')'){
                int pre = i - dp[i - 1] - 1;//对称
                if(pre >= 0 && chas[pre] == '('){
                    /**
                     * pre > 0 ? dp[pre - 1] : 0
                     * 解决了  ()(())  这种情况的连接问题
                     */
                    dp[i] = dp[i - 1] + 2 + (pre > 0 ? dp[pre - 1] : 0);
                }
            }
            res = Math.max(res, dp[i]);
        }
        return res;
    }
}
