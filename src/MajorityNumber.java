import java.util.HashMap;
import java.util.Map;

public class MajorityNumber {

    public static void main(String[] args) {
        int [] array = {1,1,1,1,3,4,5,7,1,1,1,10};
        System.out.println(isMajorityOrNot(array));
        System.out.println(isMajority(array));
    }
    //moore majority algorithm
    private static boolean isMajorityOrNot(int[] array){
        int count = 0;
        int candidate = 0;
        for(int i=0; i<array.length; i++){
            if (count==0){
                candidate = array[i];
                count =1;
            } else if (candidate==array[i]){
                count++;
            } else {
                count--;
            }
        }
        count =0;
        for(int i=0; i<array.length; i++){
            if (array[i]==candidate){
                count++;
            }
        }
        if (count> array.length/2){
            return true;
        }
        return false;
    }
    public static int isMajority(int[] array){
        Map<Integer,Integer> frequencyMap = new HashMap<>();
        for(int i=0; i<array.length; i++){
            frequencyMap.put(array[i], frequencyMap.getOrDefault(array[i], 0) + 1);
        }
        for (Map.Entry<Integer, Integer> entry : frequencyMap.entrySet()) {
            if (entry.getValue() > array.length/2){
                return entry.getKey();
            }
        }
        return 0;
    }
}
