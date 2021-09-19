public class Invert_Binary_Tree {

    // Definition for a binary tree node.
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public static void main(String args[])
	{
        TreeNode node1 =new TreeNode(2);
        TreeNode node2 =new TreeNode(7);
        TreeNode node =new TreeNode(4,node1,node2);

		var result = new Invert_Binary_Tree().invertTree(node);
		System.out.println(result);
	}

    public TreeNode invertTree(TreeNode root) {
        if (root == null)
            return null;

        TreeNode left = invertTree(root.left);
        TreeNode right = invertTree(root.right);

        root.right = left;
        root.left = right;

        return root;
    }

}

// public TreeNode invertTree(TreeNode root) {
//     if(root == null) return null;

//     TreeNode left = invertTree(root.left);
//     TreeNode bright  = invertTree(root.right);

//     root.right = left;
//     root.left = right;

//     return node;
// }