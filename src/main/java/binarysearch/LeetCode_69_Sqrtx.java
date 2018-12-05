package binarysearch;

public class LeetCode_69_Sqrtx {
    public static void main(String[] args) {
        System.out.println(mySqrt(34));
    }

    /**
     * 牛顿迭代 详细可见650题解
     */
    public static int mySqrt(int x) {
        if(0 == x)return 0;
        long i = x;
        while(i > x / i){
            i = (i + x / i) / 2;
        }
        return (int)i;
    }
}
