package array.simulation;

public class LeetCode_724_FindPivotIndex {
    public static void main(String[] args) {
//        int list[] = {1, 7, 3, 6, 5, 6};//3
//        int list[] = {-1,-1,-1,-1,-1,0};//2
        int list[] = {-1,-1,-1,0,1,1};//0
        System.out.println(pivotIndex(list));
    }

    /**
     * 两个下标左右往中间遍历 两个变量记录和
     * 记录和可以用一个变脸 左加右减等于0即可
     * 为何我直接就想到了这一点？变强？滑稽
     * 然而可能有负数 所有并不能省
     * 菜鸡！
     */

    /**
     * 累加数组辅助
     */
    public static int pivotIndex(int[] nums) {
        if(0 == nums.length)
            return -1;

        int[] sum = new int[nums.length];
        sum[0] = nums[0];
        for (int i = 1; i < nums.length; i++)
            sum[i] += nums[i] + sum[i - 1];
        if((0 == sum[nums.length - 1] - nums[0]))
            return 0;
        for (int i = 1; i < nums.length; i++)
            if((sum[nums.length - 1] - nums[i]) == sum[i - 1] * 2) // 不能除以2 整数截断
                    return i;
        return -1;
    }
}
