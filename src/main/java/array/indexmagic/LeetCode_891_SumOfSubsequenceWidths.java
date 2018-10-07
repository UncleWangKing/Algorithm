package array.indexmagic;

import java.util.Arrays;

public class LeetCode_891_SumOfSubsequenceWidths {
    public static void main(String[] args) {
        int list[] = {2,1,3};
        System.out.println(sumSubseqWidths(list));
    }

    /**
     * 每个子序列只算最大最小值只差
     * 可以累加差
     * 也可以累加被减数 再减去累加的减数
     * 那么只需求得每个数被当做被减数和减数的次数即可
     * 当被减数 i^2 - 1次 i为从0开始的下标
     * 当减数 (length - 1 - i)^2 - 1次
     */
    public static int sumSubseqWidths(int[] A) {
        Arrays.sort(A);
        long ans = 0;
        long [] pow2 = new long[20001];
        for(int i = 0; i <= A.length; ++i){
            if(i == 0) pow2[i] = 1;
            else pow2[i] = pow2[i - 1] * 2 % 1000000007;
        }

        for(int i = 0; i < A.length; ++i){
            ans += (pow2[i] - 1) * A[i] % 1000000007;
            ans = (ans - (pow2[A.length - 1 - i] - 1) * A[i]) % 1000000007;
        }

        return (int)ans;
    }

    /**
     * 和上方一样的思路 A[i]作为被减数总次数 减去 A[i]作为减数总次数 累积
     * 利用了取余和 加减乘 的分配率
     */
    public static int sumSubseqWidths2(int[] A) {
        Arrays.sort(A);
        long c = 1, res = 0, mod = (long)1e9+7;

        for (int i = 0; i < A.length; i++, c = (c << 1) % mod)
            res = (res + (A[i] - A[A.length - i - 1]) * c) % mod;

        return (int)res;//应该写成 (res + mod) % mod 保证是正值 但题目数据目前都是正值 也能过
    }
}
