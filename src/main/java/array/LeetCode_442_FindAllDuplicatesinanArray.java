package array;

import java.util.LinkedList;
import java.util.List;

/**
 * @author ZhangDaPang 285296372@qq.com
 * @date 2018/9/18 11:02
 */
public class LeetCode_442_FindAllDuplicatesinanArray {
    public static void main(String[] args) {
        int list[] = {4,3,2,7,8,2,3,1};

        System.out.println(findDuplicates(list));
    }
    //看漏了 1 ≤ a[i] ≤ n  想了两天 本来还很期待是什么惊人的操作
    public static List<Integer> findDuplicates(int[] nums) {
        List<Integer> resultList = new LinkedList<>();
        for (int i = 0; i < nums.length; i++) {
            int index = Math.abs(nums[i]) - 1;
            if(nums[index] < 0)
                resultList.add(index + 1); // index + 1 == Math.abs(nums[i])

            nums[index] = -nums[index];
        }
        return resultList;
    }
}
