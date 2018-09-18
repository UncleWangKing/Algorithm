package array.simulation;

/**
 * @author ZhangDaPang 285296372@qq.com
 * @date 2018/9/18 17:22
 */
public class LeetCode_7_ReverseInteger {
    public static void main(String[] args) {
        System.out.println(reverse(-4214));
    }

    public static int reverse(int x) {
        boolean negative = x < 0;
        if (negative) x = -x;
        long r = 0;
        while (x>0) {
            r = r * 10 + x % 10;
            x /= 10;
        }
        if (negative) r = -r;
        if (r > Integer.MAX_VALUE || r < Integer.MIN_VALUE) return 0;
        return (int)r;
    }
}
