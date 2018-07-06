package dp.dp01;

import java.util.Arrays;
import java.util.List;

/**
 * @author ZhangDaPang 285296372@qq.com
 * @date 2018/7/3 10:11
 */
public class LeetCode_139_WordBreak {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("apple", "pen");
        String str = "applepenapple";
        System.out.println(wordBreak(str, list));
    }

    /**
     * res[i]表示前i个字符能不能被dict完美划分
     * 判断res[i]，则需要遍历0~i中是否存在一个j，使得res[j]=true而且j+1~i存在于dict中
     * 本题还有一个值得注意的地方，一定要考虑到单词长度是有上限的！，所以每次不需要遍历0~i而是x~i(i-x为单词最大长度)
     */
    public static boolean wordBreak(String s, List<String> wordDict) {
        if(null == s || 0 == s.length())
            return true;
        boolean[] res = new boolean[s.length()+1];
        res[0] = true;
        for (int i = 0; i < s.length(); i++) {
            StringBuilder str = new StringBuilder(s.substring(0, i + 1));
            for(int j = 0; j <= i; j++){
                if(res[j] && wordDict.contains(str.toString())){
                    res[i + 1] = true;
                    break;
                }
                str.deleteCharAt(0);
            }
        }
        return res[s.length()];
    }
}
