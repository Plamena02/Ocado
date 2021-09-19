package code;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

public class Ex3 {
	public static void main(String args[]) throws IOException
	{
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Write array of integers separated by a space:");
		var input = Arrays.stream(reader.readLine().split(", ")).mapToInt(Integer::parseInt).toArray();
		var result = new Ex3().sort(input);
		System.out.println(Arrays.toString(result));
	}

	public int[] sort(int[] array){

		return Arrays.stream(array).boxed().sorted((a, b) -> Convert_to_Binary(a).compareTo(Convert_to_Binary(b))).mapToInt(i -> i).toArray();

	}

	private static Integer Convert_to_Binary(int n){

		int[] binary = Arrays.stream(Integer.toBinaryString(n).split("")).mapToInt(Integer::parseInt).toArray();
		return IntStream.of(binary).sum();

	}
}
