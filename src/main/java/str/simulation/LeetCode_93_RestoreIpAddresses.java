package str.simulation;

import java.util.ArrayList;
import java.util.List;

public class LeetCode_93_RestoreIpAddresses {
    public static void main(String[] args) {
        System.out.println(restoreIpAddresses("25525511135"));
    }

    public static List<String> restoreIpAddresses(String s) {
        List<String> res = new ArrayList<>();
        helper(s, 0, "", res);
        return res;
    }

    public static void helper(String s, int n, String out, List<String> res) {
        if (n == 4) {
            if (s.isEmpty()) res.add(out);
            return;
        }
        for (int k = 1; k < 4; ++k) {
            if (s.length() < k) break;
            int val = Integer.parseInt(s.substring(0, k));
            if (val > 255 || k != String.valueOf(val).length()) continue;
            helper(s.substring(k), n + 1, out + s.substring(0, k) + (n == 3 ? "" : "."), res);
        }
    }

    public List<String> restoreIpAddresses2(String s) {
        List<String> res = new ArrayList<String>();
        for (int a = 1; a < 4; ++a)
            for (int b = 1; b < 4; ++b)
                for (int c = 1; c < 4; ++c)
                    for (int d = 1; d < 4; ++d)
                        if (a + b + c + d == s.length()) {
                            int A = Integer.parseInt(s.substring(0, a));
                            int B = Integer.parseInt(s.substring(a, a + b));
                            int C = Integer.parseInt(s.substring(a + b, a + b + c));
                            int D = Integer.parseInt(s.substring(a + b + c));
                            if (A <= 255 && B <= 255 && C <= 255 && D <= 255) {
                                String t = String.valueOf(A) + "." + String.valueOf(B) + "." + String.valueOf(C) + "." + String.valueOf(D);
                                if (t.length() == s.length() + 3) res.add(t);
                            }
                        }
        return res;
    }
}
