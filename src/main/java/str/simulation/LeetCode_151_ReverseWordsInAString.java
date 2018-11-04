package str.simulation;

import java.util.Arrays;
import java.util.Collections;

public class LeetCode_151_ReverseWordsInAString {
    public static void main(String[] args) {
        System.out.println(reverseWords("the sky is blue"));
    }

    public static String reverseWords(String s) {
        int storeIndex = 0, n = s.length();
        StringBuilder sb = new StringBuilder(s).reverse();
        for (int i = 0; i < n; ++i) {
            if (sb.charAt(i) != ' ') {
                if (storeIndex != 0) sb.setCharAt(storeIndex++, ' ');
                int j = i;
                while (j < n && sb.charAt(j) != ' ') sb.setCharAt(storeIndex++, sb.charAt(j++));
                String t = new StringBuilder(sb.substring(storeIndex - (j - i), storeIndex)).reverse().toString();
                sb.replace(storeIndex - (j - i), storeIndex, t);
                i = j;
            }
        }
        sb.setLength(storeIndex);
        return sb.toString();
    }

    public static String reverseWords2(String s) {
        String res = "";
        String[] words = s.trim().split("\\s+");
        for (int i = words.length - 1; i > 0; --i) {
            res += words[i] + " ";
        }
        return res + words[0];
    }

    public static String reverseWords3(String s) {
        String[] words = s.trim().split(" +");
        Collections.reverse(Arrays.asList(words));
        return String.join(" ", words);
    }
}
