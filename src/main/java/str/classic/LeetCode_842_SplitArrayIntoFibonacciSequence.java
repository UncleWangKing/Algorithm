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
            list.add((int)first);
            for (int j = i + 1; j < Math.min(i + 10, S.length()); j++) {
                long second = Long.valueOf(S.substring(i, j));
                list.add((int)second);
                long tempFirst = first;
                long tempSecond = second;
                int start = j;
                int end = j + 1;
                while (end <= S.length()) {
                    if(end - start > 10)
                        break;
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
                    }else if('0' == S.charAt(start))//0开头只需要验证第一个数是0的情况
                        break;
                    end++;
                }
                list.clear();
                list.add((int)first);
                if('0' == S.charAt(i))//0开头只需要验证第一个数是0的情况
                    break;
            }
            list.clear();
            if('0' == S.charAt(0))//0开头只需要验证第一个数是0的情况
                break;
        }

        return list;
    }
}
