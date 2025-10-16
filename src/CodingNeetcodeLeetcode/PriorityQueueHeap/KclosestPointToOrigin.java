package CodingNeetcodeLeetcode.PriorityQueueHeap;

import java.util.Arrays;
import java.util.PriorityQueue;

public class KclosestPointToOrigin {
    public static void main(String[] args) {
        int[][] points= {{0,2},{2,0},{2,2}};
        int k =2;
        clostestToOrigin(points,k);
    }

    private static void clostestToOrigin(int[][] points, int k) {
       // PriorityQueue<Integer> pq1 = new PriorityQueue<>((a,b)-> a-b);
        //Max heap
        PriorityQueue<int[]> pq = new PriorityQueue<int[]>((a,b)->getDistance(b)-getDistance(a));
        for(int[] point: points){
            pq.add(point);
            if(pq.size()>k){
                pq.poll();
            }
        }
        /*while(!pq.isEmpty()){
            int[] point = pq.poll();
            System.out.println(point[0]+","+point[1]);
        }*/
        int [][] point = new int[k][2];
        for(int i=0;i<k;i++){
            point[i]=pq.poll();
        }
        Arrays.stream(point).forEach(a->System.out.println(a[0]+" "+a[1]));
    }

    private static int getDistance(int[] a) {
        return a[0]*a[0] + a[1]*a[1];
    }
}
