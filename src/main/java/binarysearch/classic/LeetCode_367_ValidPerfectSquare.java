package binarysearch.classic;

public class LeetCode_367_ValidPerfectSquare {
    public static void main(String[] args) {
        System.out.println(isPerfectSquare2(17));
    }

    /**
     * 除2 缩小范围
     */
    public static boolean isPerfectSquare(int num) {
        if (num == 1) return true;
        long x = num / 2, t = x * x;
        while (t > num) {
            x /= 2;
            t = x * x;
        }
        for (int i = (int)x; i <= 2 * x; ++i) {
            if (i * i == num) return true;
        }
        return false;
    }

    /**
     * 方法1改进版
     */
    public static boolean isPerfectSquare2(int num) {
        long x = num;
        while (x * x > num) {
            x = (x + num / x) / 2;
        }
        return x * x == num;
    }

    /**
     * 挨着数平方
     */
    public static boolean isPerfectSquare3(int num) {
        for (int i = 1; i <= num / i; ++i) {
            if (i * i == num) return true;
        }
        return false;
    }

    /**
     * 二分
     */
    public static boolean isPerfectSquare4(int num) {
        long left = 0, right = num;
        while (left <= right) {
            long mid = left + (right - left) / 2, t = mid * mid;
            if (t == num) return true;
            else if (t < num) left = mid + 1;
            else right = mid - 1;
        }
        return false;
    }
    /**
     * 1 = 1
     * 4 = 1 + 3
     * 9 = 1 + 3 + 5
     * 16 = 1 + 3 + 5 + 7
     * 25 = 1 + 3 + 5 + 7 + 9
     * 36 = 1 + 3 + 5 + 7 + 9 + 11
     ....
     * 1+3+...+(2n-1) = (2n-1 + 1)n/2 = n*n
     * 找规律
     */
    public static boolean isPerfectSquare5(int num) {
        int i = 1;
        while (num > 0) {
            num -= i;
            i += 2;
        }
        return num == 0;
    }

    /**
     * 丧心病狂！ 约翰卡马克的快速开平方根 耗子哥专门有篇文章写了这个著名的魔术
     * 没有想到java的实现版本~
     */
    /**
     bool isPerfectSquare(int num) {
     if (num < 0) return false;
     int root = floorSqrt(num);
     return root * root == num;
     }

     int32_t floorSqrt(int32_t x) {
     double y=x; int64_t i=0x5fe6eb50c7b537a9;
     y = *(double*)&(i = i-(*(int64_t*)&y)/2);
     y = y * (3 - x * y * y) * 0.5;
     y = y * (3 - x * y * y) * 0.5;
     i = x * y + 1; return i - (i * i > x);
     }
     */

}
