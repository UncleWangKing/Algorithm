package str.simulation;

public class LeetCode_12_IntegerToRoman {
    public static void main(String[] args) {
        System.out.println(intToRoman(58));//LVIII
    }

    public static String intToRoman(int num) {
        String res = "";
        int[] val = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] str = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        for (int i = 0; i < val.length; ++i) {
            while (num >= val[i]) {
                num -= val[i];
                res += str[i];
            }
        }
        return res;
    }

    /**
     * 理由输入范围 投机取巧
     */
    public static String intToRoman2(int num) {
        String res = "";
        String[] v1 = {"", "M", "MM", "MMM"};
        String[] v2 = {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
        String[] v3 = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
        String[] v4 = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};
        return v1[num / 1000] + v2[(num % 1000) / 100] + v3[(num % 100) / 10] + v4[num % 10];
    }
}
