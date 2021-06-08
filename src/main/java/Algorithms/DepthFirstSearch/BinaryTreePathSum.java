package Algorithms.DepthFirstSearch;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
};

class BinaryTreePathSum {
    public static boolean hasPath(TreeNode root, int sum) {
        System.out.println(root.val + " " + sum);
        // TODO: Write your code here
        boolean leftPath = false;
        boolean rightPath = false;
        if(root.left != null)
            leftPath = hasPath(root.left, sum - root.val);

        if(root.right != null)
            rightPath = hasPath(root.right, sum - root.val);

        if(root.left == null && root.right == null)
            return root.val == sum;
        else
            return leftPath || rightPath;
    }

    public static boolean hasPathConcise(TreeNode root, int sum) {
        if (root == null)
            return false;

        // if the current node is a leaf and its value is equal to the sum, we've found a path
        if (root.val == sum && root.left == null && root.right == null)
            return true;

        // recursively call to traverse the left and right sub-tree
        // return true if any of the two recursive call return true
        return hasPath(root.left, sum - root.val) || hasPath(root.right, sum - root.val);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(12);
        root.left = new TreeNode(7);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(9);
        root.right.left = new TreeNode(10);
        root.right.right = new TreeNode(5);
        System.out.println("Tree has path: " + BinaryTreePathSum.hasPath(root, 23));
        System.out.println("Tree has path: " + BinaryTreePathSum.hasPath(root, 16));
    }
}
