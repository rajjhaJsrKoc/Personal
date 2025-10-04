package CodingNeetcodeLeetcode.PriorityQueueHeap;

import java.util.PriorityQueue;

public class kthLargestElement {
    public static void main(String[] args) {
        int[] nums = {3,2,1,5,6,4};
        int k = 3;
        PriorityQueue<Integer> pq = new PriorityQueue<>((a,b)->b-a);
        for (int i = 0; i < nums.length; i++) {
            pq.add(nums[i]);
            if (pq.size() > k) {
                pq.poll();
            }
        }
        System.out.println(pq.poll());
    }
}
