package str.simulation;

public class LeetCode_521_LongestUncommonSubsequenceI {
    public static void main(String[] args) {
        System.out.println(findLUSlength("aba","cdc"));//3
    }

    public static int findLUSlength(String a, String b) {
        return a.equals(b) ? -1 : Math.max(a.length(), b.length());
    }
}
