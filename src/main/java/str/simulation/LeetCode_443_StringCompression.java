package str.simulation;

import java.util.Arrays;

/**
 * @author ZhangDaPang 285296372@qq.com
 * @date 2018/10/26 19:00
 */
public class LeetCode_443_StringCompression {
    public static void main(String[] args) {
        char list[] = {'a','b','b','b','b','b','b','b','b','b','b','b','b'};
        System.out.println(compress(list));
    }

    public static int compress(char[] chars) {
        int n = chars.length, cur = 0;
        for (int i = 0, j = 0; i < n; i = j) {
            while (j < n && chars[j] == chars[i]) ++j;
            chars[cur++] = chars[i];
            if (j - i == 1) continue;
            for (char c : String.valueOf(j - i).toCharArray()) chars[cur++] = c;
        }
        return cur;
    }
}
