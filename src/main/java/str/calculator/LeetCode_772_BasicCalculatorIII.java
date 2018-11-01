package str.calculator;

/**
 * @author ZhangDaPang 285296372@qq.com
 * @date 2018/10/31 17:47
 */

/**
 * 题目要收费，那么简而言之一句话
 * 题目描述:有加减乘除括号空格，求值
 */
public class LeetCode_772_BasicCalculatorIII {
    public static void main(String[] args) {
        System.out.println(calculate("(2+6* 3+5- (3*14/7+2)*5)+3"));//-12
        System.out.println("(2+6* 3+5- (3*14/7+2)*5)+3".substring(1,3));//-12
    }

    /**
     * 这算是1和2题的结合
     * 第一题教会我们解决括号优先级问题，可以递归或者栈
     * 第二题教会我们解决乘除优先级问题，一个变量
     * 1.递归解决括号优先级
     * 2.curRes解决加减乘除间的优先级
     */
    public static int calculate(String s) {
        int n = s.length(), num = 0, curRes = 0, res = 0;
        char op = '+';
        for (int i = 0; i < n; ++i) {
            char c = s.charAt(i);
            if (c >= '0') {
                num = num * 10 + c - '0';
            } else if (c == '(') {
                int j = i, cnt = 0;
                for (; i < n; ++i) {
                    if (s.charAt(i) == '(') ++cnt;
                    if (s.charAt(i) == ')') --cnt;
                    if (cnt == 0) break;
                }
                num = calculate(s.substring(j + 1, i));
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
