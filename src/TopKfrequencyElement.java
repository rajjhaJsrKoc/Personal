import java.util.*;

public class TopKfrequencyElement {

    public static void main(String[] args) {
        int[] arr = {1,1,1,2,2,3};
        int k = 2;
        topKFrequency(arr, k);
        LinkedList<Integer> newLinked = new LinkedList<>();
        newLinked.add(1);
        newLinked.add(2);
        newLinked.add(3);
    }
    public static int[] topKFrequency(int[] arr, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            map.put(arr[i], map.getOrDefault(arr[i], 0) + 1);
        }
        for (Map.Entry<Integer,Integer> entry: map.entrySet()){
            System.out.println("Testing Value :" +entry.getKey() + " " + entry.getValue());
        }
        map.entrySet().stream().forEach(entry -> System.out.println(entry.getKey() + " " + entry.getValue()));
        //map.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()));
        map.forEach((a,count) -> System.out.println(a + " " + count));



        PriorityQueue<Map.Entry<Integer,Integer>> pq = new PriorityQueue<>((a,b) -> a.getValue() - b.getValue());

        for (Map.Entry<Integer,Integer> entry: map.entrySet()){
            pq.add(entry);
            if (pq.size() > k) pq.poll();
        }
        int[] res = new int[k];
        for (int i = 0; i < k; i++) {
            res[i] = pq.poll().getKey();
        }
        Arrays.stream(res).forEach(System.out::println);
        return arr;
    }
}
