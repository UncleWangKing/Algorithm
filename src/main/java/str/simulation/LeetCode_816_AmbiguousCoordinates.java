package str.simulation;

import java.util.ArrayList;
import java.util.List;

public class LeetCode_816_AmbiguousCoordinates {
    public static void main(String[] args) {
        System.out.println(ambiguousCoordinates("(123)"));
    }

    public static List<String> ambiguousCoordinates(String S) {
        List<String> res = new ArrayList<>();
        int n = S.length();
        for(int i = 1; i < n - 2; i++) {//拆分字符串为两部分
            List<String> xs  = genXY(S.substring(1, i + 1));
            List<String> ys  = genXY(S.substring(1 + i, n - 1));
            int xLen = xs.size();
            int yLen = ys.size();
            for(int j = 0; j < xLen; j++)
                for(int k = 0; k < yLen; k++)
                    res.add("(" + xs.get(j) + ", " + ys.get(k) + ")");
        }
        return res;
    }

    private static List<String> genXY(String s) {// 生成X坐标或者Y坐标
        int n = s.length();
        List<String> res = new ArrayList<>();
        if(n > 1 && s.charAt(0) == '0' && s.charAt(n-1) == '0') {// "0XXX0", return {}
            return res;
        }
        if(n == 1 || s.charAt(n-1) == '0') {// "X" or "XXX0" return {S}
            res.add(s);
            return res;
        }
        if(s.charAt(0) == '0') {// "0XXX" return {"0.XXX"}
            res.add("0." + s.substring(1));
            return res;
        }
        res.add(s);// "XXXX" return {"XXXX", "X.XXX", "XX.XX", "XXX.X"}
        for(int i = 1; i < n; i++) {
            res.add(s.substring(0, i) + "." + s.substring(i));
        }
        return res;
    }
}
