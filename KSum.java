package arraysandstrings;

import java.util.HashMap;
import java.util.Map.Entry;

/**
 * KSum class provides a generic solution for finding 'K' numbers in an array
 * that sum up to a given target using a recursive approach and a HashMap
 * for efficient lookups.
 *
 * Key Features:
 * - Handles any 'K' sum problem.
 * - Uses a HashMap to store intermediate results to avoid redundant computations.
 * - Optimized to ensure unique index selections.
 */
public class KSum {

    /**
     * Finds 'numberOfSum' elements in the array that sum up to 'target'.
     * 
     * Algorithm:
     * - Uses a recursive approach with memoization to efficiently find the required elements.
     * - Starts by storing single elements in a HashMap as potential candidates.
     * - Iterates and updates possible sums using previous results.
     * - Returns indices of elements that satisfy the condition.
     * 
     * @param nums          The input array of integers
     * @param target        The target sum to achieve
     * @param numberOfSum   The number of elements that should sum to the target
     * @param sumIndex      The current level of recursion
     * @param sumMap        A HashMap storing intermediate sums with their respective indices
     * @return An array containing indices of elements that sum to the target, or null if no solution is found
     */
    public static Integer[] findKSum(int[] nums, int target, int numberOfSum, int sumIndex, HashMap<Integer, Integer[]> sumMap) {
        // Base case: If we exceed the required number of elements, return null
        if (sumIndex > numberOfSum) {
            System.out.println("Returning null: No valid combination found.");
            return null;
        }

        int complement=0;

        // Initialize the map for the first iteration with individual elements
        if (sumIndex == 1) {
            for (int i = 0; i < nums.length; i++) {
                complement = target - nums[i];
                Integer[] indices = {i};  // Store index of the current element
                sumMap.put(complement, indices); // Store complement and index
            }
        }

        // Increment recursive depth level
        sumIndex++;

        // Store new combinations
        HashMap<Integer, Integer[]> newSumMap = new HashMap<>();

        // Iterate through previous sums stored in the map
        for (Entry<Integer, Integer[]> entry : sumMap.entrySet()) {
            outer:
            for (int i = 0; i < nums.length; i++) {
                Integer[] indexArr = entry.getValue();

                // Ensure we do not reuse the same index
                for (Integer index : indexArr) {
                    if (i == index) {
                        continue outer;  // Skip this iteration if index is already used
                    }
                }

                // Compute new complement
                complement = entry.getKey() - nums[i];

                // Construct new index array
                Integer[] newIndices = new Integer[indexArr.length + 1];
                newIndices[0] = i;
                System.arraycopy(indexArr, 0, newIndices, 1, indexArr.length);

                // Store updated combination
                newSumMap.put(complement, newIndices);

                // If we have the required number of elements and the sum is zero, return result
                if (numberOfSum == sumIndex && complement == 0) {
                    return newIndices;
                }
            }
        }

        // Recursive call with updated map
        return findKSum(nums, complement, numberOfSum, sumIndex, newSumMap);
    }

    /**
     * Main method to test the K-Sum implementation.
     */
    public static void main(String[] args) {
        // Start time measurement for performance analysis
        long startTime = System.nanoTime();

        // Input data
        int[] nums = {
            -50, -48, -47, -45, -42, -40, -38, -36, -34, -32,
            -30, -28, -27, -25, -23, -22, -21, -19, -18, -17,
            -15, -14, -12, -10, -9, -8, -6, -5, -4, -3,
            -2, -1, 0, 1, 2, 3, 4, 5, 6, 7,
            8, 9, 10, 12, 14, 15, 17, 18, 19, 20,
            21, 22, 23, 25, 26, 27, 28, 29, 30, 31,
            32, 33, 34, 35, 36, 37, 38, 39, 40, 41,
            42, 43, 44, 45, 46, 47, 48, 49, 50, -37,
            -29, -26, -24, -20, -16, -13, -11, -7, 11, 13,
            16, 24, 37, -46, -39, -33, -31, -41, 46, 19
        };

        int target = 50; // Target sum

        // Find 10 numbers that sum up to the target
        Integer[] result = findKSum(nums, target, 10, 1, new HashMap<>());

        // Display result indices
        System.out.print("Indices: ");
        for (int i = 0; i < result.length; i++) {
            System.out.print(result[i] + (i < result.length - 1 ? ", " : "\n"));
        }

        // Compute and display the sum of selected numbers
        int computedSum = 0;
        for (int index : result) {
            computedSum += nums[index];
        }
        System.out.println("Computed Sum: " + computedSum + " (Target: " + target + ")");

        // End time measurement
        long endTime = System.nanoTime();
        System.out.println("Execution Time: " + (endTime - startTime) / 1e6 + " ms");
    }
}
