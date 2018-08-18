package array;

import java.util.HashMap;
import java.util.Map;

/**
 * 虽然通过速度比排序会更慢 但是O(n) 准确是3N 满足了题目要求 --- 这道题排序等于作弊
 * 1.将数组扩容为long 然后加上最小的负数的绝对值(如果有) 目的是将数组转化成全部为正整数的等效数组 -- 扩容为long就是为了防止相加的时候爆掉
 * 2.将数组中值依次以 除以length+1为key 对length+1取余为数组下标的方式 放入hashMap的数组中 -- 放入的值为1即可，只为区别于数组默认的0
 * 3.遍历map中所有数组 然后取出结果 -- 最长连续出现1的数量
 */

public class LeetCode_128_LongestConsecutiveSequence {
    public static void main(String[] args) {
        int list[] = {100, 4, 200, 1, 3, 2};
        System.out.println(longestConsecutive(list));
    }

    public static int longestConsecutive(int[] nums) {
        long list[] = new long[nums.length];
        long min = 0;
        for (int i = 0; i < nums.length; i++) {
            list[i] = nums[i];
            if(min > list[i])
                min = list[i];
        }
        if(0 > min)
            for (int i = 0; i < list.length; i++)
                list[i] -= min;

        Map<Long, long[]> map = new HashMap<>();
        for (int i = 0; i < list.length; i++) {
            long divide = list[i] / (list.length + 1);
            long value = list[i] % (list.length + 1);

            if(null == map.get(divide))
                map.put(divide, new long[list.length + 1]);

            map.get(divide)[(int)value] = 1;
        }
        int result = 0;

        for (Map.Entry<Long, long[]> entry: map.entrySet()) {
            int temp = 0;
            for (int i = 0; i < entry.getValue().length; i++) {
                if(1 == entry.getValue()[i]) {
                    temp++;
                    if(result < temp)
                        result = temp;
                }else {
                    temp = 0;
                }
            }
        }

        return result;
    }
}
