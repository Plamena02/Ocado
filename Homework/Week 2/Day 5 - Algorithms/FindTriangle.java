package week2.Algorithms;

import java.util.Arrays;

public class FindTriangle {
    public boolean arrayContainsTringleTriple(Integer[] input){
		Arrays.sort(input);

        for (int i = 0; i < input.length-2; i++)
        {
            if(input[i]+input[i+1] > input[i+2])
            {
                return true;
            }
        }
        return false;
    }
}
