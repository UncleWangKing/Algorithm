package str.classic;

import java.util.Arrays;

public class LeetCode_564_FindTheClosestPalindrome {
    public static void main(String[] args) {
        System.out.println(nearestPalindromic("123"));
    }

    /**
     *
     */
    public static String nearestPalindromic(String n) {
        char[] arr = n.toCharArray();
        for (int i = 0, j = arr.length - 1; i < j; i++, j--) arr[j] = arr[i];

        String curP = String.valueOf(arr);
        String preP = nearestPalindrom(curP, false);
        String nextP = nearestPalindrom(curP, true);

        long num = Long.valueOf(n);
        long cur = Long.valueOf(curP);
        long pre = Long.valueOf(preP);
        long next = Long.valueOf(nextP);

        long dpre = Math.abs(num - pre);
        long dcur = Math.abs(num - cur);
        long dnext = Math.abs(num - next);

        if (num == cur) {
            return dpre <= dnext ? preP : nextP;
        } else if (num > cur) {
            return dcur <= dnext ? curP : nextP;
        } else {
            return dpre <= dcur ? preP : curP;
        }
    }

    private static String nearestPalindrom(String curP, boolean dir) {
        int right = curP.length() >> 1, left = curP.length() - right;
        int leftValue = Integer.valueOf(curP.substring(0, left));
        leftValue += (dir ? 1 : -1);

        if (leftValue == 0) return right == 0 ? "0" : "9";

        StringBuilder leftBuilder = new StringBuilder(String.valueOf(leftValue));
        StringBuilder rightBuilder = new StringBuilder(leftBuilder).reverse();
        if (right > leftBuilder.length()) rightBuilder.append("9");

        return leftBuilder.append(rightBuilder.substring(rightBuilder.length() - right)).toString();
    }

    public static String nearestPalindromic2(String n) {
        Long number = Long.parseLong(n);
        Long big = findHigherPalindrome(number + 1);
        Long small = findLowerPalindrome(number - 1);
        return Math.abs(number - small) > Math.abs(big - number) ? String.valueOf(big) : String.valueOf(small);
    }

    private static char[] makePalindrome(char[] s) {
        int m = s.length;
        char[] t = Arrays.copyOf(s, m);
        for (int i = 0; i < m / 2; i++)
            t[m - 1 - i] = t[i];
        return t;
    }

    public static Long findHigherPalindrome(Long limit) {
        String n = Long.toString(limit);
        char[] s = n.toCharArray();
        int m = s.length;
        char[] t = makePalindrome(s);
        for(int i = 0; i < m; i++) {
            if(s[i] < t[i])
                return Long.parseLong(String.valueOf(t));
            else if(s[i] > t[i]) {
                for(int j = (m - 1) / 2; j >= 0; j--)
                    if(++t[j] > '9')
                        t[j] = '0';
                    else
                        break;
                t = makePalindrome(t);
                return Long.parseLong(String.valueOf(t));
            }
        }
        return Long.parseLong(String.valueOf(t));
    }

    public static Long findLowerPalindrome(Long limit) {
        String n = Long.toString(limit);
        char[] s = n.toCharArray();
        int m = s.length;
        char[] t = makePalindrome(s);
        for(int i = 0; i < m; i++) {
            if(s[i] > t[i])
                return Long.parseLong(String.valueOf(t));
            else if(s[i] < t[i]) {
                for(int j = (m - 1) / 2; j >= 0; j--)
                    if(--t[j] < '0')
                        t[j] = '9';
                    else
                        break;
                if(t[0] == '0') {
                    char[] a = new char[m - 1];
                    Arrays.fill(a, '9');
                    return Long.parseLong(String.valueOf(a));
                }
                t = makePalindrome(t);
                return Long.parseLong(String.valueOf(t));
            }
        }
        return Long.parseLong(String.valueOf(t));
    }
}
