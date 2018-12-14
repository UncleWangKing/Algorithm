package binarysearch.classic;

import java.util.Stack;

public class LeetCode_230_KthSmallestElementInABst {
    /**
     输入: root = [5,3,6,2,4,null,null,1], k = 3
                 5
               / \
             3   6
           / \
         2   4
       /
      1
     输出: 3
     */
    public static void main(String[] args) {
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        TreeNode n4 = new TreeNode(4);
        TreeNode n5 = new TreeNode(5);
        TreeNode n6 = new TreeNode(6);

        n5.left = n3;
        n5.right = n6;
        n3.left = n2;
        n3.right = n4;
        n2.left = n1;

        System.out.println(kthSmallest(n5, 3));
    }

    /**
     * 中序遍历 弹栈计数
     */
    public static int kthSmallest(TreeNode root, int k) {
        Stack<Integer> stack = new Stack<>();
        helper(root, k, stack);
        return stack.pop();
    }

    public static void helper(TreeNode root, int k, Stack<Integer> stack) {
        if(null != root && stack.size() < k) {
            helper(root.left, k, stack);
            if(stack.size() < k)
                stack.push(root.val);
            helper(root.right, k, stack);
        }
    }

    public static int kthSmallest2(TreeNode root, int k) {
        return process(root,k,0)[0];
    }

    public static int[] process(TreeNode root,int k,int count){
        if(root==null){
            return new int[]{count,0};
        }
        int[] c1 = process(root.left, k, count);
        if(1 == c1[1]){  //如果已经找到直接返回
            return c1;
        }
        //到节点逻辑处理部分
        c1[0]++;
        //如果flag为1，直接返回结果，因为找到了｛值,flag｝
        if(k == c1[0]){
            return new int[]{root.val, 1};
        }
        int[] c2 = process(root.right, k, c1[0]);
        return c2;
    }


    private static class TreeNode {
     int val;
     TreeNode left;
     TreeNode right;
     TreeNode(int x) { val = x; }
    }
}
