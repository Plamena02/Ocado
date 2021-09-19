package tests;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import code.Ex1;

import org.junit.Before;

public class Tests{

    private Ex1 problem1;

    @Before
    public void setup(){ problem1 = new Ex1(); }

    @Test
    public void tests(){

        assertEquals(true, problem1.Generate_Squares(new int[][] { 
			{0, 1, 2, 3, 5}, 
			{2, 1, 55, 0, 8}, 
			{0, 1, 1, 21, 13},
			{13, 1, 2, 0, 2}, 
			{8, 4, 3, 2, 1}}));

        assertEquals(true, problem1.Generate_Squares(new int[][] { 
            {0, 1, 1, 3, 5}, 
            {13, 2, 2, 0, 8}, 
            {8, 5, 3, 21, 13},
            {13, 1, 2, 0, 2}, 
            {8, 4, 3, 2, 1}}));
    }

}