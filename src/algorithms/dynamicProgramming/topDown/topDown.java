package src.algorithms.dynamicProgramming.topDown;

/**
 * Implementation of a general top-down DP.
 *
 * Suppose we are given two arrays, value and cost.
 * The index represents that given element and its entry represents its value and cost respectively.
 * Cost subtracts from our capacity whereas value is what we are trying to maximise.
 */

public class topDown {

    /**
     * Keeps track of the best value we could have obtained by selecting which element.
     */
    private static int[] dp;

    /**
     * @param value_array Array of elements with its associated values.
     * @param cost_array Array of elements with the cost of selecting each element.
     * @param capacity The amount of cost we can afford to bear.
     * @return The maximum possible value we can obtain.
     */
    public static int topDownDP(int[] value_array, int[] cost_array, int capacity) {
        int n = cost_array.length;
        dp = new int[n]; // Create the dp array to store our best value.
        for (int i = 0; i < n; i++) {
            dp[i] = -1; // Fill the dp array with -1, since we have not visited it yet.
        }

    };

    /**
     * @param value_array Array of elements with its associated values.
     * @param cost_array Array of elements with the cost of selecting each element.
     * @param capacity The amount of cost we can afford to bear.
     * @param i The current index we are looking at.  (Length of element_array).
     * @param value The total value obtained so far.
     * @return The maximum possible value we can obtain.
     */
    public static int recurse(int[] value_array, int[] cost_array, int capacity, int value, int i) {
        if (i < 0 || capacity  == 0) { // Base case : We have 0 capacity or ran out of items to look for.
            return 0;
        }
        if (dp[i] != -1) { // Check if this has been explored before.
            return dp[i];
        }
        if (cost_array[i] > capacity){ // If our cost breaks our capacity, we cannot include it.
            return recurse(value_array, cost_array, capacity, value, i - 1);
        }
        else { // We now consider 2 cases : Whether we include that index in our subset or not.
            int include = recurse(value_array, cost_array,
                    capacity - cost_array[i], value + value_array[i], // Update cost & value.
                    i - 1);
            int exclude = recurse(value_array, cost_array, capacity, value, i - 1);
            dp[i] = Math.max(include, exclude); // Update our array with our best possible value.
            return dp[i]; // Return our updated value.
        }
    };

}
