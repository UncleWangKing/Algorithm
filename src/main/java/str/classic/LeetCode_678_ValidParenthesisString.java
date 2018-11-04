package str.classic;

import java.util.Stack;

public class LeetCode_678_ValidParenthesisString {
    public static void main(String[] args) {
        System.out.println(checkValidString("(())((())()()(*)(*()(())())())()()((()())((()))(*"));//false
        System.out.println(checkValidString("((*"));//false
        System.out.println(checkValidString("(*))"));//true
        System.out.println(checkValidString("("));//false
        System.out.println(checkValidString("(*)"));//true
    }

    /**
     * '('和*分别压栈
     * 遇到')'弹'('  '('弹完弹'*'
     * 最后如果left star 都不为空 区分*) 和 *(两个模式 如果后者模式 即存在栈顶的*在一个'('左方 false
     * 如果最终left用完 true 否则说明(右方的星号不够用
     */
    public static boolean checkValidString(String s) {
        Stack<Integer> left = new Stack<>();
        Stack<Integer> star = new Stack<>();
        for (int i = 0; i < s.length(); ++i) {
            if (s.charAt(i) == '*') star.push(i);
            else if (s.charAt(i) == '(') left.push(i);
            else {
                if (left.empty() && star.empty()) return false;
                if (!left.empty()) left.pop();
                else star.pop();
            }
        }
        while (!left.empty() && !star.empty()) {
            if (left.peek() > star.peek()) return false;
            left.pop(); star.pop();
        }
        return left.empty();
    }

    /**
     * 正反两遍
     * 正时 将*当'('
     * 反时 将*当')'
     */
    public static boolean checkValidString2(String s) {
        int left = 0, right = 0, n = s.length();
        for (int i = 0; i < n; ++i) {
            if (s.charAt(i) == '(' || s.charAt(i) == '*') ++left;
            else --left;
            if (left < 0) return false;
        }
        if (left == 0) return true;
        for (int i = n - 1; i >= 0; --i) {
            if (s.charAt(i) == ')' || s.charAt(i) == '*') ++right;
            else --right;
            if (right < 0) return false;
        }
        return true;
    }

    /**
     * 递归法暴力 遇到* 将*的三种情况都带入
     */
    public static boolean checkValidString3(String s) {
        return helper(s, 0, 0);
    }

    public static boolean helper(String s, int start, int cnt) {
        if (cnt < 0) return false;
        for (int i = start; i < s.length(); ++i) {
            if (s.charAt(i) == '(') {
                ++cnt;
            } else if (s.charAt(i) == ')') {
                if (cnt <= 0) return false;
                --cnt;
            } else {
                return helper(s, i + 1, cnt) || helper(s, i + 1, cnt + 1) || helper(s, i + 1, cnt - 1);
            }
        }
        return cnt == 0;
    }

    /**
     * low high 分别代表遍历过程中 可存在的左括号个数的 下限 和 上限
     * 上限 < 0 false
     */
    public static boolean checkValidString4(String s) {
        int low = 0, high = 0;
        for (char c : s.toCharArray()) {
            if (c == '(') {
                ++low; ++high;
            } else if (c == ')') {
                if (low > 0) --low;
                --high;
            } else {
                if (low > 0) --low;
                ++high;
            }
            if (high < 0) return false;
        }
        return low == 0;
    }
}
