package str.matching;

/**
 * @author ZhangDaPang 285296372@qq.com
 * @date 2018/10/24 13:56
 */
public class KMP {
    public static void main(String[] args) {
        System.out.println(kmp("aabac d abaabab", "abab"));
    }

    public static int kmp(String s, String target){
        int i = 0, j = 0;
        int[] next = getNext(target);
        while (i < s.length() && j < next.length){
            if(-1 == j || s.charAt(i) == target.charAt(j)){
                i++;j++;
            }else
                j = next[j];
        }
        return j == next.length ? i - j : -1;
    }

    /**
     * 关于next数组的三个点 -- str = "abab"为例
     * 1.最开始是最大前后缀数组 记录"当前位置"如果"失配"的"当前位置"为结尾所形成的后缀长度-----------------------------[0,0,1,2]
     * 2.为了使用方便 将最大前后缀数组右移一维并首位改为-1 就是我们所说的next数组------------------------------------[-1,0,0,1]
     * 3.abab这种情况 很明显 可以优化 因为和第str[3]不相等必然和str[next[3]]也不相等 str[2]和str[next[2]]同理------[-1,0,-1,0]
     */
    public static int[] getNext(String s){
        int [] next = new int[s.length()];
        next[0] = -1;
        int i = 0, j = -1;
        while (i < s.length() - 1){
            if(-1 == j || s.charAt(i) == s.charAt(j)){
                ++i;++j;
                next[i] = (s.charAt(i) != s.charAt(j)) ? j : next[j];
            }else
                j = next[j];
        }

        return next;
    }
}
