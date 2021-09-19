public class Kth_Smallest_Element {
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
    
// public int kthSmallest(TreeNode root, int k) {
//     fillList(root);
//     Collections.sort(list);
//     return l;
// }

// public void fillList(TreeNode root)
// {
//     if(root == null) return;
    
//     fillList(root.left);
//     list.add(root.val);
//     fillList(root.right);
//     return;
// }