package week2.BinaryTree;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.BeforeAll;

public class Symmetric_TreeTest{

    private static Symmetric_Tree problem;

    @BeforeAll
    public static void setup(){ problem = new Symmetric_Tree(); }

    @Test
    public void tests(){
        assertTrue(problem.Test(new Integer[]{1,2,2,3,4,4,3}));
        assertFalse(problem.Test(new Integer[]{1,2,2,null,3,null,3}));
        assertFalse(problem.Test(new Integer[]{1,2,2,2,null,2}));
        assertFalse(problem.Test(new Integer[]{1,2,3}));
    }
}
