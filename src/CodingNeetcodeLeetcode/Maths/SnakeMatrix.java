package CodingNeetcodeLeetcode.Maths;

import java.util.Arrays;

public class SnakeMatrix {
    public static void main(String[] args){
        int[][] arr = {{1,2,3},//0
                {4,5,6},//1
                {7,8,9}//2
        };

        int length = arr.length;
        int cols = arr[0].length;
        int [] res = new int[cols*length];
        int l =0;
        for (int i =0; i<length; i++){
            if(i%2 ==0){
                for(int j = 0; j< arr.length;j++){
                    res[l] =arr[i][j];
                    l++;
                }
            } else  {
                for (int k = arr.length-1;k>=0;k--){
                    res[l] =arr[i][k];
                    l++;
                }
            }
        }
        Arrays.stream(res).forEach(System.out::print);
    }
}
