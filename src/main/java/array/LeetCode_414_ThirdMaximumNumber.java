package array;

/**
 * @author ZhangDaPang 285296372@qq.com
 * @date 2018/9/17 14:50
 */
public class LeetCode_414_ThirdMaximumNumber {
    public static void main(String[] args) {
        int list[] = {3, 2, 1};
        System.out.println(thirdMax(list));
    }

    public static int thirdMax(int[] nums) {
        int one, two, three, count = 0;
        one = two = three = Integer.MIN_VALUE;
        for (int x : nums) {
            //若相同继续循环，要有count判断，因为nums中可能有Integer.MIN_VALUE
            if (x == one && count >= 1 || x == two && count >= 2 || x == three
                    && count >= 3)
                continue;
            if (x > one) {
                three = two;
                two = one;
                one = x;
                count++;
            } else if (x > two) {
                three = two;
                two = x;
                count++;
                //此处一定是有等于的，若没有有可能露掉
                //int nums[] = { 1, 2, Integer.MIN_VALUE };缺少一次判断
            } else if (x >= three) {
                three = x;
                count++;
            }

        }
        //count 代表更新的次数，若大于等于3，表示有第三大的数
        if (count >= 3)
            return three;
        else
            return one;
    }
}
