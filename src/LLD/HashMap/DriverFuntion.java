package LLD.HashMap;

public class DriverFuntion {

    public static void main(String[] args) {
        MyHashMap<String, Integer> map = new MyHashMap<>();

        map.put("A", 1);
        map.put("B", 2);
        map.put("C", 3);
        map.put("A", 10); // update

        System.out.println("Size: " + map.size()); // 3
        System.out.println("Get A: " + map.get("A")); // 10
        System.out.println("Contains B? " + map.containsKey("B")); // true

        map.remove("B");
        System.out.println("Contains B? " + map.containsKey("B")); // false
    }
}
