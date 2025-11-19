import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class DublicateListPrint {
    public static void main(String[] args){
        List<Integer> list = Arrays.asList(1,2,3,1,2,3);
        while (list.size()>1){
            Map<Integer,Long> freq = list.stream().collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));

            Integer dublicate = freq.entrySet().stream().filter(e->e.getValue()>1).map(Map.Entry::getKey).findFirst().orElse(null);
            if (dublicate.equals(null))
                break;
            int dublicateValue = dublicate;
            int value = Math.toIntExact(dublicateValue * freq.get(dublicateValue));
            List newList = new ArrayList<>();
            boolean flag = false;
            for(int list1 : list){
                if (list1==dublicateValue){
                    if (!flag) {       // add only once
                        newList.add(value);
                        flag = true;
                    }
                }else {
                    newList.add(list1);
                }
            }
            list= newList;
            System.out.println(list);
    }
    }
}
