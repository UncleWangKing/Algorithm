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
     * 子序列 不是子数组
     * 建立字典 方便查询 O(n^2) O(n)
     */
    public static int lenLongestFibSubseq(int[] A) {
        int ans = 0;
        Set<Integer> set = new HashSet<>();
        for (int a : A)
            set.add(a);
        for (int i = 0; i < A.length; ++i)
            for (int j = i+1; j < A.length; ++j) {
                int x = A[j], y = A[i] + A[j];
                int length = 2;
                while (set.contains(y)) {
                    int z = x + y;
                    x = y;
                    y = z;
                    ans = Math.max(ans, ++length);
                }
            }

        return ans >= 3 ? ans : 0;
    }

    /**
     * dp
     */
    public static int lenLongestFibSubseq2(int[] A) {
        int retData = 0;
        Map<Integer,Integer> dataMap = new HashMap<>();

        for(int i = 0; i < A.length; i++)
            dataMap.put(A[i], i);
        int [][]tmpData = new int[A.length][A.length];

        for(int i = A.length - 1; i >= 0; i--){
            for(int j = i + 1; j < A.length; j++){
                if(dataMap.containsKey(A[i] + A[j])){
                    int position = dataMap.get(A[i] + A[j]);
                    tmpData[i][j] = tmpData[j][position] + 1;
                    retData = Math.max(retData,tmpData[i][j]);
                }else {
                    tmpData[i][j] = 2;
                }
            }
        }

        return retData;
    }
}
