package week2.BinaryTree;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.BeforeAll;

public class Range_Sum_of_BSTTest{

    private static Range_Sum_of_BST problem;

    @BeforeAll
    public static void setup(){ problem = new Range_Sum_of_BST(); }

    @Test
    public void tests(){
        assertEquals(32, problem.Test(new Integer[]{10,5,15,3,7,null,18},7,15));
        assertEquals(23, problem.Test(new Integer[]{10,5,15,3,7,13,18,1,null,6},6,10));
        assertEquals(28, problem.Test(new Integer[]{20,11,29,null,17},3,19));
        assertEquals(22, problem.Test(new Integer[]{34,22,null,19},21,24));
        assertEquals(13, problem.Test(new Integer[]{5,3,8,1,4,null,null},5,8));
    }
}