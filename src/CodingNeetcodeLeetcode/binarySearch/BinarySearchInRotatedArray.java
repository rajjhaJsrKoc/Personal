package CodingNeetcodeLeetcode.binarySearch;

public class BinarySearchInRotatedArray {
    public static void main(String[] args){
        int [] arr = {3,4,5,1,2,};
        int target = 3;
        int low = 0 , high = arr.length-1;
        while (low<=high){
            int mid = (low+high)/2;
            if (arr[mid]==target){
                System.out.println(arr[mid]);
                break;
            }
            if (arr[mid]<= arr[high]){
                if (arr[mid]<target && target<=arr[high])
                    low = mid+1;
                else
                    high = mid -1;
            }else
                if (arr[mid]>target && arr[low]<=target)
                    high = mid -1;
                else
                    low = mid+1;
        }
    }
}
