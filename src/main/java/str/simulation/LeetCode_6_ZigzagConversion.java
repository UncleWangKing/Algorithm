package str.simulation;

public class LeetCode_6_ZigzagConversion {
    public static void main(String[] args) {
        System.out.println(convert("PAYPALISHIRING", 3));
    }

    public static String convert(String s, int numRows) {
        if (numRows <= 1) return s;
        String res = "";
        int size = 2 * numRows - 2;
        for (int i = 0; i < numRows; ++i) {
            for (int j = i; j < s.length(); j += size) {
                res += s.charAt(j);
                int tmp = j + size - 2 * i;
                if (i != 0 && i != numRows - 1 && tmp < s.length()) res += s.charAt(tmp);
            }
        }
        return res;
    }

    public static String convert2(String s, int numRows) {
        if(1 == numRows || s.length() <= numRows)
            return s;

        int i,j;
        final int dr = (numRows - 1) << 1, n = s.length();
        char[] res = new char[n];

        i = 0;
        for(int col = 0; col < numRows - 1; ++col) {
            res[i++] = s.charAt(col);
            for(j = dr; j < n; j += dr) {
                res[i++] = s.charAt(j - col);
                if(0 != col && j + col < s.length())
                    res[i++] = s.charAt(j + col);
            }
            if (j - col < n){
                res[i++] = s.charAt(j - col);
            }
        }
        for(j = 0; j + numRows - 1 < n; j += dr){
            res[i++] = s.charAt(j + numRows - 1);
        }
        return new String(res);
    }
}
