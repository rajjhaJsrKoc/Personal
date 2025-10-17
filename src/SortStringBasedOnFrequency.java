import java.util.Collections;
import java.util.Comparator;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.function.Function;
import java.util.stream.Collectors;

public class SortStringBasedOnFrequency {
    public static void main(String[] args){
        String s = "tree";
        Map<Character,Long> frequency = s.chars().mapToObj(c-> (char)c).collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));

        PriorityQueue<Map.Entry<Character,Long>> pq = new PriorityQueue<>(
                (a,b)-> Long.compare(b.getValue(),a.getValue())
        );

        for (Map.Entry<Character,Long> map : frequency.entrySet()){
            pq.add(map);
        }
        StringBuilder appendedString = new StringBuilder();
        while (pq.size()>0){
            Map.Entry<Character,Long>mp =pq.poll();
            int count = Math.toIntExact(mp.getValue());
            Character c = mp.getKey();
            appendedString.append(String.valueOf(c).repeat(count));
        }
        System.out.println(appendedString);

        frequency.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .flatMap(e-> Collections.nCopies(e.getValue().intValue(),e.getKey().toString()).stream())
                .collect(Collectors.toList()).forEach(System.out::print);
    }
}
