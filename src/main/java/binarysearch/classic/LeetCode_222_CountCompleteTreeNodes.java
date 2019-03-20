package binarysearch.classic;

/**
 * @author ZhangDaPang 285296372@qq.com
 * @date 2019/3/20 13:38
 */
public class LeetCode_222_CountCompleteTreeNodes {
    public static void main(String[] args) {
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        TreeNode n4 = new TreeNode(4);
        TreeNode n5 = new TreeNode(5);
        TreeNode n6 = new TreeNode(6);

        n1.left = n2;
        n1.right = n3;
        n2.left = n4;
        n2.right = n5;
        n3.left = n6;
        System.out.println(countNodes(n1));
        /**
         *       1
         *     /  \
         *    2   3
         *   / \  /
         *  4  5 6
         */

    }

    public static int countNodes(TreeNode root) {
        int hLeft = 0, hRight = 0;
        TreeNode pLeft = root, pRight = root;
        while (null != pLeft) {
            ++hLeft;
            pLeft = pLeft.left;
        }
        while (null != pRight) {
            ++hRight;
            pRight = pRight.right;
        }
        if (hLeft == hRight) {
            /**
             * << 运算符优先级 低于'减号' 一定要加括号！！！
             */
            return (1 << hLeft) - 1;
        }
        return countNodes(root.left) + countNodes(root.right) + 1;
    }

    /**
     完全二叉树的高度直接通过不断访问左子树获取
     判断左右子树的高度:
     递归过程：
     如果相等说明左子树是满二叉树, 然后进一步判断右子树的节点数(最后一层最后出现的节点必然在右子树中)
     如果不等说明右子树是深度小于左子树的满二叉树, 然后进一步判断左子树的节点数(最后一层最后出现的节点必然在左子树中)
     **/
    public static int countNodes2(TreeNode root) {
        if(root == null)
            return 0;
        int ld = getDepth(root.left);
        int rd = getDepth(root.right);
        if(ld == rd)
            //1(根节点)+(1 << ld)-1(左完全左子树节点数) + 右子树节点数量
            return (1 << ld) + countNodes(root.right);  //左移代替了Math.pow
        else
            //1(根节点)+(1 << rd)-1(右完全右子树节点数)+左子树节点数量
            return (1 << rd) + countNodes(root.left);
    }

    private static int getDepth(TreeNode r){
        int depth = 0;
        while(r != null){
            depth++;
            /**
             * 改变了r的值 关键！
             */
            r = r.left;
        }
        return depth;
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
