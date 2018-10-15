package array.simulation;

import java.util.LinkedList;
import java.util.List;

public class LeetCode_228_SummaryRanges {
    /**
     * 输入: [0,1,2,4,5,7]
     输出: ["0->2","4->5","7"]
     * @param args
     */
    public static void main(String[] args) {
        int list[] = {0,2,4,5,7};
        System.out.println(summaryRanges(list));
    }

    public static List<String> summaryRanges(int[] nums) {
        List<String > res = new LinkedList<>();
        for (int i = 0; i < nums.length; i++) {
            int left = i;
            String str = String.valueOf(nums[i]);
            while (i < nums.length - 1  && nums[i] == nums[i + 1] - 1){
                i++;
            }
            if(i != left)
                str += "->" + nums[i];
            res.add(str);
        }

        return res;
    }
}
