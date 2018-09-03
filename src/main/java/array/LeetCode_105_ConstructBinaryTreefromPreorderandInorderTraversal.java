package array;

/**
 * 理解清楚 中序和后续遍历出来的数组的结构就明白怎么写了
 * 先续:[根节点, 所有根左边节点，所有根右边节点]
 * 中序:[所有根左边节点, 根节点, 所有根右边节点]
 * 可对照106注释
 */
public class LeetCode_105_ConstructBinaryTreefromPreorderandInorderTraversal {
    public static void main(String[] args) {
        int [] preorder = {3,9,20,15,7};
        int [] inorder = {9,3,15,20,7};
        TreeNode treeNode = buildTree(preorder, inorder);
        System.out.println(treeNode);
    }


    public static TreeNode buildTree(int[] preorder, int[] inorder) {
        return buildTree(inorder, 0, inorder.length - 1, preorder, 0, preorder.length - 1);
    }

    public static TreeNode buildTree(int[] inorder, int iLeft, int iRight, int[] preorder, int pLeft, int pRight){
        if(iLeft > iRight || pLeft > pRight) return null;
        TreeNode node = new TreeNode(preorder[pLeft]);
        int i = 0;
        for (i = iLeft; i < inorder.length; i++) {
            if(node.val == inorder[i]) break;
        }

        node.left = buildTree(inorder, iLeft, i - 1, preorder, pLeft + 1, pLeft + 1 + i - iLeft - 1);
        node.right = buildTree(inorder, i + 1, iRight, preorder, pLeft + i - iLeft + 1, pRight);

        return node;
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
