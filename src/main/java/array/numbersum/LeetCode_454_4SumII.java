package array.numbersum;

import java.util.HashMap;
import java.util.Map;

public class LeetCode_454_4SumII {
    public static void main(String[] args) {
        int A[] = {1,2};
        int B[] = {-2,-1};
        int C[] = {-1,2};
        int D[] = {0,2};
        System.out.println(fourSumCount(A, B, C, D));
    }

    //单map
    public static int fourSumCount2(int[] A, int[] B, int[] C, int[] D) {
//        预分配过大 反而非常严重地影响效率~
//        Map<Integer,  Integer> map = new HashMap<>(A.length * A.length);
        Map<Integer,  Integer> map = new HashMap<>();
        for (int a : A) {
            for (int b : B) {
                int sum = a + b;
                map.put(sum, map.getOrDefault(sum, 0) + 1);
            }
        }
        int count = 0;
        for (int c : C) {
            for (int d : D) {
                int target = -(c + d);
                if(map.containsKey(target))
                    count += map.get(target);
            }
        }
        return count;
    }

    //双map
    public static int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        Map<Integer,  Integer> mapAB = new HashMap<>();
        Map<Integer,  Integer> mapCD = new HashMap<>();
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < B.length; j++) {
                mapAB.put(A[i] + B[j], mapAB.getOrDefault(A[i] + B[j], 0) + 1);
                mapCD.put(C[i] + D[j], mapCD.getOrDefault(C[i] + D[j], 0) + 1);
            }
        }
        int count = 0;
        for (Map.Entry<Integer, Integer> entry : mapAB.entrySet())
            if(mapCD.containsKey(-entry.getKey()))
                count += entry.getValue() * mapCD.get(-entry.getKey());

        return count;
    }
}
