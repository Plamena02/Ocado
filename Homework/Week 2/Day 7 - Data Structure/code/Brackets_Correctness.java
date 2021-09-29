package code;
import java.util.Arrays;
import java.util.List;

public class Brackets_Correctness {
    
    public static void main(String args[]) {}

    public boolean isCorrect(String input)
    {
        List<String> brackets = Arrays.asList(input.split(""));

        if(brackets.get(0)!="(" || brackets.get(brackets.size()-1) == "(") return false;

        int i = 0;
        while (brackets.size() != 0) 
        {

            brackets.remove(brackets.get(i));
            i++;
        }
        return true;
    }
}
