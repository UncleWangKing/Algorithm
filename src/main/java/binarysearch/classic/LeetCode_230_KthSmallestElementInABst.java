package binarysearch.classic;

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

    public static int kthSmallest(TreeNode root, int k) {

    }


    private static class TreeNode {
     int val;
     TreeNode left;
     TreeNode right;
     TreeNode(int x) { val = x; }
    }
}
