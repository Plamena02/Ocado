package week2.BinaryTree;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Kth_Smallest_Element {
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

    public static void main(String args[]) { new Kth_Smallest_Element().Test(new Integer[]{10,8,15,null,null,12}, 2); }

    public List<Integer> list;

    public int Test(Integer[] arr, int k)
    {
        list = new ArrayList<Integer>();
        TreeNode tree = Insert(arr);
        System.out.println(kthSmallest(tree, k));
        return kthSmallest(tree, k);
    }
    
    public Integer kthSmallest(TreeNode root, int k) {
        fillList(root);
        // Collections.sort(list);
        return list.get(k-1);
    }
    
    public void fillList(TreeNode root)
    {
        if(root == null) return;
        
        fillList(root.left);
        if(root.val != null) list.add(root.val);
        fillList(root.right);
        return;
    }
}