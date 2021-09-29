package week2.BinaryTree;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Invert_Binary_Tree {

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
    
        while (!integerQueue.isEmpty() && !treeNodeQueue.isEmpty()){
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
    public static void main(String args[]) { new Invert_Binary_Tree().Test(new Integer[] {4, 2, 7, 1, 3, 6, 9});}

    public Integer[] Test(Integer[] arr)
    {
        // Print(Insert(arr));
        result = new ArrayList<Integer>();
        TreeNode root = Insert(arr);
        invertTree(root);
        System.out.println(result);
        getLevelOrder(root);
        return result.stream().toArray(Integer[]::new);
    }

    public List<Integer> result;

    public void invertTree(TreeNode root) {
        
        if (root == null) return;

        TreeNode temp = root.right;
        root.right = root.left;
        root.left = temp;

        invertTree(root.left);
        // result.add(root.val);
        invertTree(root.right);

    }
    
    public void clearEmpty(TreeNode root){

        if(root.left != null){
            
            if(root.left.val == null) root.left = null;
            else clearEmpty(root.left);

        }
        if(root.right != null){
            
            if(root.right.val == null) root.right = null;
            else clearEmpty(root.right);
        
        }

    }
    
    private void getLevelOrder(TreeNode root){

        int h = height(root);
        for (int i = 1; i <= h; i++) getValues(root, i);

    }
 
    private int height(TreeNode root){

        if(root == null) return 0;
        else{
            
            int lheight = height(root.left);
            int rheight = height(root.right);
             
            if(lheight > rheight) return (lheight + 1);
            else return (rheight + 1);

        }

    }

    private void getValues(TreeNode root ,int level){

        if (root == null) return;
        if (level == 1) result.add(root.val);
        else if (level > 1){

            getValues(root.left, level - 1);
            getValues(root.right, level - 1);

        }

    }
}