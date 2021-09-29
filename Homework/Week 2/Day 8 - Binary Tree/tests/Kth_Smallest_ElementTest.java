package week2.BinaryTree;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.BeforeAll;

public class Kth_Smallest_ElementTest{

    private static Kth_Smallest_Element problem;

    @BeforeAll
    public static void setup(){ problem = new Kth_Smallest_Element(); }

    @Test
    public void tests(){
        assertEquals(1, problem.Test(new Integer[]{3,1,4,null,2},1));
        assertEquals(3, problem.Test(new Integer[]{5,3,6,2,4,null,null,1},3));
        assertEquals(10, problem.Test(new Integer[]{10,8,15,null,null,12},2));
        assertEquals(11, problem.Test(new Integer[]{11,6,null,2,9},4));
    }
}
