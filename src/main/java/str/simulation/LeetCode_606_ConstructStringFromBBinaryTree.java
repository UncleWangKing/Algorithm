package str.simulation;

public class LeetCode_606_ConstructStringFromBBinaryTree {
    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(1);
        treeNode.left = new TreeNode(2);
        treeNode.right = new TreeNode(3);
        treeNode.left.right = new TreeNode(4);
        //1(2()(4))(3)
        System.out.println(tree2str(treeNode));
    }

    public static String tree2str(TreeNode t) {
        if(null == t)
            return "";
        String left = null == t.left && null == t.right ? "" : "(" + tree2str(t.left) + ")";
        String right = null == t.right ? "" : "(" + tree2str(t.right) + ")";
        return t.val + left + right;
    }

     public static class TreeNode {
         int val;
         TreeNode left;
         TreeNode right;
         TreeNode(int x) { val = x; }
     }
}
