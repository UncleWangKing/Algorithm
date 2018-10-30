package str.simulation;

import java.util.Arrays;
import java.util.List;

public class LeetCode_MinimumTimeDifference {
    public static void main(String[] args) {
        List<String> timePoints = Arrays.asList("23:59","00:00");
        System.out.println(findMinDifference(timePoints));
    }

    /**
     * 利用数值范围的trick
     */
    public static int findMinDifference(List<String> timePoints) {
        int res = Integer.MAX_VALUE, pre = 0, first = Integer.MAX_VALUE, last = Integer.MIN_VALUE;
        int[] mask = new int[1440];
        for (String str : timePoints) {//构造mask并顺便剪枝
            int h = Integer.valueOf(str.substring(0, 2)), m = Integer.valueOf(str.substring(3));
            if (1 == mask[h * 60 + m]) return 0;//剪枝
            mask[h * 60 + m] = 1;
        }
        for (int i = 0; i < 1440; ++i) {
            if (1 == mask[i]) {
                if (first != Integer.MAX_VALUE) {
                    res = Math.min(res, i - pre);
                }
                first = Math.min(first, i);
                last = Math.max(last, i);
                pre = i;
            }
        }
        return Math.min(res, 1440 + first - last);
    }
}
