package str.simulation;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author ZhangDaPang 285296372@qq.com
 * @date 2018/11/2 18:42
 */
public class LeetCode_71_SimplifyPath {
    public static void main(String[] args) {
        System.out.println(simplifyPath("/a/./b/../../c/"));
    }

    public static String simplifyPath(String path) {
        Stack<String> s = new Stack<>();
        String[] p = path.split("/");
        for (String t : p) {
            if (!s.isEmpty() && t.equals("..")) {
                s.pop();
            } else if (!t.equals(".") && !t.equals("") && !t.equals("..")) {
                s.push(t);
            }
        }
        List<String> list = new ArrayList(s);
        return "/" + String.join("/", list);
    }
}
