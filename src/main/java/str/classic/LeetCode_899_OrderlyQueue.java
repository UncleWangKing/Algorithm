package str.classic;

import java.util.Arrays;

public class LeetCode_899_OrderlyQueue {
    public static void main(String[] args) {
        System.out.println(orderlyQueue("cba", 1));
    }

    public static String orderlyQueue(String S, int K) {
        if(1 == K) {// 当 K == 1 时， 只能循环移动每个元素。无法改变相对位置。
            String res = S;
            int len = S.length();
            S = S + S;//很舒服的技巧
            for (int i = 0; i < len; i++) {
                if (res.compareTo(S.substring(i, i + len)) > 0) {
                    res = S.substring(i,i + len);
                }
            }
            return res;
        }

        char[] chars = S.toCharArray();
        Arrays.sort(chars);
        return String.valueOf(chars);
    }
}
