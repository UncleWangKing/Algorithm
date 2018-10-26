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
        int count = 1;
        Arrays.sort(chars);
        for (int i = 1; i < chars.length; i++) {
            if(chars[i] != chars[i - 1])
                count++;
        }
        return count * 2;
    }
}
