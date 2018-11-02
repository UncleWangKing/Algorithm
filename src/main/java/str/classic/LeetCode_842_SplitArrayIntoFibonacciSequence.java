package str.classic;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ZhangDaPang 285296372@qq.com
 * @date 2018/11/2 16:10
 */
public class LeetCode_842_SplitArrayIntoFibonacciSequence {
    public static void main(String[] args) {
//        String S = "1101111";
//        String S = "123456579";//[123,456,789]
//        String S = "0000";//[0,0,0,0]
        String S = "417420815174208193484163452262453871040871393665402264706273658371675923077949581449611550452755";//[4,17420815,17420819,34841634,52262453,87104087,139366540,226470627,365837167,592307794,958144961,1550452755]
//        String S = "11235813";//[1,1,2,3,5,8,13]
//        String S = "97127987005586830212";
        System.out.println(splitIntoFibonacci(S));
    }

    /**
     * 遍历前两个数的选取 后面的步骤都是验证
     */
    public static List<Integer> splitIntoFibonacci(String S) {
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i <= Math.min(10, S.length()); i++) {//F[i] 在 int范围内 -- 剪枝动作
            long first = Long.valueOf(S.substring(0, i));
            for (int j = i + 1; j < Math.min(i + 10, S.length()); j++) {
                long second = Long.valueOf(S.substring(i, j));
                list.add((int)first);
                list.add((int)second);
                long tempFirst = first;
                long tempSecond = second;
                int start = j;
                int end = j + 1;
                while (end <= S.length()) {
                    long third = Long.valueOf(S.substring(start, end));
                    if(third > tempFirst + tempSecond || third > Integer.MAX_VALUE)
                        break;
                    if(third == tempFirst + tempSecond){
                        list.add((int)third);
                        tempFirst = tempSecond;
                        tempSecond = third;
                        start = end;
                        if(end == S.length())
                            return list;
                    }
                    end++;
                }
                list.clear();
                if('0' == S.charAt(i))//0开头只需要验证第一个数是0的情况
                    break;
            }
            if('0' == S.charAt(0))//0开头只需要验证第一个数是0的情况
                break;
        }

        return list;
    }
    /**
     * 看看人家！！！
     */
    public static List<Integer> splitIntoFibonacci2(String S) {
        List<Integer> res = new ArrayList<>(S.length());

        for (int i = 1; i < S.length() && i < 11; i++) {
            if (S.charAt(0) == '0' && i > 1) {
                break;
            }

            for (int j = 1; j < S.length() - i && j < 11; j++) {
                if (S.charAt(i) == '0' && j > 1) {
                    break;
                }
                long a = Long.parseLong(S.substring(0, i));
                long b = Long.parseLong(S.substring(i, i + j));

                res.add((int) a);
                res.add((int) b);

                if (isFibonacci(S, a, b, i + j, res)) {
                    return res;
                }
                res.clear();
            }
        }

        return res;
    }


    private static boolean isFibonacci(String S, long a, long b, int idx, List<Integer> res) {
        long sum = a + b;
        if (sum > Integer.MAX_VALUE) {
            return false;
        }
        String sumStr = String.valueOf(sum);
        if (S.startsWith(sumStr, idx)) {
            res.add((int) sum);
            if (idx + sumStr.length() == S.length()) {
                return true;
            }
            return isFibonacci(S, b, sum, idx + sumStr.length(), res);
        }
        return false;
    }
}
