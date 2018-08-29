package array;

import util.ZDaPangArrayUtil;

public class LeetCode_80_RemoveDuplicatesfromSortedArrayII {
    public static void main(String[] args) {
        int []list = {1,1,1,1,2,3,3};
        System.out.println(removeDuplicates(list));
        ZDaPangArrayUtil.printArray(list);
    }
    //核心思路就和遮着数组挨个往后看一样 碰到不一样的 先拿2个放过来
    public static int removeDuplicates(int[] nums) {
        int pos = 0;//记录循环中 下一个可以填充值的位置 也就是有效数字下标+1 也就是个数
        int lastValue = 0;//记录循环的上一个值 -- 初始化为nums[0]会过不了[]输入
        int flag = 0;//记录某个值连续出现次数
        for (int i = 0; i < nums.length; i++) {
            //这个if属于维护flag值
            if(lastValue == nums[i]){
                flag++;
            }else {
                flag = 1;
            }
            //2个以内的 赋值到pos上去
            if(flag <= 2){
                nums[pos] = nums[i];
                pos++;
            }
            //维护lastValue
            lastValue = nums[i];
        }

        return pos;
    }
}