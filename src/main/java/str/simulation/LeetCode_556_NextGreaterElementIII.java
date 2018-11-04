package str.simulation;


import java.util.Arrays;

public class LeetCode_556_NextGreaterElementIII {
    public static void main(String[] args) {
        System.out.println(nextGreaterElement(12443322));//13222344
    }

    public static int nextGreaterElement(int n) {
        if(n < 10)
            return -1;
        String s = String.valueOf(n);
        char[] chars = s.toCharArray();
        for (int i = s.length() - 2; i >= 0 ; i--) {
            if(chars[i] < chars[i + 1]) {
                int index = i + 1;
                while (index < s.length() && chars[index] > chars[i])
                    index++;
                index--;
                char temp = chars[i];
                chars[i] = chars[index];
                chars[index] = temp;
                Arrays.sort(chars, i + 1, s.length());
                long res = Long.valueOf(String.valueOf(chars));
                return res > Integer.MAX_VALUE ? -1 : (int) res;
            }
        }
        return -1;
    }
}
