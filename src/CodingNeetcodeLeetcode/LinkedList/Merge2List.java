package CodingNeetcodeLeetcode.LinkedList;

public class Merge2List {
    public static void main(String[] args) {
        LinkedList list1 = new LinkedList(1);
        list1.next = new LinkedList(2);
        list1.next.next = new LinkedList(3);

        LinkedList list2 = new LinkedList(4);
        list2.next = new LinkedList(5);
        list2.next.next = new LinkedList(6);
        list2.next.next.next = new LinkedList(8);
        list2.next.next.next.next = new LinkedList(9);


        LinkedList merged = MergeListFunction(list1,list2);
        while (merged!=null){
            System.out.println(merged.d);
            merged = merged.next;
        }
    }


    private static LinkedList MergeListFunction(LinkedList list1, LinkedList list2) {
        LinkedList dummy = new LinkedList(-1);
        LinkedList merge = dummy;// pointer for building list
        while (list1 != null && list2 != null) {
            if (list2.d > list1.d) {
                merge.next = list1;
                list1 = list1.next;
            } else {
                merge.next = list2;
                list2 = list2.next;
            }
            merge = merge.next;
        }
        if (list1 != null) merge.next = list1;
        if (list2 != null) merge.next = list2;
        return dummy.next;
    }
}
