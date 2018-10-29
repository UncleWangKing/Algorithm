package str.simulation;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author ZhangDaPang 285296372@qq.com
 * @date 2018/10/29 17:10
 */
public class LeetCode_22_GenerateParentheses {
    public static void main(String[] args) {
        System.out.println(generateParenthesis2(3));
    }

    public static List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<String>();
        helper(n, n, "", res);
        return res;
    }

    /**
     * 从0开始的任何子串 left个数>=right个数 既是合法串
     */
    public static void helper(int left, int right, String out, List<String> res) {
        if (left < 0 || right < 0 || left > right) return;
        if (0 == left && 0 == right) {
            res.add(out);
            return;
        }
        helper(left - 1, right, out + "(", res);
        helper(left, right - 1, out + ")", res);
    }

    /**
     * 自己一开始的想法 但是没写出来
     * 从""空串开始
     * 每轮新增一对括号 等于 "()"+旧串  外加 一组"()"放在任意"("后的串
     * set去重
     */
    public static List<String> generateParenthesis2(int n) {
        Set<String> res = new HashSet<>();
        if (n == 0) {
            res.add("");
        } else {
            List<String> pre = generateParenthesis2(n - 1);
            for (String str : pre) {
                for (int i = 0; i < str.length(); ++i) {
                    if (str.charAt(i) == '(') {
                        str = str.substring(0, i + 1) + "()" + str.substring(i + 1, str.length());
                        res.add(str);
                        str = str.substring(0, i + 1) +  str.substring(i + 3, str.length());
                    }
                }
                res.add("()" + str);
            }
        }
        return new ArrayList(res);
    }
}
