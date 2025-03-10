package algorithms.minimumSpanningTree.prims;

import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * Implementation of Prim's Algorithm to find MSTs
 * Idea:
 * Starting from any source (this will be the first node to be in the MST),
 * pick the lightest outgoing edge, and include the node at the other end as part of MST
 * Now repeatedly do the above by picking the lightest outgoing edge adjacent to any node in the MST
 * (ensuring the other end of the node is not already in the MST)
 * Do until the MST has all the nodes.
 * <p>
 * Motivating Example: Minimum Cost to Connect All Points
 * <p>
 * A -9- C -2- E
 * /     /  \     \
 * 3     4    7     2
 * /     /      \  /
 * F -1- B  --5--  D
 * <p>
 * Implementation 1: Using heap
 * Time: O(V) + O(ElogE) (since heap could possibly hold E number of weights) + O(E-V) (nodes that have been 'seen'
 * are still added to the heap, just not expanded) + O(V^2)
 * Space: O(V) (hashmap to decide on MST) + O(E) (heap) = O(V+E) = O(E)
 */
public class Prim {
    /**
     * points: Adjacency matrix that encapsulates the distance/weight between nodes
     * adjM[i][j] is the weight of the edge connecting points i and j; a value of 0 suggests there is no connection
     * between i and j
     *
     * @param adjM Adjacency matrix that encapsulates the distance/weight between nodes
     * @return minimum weight of the spanning tree
     */
    public int minCostConnectPoints(int[][] adjM) {
        int v = adjM.length;
        int minCost = 0;
        Set<Integer> mst = new HashSet<>();
        mst.add(0);
        PriorityQueue<Pair> pq = new PriorityQueue<>(Comparator.comparingInt(Pair::getDist));
        for (int i = 0; i < v; i++) {
            if (!mst.contains(i)) {
                if (adjM[0][i] != 0) { // ensure valid edge
                    pq.add(new Pair(adjM[0][i], i));
                }
            }
        }
        while (mst.size() != v) {
            Pair popped = pq.poll();
            if (mst.contains(popped.getEndNode())) {
                continue;
            }
            minCost += popped.getDist();
            mst.add(popped.getEndNode());
            for (int i = 0; i < v; i++) {
                if (mst.contains(i)) {
                    continue;
                }
                if (adjM[popped.getEndNode()][i] != 0) { // ensure valid edge
                    pq.add(new Pair(adjM[popped.getEndNode()][i], i));
                }
            }
        }
        return minCost;
    }

    /**
     * Alternative implementation that simply uses array to hold weights rather than heap.
     * Note: Starts from the node labelled 0 and repeatedly update weights
     * which stores the minimum weight from any node in the MST to other nodes.
     * Time: O(V) + O(V*2V)
     * Space: O(V)
     *
     * @param adjM Adjacency matrix that encapsulates the distance/weight between nodes
     * @return minimum weight of the spanning tree
     */
    public int minCostConnectPoints2(int[][] adjM) {
        int v = adjM.length;
        int[] weights = new int[v];

        System.arraycopy(adjM[0], 0, weights, 0, v);

        Set<Integer> mst = new HashSet<>();
        mst.add(0); // start from source 0
        int ans = 0;
        while (mst.size() < v) {
            int next = v;
            for (int i = 0; i < v; i++) {
                if (!mst.contains(i)) {
                    if (weights[i] != 0 && (next == v
                        || weights[i] < weights[next])) { // check for valid connection, then try to find min weight
                        next = i;
                    }
                }
            }
            mst.add(next);
            ans += weights[next];

            for (int i = 0; i < v; i++) {
                if (!mst.contains(i)) {
                    if (weights[i] == 0
                        || adjM[next][i] < weights[i]) { // update shortest dist to nodes that are not added to mst yet
                        weights[i] = adjM[next][i];
                    }
                }
            }
        }
        return ans;
    }
}

