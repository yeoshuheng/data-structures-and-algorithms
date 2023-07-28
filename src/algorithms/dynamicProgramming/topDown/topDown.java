package src.algorithms.dynamicProgramming.topDown;

/**
 * Implementation of a general top-down DP.
 *
 * We can structure our knapsack problem as such:
 * We have a constraint of only being able to select n elements given a capacity of j.
 * Each element has a 'value' assigned to it, which we aim to maximise.
 *
 * The naive solution would be to consider all possible subsets and select only the ones that fulfill our constraint.
 * Afterwards, we only select the subset that gives us the best possible value. This takes a O(2^n) time complexity.
 *
 * With DP, we now only need to consider
 */
public class topDown {


}
