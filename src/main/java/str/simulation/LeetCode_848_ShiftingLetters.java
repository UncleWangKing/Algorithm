package str.simulation;

/**
 * @author ZhangDaPang 285296372@qq.com
 * @date 2018/11/2 18:46
 */
public class LeetCode_848_ShiftingLetters {
    public static void main(String[] args) {
//        String S = "abc";int[] shifts = {3, 5, 9};//rpl
        String S = "bad";int[] shifts = {10, 20, 30};//jyh
        System.out.println(shiftingLetters(S, shifts));
    }

    /**
     * 累加数组妙不可言
     */
    public static String shiftingLetters(String S, int[] shifts) {
        int []sum = new int [shifts.length + 1];
        for (int i = 1; i <= shifts.length; i++)
            sum[i] = (sum[i - 1] + shifts[i - 1]) % 26;
        char[] chars = S.toCharArray();
        for (int i = 0; i < chars.length; i++){
            if(i < shifts.length){
                int step = sum[sum.length - 1] - sum[i];
                chars[i] += step > 0 ? step : step + 26;
                if(chars[i] > 'z')
                    chars[i] -= 26;
            }

        }

        return String.valueOf(chars);
    }

    /**
     * 倒着写就好 累加个蛋 打自己一巴掌
     */
    public static String shiftingLetters2(String S, int[] shifts) {
        char[] arr = S.toCharArray();
        int shift = 0;
        for (int i = arr.length - 1; i >= 0; i--) {
            shift = (shift + shifts[i]) % 26;
            arr[i] = (char)((arr[i] - 'a' + shift) % 26 + 'a');
        }
        return new String(arr);
    }
}
