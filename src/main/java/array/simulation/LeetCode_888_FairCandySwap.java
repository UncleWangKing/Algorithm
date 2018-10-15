package array.simulation;

import util.ZDaPangArrayUtil;

import java.util.HashMap;
import java.util.Map;

public class LeetCode_888_FairCandySwap {
    public static void main(String[] args) {
//        int A[] = {1,2};int B[] = {2,2};//[1,2]
        int A[] = {1,1};int B[] = {2,2};//[1,2]
        ZDaPangArrayUtil.printArray(fairCandySwap(A, B));
    }

    public static int[] fairCandySwap(int[] A, int[] B) {
        int sumA = 0, sumB = 0;
        for (int a : A)
            sumA += a;
        for (int b : B)
            sumB += b;
        int dif = sumA - sumB;//此处不取绝对值
        int res[] = new int [2];
        if((dif & 1) == 1)//奇数 不可能
            return res;
        dif >>= 1;
        Map<Integer, Integer> mapA = new HashMap<>();
        for (int a : A)
            mapA.put(a - dif, a);//为了这里
        for (int b : B)
            if(mapA.containsKey(b)) {
                res[0] = mapA.get(b);
                res[1] = b;
                break;
            }
        return res;
    }
}
