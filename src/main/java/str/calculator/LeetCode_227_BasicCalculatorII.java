package str.calculator;

import java.util.Stack;

/**
 * @author ZhangDaPang 285296372@qq.com
 * @date 2018/10/31 17:47
 */
public class LeetCode_227_BasicCalculatorII {
    public static void main(String[] args) {
//        System.out.println(calculate(" 3+5 / 2 "));//5
//        System.out.println(calculate("100000000/1/2/3/4/5/6/7/8/9/10"));//27
//        System.out.println(calculate("1-1-1"));//-1
        System.out.println(calculate2("1+2*5/3+6/4*2"));//6
    }

    public static int calculate(String s) {
        int res = 0, num = 0, n = s.length();
        char op = '+';
        Stack<Integer> st = new Stack<>();
        for (int i = 0; i < n; ++i) {
            char c = s.charAt(i);
            if (c >= '0') {
                num = num * 10 + c - '0';
            }
            if ((c < '0' && c != ' ') || i == n - 1) {
                if (op == '+') st.push(num);
                if (op == '-') st.push(-num);
                if (op == '*' || op == '/') {
                    int tmp = (op == '*') ? st.pop() * num : st.pop() / num;
                    st.push(tmp);
                }
                op = c;
                num = 0;
            }
        }
        while (!st.empty()) {
            res += st.pop();
        }
        return res;
    }

    /**
     * 只有加减乘除优先级
     * 其实本质就是------------有且仅有一层括号，不会出现括号嵌套
     * 那么用栈就没有必要了，curRes,毕竟用栈只需要一层栈。
     * 妙不可言
     */
    public static int calculate2(String s) {
        int res = 0, curRes = 0, num = 0, n = s.length();
        char op = '+';
        for (int i = 0; i < n; ++i) {
            char c = s.charAt(i);
            if (c >= '0') {
                num = num * 10 + c - '0';
            }
            if (c == '+' || c == '-' || c == '*' || c == '/' || i == n - 1) {
                switch (op) {
                    case '+': curRes += num; break;
                    case '-': curRes -= num; break;
                    case '*': curRes *= num; break;
                    case '/': curRes /= num; break;
                }
                if (c == '+' || c == '-' || i == n - 1) {
                    res += curRes;
                    curRes = 0;
                }
                op = c;
                num = 0;
            }
        }
        return res;
    }
}
