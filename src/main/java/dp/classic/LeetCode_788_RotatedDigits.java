package dp.classic;

public class LeetCode_788_RotatedDigits {
    public static void main(String[] args) {
        System.out.println(rotatedDigits2(100));
    }

    public static int rotatedDigits(int N) {
        int res = 0;
        for (int i = 1; i <= N; ++i)
            if (check(i)) ++res;
        return res;
    }

    public static boolean check(int k) {
        String  str = String.valueOf(k);
        boolean flag = false;
        for (char c : str.toCharArray()) {
            if (c == '3' || c == '4' || c == '7') return false;
            if (c == '2' || c == '5' || c == '6' || c == '9') flag = true;
        }
        return flag;
    }

    /**
     * dp
     */
    public static  int rotatedDigits2(int N) {
        int res = 0;
        int[] dp = new int[N + 1];
        for (int i = 0; i <= N; ++i) {
            if (i < 10) {
                if (i == 0 || i == 1 || i == 8) dp[i] = 1;
                else if (i == 2 || i == 5 || i == 6 || i == 9) {
                    dp[i] = 2; ++res;
                }
            } else {
                int a = dp[i / 10], b = dp[i % 10];
                if (a == 1 && b == 1) dp[i] = 1;
                else if (a >= 1 && b >= 1) {
                    dp[i] = 2; ++res;
                }
            }
        }
        return res;
    }
}
