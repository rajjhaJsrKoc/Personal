package CodingNeetcodeLeetcode.PriorityQueueHeap;

import java.util.PriorityQueue;

public class KthLargestInStream {
    static int kthLargest = 3;
    static PriorityQueue<Integer> pq = new PriorityQueue<>();
    public static void main(String[] args) {
        int[] nums = {4, 5, 8, 2};

        for (int i = 0; i < nums.length; i++) {
            pq.add(nums[i]);
        }
         System.out.println(add(1));
    }
    public static int add(int k) {
        pq.add(k);
        while (pq.size() > kthLargest) {
            pq.poll();
        }
        return pq.peek();
    }
}
