package str.classic;

/**
 * @author ZhangDaPang 285296372@qq.com
 * @date 2018/10/24 15:33
 */
public class LeetCode_696_CountBinarySubstrings {
    public static void main(String[] args) {
        System.out.println(countBinarySubstrings("0011"));
    }

    /**
     * 这道题给了我们一个二进制字符串，然后我们统计具有相同0和1的个数，
     * 且0和1各自都群组在一起(即0和1不能交替出现)的子字符串的个数，
     * 题目中的两个例子也很能说明问题。那么我们来分析题目中的第一个例子00110011，
     * 符合要求的子字符串要求0和1同时出现，那么当第一个1出现的时候，前面由于前面有两个0，
     * 所以肯定能组成01，再遇到下一个1时，此时1有2个，0有2个，能组成0011，下一个遇到0时，此时0的个数重置为1，而1的个数有两个，
     * 所以一定有10，同理，下一个还为0，就会有1100存在，之后的也是这样分析。那么我们可以发现我们只要分别统计0和1的个数，
     * 而且如果当前遇到的是1，那么只要之前统计的0的个数大于当前1的个数，就一定有一个对应的子字符串，
     * 而一旦前一个数字和当前的数字不一样的时候，那么当前数字的计数要重置为1。所以我们遍历元数组，如果是第一个数字，
     * 那么对应的ones或zeros自增1。然后进行分情况讨论，如果当前数字是1，然后判断如果前面的数字也是1，则ones自增1，否则ones重置为1。
     * 如果此时zeros大于ones，res自增1。反之同理，如果当前数字是0，然后判断如果前面的数字也是0，则zeros自增1，否则zeros重置为1。如果此时ones大于zeros，res自增1
     */
    public static int countBinarySubstrings(String s) {
        int zeros = 0, ones = 0, res = 0;
        for (int i = 0; i < s.length(); ++i) {
            if (i == 0) {
                if(s.charAt(i) == '1')
                    ++ones;
                else
                    ++zeros;
            } else {
                if (s.charAt(i) == '1') {
                    ones = (s.charAt(i - 1) == '1') ? ones + 1 : 1;
                    if (zeros >= ones) ++res;
                } else if (s.charAt(i) == '0') {
                    zeros = (s.charAt(i - 1) == '0') ? zeros + 1 : 1;
                    if (ones >= zeros) ++res;
                }
            }
        }
        return res;
    }

    /**
     * 下面这种方法更加简洁了，不用具体的分0和1的情况来讨论了，
     * 而是直接用了pre和cur两个变量，其中pre初始化为0，cur初始化为1，然后从第二个数字开始遍历，
     * 如果当前数字和前面的数字相同，则cur自增1，否则pre赋值为cur，cur重置1。
     * 然后判断如果pre大于等于cur，res自增1。其实核心思想跟上面的方法一样，只不过pre和cur可以在0和1之间切换
     */
    public static int countBinarySubstrings2(String s) {
        int res = 0, pre = 0, cur = 1, n = s.length();
        for (int i = 1; i < n; ++i) {
            if (s.charAt(i) == s.charAt(i - 1)) ++cur;
            else {
                pre = cur;
                cur = 1;
            }
            if (pre >= cur) ++res;
        }
        return res;
    }
}
