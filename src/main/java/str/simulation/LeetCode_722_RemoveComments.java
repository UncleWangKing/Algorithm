package str.simulation;

import java.util.LinkedList;
import java.util.List;

public class LeetCode_722_RemoveComments {
    public static void main(String[] args) {

    }

    public static List<String> removeComments(String[] source) {
        List<String> res = new LinkedList<>();
        boolean blocked = false;
        StringBuilder out = new StringBuilder();
        for (String line : source) {
            for (int i = 0; i < line.length(); ++i) {
                if (!blocked) {
                    if (i == line.length() - 1) out.append(line.charAt(i));
                    else {
                        String t = line.substring(i, i + 2);
                        if (t.equals("/*")) {
                            blocked = true;++i;
                        }
                        else if (t.equals("//")) break;
                        else out.append(line.charAt(i));
                    }
                } else {
                    if (i < line.length() - 1) {
                        String t = line.substring(i, i + 2);
                        if (t.equals("*/")){
                            blocked = false;++i;
                        }
                    }
                }
            }
            if (0 != out.length() && !blocked) {
                res.add(out.toString());
                out.delete(0, out.length());
            }
        }
        return res;
    }
}
