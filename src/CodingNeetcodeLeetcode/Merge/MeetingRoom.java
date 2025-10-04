package CodingNeetcodeLeetcode.Merge;

import java.util.Arrays;
import java.util.PriorityQueue;

public class MeetingRoom {
    public static void main(String[] args) {

        int[][] intervals1 = {{0, 30}, {5, 10}, {15, 20}};
        System.out.println(noOfMeetingRoomRequired(intervals1));
        System.out.println(noOfMeetingRoomRequiredTwoPointer(intervals1));

    }
    private static int noOfMeetingRoomRequiredTwoPointer(int[][] intervals1) {
        if (intervals1 == null || intervals1.length == 0) return 0;
        int n = intervals1.length;
        int[] start = new int[n];
        int[] end = new int[n];
        for (int i = 0; i < n; i++) {
            start[i] = intervals1[i][0];
            end[i] = intervals1[i][1];
        }
        Arrays.sort(start);
        Arrays.sort(end);

        int startPointer = 0, endPointer = 0;
        int rooms = 0, maxRooms = 0;

        while (startPointer < start.length) {
            if (start[startPointer] < end[endPointer]) {
                // Need a new room
                rooms++;
                startPointer++;
            } else {
                // One meeting ended, free a room
                rooms--;
                endPointer++;
            }
            maxRooms = Math.max(maxRooms, rooms);
        }

        return maxRooms;
    }

    private static int noOfMeetingRoomRequired(int[][] intervals1) {
        if (intervals1 == null || intervals1.length == 0) return 0;
        Arrays.sort(intervals1, (a, b) -> a[0] - b[0]);
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        minHeap.add(intervals1[0][1]);
        for (int i = 1; i < intervals1.length; i++) {
            // think it like this way,
            if (intervals1[i][0] >= minHeap.peek()) {
                minHeap.poll(); // Reuse room
            }
            minHeap.add(intervals1[i][1]); // Allocate room
        }
        return minHeap.size();
    }
}
