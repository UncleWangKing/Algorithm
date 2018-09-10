package dp.classic.localandglobal;

public class LeetCode_198_HouseRobber {
    public static void main(String[] args) {
        int[] list = {1,2,3,1};
        System.out.println(rob4(list));
    }

    //rob[i] = max(num[i] + rob[i - 2], rob[i - 1])
    public static int rob(int[] nums) {
        if(nums.length==0)
            return 0;
        if(nums.length==1)
            return nums[0];
        if(nums[1]<nums[0]){
            nums[1]=nums[0];
        }
        //直接利用原数组 不再使用额外空间 漂亮
        for(int i=2;i<nums.length;i++){
            nums[i]=nums[i-1]>(nums[i-2]+nums[i])?nums[i-1]:(nums[i-2]+nums[i]);
        }
        return nums[nums.length-1];
    }

    public static int rob2(int[] nums) {
        int a = 0, b = 0;
        for (int i = 0; i < nums.length; ++i) {
            if (i % 2 == 0) {
                a = Math.max(a + nums[i], b);
            } else {
                b = Math.max(a, b + nums[i]);
            }
        }
        return Math.max(a, b);
    }
    //3是2的简化版本
    public static int rob3(int[] nums) {
        int a = 0, b = 0;
        for (int i = 0; i < nums.length; ++i) {
            int m = a, n = b;
            a = n + nums[i];
            b = Math.max(m, n);
        }
        return Math.max(a, b);
    }
    //local global dp
    public static int rob4(int[] nums){
        if (nums == null || nums.length == 0)
            return 0;
        if(nums.length == 1)
            return nums[0];
        if(nums.length == 2)
            return Math.max(nums[0],nums[1]);
        int[] local = new int[3];//滚动压掉了 只需要3个 %3这种压缩法 第一次见 但是消耗计算量
        int[] global = new int[3];
        local[0] = global[0] = 0;
        local[1] = global[1] = nums[0];
        local[2] = global[2] = Math.max(nums[0],nums[1]);
        for(int i = 3; i < nums.length+1; i++){
            local[i % 3] = Math.max(global[(i - 2) % 3], global[(i - 3) % 3]) + nums[i - 1];
            global[i % 3] = Math.max(global[(i - 1) % 3], local[i % 3]);
        }
        return global[nums.length % 3];
    }
}
