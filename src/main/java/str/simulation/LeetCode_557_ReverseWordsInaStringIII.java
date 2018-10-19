package str.simulation;

public class LeetCode_557_ReverseWordsInaStringIII {
    public static void main(String[] args) {
        System.out.println(reverseWords("Let's take LeetCode contest"));
    }

    public static String reverseWords(String s) {
        String[] split = s.split(" ");
        for (int i = 0; i < split.length; i++) {
            char[] chars = split[i].toCharArray();
            for (int j = 0; j < chars.length / 2; j++) {
                char temp = chars[j];
                chars[j] = chars[chars.length - 1 - j];
                chars[chars.length - 1 - j] = temp;
            }
            split[i] = String.valueOf(chars);
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (String str : split) {
            stringBuilder.append(str).append(" ");
        }
        String res = stringBuilder.toString();
        return res.substring(0, res.length() - 1);
    }

    public static String reverseWords2(String s) {
        char[] res = s.toCharArray();
        int start = 0, end = 0;
        while(start < res.length){
            end = s.indexOf(' ', start);
            if(end == -1){
                reverse(res, start, res.length - 1);
                break;
            }
            reverse(res, start, end - 1);
            start = end + 1;
        }
        return new String(res);
    }

    public static void reverse(char[] w, int l, int r){
        while(l < r){
            char t = w[l];
            w[l] = w[r];
            w[r] = t;
            l++;
            r--;
        }
    }
}
