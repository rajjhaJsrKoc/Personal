package CodingNeetcodeLeetcode.binarySearch;

import java.util.Arrays;

public class KokeEatingBanana {
    public static void main(String[] args){
        int [] arr = {3, 6, 7, 11};
        int h = 8;
        int max = Arrays.stream(arr).max().getAsInt();
        int result = max;
        int low = 0;
        while (low<=max){
            int mid = (max+low)/2;
            if (canEatAll(arr,mid,h)){
                result = mid;
                max= mid -1;
            }else {
                low = mid+1;
            }
        }
        System.out.println(result);
    }
    private static boolean canEatAll(int[] piles, int speed, int h) {
        int hours = 0;
        for (int pile : piles) {
            // ceil(pile / speed)
            hours += Math.ceil((double) pile / speed);
        }
        return hours <= h;
    }
}
