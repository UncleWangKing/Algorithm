package array.classic;

public class LeetCode_42_TrappingRainWater {
    public static void main(String[] args) {
        int list[] = {0,1,0,2,1,0,1,3,2,1,2,1};
        System.out.println(trap2(list));
    }

    //第一直觉:极值之间的开区间 矮边X间距 减去中间数值
    //问题1:中间值大于矮边的部分不能减去 -- 如果出现这种情况 说明应该选那个大于矮边的部分和长边匹配
    //问题2:{3,1,2,1,3}应该是6 而不是2 -- 不能顺着来

    //两边极值往中间走，矮的那边开始走，遇到比矮的更高的，替换掉矮的
    //再从两边中矮的继续走
    //-------------------夭折的思路分割线------------------
    //看看人家！！！
    //走两遍
    //核心思路 找出每个点左右的最大值 自身和较小的值的差值 就是自己位置上的水量
    //这种划分思路 将问题独立了出来 每个i位置上的水量可以独立计算 而不是之前的按区域来划分计算的思路
    //这题的亮点 就是如何划分这个问题！！！考验抽象问题的能力
    //PS 虽然有人说这是动归 但是这跟动归完全不沾边！！！
    public static int trap(int[] height) {
        int res = 0, mx = 0, n = height.length;
        int temp[] = new int[n];
        //第一遍求出i左边最大值 放入dp[i]
        for (int i = 0; i < n; ++i) {
            temp[i] = mx;
            mx = Math.max(mx, height[i]);
        }
        mx = 0;
        //第二遍求出右边最大值 并和原来位置的左边最大值比较 选较小的
        //如果左右两边较小的最大值还大于i 将差值将值放入res
        //i位置上的雨水的量就是每个i值和左右两边最大值较小的值的差
        for (int i = n - 1; i >= 0; --i) {
            temp[i] = Math.min(temp[i], mx);
            mx = Math.max(mx, height[i]);
            if (temp[i] > height[i]) res += temp[i] - height[i];
        }
        return res;
    }

    //双指针 就只用走一遍
    //同样的划分方式 将问题独立出来 让每个i位置水量单独求值并累加 这种写法更进一步也更加精彩
    //上一个写法的思路很直观，自身水量必然等于左右两边最大值的较小值和自身之差(小于自身就不算是个巧妙的排除法)
    //这个写法就是不用两边最大值都拿到了再求自身水量 而是左边两边较小的那个的最大值拿到就可以求水量了
    //较大的最大值不用确定是哪一个 只要知道肯定比较小那一侧最大值大即可
    public static int trap2(int[] height) {
        int res = 0, left = 0, right = height.length - 1;
        while (left < right) {
            int mn = Math.min(height[left], height[right]);
            if (mn == height[left]) {//左边低
                ++left;
                while (left < right && height[left] < mn)//两指针未重合且往"右"走一步后依然更小 将变小的差值放入
                    res += mn - height[left++];
            } else {//右边低
                --right;
                while (left < right && height[right] < mn)//两指针未重合且往"左"走一步后依然更小 将变小的差值放入
                    res += mn - height[right--];
            }
        }
        return res;
    }
}
