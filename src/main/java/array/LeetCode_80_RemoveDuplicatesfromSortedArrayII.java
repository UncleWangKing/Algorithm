package array;

import util.ZDaPangArrayUtil;

public class LeetCode_80_RemoveDuplicatesfromSortedArrayII {
    public static void main(String[] args) {
        int []list = {0,0,1,1,1,1,2,3,3};
        System.out.println(removeDuplicates(list));
        ZDaPangArrayUtil.printArray(list);
    }
    public static int removeDuplicates(int[] nums) {
        int pos = 0;
        int flag = 0;
        int last = 0;
        for(int i = 0;i < nums.length;i++){
            if(i == 0){
                last = nums[i];
                flag = 1;
                pos++;
            }
            else{
                if(nums[i] == last){
                    flag++;
                }
                else{
                    flag = 1;
                }
                if(flag <= 2){
                    nums[pos] = nums[i];pos++;
                }
                last = nums[i];
            }
        }
        return pos;
    }
}