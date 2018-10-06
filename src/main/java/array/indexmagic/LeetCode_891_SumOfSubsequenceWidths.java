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
     * [1,2,3,4,5]
     * 最大值的组合数量为
     * 5: 2^4-1=15
     * 4: 2^3-1=7
     * 3: 2^2-1=3
     * 2: 2^1-1=1
     * 最小值的组合数量为
     * 1: 2^4-1=15
     * 2: 2^3-1=7
     * 3: 2^2-1=3
     * 4: 2^1-1=1
     * 两组之间的差值为2，4，8=>2^1,2^2,2^3

     * 这里还利用了数组内值小于等于数组下标这个特性 用了方便写法 -- A[A.length - i - 1]
     * 都要注意的是取余的时机 仔细观察 都有讲究
     */
    public static int sumSubseqWidths2(int[] A) {
        Arrays.sort(A);
        long c = 1, res = 0, mod = (long)1e9+7;

        for (int i = 0; i < A.length; i++, c = (c << 1) % mod)
            res = (res + A[i] * c - A[A.length - i - 1] * c) % mod;

        return (int)res;
    }
}
