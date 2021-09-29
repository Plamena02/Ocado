package week2.Algorithms;

import java.util.ArrayList;

public class FindMissingNumber{
	public ArrayList<Integer> missingNumbersFrom1ToN(Integer[] input){
        var result = new ArrayList<Integer>();
		if(input.length == 0) return result;
		for (int i = 1; i < input[input.length-1]; i++)
		{
            if(!contains(input, i))
            {
                result.add(i);
            }
		}
        return result;
	}
	private boolean contains(Integer[] array, Integer element){

		for(int i = 0; i < array.length; i++)
			if(array[i] == element) return true;

		return false;

	}
}

// 5 7 10 11