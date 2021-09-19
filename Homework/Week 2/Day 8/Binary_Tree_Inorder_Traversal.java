public class Binary_Tree_Inorder_Traversal {
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
    }
}

// public List<Integer> list = new ArrayList<Integer>();
    
//     public List<Integer> inorderTraversal(TreeNode root) {
//         if(root == null) return list;
//         inorderTraversal(root.left);
//         list.add(root.val);
//         inorderTraversal(root.right);
//         return list;
//     }
