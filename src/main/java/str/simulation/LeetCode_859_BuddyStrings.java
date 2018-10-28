package str.simulation;

public class LeetCode_859_BuddyStrings {
    public static void main(String[] args) {
        System.out.println(buddyStrings("aa", "aa"));
    }

    /**
     * 注意读题 是交换A中两个 而不是AB交换
     */
    public static boolean buddyStrings(String A, String B) {
        if (A.length() != B.length())
            return false;
        char[] charsA = A.toCharArray();
        char[] charsB = B.toCharArray();
        if (A.equals(B)){                //如果字符串内容相等
            int [] count = new int[26];
            for ( char chars : charsA)
                count[chars - 'a'] ++;  //记录下该位置的各数

            for (int c : count)
                if (c > 1)
                    return true;   //如果存在个数大于1，则返回true

            return false;
        }else {
            int first = 0;       //第一个不相等的位置
            int second = 0;      //第二个不相等的位置
            for (int i = 0; i < charsA.length; i++)
                if (charsA[i] != charsB[i])
                    if (first == 0)
                        first = i;
                    else if (second == 0)
                        second = i;
                    else
                        return false;    //不相等的位置超过两个

            if (charsA[first] != charsB[second] || charsA[second] != charsB[first])
                return false;
        }
        return true;
    }
}
