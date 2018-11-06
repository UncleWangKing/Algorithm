package str.classic;

public class LeetCode_72_EditDistance {
    public static void main(String[] args) {

    }

    /**
     * 针对LCS思路举一些反例
     * abc dea 公共a 暴力3 LCS 4 最优3
     * abcd adef 公共ad 暴力4 LCS 4 最优3
     * 思考 是否不在对应位置的都得不偿失？
     * 不对比如
     * abc ac 公共ac 暴力3 LCS 1 最优1
     * 思考 是否间距在左边的大于右边间距的LCS一定赚？
     * 不对 比如两个赚 其他亏 不好判断盈亏
     * 总之LCS未必是最优的 某个三个LCS可能不如某个两个的CS
     * 迷茫
     * 找规律判断盈亏？还是另辟蹊径？
     * kmp算法next能走最远的? 有过不了不了左长于右时的右方全匹配的LCS
     * abc acd
     * 左右谁更长分情况？
     */
    public static int minDistance(String word1, String word2) {

    }
}
