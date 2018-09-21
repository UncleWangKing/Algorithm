package array.indexmagic;

import java.util.HashSet;
import java.util.Set;

/**
 * @author ZhangDaPang 285296372@qq.com
 * @date 2018/9/21 15:36
 */
public class LeetCode_565_ArrayNesting {
    public static void main(String[] args) {
        int list[] = {5,4,0,3,1,6,2};

        System.out.println(arrayNesting2(list));
    }
    //不记录会TLE
    public static int arrayNesting(int[] nums) {
        int max = 0;
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if(set.contains(nums[i]))
                continue;
            set.add(nums[i]);
            int temp = nums[i];
            int local = 0;
            do{
                temp = nums[temp];
                local++;
                set.add(temp);
            }
            while (temp != nums[i]);
            max = Math.max(max, local);
        }

        return max;
    }
    //空间优化
    public static int arrayNesting2(int[] nums) {
        int n = nums.length, res = 0;
        for (int i = 0; i < n; ++i) {
            int cnt = 1;
            while (nums[i] != i && nums[i] != nums[nums[i]]) {//把当前位置值 和值对应位置交换 即完成迭代 又完成了记录重复 妙不可言
                swap(nums, i, nums[i]);
                ++cnt;
            }
            res = Math.max(res, cnt);
        }
        return res;
    }

    public static void swap(int[] nums, int left, int right){
        int temp = nums[left];
        nums[left] = nums[right];
        nums[right] = temp;
    }
}
