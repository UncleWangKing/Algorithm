package array.numbersum;

import util.ZDaPangArrayUtil;

import java.util.HashMap;
import java.util.Map;

public class LeetCode_1_TwoSum {
    public static void main(String[] args) {
        int list[] = {2, 7, 11, 15};
        ZDaPangArrayUtil.printArray(twoSum(list, 9));
    }
    public static int[] twoSum(int[] list, int target) {
        Map<Integer, Integer> m = new HashMap<Integer, Integer>();
        int[] res = new int[2];
        for (int i = 0; i < list.length; ++i) {
            if (m.containsKey(target - list[i])) {
                res[0] = i;
                res[1] = m.get(target - list[i]);
                break;
            }
            m.put(list[i], i);
        }
        return res;
    }
}
