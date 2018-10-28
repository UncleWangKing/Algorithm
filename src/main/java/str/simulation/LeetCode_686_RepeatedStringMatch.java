package str.simulation;

public class LeetCode_686_RepeatedStringMatch {
    public static void main(String[] args) {
        System.out.println(repeatedStringMatch("aaaaaaaaaaaaaaaaaaaaaab", "ba"));
    }

    public static int repeatedStringMatch(String A, String B) {
        StringBuffer sb = new StringBuffer();
        sb.append(A);
        int num = 1;
        while(sb.length() < B.length()){
            sb.append(A);
            num++;
        }
        if(-1 != sb.lastIndexOf(B))return num;
        sb.append(A);
        num++;
        if(-1 != sb.lastIndexOf(B))return num;
        return -1;
    }
}
