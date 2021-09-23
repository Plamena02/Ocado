import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Range_Sum_of_BST {
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
        Queue<Integer> integerQueue = new LinkedList<>(Arrays.asList(array));

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
    
    public static void main(String args[])
	{ }
    
    public int Test(Integer[] array, int low, int high)
    {
        return rangeSumBST(Insert(array), low, high);
    }

    private int sum = 0;
    private int low;
    private int high;

    public int rangeSumBST(TreeNode root, int l, int h) {
        if(root == null) return sum;

        high = h;
        low = l;

        calculateSum(root);
        return sum;
    }

    private void calculateSum(TreeNode root)
    {
        if(root == null) return;

        calculateSum(root.left);

        if(root.val >= low && root.val <= high) sum += root.val;

        calculateSum(root.right);
    }
}
