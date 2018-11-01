package str.calculator;

import java.util.Stack;

/**
 * @author ZhangDaPang 285296372@qq.com
 * @date 2018/11/1 15:19
 */
public class LeetCode_224_BasicCalculator {
    public static void main(String[] args) {
        System.out.println(calculate2("(1+(4+5+2)-3)+(6+8)"));

        System.out.println("'0'==" + Integer.valueOf('0'));
        System.out.println("空格==" + Integer.valueOf(' '));
        System.out.println("'+'==" + Integer.valueOf('+'));
        System.out.println("'-'==" + Integer.valueOf('-'));
        System.out.println("'*'==" + Integer.valueOf('*'));
        System.out.println("'/'==" + Integer.valueOf('/'));
    }

    /**
     * 解题简语：
     * 1.从左到右挨个计算 用一个res累计当前结果
     * 2.用一个sign来区分加减
     * 3.遇到"("将之前结果压栈
     *   遇到")"弹栈 -- 压栈时同时压入sign
     * PS:1.res = 0和 sign = 1 其实相当于是表达式前方加了个 "0+"
     *    2.
     *    '0' == 48
     *    '空格' == 32
     *    '+' == 43
     *    '-' == 45
     *    '*' == 42
     *    '/' == 47
     *    且题目只空格数字运算符
     *    所以 c >= '0' c就是数字字符
     */
    public static int calculate(String s) {
        int res = 0, num = 0, sign = 1, n = s.length();
        Stack<Integer> st = new Stack<>();
        for (int i = 0; i < n; ++i) {
            char c = s.charAt(i);
            if (c >= '0') {
                num = 10 * num + (c - '0');
            } else if (c == '+' || c == '-') {
                res += sign * num;
                num = 0;
                sign = (c == '+') ? 1 : -1;
            } else if (c == '(') {
                st.push(res);
                st.push(sign);
                res = 0;
                sign = 1;
            } else if (c == ')') {
                res += sign * num;
                num = 0;
                res *= st.pop();
                res += st.pop();
            }
        }
        res += sign * num;
        return res;
    }

    /**
     * 解题简语：
     * 递归也可以用来达到栈的功能 -- 后进先出，妙不可言
     * 但是平衡括号部分有重复遍历 慢
     */
    public static int calculate2(String s) {
        int res = 0, num = 0, sign = 1, n = s.length();
        for (int i = 0; i < n; ++i) {
            char c = s.charAt(i);
            if (c >= '0') {
                num = 10 * num + (c - '0');
            } else if (c == '(') {
                int j = i, cnt = 0;
                for (; i < n; ++i) {
                    if (s.charAt(i) == '(') ++cnt;
                    if (s.charAt(i) == ')') --cnt;
                    if (cnt == 0) break;
                }
                num = calculate2(s.substring(j + 1, i));
            }
            if (c == '+' || c == '-' || i == n - 1) {
                res += sign * num;
                num = 0;
                sign = (c == '+') ? 1 : -1;
            }
        }
        return res;
    }
}
