package str.simulation;

public class LeetCode_541_ReverseStringII {
    public static void main(String[] args) {
        System.out.println(reverseStr("abcdefg", 2));//"bacdfeg"
    }

    /**
     * 题目转换:奇偶k个字符的处理不同
     * 奇:翻
     * 偶:不翻
     */
    public static String reverseStr(String s, int k) {
        char[] list = s.toCharArray();

        for (int i = 0; i < list.length; i += 2 * k) {
            int left = i, right = Math.min(i + k - 1, list.length - 1);
            while (left < right){
                char temp = list[left];
                list[left] = list[right];
                list[right] = temp;
                left++;
                right--;
            }
        }

        return String.valueOf(list);
    }
}
