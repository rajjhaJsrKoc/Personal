import java.util.Arrays;
import java.util.Stack;

public class MonolothicDecreasingStackTemperature {
    public static int[] dailyTemperatures(int[] temperatures) {
        int n = temperatures.length;
        int[] result = new int[n];
        Arrays.fill(result,0);// Result array initialized with 0s
        Stack<Integer> stack = new Stack<>(); // Monotonic decreasing stack (stores indices)

        // Iterate through the temperature array
        for (int i = 0; i < n; i++) {
            // While stack is not empty AND the current temperature is warmer than the temperature at stack top
            while (!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()]) {
                int prevIndex = stack.pop(); // Pop the previous day's index
                result[prevIndex] = i - prevIndex; // Calculate the wait time
            }
            stack.push(i); // Push current index onto the stack
        }

        return result; // Return the computed results
    }

    public static void main(String[] args) {
        int[] temperatures = {73, 74, 75, 71, 69, 72, 76, 73};
        int[] result = dailyTemperatures(temperatures);

        System.out.println("Temperatures: " + Arrays.toString(temperatures));
        System.out.println("Days to wait: " + Arrays.toString(result));
    }
}
