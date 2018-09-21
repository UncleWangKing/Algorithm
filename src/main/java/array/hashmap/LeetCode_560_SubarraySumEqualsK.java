package array.hashmap;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ZhangDaPang 285296372@qq.com
 * @date 2018/9/21 14:38
 */
public class LeetCode_560_SubarraySumEqualsK {
    public static void main(String[] args) {
        int list[] = {1,1,1};
        System.out.println(subarraySum2(list, 2));
    }

    //暴力 构造所有情况
    public static int subarraySum(int[] nums, int k) {
        int result = 0;

        for (int i = 0; i < nums.length; i++) {
            int sum = nums[i];
            if (sum == k)
                result++;
            for (int j = i + 1; j < nums.length; j++) {
                sum += nums[j];
                if (sum == k)
                    result++;
            }
        }

        return result;
    }
    //map
    public static int subarraySum2(int[] nums, int k) {
        int sum = 0, count = 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        for(int i = 0; i < nums.length; i++){
            sum += nums[i];
            if(map.containsKey(sum - k))//妙不可言 以i为结尾的满足条件子数组的个数
                count += map.get(sum - k);
            map.put(sum, map.getOrDefault(sum, 0) + 1);//缓存0-i之和sum出现的次数
        }
        return count;
    }
}
