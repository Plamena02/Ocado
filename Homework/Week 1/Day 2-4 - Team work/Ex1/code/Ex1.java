package code;

import java.util.List;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Collections;

public class Ex1 {

	private static int[][] matrix;
	private static List<Integer> fibNumbers = new ArrayList<Integer>();
	private static HashMap<Integer, Integer> num2Ind = new HashMap<Integer, Integer>();

	public static void main(String args[]) {
		matrix = new int[][] { 
			{0, 1, 2, 3, 5}, 
			{2, 1, 55, 0, 8}, 
			{0, 1, 1, 21, 13},
			{13, 1, 2, 0, 2}, 
			{8, 4, 3, 2, 1}};

		var result = new Ex1().Generate_Squares(matrix);
		System.out.println(result);
	}

	public boolean Generate_Squares(int[][] matrix) {
		boolean flag = false;
		fibNumbers.add(0);
		fibNumbers.add(1);
		num2Ind.put(0, 0);
		generateFib(20);

		for (int i = 0; i < matrix.length - 1; i++) {
			flag = Try_to_make_square(2, i, matrix);
			if (flag == true) {
				return true;
			}
		}
		return false;
	}

	public static boolean Try_to_make_square(int length, int line, int[][] matrix) {

		int start = 0;
		int end = length;
		if (length > matrix.length - line) {
			return false;
		}

		// Problem day3

		// var copy = fibNumbers;
		// for (int j = start; j < length - 1; j++) {

		// 	var num1 = matrix[line][j];
		// 	if (!copy.contains(num1))
		// 		return false;
		// 	copy.remove(num1);

		// 	var num2 = matrix[line + j][length - 1];
		// 	if (!copy.contains(num2))
		// 		return false;
		// 	copy.remove(num2);

		// 	var num3 = matrix[length - 1][length - 1 - j];
		// 	if (!copy.contains(num3))
		// 		return false;
		// 	copy.remove(num3);

		// 	var num4 = matrix[length - 1 - j][start];
		// 	if (!copy.contains(num4))
		// 		return false;
		// 	copy.remove(num4);

		// }

		while (start <= matrix.length - end) {

			ArrayList<Integer> elements = new ArrayList<Integer>();
			for (int j = start; j < length; j++)
				elements.add(matrix[line][j]);
			for (int j = 1; j < end - 1; j++)
				elements.add(matrix[line + j][length - 1]);
			for (int j = length - 1; j >= start; j--)
				elements.add(matrix[line + end - 1][j]);
			for (int j = end - 2; j > start; j--)
				elements.add(matrix[j][start]);

			start++;
			length++;

			// Problem day1
			elements = getCorrectCyclicShiftClockwise(elements);

			// Problem day2 part 1
			// Collections.sort(elements);

			// Problem day2 part 2
			// elements = getCorrectCyclicShiftCounterclockwise(elements);

			if (isFibonacci(elements))
				return true;
		}

		return Try_to_make_square(end + 1, line, matrix);

	}

	private static void generateFib(int n) {

		if (n == 0)
			return;

		fibNumbers.add(fibNumbers.get(fibNumbers.size() - 2) + fibNumbers.get(fibNumbers.size() - 1));
		num2Ind.put(fibNumbers.get(fibNumbers.size() - 1), fibNumbers.size() - 1);

		generateFib(n - 1);

	}

	private static boolean areConsecutive(int a, int b, int c) {
		if (num2Ind.containsKey(a) == false)
			return false;
		if (num2Ind.containsKey(b) == false)
			return false;
		if (num2Ind.containsKey(c) == false)
			return false;
		if (a == 0 && b == 1 && c == 1)
			return true;
		if (a == 1 && b == 1 && c == 2)
			return true;
		if (a == 1 && b == 2 && c == 3)
			return true;

		int aInd = num2Ind.get(a);
		int bInd = num2Ind.get(b);
		int cInd = num2Ind.get(c);
		return (aInd + 1 == bInd && bInd + 1 == cInd);
	}

	public static ArrayList<Integer> getCorrectCyclicShiftClockwise(ArrayList<Integer> l) {
		int maxInd = 0;
		for (int i = 1; i < l.size(); i++)
			if (l.get(i) > l.get(maxInd))
				maxInd = i;

		ArrayList<Integer> output = new ArrayList<Integer>();
		for (int i = maxInd + 1; i < l.size(); i++)
			output.add(l.get(i));
		for (int i = 0; i <= maxInd; i++)
			output.add(l.get(i));

		return output;
	}

	public static ArrayList<Integer> getCorrectCyclicShiftCounterclockwise(ArrayList<Integer> l) {
		int maxInd = 0;
		for (int i = 1; i < l.size(); i++)
			if (l.get(i) > l.get(maxInd))
				maxInd = i;

		ArrayList<Integer> output = new ArrayList<Integer>();
		for (int i = maxInd; i < l.size(); i++)
			output.add(l.get(i));
		for (int i = 0; i < maxInd; i++)
			output.add(l.get(i));
		Collections.reverse(output);

		return output;
	}

	public static boolean isFibonacci(List<Integer> list) {

		for (int i = 0; i < list.size() - 2; i++)
			if (!areConsecutive(list.get(i), list.get(i + 1), list.get(i + 2)))
				return false;

		return true;

	}

}
