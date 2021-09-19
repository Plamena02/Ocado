import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;

class MinMissingNumber {
	public static void main(String args[]) throws IOException{
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Write array of integers separated by a space:");
		var input = Arrays.stream(reader.readLine().split(", ")).mapToInt(Integer::parseInt).toArray();
		Arrays.sort(input);
		for (int i = 1; i < input.length+1; i++)
		{
			if (input[i-1] > i)
			{
				System.out.println(i); return;
			}
		}
		System.out.println(input.length+1);
	}
}