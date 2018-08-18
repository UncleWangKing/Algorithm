package array;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


public class LeetCode_128_LongestConsecutiveSequence {
    public static void main(String[] args) {
        int list[] = {100, 4, 200, 1, 3, 2};
        System.out.println(longestConsecutive_3(list));
    }

    /**
     * 虽然通过速度比排序会更慢 但是O(n) 准确是3N 满足了题目要求 --- 这道题排序等于作弊
     * 1.将数组扩容为long 然后加上最小的负数的绝对值(如果有) 目的是将数组转化成全部为正整数的等效数组 -- 扩容为long就是为了防止相加的时候爆掉
     * 2.将数组中值依次以 除以length+1为key 对length+1取余为数组下标的方式 放入hashMap的数组中 -- 放入的值为1即可，只为区别于数组默认的0
     * 3.遍历map中所有数组 然后取出结果 -- 最长连续出现1的数量
     */
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
    /**
     * 使用一个集合set存入所有的数字，然后遍历数组中的每个数字，如果其在集合中存在，那么将其移除，
     * 然后分别用两个变量pre和next算出其前一个数跟后一个数，然后在集合中循环查找，如果pre在集合中，那么将pre移除集合，然后pre再自减1，直至pre不在集合之中，
     * 对next采用同样的方法，那么next - pre - 1就是当前数字的最长连续序列，更新res即可。
     */
    public static int longestConsecutive_2(int[] nums) {
        Set<Integer> set = new HashSet<>(nums.length);
        for (int i = 0; i < nums.length; i++) {
            set.add(nums[i]);
        }

        int result = 0;

        for (int i = 0; i < nums.length; i++) {
            if(set.contains(nums[i])){
                set.remove(nums[i]);
                int pre = nums[i] - 1;
                int next = nums[i] + 1;
                while(set.contains(pre)) {
                    set.remove(pre--);
                }
                while(set.contains(next)) {
                    set.remove(next++);
                }
                int tempLength = next - pre - 1;
                if(result < tempLength)
                    result = tempLength;
            }
        }

        return result;
    }

    /**
     * 方法二类似思想换成hashMap
     */
    public static int longestConsecutive_3(int[] nums) {
        Map<Integer, Boolean> map = new HashMap<>(nums.length);
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], true);
        }

        int result = 0;

        for (int i = 0; i < nums.length; i++) {
            if(map.containsKey(nums[i])){
                map.remove(nums[i]);
                int pre = nums[i] - 1;
                int next = nums[i] + 1;
                while(map.containsKey(pre)) {
                    map.remove(pre--);
                }
                while(map.containsKey(next)) {
                    map.remove(next++);
                }
                int tempLength = next - pre - 1;
                if(result < tempLength)
                    result = tempLength;
            }
        }

        return result;
    }
}
