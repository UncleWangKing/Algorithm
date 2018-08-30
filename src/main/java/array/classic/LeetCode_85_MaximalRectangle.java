package array.classic;

import java.util.Arrays;
import java.util.Stack;

public class LeetCode_85_MaximalRectangle {
    public static void main(String[] args) {
        char [][] list = {
                {'1','0','1','0','0'},
                {'1','0','1','1','1'},
                {'1','1','1','1','1'},
                {'1','0','0','1','0'}
        };
        System.out.println(maximalRectangle2(list));
    }

    /**
     * 这是一个精彩的思路 网上看了很多 包括leetcode英文区的discuss 个人感觉都没有说清楚 自己写一个
     *
     * 回顾第一种解法 本质是两步
     *  1.逐行转化为直方图
     *  2.用84题的解法求得直方图中最大矩形 --- 一句话总结 转成直方图了 扔给84解决
     *
     * 这种解法的思路前半部分是一样的
     *  1.逐行转化为直方图
     *  2.求得直方图中最大矩形 --- 不同之处就在第二步
     * ****************************************************************************************************
     * 该解法的亮点就是 利用了生成直方图的逐行扫描过程 维护出了相应直方图中 各个位置值为高的矩形的左右边界*
     * ****************************************************************************************************
     * 求得相应位置高度的矩形面积也就变成了 (右边界-左边界)*高度  因为每个位置都遍历了(所有可能的高度的矩形) 所以是遍历了所有可能的矩形 也就能得到该直方图的最大矩形
     * 现在只要弄清楚两个问题 就可以理解这个解法了
     *
     * 1.left[i][j] right[i][j]分别代表什么意思
     *
     *      left[i][j] 代表前i(包括i)行数据形成的直方图中，j位置高度为高的矩形的左边界下标。
     *      right[i][j] 代表前i(包括i)行数据形成的直方图中，j位置高度为高的矩形的右边界下标 + 1。 ---- +1 是为了宽度为1时候 right - left 也有值方便计算
     *
     * 观察一组数据
     * array:
     * 1 0 1 0 0
     * 1 0 1 1 1
     * 1 1 1 1 1
     * 1 0 0 1 0
     * height:height[2][2]为3，表示0,1,2这三行形成的直方图的第3个(下标2)位置，是高度为3的矩形
     * 1 0 1 0 0
     * 2 0 2 1 1
     * 3 1 3 2 2
     * 4 0 0 3 0
     * left:left[2][2]为2，表示0,1,2这三行形成的直方图的第3个(下标2)位置，也就是那个高度为3的矩形，左边界下标是2(也就是第三个位置)
     * 0 0 2 0 0
     * 0 0 2 2 2
     * 0 0 2 2 2
     * 0 0 0 3 0
     * right:right[2][2]为3，表示0,1,2这三行形成的直方图的第3个(下标2)位置，也就是那个高度为3的矩形，右边界下标是2+1.(本质就是2，+1是为了方便减去left得到宽，而不用再加1)
     * 1 5 3 5 5
     * 1 5 3 5 5
     * 1 5 3 5 5
     * 1 5 5 4 5
     * 所以(right[2][2] - left[2][2]) * height[2][2]就是那个矩形的面积
     * height[i][j]是0的情况对应的
     * left[i][j]是0
     * right[i][j]是height[i].length
     * 这样做只是为了方便迭代出下一行的left[i+1][j] right[i+1][j]而放的临时值，因为height[i][j]等于0，
     * 对应left[i][j]和right[i][j]等于任何值都不影响结果 (right[i][j] - left[i][j]) * 0 肯定定于0
     *
     * 2.是如何得到保证left[i][j] right[i][j]是期待的值的
     *
     *  height[i][j] 为0情况上面已经说了，这里看为1的情况。
     *
     *  令cur_left[i][j] : 表示第i行中，从j位置开始往左，最后出现(最靠左或者说下标最小)的'''连续'''的1,的下标。
     *  则 left[i][j] = max(cur_left[0][j],...cur_left[i-1][j],cur_left[i][j])
     *  可简化为 left[i][j] = max(left[i-1][j], cur_left[i][j])
     *
     *  令cur_right[i][j] : 表示第i行中，从j位置开始往右,最后出现(最靠右或者说下标最大)的'''连续'''的1，的下标 + 1. -- + 1可别忘了
     *  则 right[i][j] = min(cur_right[0][j],...cur_right[i-1][j],cur_right[i][j])
     *  可简化为 right[i][j] = max(right[i-1][j], cur_right[i][j])
     *
     * array:
     * 1 0 1 0 0
     * 1 0 1 1 1
     * 1 1 1 1 1
     * 1 0 0 1 0
     * cur_left:      left:
     * 0 0 2 0 0      0 0 2 0 0
     * 0 0 2 2 2      0 0 2 2 2
     * 0 0 0 0 0      0 0 2 2 2
     * 0 0 0 3 0      0 0 0 3 0
     * cur_left:      right:
     * 1 5 3 5 5      1 5 3 5 5
     * 1 5 5 5 5      1 5 3 5 5
     * 5 5 5 5 5      1 5 3 5 5
     * 1 5 5 4 5      1 5 5 4 5
     *
     *  PS:正式因为这个简化的表达式有状态转换方程的感觉 很多人说这种解法是动归解法 也不能说有错 确实在求left right时候用了dp --- 准确说是贪心
     *  但并非属于核心解法 毕竟没有给出最终结果值相关的状态转换方程
     */
    public static int maximalRectangle2(char[][] matrix) {
        if(0 == matrix.length || 0 == matrix[0].length) return 0;
        int res = 0, m = matrix.length, n = matrix[0].length;
        int[] height = new int[n];
        int[] left = new int[n];
        int[] right = new int[n];
        Arrays.fill(right, n);
        for (int i = 0; i < m; ++i) {//逐行扫描
            int cur_left = 0, cur_right = n;
            for (int j = 0; j < n; ++j) {//处理当前height '1'累加 '0'清零 --- 本质也是维护成前N列形成的直方图 当前列是0 清零
                if (matrix[i][j] == '1') ++height[j];
                else height[j] = 0;
            }
            for (int j = 0; j < n; ++j) {//维护left
                if (matrix[i][j] == '1') left[j] = Math.max(left[j], cur_left);
                else {left[j] = 0; cur_left = j + 1;}
            }
            for (int j = n - 1; j >= 0; --j) {//维护right
                if (matrix[i][j] == '1') right[j] = Math.min(right[j], cur_right);
                else {right[j] = n; cur_right = j;}
            }
            for (int j = 0; j < n; ++j) {//维护最大值
                res = Math.max(res, (right[j] - left[j]) * height[j]);
            }
        }
        return res;
    }
    /**
     * 在84题的基础上 逐行转化为直方图 用84题算法计算 取最大值
     */
    public static int maximalRectangle(char[][] matrix) {
        if(0 == matrix.length || 0 == matrix[0].length) return 0;
        int max = 0;
        int []heights = new int[matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                heights[j] = matrix[i][j] == '0' ? 0 : 1 + heights[j];
            }
            max = Math.max(max, largestRectangleArea(heights));
        }

        return max;
    }

    public static int largestRectangleArea(int[] heights) {
        int res = 0;
        Stack<Integer> st = new Stack<>();
        int list[] = new int[heights.length + 1];
        System.arraycopy(heights, 0, list,0,  heights.length);
        list[list.length - 1] = 0;
        //上方一堆丑陋的东西只为了在原数组扩容1 放个0
        for (int i = 0; i < list.length; ++i) {
            if (st.empty() || list[st.peek()] < list[i]) {
                st.push(i);
            } else {
                int cur = st.pop();
                res = Math.max(res, list[cur] * (st.empty() ? i : (i - st.peek() - 1)));
                --i;//如果做了弹出 i还要再参与一轮
            }
        }
        return res;
    }
}
