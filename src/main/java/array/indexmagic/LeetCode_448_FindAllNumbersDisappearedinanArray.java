package array.indexmagic;

import java.util.LinkedList;
import java.util.List;

/**
 * @author ZhangDaPang 285296372@qq.com
 * @date 2018/9/18 16:31
 */
public class LeetCode_448_FindAllNumbersDisappearedinanArray {
    public static void main(String[] args) {
        int list[] = {4,3,2,7,8,2,3,1};

        System.out.println(findDisappearedNumbers(list));
    }

    /**
     * 沿用442思路 只是第二次以后索引到都不再不转回来
     */
    public static List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> resultList = new LinkedList<>();
        for (int i = 0; i < nums.length; i++) {
            int index = Math.abs(nums[i]) - 1;
            if(nums[index] > 0)
                nums[index] = -nums[index];
        }
        for (int i = 0; i < nums.length; i++) {
            if(nums[i] > 0)
                resultList.add(i + 1); // index + 1 == Math.abs(nums[i])
        }

        return resultList;
    }
}
