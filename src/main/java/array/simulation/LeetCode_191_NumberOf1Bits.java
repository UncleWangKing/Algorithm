package array.simulation;

public class LeetCode_191_NumberOf1Bits {
    public static void main(String[] args) {
        System.out.println(hammingWeight(666));
    }
    public static int hammingWeight(int n) {
        int result = 0;
        while (n != 0) {
            result++;
            n = n & (n - 1);
        }

        return result;
    }

    public static int hammingWeight2(int n) {
        n = n - ((n>>1) & 0x55555555);
        n = (n & 0x33333333) + ((n>>2) & 0x33333333);
        n = (n + (n>>4)) & 0x0f0f0f0f;
        n = n + (n>>8);
        n = n + (n>>16);
        return n & 0x3f;
    }
}
