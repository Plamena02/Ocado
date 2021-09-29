package week2.BinaryTree;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.BeforeAll;

public class Binary_Tree_Inorder_TraversalTest{

    private static Binary_Tree_Inorder_Traversal problem;

    @BeforeAll
    public static void setup(){ problem = new Binary_Tree_Inorder_Traversal(); }

    @Test
    public void tests(){
        assertArrayEquals(new Integer[]{1,3,2}, problem.Test(new Integer[]{1,null,2,3}));
        assertArrayEquals(new Integer[]{}, problem.Test(new Integer[]{}));
        assertArrayEquals(new Integer[]{2,1}, problem.Test(new Integer[]{1,2}));
        assertArrayEquals(new Integer[]{1,2}, problem.Test(new Integer[]{1,null,2}));
        assertArrayEquals(new Integer[]{5,7,10}, problem.Test(new Integer[]{10,7,null,5}));
    }
}
