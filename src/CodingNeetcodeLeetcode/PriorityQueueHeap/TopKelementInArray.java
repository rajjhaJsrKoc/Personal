package CodingNeetcodeLeetcode.PriorityQueueHeap;

import java.util.Arrays;
import java.util.PriorityQueue;

public class TopKelementInArray {

    public static void main(String[] args) {
        int k =3;
        int[] arr = {1,2,3,15,5,6,7,8,9};
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 0; i < arr.length; i++) {
            pq.add(arr[i]);
            if (pq.size() > k) {
                pq.poll();
            }
        }
       int[] arrNew = new int[k];
       for (int i = 0; i < k; i++) {
           arrNew[i] = pq.poll();
       }
        Arrays.stream(arrNew).forEach(System.out::println);
    }
}
