package array.combination;

import java.util.LinkedList;
import java.util.List;

public class LeetCode_216_CombinationSumIII {
    public static void main(String[] args) {
        System.out.println(combinationSum3(3, 7));
    }

    /**
     * DFS
     */
    public static List<List<Integer>> combinationSum3(int k, int n) {
        List<Integer> temp = new LinkedList<>();
        List<List<Integer>> res = new LinkedList<>();
        helper(res, temp, n, 1, k);
        return res;
    }

    public static void helper(List<List<Integer>> res, List<Integer> temp, int target, int index, int k){
        if(temp.size() > k)
            return;
        if(0 == target && temp.size() == k){
            res.add(new LinkedList<>(temp));
            return;
        }else if(0 < target){
            for (int i = index; i <= 9; i++) {
                temp.add(i);
                helper(res, temp, target - i, i + 1, k);
                temp.remove(temp.size() - 1);
            }
        }
    }
}
