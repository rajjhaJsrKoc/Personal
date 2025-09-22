public class SearchRotatedArray {
    public int search(int[] nums, int target) {
        int left = 0, right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] == target) return mid;

            // Left half is sorted
            if (nums[left] <= nums[mid]) {
                if (target >= nums[left] && target < nums[mid]) {
                    right = mid - 1; // target in left half
                } else {
                    left = mid + 1;  // target in right half
                }
            }
            // Right half is sorted
            else {
                if (target > nums[mid] && target <= nums[right]) {
                    left = mid + 1; // target in right half
                } else {
                    right = mid - 1; // target in left half
                }
            }
        }

        return -1; // not found
    }

    // Driver
    public static void main(String[] args) {
        SearchRotatedArray sra = new SearchRotatedArray();
        int[] nums = { 6, 7, 0, 1, 2, 4, 5};
        int target = 0;
        System.out.println("Index of " + target + " = " + sra.search(nums, target));
    }
}
