package CodingNeetcodeLeetcode.LinkedList;

public class DublicateElement {
    public static void main(String[] args) {
        int[] arr = {1,3,2,5,6,2};
        int dublicateElement = dublicateElement(arr);
        System.out.println(dublicateElement);

    }

    private static int dublicateElement(int[] arr) {

        int slow = arr[0];
        int fast = arr[0];
        do {
            slow = arr[slow];
            fast = arr[arr[fast]];
        }while(fast != slow);

        slow = arr[0];
        while (slow != fast) {
            slow = arr[slow];
            fast = arr[fast];
        }
        return slow;
    }
}
