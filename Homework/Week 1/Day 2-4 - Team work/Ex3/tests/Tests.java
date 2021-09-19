package tests;
import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

import code.Ex3;

import org.junit.Before;

public class Tests{

    private Ex3 problem3;

    @Before
    public void setup(){ problem3 = new Ex3(); }

    @Test
    public void tests(){

        assertArrayEquals(new int[]{2, 8, 4, 3, 7}, problem3.sort(new int[]{2, 8, 3, 4, 7}));
        assertArrayEquals(new int[]{0, 1, 3}, problem3.sort(new int[]{3, 1, 0}));
        assertArrayEquals(new int[]{0, 0, 1}, problem3.sort(new int[]{0, 1, 0}));
        assertArrayEquals(new int[]{0, 64, 3, 55, 989}, problem3.sort(new int[]{989, 55, 3, 0, 64}));
        assertArrayEquals(new int[]{32, 36, 112, 44, 87}, problem3.sort(new int[]{112, 32, 87, 44, 36}));
    }
}
