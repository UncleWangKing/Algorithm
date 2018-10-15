package array.duplicate;

import java.util.SortedSet;
import java.util.TreeSet;

public class LeetCode_220_ContainsDuplicateIII {
    public static void main(String[] args) {
        int list [] = {1,2,3,1};int k = 3;int t = 0;//true
        System.out.println(containsNearbyAlmostDuplicate(list, k, t));
    }

    /**
     * 和上一题的变化是相等变为了绝对值只差为t
     * 就不能方便的利用map的key了
     * k下标差 t值差 都是最大不能超过
     */
    public static boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        if(nums == null || nums.length < 2 || k < 1 || t < 0)
            return false;

        TreeSet<Long> window = new TreeSet<>();
        for(int i = 0; i<nums.length; i++){
            SortedSet<Long> set = window.subSet((long)nums[i] - t, true, (long)nums[i] + t, true);
            if(!set.isEmpty()){
                return true;
            }else{
                if(i >= k){
                    window.remove((long)nums[i - k]);
                }
                window.add((long)nums[i]);
            }
        }
        return false;
    }
}
