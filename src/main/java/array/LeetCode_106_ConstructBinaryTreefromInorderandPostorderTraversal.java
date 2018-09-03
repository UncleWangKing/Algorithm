package array;

/**
 * 理解清楚 中序和后续遍历出来的数组的结构就明白怎么写了
 * 中序:[所有根左边节点, 根节点, 所有根右边节点]
 * 后续:[所有根左边节点，所有根右边节点，根节点]
 */
public class LeetCode_106_ConstructBinaryTreefromInorderandPostorderTraversal {
    public static void main(String[] args) {
        int [] inorder = {9,3,15,20,7};
        int [] postorder = {9,15,7,20,3};
        TreeNode treeNode = buildTree(inorder, postorder);
        System.out.println(treeNode);
    }
    public static TreeNode buildTree(int[] inorder, int[] postorder) {
        return buildTree(inorder, 0, inorder.length - 1, postorder, 0, postorder.length - 1);
    }

    public static TreeNode buildTree(int[] inorder, int iLeft, int iRight, int[] postorder, int pLeft, int pRight) {
        if (iLeft > iRight || pLeft > pRight) return null;
        TreeNode cur = new TreeNode(postorder[pRight]);//后续的最右就是根节点
        int i = 0;
        for (i = iLeft; i < inorder.length; ++i) {
            if (inorder[i] == cur.val) break;//找到中序中根节点位置用于拆分左右
        }
        //核心点就是分割左右的数组的下标如何计算取得
        //---------------------------------------------
        //中序:[左边节点, 根节点, 右边节点]
        //后续:[左边节点，右边节点，根节点]
        //---------------------------------------------
        //中序是最简单的，以根节点位置i为分割即可

        //后序的左方取法:
        //begin:pLeft -- 显然
        //end:pLeft + i - iLeft - 1 --- 关键 i - iLeft - 1 就是左边节点的个数
        //后序的右方取法:
        //begin:pLeft + i - iLeft --- 等于左方的end + 1
        //end:pRight - 1 去掉根节点
        cur.left = buildTree(inorder, iLeft, i - 1, postorder, pLeft, pLeft + i - iLeft - 1);
        cur.right = buildTree(inorder, i + 1, iRight, postorder, pLeft + i - iLeft, pRight - 1);
        return cur;
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
