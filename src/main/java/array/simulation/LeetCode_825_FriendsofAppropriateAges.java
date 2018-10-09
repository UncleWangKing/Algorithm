package array.simulation;

import java.util.Arrays;

/**
 * @author ZhangDaPang 285296372@qq.com
 * @date 2018/10/9 18:37
 */
public class LeetCode_825_FriendsofAppropriateAges {
    public static void main(String[] args) {
//        int list[] = {20,30,100,110,120};//3
//        int list[] = {73,106,39,6,26,15,30,100,71,35,46,112,6,60,110};//29
        int list[] = {8,85,24,85,69};//4
//        int list[] = {16,16};//2
//        int list[] = {98,60,24,89,84,51,61,96,108,87,68,29,14,11,13,50,13,104,57,8,57,111,92,87,9,59,65,116,56,39,55,11,21,105,57,36,48,93,20,94,35,68,64,41,37,11,50,47,8,9};//439
        System.out.println(numFriendRequests3(list));
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

    /**
     * 二分优化
     */
    public static int numFriendRequests2(int[] ages) {
        Arrays.sort(ages);
        int count = 0;

        for (int i = ages.length - 1; i > 0; i--) {
            int target = (ages[i] >> 1) + 7, begin = 0, end = i;//end是i而不是i - 1 否则会多算
            /**
             * 找左方 大于target 的最小值的位置
             */
            while (begin < end){
                int mid = (begin + end) / 2;
                if(ages[mid] > target)
                    end = mid;
                else
                    begin = mid + 1;
            }
            if(ages[begin] > target) {//不合法或者只有自身合法 找出的是左边第一个 要排除下
                count += i - begin;
                int temp = 0;
                while (i > 0 && ages[i] == ages[i - 1]) {//相等可互发多加
                    i--;
                    count += i - begin;
                    temp++;
                }
                count += (temp * (temp + 1)) / 2;
            }
        }

        return count;
    }

    /**
     * O(n)时 O(1)空间 巨屌写法
     * 利用了数据范围 来空间换时间
     */
    public static int numFriendRequests3(int[] ages) {
        int res = 0;
        int[] numInAge = new int[121], sumInAge = new int[121];

        for(int i : ages)//年龄为下标数的人数
            numInAge[i] ++;

        for(int i = 1; i <= 120; ++i)//年龄小于等于下标数的人数
            sumInAge[i] = numInAge[i] + sumInAge[i - 1];

        for(int i = 15; i <= 120; ++i) {//根据规则 15岁以下 也就是14岁开始 14/2 + 7 = 14 是没有朋友的 Σ(⊙▽⊙"a
            if(numInAge[i] == 0) continue;
            int count = sumInAge[i] - sumInAge[i / 2 + 7];//轻易得到可与i年龄人做朋友总人数
            res += count * numInAge[i] - numInAge[i]; //不能和自己做朋友 so  - numInAge[i]
        }
        return res;
    }
}
