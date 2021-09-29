package week2.Algorithms;

import java.util.Arrays;

class MinMissingNumber {
	public Integer minMissingNumberInArray(Integer[] input){
		Arrays.sort(input);
		for (int i = 1; i < input.length+1; i++)
		{
			if (input[i-1] > i)
			{
				return i;
			}
		}
		return input.length + 1;
	}
}