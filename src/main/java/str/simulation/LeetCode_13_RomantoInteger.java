package str.simulation;

import java.util.HashMap;
import java.util.Map;

public class LeetCode_13_RomantoInteger {
    public static void main(String[] args) {
        System.out.println(romanToInt("III"));
    }

    public static int romanToInt(String s) {
        int res = 0;
        Map<Character, Integer> m = new HashMap<>();
        m.put('I', 1);
        m.put('V', 5);
        m.put('X', 10);
        m.put('L', 50);
        m.put('C', 100);
        m.put('D', 500);
        m.put('M', 1000);
        for (int i = 0; i < s.length(); ++i) {
            int val = m.get(s.charAt(i));
            if (i == s.length() - 1 || m.get(s.charAt(i + 1)) <= m.get(s.charAt(i))) res += val;
            else res -= val;
        }
        return res;
    }

    public static int romanToInt2(String s) {
        int res = 0;
        Map<Character, Integer> m = new HashMap<>();
        m.put('I', 1);
        m.put('V', 5);
        m.put('X', 10);
        m.put('L', 50);
        m.put('C', 100);
        m.put('D', 500);
        m.put('M', 1000);
        for (int i = 0; i < s.length(); ++i) {
            if (i == 0 || m.get(s.charAt(i)) <= m.get(s.charAt(i - 1))) res += m.get(s.charAt(i));
            else res += m.get(s.charAt(i)) - 2 * m.get(s.charAt(i - 1));
        }
        return res;
    }
}
