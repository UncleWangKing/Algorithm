package str.simulation;

public class LeetCode_58_LengthofLastWord {
    public static void main(String[] args) {
        System.out.println(lengthOfLastWord("a"));
    }

    public static int lengthOfLastWord(String s) {
        int count = 0;
        for(int i = s.length() - 1; i >= 0; i--){
            if(' ' != s.charAt(i))
                count++;
            else if(0 != count)
                return count;
        }
        return count;
    }
}
