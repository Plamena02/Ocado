import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;

public class FindTriangle {
    public static void main(String args[]) throws IOException{

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Write array of integers separated by a space:");
		var input = Arrays.stream(reader.readLine().split(", ")).mapToInt(Integer::parseInt).toArray();
		Arrays.sort(input);

        for (int i = 0; i < input.length-2; i++)
        {
            if(input[i]+input[i+1] > input[i+2])
            {
                System.out.println(input[i] + input[i+1] + input[i+2]);
                break;
            }
        }
    }
}
