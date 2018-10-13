package array.combination;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class LeetCode_39_CombinationSum {
    public static void main(String[] args) {
        int list[] = {2,3,6,7};
        System.out.println(combinationSum2(list, 7));
    }

    //真香
    public static List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<Integer> list = new ArrayList<>();
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(candidates);
        helper(res, list, candidates, target, 0);
        return res;
    }

    private static void helper(List<List<Integer>> res, List<Integer> list, int[] candidates, int target, int index) {
        if (0 == target) {
            res.add(new ArrayList<>(list));
            return;
        }

        for (int i = index; i < candidates.length; i++) {
            if (target - candidates[i] >= 0) {
                list.add(candidates[i]);
                helper(res, list, candidates, target - candidates[i], i);
                list.remove(list.size() - 1);
            } else {
                break;
            }
        }
    }

    //太丑陋的题了
    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        int list [] = new int[candidates.length];
        int cout [] = new int[candidates.length];
        for (int i = 0; i < candidates.length; i++)
            list[i] = target / candidates[i];

        List<List<Integer>> result = new LinkedList<>();

        load(0, 0, list, candidates, cout, target, result);

        return result;
    }

    public static void load(int step,
                            int sum,
                            int list[],
                            int []candidates,
                            int count[],
                            int target,List<List<Integer>> result){
        if(sum > target)
            return;
        if(step == list.length)
            return;
        for (int i = 0; i <= list[step]; i++) {
            int temp = candidates[step] * i;
            sum += temp;
            if(sum > target) {
                //回溯还原操作
                sum -= temp;
                return;
            }
            count[step] = i;
            if(sum == target){
                List<Integer> tempList = new LinkedList<>();
                for (int j = 0; j <= step; j++) {
                    for (int k = 0; k < count[j]; k++) {
                        tempList.add(candidates[j]);
                    }
                }
                result.add(tempList);
                sum -= temp;
                return;
            }
            if(sum < target){
                load(step + 1, sum, list, candidates, count, target, result);
            }
            sum -= temp;
        }
    }
}
