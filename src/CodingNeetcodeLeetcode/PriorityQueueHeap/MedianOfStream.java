package CodingNeetcodeLeetcode.PriorityQueueHeap;

import java.util.Collections;
import java.util.PriorityQueue;

public class MedianOfStream {
    public static void main(String[] args){
        Median mf = new Median();
        mf.addNum(1);
        mf.addNum(2);
        System.out.println("Median: " + mf.findMedian()); // 1.5
        mf.addNum(3);
        System.out.println("Median: " + mf.findMedian()); // 2.0
        mf.addNum(5);
        System.out.println("Median: " + mf.findMedian());
    }
}
class Median{
    private PriorityQueue<Integer> maxHeap;

    // minHeap -> right half (larger numbers)
    private PriorityQueue<Integer> minHeap;

    public Median() {
        maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        minHeap = new PriorityQueue<>();
    }
    public void addNum(int num) {
        // Step 1: Add to maxHeap first
        maxHeap.add(num);

        // Step 2: Balance so that every number in maxHeap <= every number in minHeap
        minHeap.add(maxHeap.poll());

        // Step 3: Maintain size property
        if (maxHeap.size() < minHeap.size()) {
            maxHeap.add(minHeap.poll());
        }
    }
    public double findMedian() {
        if (maxHeap.size() == minHeap.size()) {
            return (maxHeap.peek() + minHeap.peek()) / 2.0;
        } else {
            return maxHeap.peek(); // maxHeap will always have the extra element
        }
    }

}