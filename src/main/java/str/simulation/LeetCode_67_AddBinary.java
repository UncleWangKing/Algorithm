package str.simulation;

/**
 * @author ZhangDaPang 285296372@qq.com
 * @date 2018/10/24 15:55
 */
public class LeetCode_67_AddBinary {
    public static void main(String[] args) {
        String a = "1010", b = "1011";
        System.out.println(addBinary(a, b));
    }

    public static String addBinary(String a, String b) {
        String res = "";
        int m = a.length() - 1, n = b.length() - 1, carry = 0;
        while (m >= 0 || n >= 0) {
            int p = m >= 0 ? a.charAt(m--) - '0' : 0;
            int q = n >= 0 ? b.charAt(n--) - '0' : 0;
            int sum = p + q + carry;
            res = String.valueOf(sum % 2) + res;
            carry = sum / 2;
        }
        return carry == 1 ? "1" + res : res;
    }
}
