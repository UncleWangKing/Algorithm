package binarysearch;

/**
 * @author ZhangDaPang 285296372@qq.com
 * @date 2019/3/20 13:18
 */
public class LeetCode_392_IsSubsequence {
    public static void main(String[] args) {
        String s = "abc";
        String t = "ahbgdc";
        System.out.println(isSubsequence(s, t));
    }

    /**
     * 用两个指针分别指向字符串s和t，然后如果字符相等，则i和j自增1，反之只有j自增1，最后看i是否等于s的长度，等于说明s已经遍历完了，而且字符都有在t中出现过
     * PS：尽管子序列可能有多重但只需找出其中一种，这种写法找出的是最"靠左"的那种
     * 例:s = abc t = acabc
     * 找出的是034 而不是 234
     */
    public static boolean isSubsequence(String s, String t) {
        int i = 0;
        for (int j = 0; j < t.length() && i < s.length(); ++j) {
            if (s.charAt(i) == t.charAt(j)) ++i;
        }
        return i == s.length();
    }

    /**
     * 剪枝加内置函数更高效
     */
    public static boolean isSubsequence2(String s, String t) {
        int index = -1;
        for(char c : s.toCharArray()){
            index = t.indexOf(c,index + 1);
            if(index == -1){
                return false;
            }
        }
        return true;
    }
}
