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

    public static int maximalRectangle2(char[][] matrix) {
        if(0 == matrix.length || 0 == matrix[0].length) return 0;
        int res = 0, m = matrix.length, n = matrix[0].length;
        int[] height = new int[n];
        int[] left = new int[n];
        int[] right = new int[n];
        Arrays.fill(right, n);
        for (int i = 0; i < m; ++i) {//逐行扫描
            int cur_left = 0, cur_right = n;
            for (int j = 0; j < n; ++j) {//处理当前height '1'累加 '0'清零
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
