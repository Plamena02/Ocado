package week2.BinaryTree;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Binary_Tree_Inorder_Traversal {
    // Definition for a binary tree node.
    public static class TreeNode {
        Integer val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(Integer val) {
            this.val = val;
        }

        TreeNode(Integer val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public static TreeNode Insert(Integer[] array) {

        if (array == null || array.length==0) {
            return null;
        }
    
        Queue<TreeNode> treeNodeQueue = new LinkedList<>();
        Queue<Integer> integerQueue = new LinkedList<>(Arrays.asList(Arrays.copyOfRange(array, 1, array.length)));

       TreeNode treeNode = new TreeNode(array[0]);
        treeNodeQueue.offer(treeNode);
    
        while (!integerQueue.isEmpty()){
            Integer leftVal = integerQueue.isEmpty() ? null : integerQueue.poll();
            Integer rightVal = integerQueue.isEmpty() ? null : integerQueue.poll();
            TreeNode current = treeNodeQueue.poll();
            if (leftVal !=null) {
                    TreeNode left = new TreeNode(leftVal);
                    current.left = left;
                    treeNodeQueue.offer(left);
            }
            if (rightVal !=null){
                    TreeNode right = new TreeNode(rightVal);
                    current.right = right;
                    treeNodeQueue.offer(right);
            }
        }
        return treeNode;
    }

    public static void Print(TreeNode root) {
        if (root == null)
            return;
        Print(root.left);
        System.out.print(root.val + " ");
        Print(root.right);
    }

    public static void main(String args[]) {
        Integer[] test = new Binary_Tree_Inorder_Traversal().Test(new Integer[]{1, null, 2, 3});
        for(int i = 0; i < test.length; i++) System.out.println(test[i]);
    }

    public Integer[] Test(Integer[] arr) {
        // Print(Insert(arr));
        list = new ArrayList<Integer>();
        // System.out.println(inorderTraversal(Insert(arr)));
        return inorderTraversal(Insert(arr)).stream().toArray(Integer[]::new);
    }

    public List<Integer> list;

    public List<Integer> inorderTraversal(TreeNode root) {
        if (root == null)
            return list;
        // System.out.println(root.val);
        inorderTraversal(root.left);
        list.add(root.val);
        inorderTraversal(root.right);
        return list;
    }
}