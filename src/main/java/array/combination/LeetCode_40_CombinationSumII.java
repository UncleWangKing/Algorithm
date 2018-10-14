package array.combination;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LeetCode_40_CombinationSumII {
    public static void main(String[] args) {
        int list[] = {10,1,2,7,6,1,5};
        System.out.println(combinationSum2(list, 8));
    }

    public static List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList();
        Arrays.sort(candidates);
        backtracking(candidates, 0, result, target, new ArrayList());
        return result;
    }

    private static void backtracking(int[] candidates,
                                     int step,
                                     List<List<Integer>> result,
                                     int target,
                                     List<Integer> curr) {
        if (target > 0) {
            for (int i = step; i < candidates.length; i++) {
                /**
                 * 只取一个 去重关键
                 */
                if (candidates[i] > target || ((i > step) && (candidates[i - 1] == candidates[i])))
                    continue;

                curr.add(candidates[i]);
                backtracking(candidates, i + 1, result, target - candidates[i], curr);
                curr.remove(curr.size() - 1);//回溯操作
            }
        } else if (target == 0) {
            result.add(new ArrayList(curr));
        }
    }
}
