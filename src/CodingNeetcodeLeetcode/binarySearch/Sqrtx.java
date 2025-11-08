package CodingNeetcodeLeetcode.binarySearch;

public class Sqrtx {
    public static void main(String[] args){
        int x = 24;
        int low = 0, high = x, ans= 0;
        boolean found = false;
        while(low<=high){
            int mid = (low +(high))/2;
            long sq = (long) mid*mid;
            if (sq==x){
                System.out.println(sq);
                found= true;
                break;
            }else if(sq<x){
                ans = mid;
                low = mid+1;
            } else
                high = mid-1;
        }
        if (!found)
            System.out.println(ans);
    }
}
