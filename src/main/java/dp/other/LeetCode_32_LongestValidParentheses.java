package dp.other;

public class LeetCode_32_LongestValidParentheses {
    public static void main(String[] args) {
        String str = ")()())";
        System.out.println(longestValidParentheses(str));
    }

    public static int longestValidParentheses(String s) {
        int result = 0;
        s = ')' + s;
        char[] list = s.toCharArray();
        int [] dp = new int[s.length()];

        for(int i = 1; i < list.length; i++){
            if(list[i] == ')'){
                if(list[i - 1 - dp[i - 1]] == '(')
                    dp[i] = dp[i - 1] + 2;
                dp[i] += dp[i - dp[i]];
            }
            result = Math.max(result, dp[i]);
        }
        return result;
    }
}
