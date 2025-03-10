package dataStructures.segmentTree;

/**
 * Data structure that is commonly used to solve range sum queries in O(logn)
 */
public class SegmentTree {
    private final int[] tree;
    private final int n;
    private final int[] arr;

    /**
     * TODO documentation
     *
     * @param nums
     */
    public SegmentTree(int[] nums) {
        this.arr = nums;
        this.n = nums.length;
        this.tree = new int[(int) Math.pow(2, this.n)];
        buildTree(0, 0, this.n - 1);
    }

    private void buildTree(int nodeIdx, int start, int end) {
        if (start == end) {
            this.tree[nodeIdx] = this.arr[start];
        } else {
            int mid = start + (end - start) / 2;
            buildTree(nodeIdx * 2 + 1, start, mid);
            buildTree(nodeIdx * 2 + 2, mid + 1, end);
            tree[nodeIdx] = tree[nodeIdx * 2 + 1] + tree[nodeIdx * 2 + 2];
        }
    }

    private void update(int nodeIdx, int start, int end, int index, int val) {
        if (start == end) {
            this.tree[nodeIdx] = val;
            return;
        }
        int mid = start + (end - start) / 2;
        if (index <= mid) {
            update(nodeIdx * 2 + 1, start, mid, index, val);
        } else {
            update(nodeIdx * 2 + 2, mid + 1, end, index, val);
        }
        tree[nodeIdx] = tree[nodeIdx * 2 + 1] + tree[nodeIdx * 2 + 2];
    }

    /**
     * TODO Documentation
     *
     * @param index
     * @param val
     */
    public void update(int index, int val) {
        update(0, 0, this.n - 1, index, val);
    }

    /**
     * TODO documentation
     *
     * @param left
     * @param right
     * @return
     */
    public int sumRange(int left, int right) {
        return query(0, 0, this.n - 1, left, right);
    }

    private int query(int nodeIdx, int start, int end, int left, int right) {
        if (start > right || end < left) {
            return 0;
        }
        if (left <= start && right >= end) {
            return this.tree[nodeIdx];
        } else {
            int m = start + (end - start) / 2;
            return query(nodeIdx * 2 + 1, start, m, left, right) + query(nodeIdx * 2 + 2, m + 1, end, left, right);
        }
    }
}
