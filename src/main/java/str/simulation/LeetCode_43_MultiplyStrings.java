package str.simulation;

public class LeetCode_43_MultiplyStrings {
    public static void main(String[] args) {
        System.out.println(multiply("12", "34"));
    }

    /**
     * return String.valueOf(Integer.valueOf(num1) * Integer.valueOf(num2));会爆
     */
    public static String multiply(String num1, String num2) {
        int m = num1.length(), n = num2.length();
        int[] pos = new int[m + n];

        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                int mul = (num1.charAt(i) - '0') * (num2.charAt(j) - '0');
                int p1 = i + j, p2 = i + j +1;
                int sum = mul + pos[p2];

                pos[p1] += sum / 10;
                pos[p2] = (sum) % 10;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int p : pos) {
            if(!(0 == sb.length() && 0 == p))
                sb.append(p);
        }
        return 0 == sb.length() ? "0" : sb.toString();
    }
}
