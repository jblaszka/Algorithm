import java.util.ArrayList;

public class Main {
        public static void main(String[] args)  {
            ArrayList<Integer> input = new ArrayList<>();
            input.add(784);
            input.add(4765);
            input.add(5291);
            Algorithm result = new Algorithm();
            System.out.println("------INPUT------");
            System.out.println(input);
            ArrayList<Integer> output = result.numberConversion(input);
            System.out.println("------OUTPUT------");
            System.out.println(output);
        }
}

