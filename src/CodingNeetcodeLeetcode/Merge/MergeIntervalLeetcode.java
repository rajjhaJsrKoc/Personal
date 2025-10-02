package CodingNeetcodeLeetcode.Merge;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class MergeIntervalLeetcode {
    public static void main(String[] args) {
        int [][] interval = {{1,3},{2,6},{8,10},{15,18}};
        mergeInterval(interval);
    }
    private static void mergeInterval(int[][] interval) {
        if(interval.length<=1)
            return;
        Arrays.sort(interval,Comparator.comparingInt(i -> i[0]));
        //Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
        ArrayList<int[]> res = new ArrayList<>();
        int[] newInterval = interval[0];
        res.add(newInterval);
        for(int i=1;i<interval.length;i++) {
            int[] singleInterval = interval[i];
            if(singleInterval[0] <=newInterval[1]) {
                newInterval[1]=Math.max(singleInterval[1],newInterval[1]);
            }else {
                newInterval =singleInterval;
                res.add(newInterval);
            }
        }
        res.stream()
                .forEach(x -> System.out.println(x[0] + " " + x[1]));
    }
}
