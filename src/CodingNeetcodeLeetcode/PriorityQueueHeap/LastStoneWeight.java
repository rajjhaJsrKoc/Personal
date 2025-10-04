package CodingNeetcodeLeetcode.PriorityQueueHeap;

import java.util.PriorityQueue;

public class LastStoneWeight {
    public static void main(String[] args) {
       int[] num= {2,7,4,1,8,1};
       PriorityQueue<Integer> pq = new PriorityQueue<>((a,b)->b-a);
       for (int i : num) {
           pq.add(i);
       }
       while (pq.size()>1) {
           int highest = pq.poll();
           int lowest = pq.poll();
           pq.add(highest-lowest);
       }
       System.out.println(pq.peek());

    }
}
