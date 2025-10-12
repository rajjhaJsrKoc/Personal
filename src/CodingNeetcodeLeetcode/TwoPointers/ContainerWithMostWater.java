package CodingNeetcodeLeetcode.TwoPointers;

public class ContainerWithMostWater {
    public static void main(String[] args){
        int[] height = {1, 8, 6, 2, 5, 4, 8, 3, 7};
        int left = 0, right= height.length -1;
        int length=0, wight, area, maxArea = 0;
        while(left<right){
            length = Math.min(height[right],height[left]);
            wight = right-left;
            area = length*wight;
            maxArea=Math.max(area,maxArea);
            if(height[left]<height[right]){
                left++;
            }else
                right--;
        }
        System.out.println(maxArea);
    }
}
