# Bottom Up DP

### Introduction
Bottom up DP essentially aims to solve the same problems as a [Top Down DP](../topDown/README.md). 
The key difference is that we do not recurse downwards but take a iterative approach.

We try to solve the base sub problems first and slowly combine the solutions to our sub problems to 
solve the overall issue.

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
However, we now proceed with a iterative approach, hence we only need to store a additional data structure to track the states,
creating a *O(n) + O(j * n)* space complexity.

### Good Practice Questions
- [Climbing Stairs (Easy)](https://leetcode.com/problems/climbing-stairs/)
- [House Robber (Medium)](https://leetcode.com/problems/house-robber/description/)
- [Coin Change (Medium)](https://leetcode.com/problems/coin-change/description/)
- [Unique Paths (Medium)](https://leetcode.com/problems/unique-paths/)