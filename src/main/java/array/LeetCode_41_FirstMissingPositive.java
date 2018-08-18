package array;

/**
 * 核心思路:利用该题中 数组位置和值的强关联进行数值调整
 * 比如 [3, 4, -1, 1] 调整成 [1, *, 3, 4] *表示-1那个值可能在调整过程中被数组中任何其他值覆盖
 * 然后遍历 发现*位置上存在 nums[i] != i + 1 则返回 i + 1
 */
public class LeetCode_41_FirstMissingPositive {
    public static void main(String[] args) {
        int[] list ={2, 1};
        System.out.println(firstMissingPositive(list));
    }

    public static int firstMissingPositive(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            int tempPos = i;
            int tempValue = nums[i];
            while(tempValue > 0 && tempValue <= nums.length && nums[tempPos] != tempPos + 1){
                int tempTempValue = nums[tempValue - 1];
                nums[tempValue - 1] = tempValue;
                if(tempTempValue < 0)
                    break;
                tempPos = tempTempValue - 1;
                tempValue = tempTempValue;
            }
        }

        for (int i = 0; i < nums.length; i++) {
            if(nums[i] != i + 1)
                return i + 1;
        }

        return nums.length + 1;
    }
}
