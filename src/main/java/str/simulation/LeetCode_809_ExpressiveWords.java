package str.simulation;

public class LeetCode_809_ExpressiveWords {
    /**
     * S = "heeellooo"
     * words = ["hello", "hi", "helo"]
     * Output: 1
     *
     * "zzzzzyyyyy"
     ["zzyy","zy","zyy"]
     */
    public static void main(String[] args) {
//        String S = "heeellooo";String[] words = {"hello", "hi", "helo"};
//        String S = "zzzzzyyyyy";String[] words = {"zzyy", "zy", "zyy"};
        String S = "abcd";String[] words = {"abc"};
        System.out.println(expressiveWords(S, words));
    }

    public static int expressiveWords(String S, String[] words) {
        int res = 0;
        for (String W : words) if (check(S, W)) res++;
        return res;
    }

    public static boolean check(String S, String W) {
        int n = S.length(), m = W.length(), j = 0;
        for (int i = 0; i < n; i++)
            if (j < m && S.charAt(i) == W.charAt(j)) j++;
            else if (i > 1 && S.charAt(i) == S.charAt(i - 1) && S.charAt(i - 1) == S.charAt(i - 2));
            else if (0 < i && i < n - 1 && S.charAt(i - 1) == S.charAt(i) && S.charAt(i) == S.charAt(i + 1));
            else return false;
        return j == m;
    }
}
