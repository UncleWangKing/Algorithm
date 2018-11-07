package str.deletechar;

public class LeetCode_72_EditDistance {
    public static void main(String[] args) {
        String word1 = "intention", word2 = "execution";
        System.out.println(minDistance(word1, word2));
    }

    /**
     *
     */
    public static int minDistance(String word1, String word2) {
        int n1 = word1.length(), n2 = word2.length();
        int dp[][] = new int[n1 + 1][n2 + 1];
        for (int i = 0; i <= n1; ++i) dp[i][0] = i;
        for (int i = 0; i <= n2; ++i) dp[0][i] = i;
        for (int i = 1; i <= n1; ++i) {
            for (int j = 1; j <= n2; ++j) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1])) + 1;
                }
            }
        }
        return dp[n1][n2];
    }
    //别人的最优解
    /*定义一个表达式D(i,j)。它表示从第1个字单词的第0位至第i位形成的子串和第2个单词的第0位至第j位形成的子串的编辑距离。
    显然，可以计算出动态规划的初始表达式，如下:
        D(i,0) = i
        D(0,j) = j
    然后，考虑动态规划的状态转移方程式，如下:
                      D(i-1, j) + 1
        D(i,j)=min    ( D(i, j-1) + 1 )
                      D(i-1, j-1) +1 【if  X(i) != Y(j)  】; D(i-1,j-1) 【if  X(i) == Y(j)】

    上面的状态转移方程的含义是，D(i,j)的值，要么是D(i-1, j)的操作完成之后删除一个字符(第1个单词的第i个字符)，要么是D(i, j-1)的操作完成之后增加一个字符(第2个单词的第j个字符)，要么是D(i-1, j-1)的操作完成自后替换一个字符(如果第1个单词的第i个字符和第2个单词的第j个字符不等)，或者是D(i-1, j-1)的操作完成自后什么也不做(如果第1个单词的第i个字符和第2个单词的第j个字符相等)。
*/
    public static int minDistance2(String word1, String word2) {
        int[][] dp = new int[word1.length()+1][word2.length()+1];
        return edit(word1.length(),word2.length(),word1,word2,dp);
    }
    public static int edit(int l, int r, String w1, String w2, int[][] dp){
        if(l==0) return r; if(r==0) return l;
        if(dp[l][r]!=0) return dp[l][r];
        int min = Integer.MAX_VALUE;
        if(w1.charAt(l-1)!=w2.charAt(r-1)){
            min = Math.min(edit(l-1,r,w1,w2,dp)+1,edit(l,r-1,w1,w2,dp)+1);
            min = Math.min(edit(l-1,r-1,w1,w2,dp)+1,min);
        }else{
            min = edit(l-1,r-1,w1,w2,dp);
        }
        dp[l][r] = min;
        return min;
    }
}
