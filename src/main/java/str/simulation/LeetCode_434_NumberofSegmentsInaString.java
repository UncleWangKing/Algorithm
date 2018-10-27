package str.simulation;

public class LeetCode_434_NumberofSegmentsInaString {
    public static void main(String[] args) {
        System.out.println(countSegments("Hello, my name is John"));
    }

    /**
     * 模拟题练得就是读题能力 可以数单词 也可以数空格嘛
     */
    public static int countSegments(String s) {
        int res = 0;
        for (int i = 0; i < s.length(); ++i)
            if (' ' != s.charAt(i)  && (i == 0 || ' ' == s.charAt(i - 1)))
                ++res;
        return res;
    }
}
