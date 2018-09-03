package array.classic;

import java.util.*;

public class LeetCode_90_SubsetsII {
    public static void main(String[] args) {
        int list[] = {1,2,2};
        System.out.println(subsetsWithDup2(list));
    }
    public static List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        res.add(new ArrayList<>());
        int last = nums[0];
        int lastSize = 1;
        for (int num : nums) {  // ①从数组中取出每个元素
            if(num != last) {
                lastSize = res.size();
                last = num;
            }
            int newSize = res.size();
            for (int i = newSize - lastSize; i < newSize; i++) {
                List<Integer> temp = new ArrayList<>(res.get(i));  // ②逐一取出中间结果集
                temp.add(num);  // ③将 num 放入中间结果集
                res.add(temp);  // ④加入到结果集中
            }
        }
        return res;
    }

    public static List<List<Integer>> subsetsWithDup2(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result= new ArrayList<>();
        dfs(nums,0,new ArrayList<>(),result);
        return result;
    }

    public static void dfs(int[] nums,int index,List<Integer> path,List<List<Integer>> result){
        result.add(path);
        for(int i = index; i < nums.length; i++){
            if(i > index && nums[i] == nums[i - 1]) continue;
            List<Integer> nPath= new ArrayList<>(path);
            nPath.add(nums[i]);
            dfs(nums,i + 1, nPath, result);
        }
    }
}
