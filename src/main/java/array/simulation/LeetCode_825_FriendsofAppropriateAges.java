package array.simulation;

import java.util.Arrays;

/**
 * @author ZhangDaPang 285296372@qq.com
 * @date 2018/10/9 18:37
 */
public class LeetCode_825_FriendsofAppropriateAges {
    public static void main(String[] args) {
//        int list[] = {20,30,100,110,120};//3
        int list[] = {73,106,39,6,26,15,30,100,71,35,46,112,6,60,110};//29
        System.out.println(numFriendRequests(list));
    }

    /**
     * 1.比我小太多不能发
     * 2.比我大的不能发
     * 3.我没过百他过百了不能发(被2包含了)
     * 暴力 TLE
     */
    public static int numFriendRequests(int[] ages) {
        Arrays.sort(ages);
        int count = 0;

        for (int i = ages.length - 1; i > 0; i--) {
            for (int j = i - 1; j >= 0 ; j--) {
                if((ages[i] >> 1) + 7 < ages[j]) {//i肯定大于等j 只判断就是别小太多
                    if(ages[i] == ages[j])//相等可以互发
                        count++;
                    count++;
                }
            }
        }

        return count;
    }
}
