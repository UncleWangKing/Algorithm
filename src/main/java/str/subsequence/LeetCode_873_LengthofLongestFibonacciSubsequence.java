package str.subsequence;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LeetCode_873_LengthofLongestFibonacciSubsequence {
    public static void main(String[] args) {
//        int list[] = {1,2,3,4,5,6,7,8};//5 [1,2,3,5,8]
        int list[] = {1,3,7,11,12,14,18};//3 [1,11,12]，[3,11,14] 以及 [7,11,18]
        System.out.println(lenLongestFibSubseq2(list));
    }

    /**
     * 直接暴力效率也不差
     */
    public static int lenLongestFibSubseq(int[] A) {
        int max = 0;
        Set<Integer> set = new HashSet<>();
        for (int a : A)
            set.add(a);
        for (int i = 0; i < A.length; ++i)
            for (int j = i + 1; j < A.length; ++j) {
                int x = A[j], y = A[i] + A[j];
                int length = 2;
                while (set.contains(y)) {
                    int z = x + y;
                    x = y;
                    y = z;
                    max = Math.max(max, ++length);
                }
            }

        return max >= 3 ? max : 0;
    }

    /**
     * 本质还是LIS，只是不能二分优化了。回忆这三个题走来。
     * 1.求上升。
     * 2.上升条件变了。
     * 3.不是上升了。--- 虽然结果数字是上升，但是之前维护的那个数组已经无效了。
     * dp[i][j] (i < j) 代表包含i和j下标的子序列最大长度。
     * 倒着遍历，如果A[i] + A[j]存在，长度 + 1，否则长度默认是2；
     * 用一个max收集最大长度。
     * if(map.containsKey(A[i] + A[j])){
     *  int position = map.get(A[i] + A[j]);
     *  dp[i][j] = dp[j][position] + 1;
     * }else {
     *  dp[i][j] = 2;
     * }
     * 计算过程中我们需要反复查询某个“值”是否在数组中，数组只能根据下标快速查询。
     * 所以我们用一个map，来把只能下标查询的数组，变成可以值查询的map。
     * 一点点数据结构的感觉？蛤？数据结构本质就是方便特定情况的CRUD。
     */
    public static int lenLongestFibSubseq2(int[] A) {
        int max = 0;
        Map<Integer,Integer> map = new HashMap<>();

        for(int i = 0; i < A.length; i++)
            map.put(A[i], i);
        int [][]dp = new int[A.length][A.length];

        for(int i = A.length - 1; i >= 0; i--){
            for(int j = i + 1; j < A.length; j++){
                if(map.containsKey(A[i] + A[j])){
                    int position = map.get(A[i] + A[j]);
                    dp[i][j] = dp[j][position] + 1;
                    max = Math.max(max,dp[i][j]);
                }else {
                    dp[i][j] = 2;
                }
            }
        }

        return max;
    }
}
