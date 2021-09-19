import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.stream.Collectors;

class FindMissingNumber{
	public static void main(String args[]) throws IOException{
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Write array of integers separated by a space:");
		var input = Arrays.stream(reader.readLine().split(", ")).mapToInt(Integer::parseInt).boxed().collect(Collectors.toList());
        var result = new ArrayList<Integer>();
		for (int i = 1; i < input.size()-1; i++)
		{
            if(!input.contains(i))
            {
                result.add(i);
            }
		}
        System.out.println(result);
	}
}

// 5 7 10 11