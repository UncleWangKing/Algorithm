package array.duplicate;

import java.util.HashMap;
import java.util.Map;

public class LeetCode_219_ContainsDuplicateII {
    public static void main(String[] args) {
        int list [] = {99,99};int k = 2;
        System.out.println(containsNearbyDuplicate(list, k));
    }

    /**
     * 踩的人很多
     * 因为题目有歧义 最大"下标差是k
     * 代表比k小也可以
     * 所以 [99,99] 2 最大差是1 应该返回true
     */
    public static boolean containsNearbyDuplicate(int[] nums, int k) {
        /**
         * 可能是题目的题意描述歧义
         * 导致提交者不尊重该题
         * 开始利用测试数据来优化取得名次
         * 下面这两行代码hack了这道题的测试集
         * nums.length > 1000 显然跟结果无关
         * 但是正好排除了一些大数据的测试 所以排名第一
         */
//        if(k < 0 || nums.length > 1000)
//            return false;

        Map<Integer, Integer> map = new HashMap<>();

        for(int i = 0; i < nums.length; i++){
            if(map.containsKey(nums[i])){
                if(k >= i - map.get(nums[i])) return true;
            }
            map.put(nums[i], i);
        }

        return false;
    }
}
