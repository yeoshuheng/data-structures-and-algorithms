# Top Down DP

### Introduction

In recursive problems, there may often be overlaps in sub problems. 
To improve performance, we can use top-down dynamic programming, which caches our results as we recurse.

This is often the most intuitive approach to DP, 
however, as top-down is recursive, it occupies memory within our call stack, usually a 
[bottom up DP](../bottomUp/README.md) 
approach is preferred.

This allows us to avoid solving the same problem twice. 
Such techniques are often used to solve knapsack problems.

Knapsack problems are a combinatorics / resource allocation problem where we need to select elements under a 
set of constraints to maximise a profit function.

An example would be a thief selecting which items to steal. His constraint would be the amount of items he can carry, 
whereas his profit function would be the value of the stolen items.

This problems requires subsets of all possible elements together with their 'profit' to be considered.
Subsets can be considered recursively and often hold overlaps, allowing us to optimise our solution using top-down DP.

### Considerations
We can structure our knapsack problem as such:
* We have a constraint of only being able to select *n* elements given a capacity of *j*.
* Each element has a 'value' assigned to it, which we aim to maximise.

The naive solution would be to consider all possible subsets and select only the ones that fulfill our constraint.
Afterwards, we only select the subset that gives us the best possible value. 

This takes a *O(2^n)* time complexity. 
Additionally, due to recursion, we use up a *O(n)* space complexity.

With DP, we now only need to consider states we have not seen before. 
We only need to consider *n* elements to insert into each of our *j* positions.

This requires a *O(j * n)* time complexity.
However, we now need to store a additional data structure to track the states, 
creating a *O(n) + O(j * n)* space complexity.

### Good Practice Questions
- [Fibonacci Number (Easy)](https://leetcode.com/problems/fibonacci-number/description/)
- [Min Cost Climbing Stairs (Easy)](https://leetcode.com/problems/min-cost-climbing-stairs/description/)
- [Longest Common Subsequence (Medium)](https://leetcode.com/problems/longest-common-subsequence/description/)