package array.classic;

import java.util.ArrayList;
import java.util.List;

public class LeetCode_78_Subsets {
    public static void main(String[] args) {
        int list[] = {1,2,3};
        System.out.println(subsets3(list));
    }

    /**
     * 循环版 --- 加入一个新数后的全组合数 等于原组合数所有集 外加一份 添加了新数的所有集
     * 只需取出所有集 复制一份 都加入新数就可以了
     */
    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        res.add(new ArrayList<>());
        for (int num : nums) {  // ①从数组中取出每个元素
            int size = res.size();
            for (int i = 0; i < size; i++) {
                List<Integer> temp = new ArrayList<>(res.get(i));  // ②逐一取出中间结果集
                temp.add(num);  // ③将 num 放入中间结果集
                res.add(temp);  // ④加入到结果集中
            }
        }
        return res;
    }

    /**
     * 最难理解的一种 将每个位置的值是否放入转化成二叉树 深搜
     */
    public static List<List<Integer>> subsets2(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        dfs(res, temp, nums, 0);
        return res;
    }

    private static void dfs(List<List<Integer>> res, List<Integer> temp, int[] nums, int j) {
        res.add(new ArrayList<>(temp));
        for(int i = j; i < nums.length; i++) {
            temp.add(nums[i]);  //① 加入 nums[i]
            dfs(res, temp, nums, i + 1);  //② 递归
            temp.remove(temp.size() - 1);  //③ 移除 nums[i]
        }
    }

    /**
     * 利用二进位来代表对应下标是否被加入 -- 妙不可言
     */
    public static List<List<Integer>> subsets3(int[] nums) {
        int len = nums.length;
        List<List<Integer>> res = new ArrayList<>();
        res.add(new ArrayList<>());//开始加入一个空集

        long bit = 1, bitmax = 1 << len;
        while(bit < bitmax) {
            List<Integer> tmpres = new ArrayList<>();
            long curBit = bit;
            for(int i = 0; i < len; i++){//依次检测前len个二进制位
                if(1 == (curBit & 1))
                    tmpres.add(nums[i]);
                curBit >>= 1;
            }
            res.add(tmpres);
            bit++;
        }
        return res;
    }
}
