package str.simulation;

public class LeetCode_709_ToLowerCase {
    public static void main(String[] args) {
        System.out.println(toLowerCase("Hello"));
    }

    public static String toLowerCase(String str) {
        char[] chars = str.toCharArray();
        for (int i = 0; i < chars.length; i++)
            if(chars[i] >= 'A' && chars[i] <= 'Z')
                chars[i] += 32;

        return String.valueOf(chars);
    }
}
