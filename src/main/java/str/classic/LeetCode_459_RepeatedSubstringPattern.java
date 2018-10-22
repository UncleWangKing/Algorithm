package str.classic;

public class LeetCode_459_RepeatedSubstringPattern {
    public static void main(String[] args) {
        System.out.println(repeatedSubstringPattern2("abcdabcabcabc"));
    }

    public static boolean repeatedSubstringPattern(String s) {
        for (int i = 1; i <= s.length() / 2; i++) {
            if(0 == s.length() % i) {
                boolean flag = true;
                for (int j = 0; j < i; j++) {
                    for (int k = j; k < s.length() - i; k += i) {
                        if(s.charAt(k) != s.charAt(k + i)) {
                            flag = false;
                            break;
                        }
                    }
                    if(! flag)
                        break;
                }
                if(flag)
                    return true;
            }
        }

        return false;
    }

    public static boolean repeatedSubstringPattern2(String s) {
        int i = 1, j = 0, n = s.length();
        int dp[] = new int[s.length() + 1];
        while (i < n) {
            if (s.charAt(i) == s.charAt(j)) dp[++i] = ++j;
            else if (0 == j) ++i;
            else j = dp[j];
        }
        return 0 != dp[n] && (dp[n] % (n - dp[n]) == 0);
    }

    public static boolean repeatedSubstringPattern3(String s) {
        return (s + s).substring(1, s.length() * 2 - 1).contains(s);
    }
}
