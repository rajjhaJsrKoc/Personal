package CodingNeetcodeLeetcode.binarySearch;

public class BinarySearch {
    public static void main(String[] args){
        int[] arr ={1,2,3,4,5,6,9,10};
        int target = 9;
        int low= 0,high =arr.length-1;
        while (low<=high){
            int mid = (low+high)/2;
            if(arr[mid] == target){
                System.out.println(arr[mid]);
            }
            if(arr[mid] < target){
                low= mid+1;
            }else
                high = mid-1;
        }
    }
}
