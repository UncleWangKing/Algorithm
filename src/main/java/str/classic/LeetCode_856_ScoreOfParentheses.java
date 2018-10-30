package str.classic;

public class LeetCode_856_ScoreOfParentheses {
    public static void main(String[] args) {
        System.out.println(scoreOfParentheses("(()(()))"));//6
    }

    public static int scoreOfParentheses(String S) {
        if(S.equals("()"))
            return 1;
        int left = 0, right = 0;
        int i = 0;
        for (; i < S.length(); i++) {
            if('(' == S.charAt(i))
                left++;
            else right++;
            if(left <= right)
                break;
        }
        if(i != S.length() - 1)//如果i不是S.length() - 1 i是从0开始的有效串的最后一位
            return scoreOfParentheses(S.substring(0, i + 1)) + scoreOfParentheses(S.substring(i + 1, S.length()));
        else
            return 2 * scoreOfParentheses(S.substring(1, S.length() - 1));
    }
}
