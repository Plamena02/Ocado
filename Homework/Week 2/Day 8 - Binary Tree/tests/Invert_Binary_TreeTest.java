package week2.BinaryTree;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.BeforeAll;

public class Invert_Binary_TreeTest{

    private static Invert_Binary_Tree problem;

    @BeforeAll
    public static void setup(){ problem = new Invert_Binary_Tree(); }

    @Test
    public void tests(){
        assertArrayEquals(new Integer[]{4,7,2,9,6,3,1}, problem.Test(new Integer[]{4,2,7,1,3,6,9}));
        assertArrayEquals(new Integer[]{2,3,1}, problem.Test(new Integer[]{2,1,3}));
        assertArrayEquals(new Integer[]{4,7}, problem.Test(new Integer[]{4,null,7,null,null,6,9}));
        assertArrayEquals(new Integer[]{10,17,4,12}, problem.Test(new Integer[]{10,4,17,null,null,12}));
    }
}
