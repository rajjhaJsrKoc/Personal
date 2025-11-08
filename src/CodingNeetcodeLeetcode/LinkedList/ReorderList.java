package CodingNeetcodeLeetcode.LinkedList;

public class ReorderList {
    public static void main(String[] args){
        LinkedList list = new LinkedList(1);
        list.next = new LinkedList(2);
        list.next.next = new LinkedList(3);
        list.next.next.next = new LinkedList(4);
        list.next.next.next.next = new LinkedList(5);
        // 1->5->2->4->3

        LinkedList slow = list;
        LinkedList fast = list;

        while (fast.next!= null && fast!= null){
            slow = slow.next;
            fast= fast.next.next;
        }
        // split it rajat
        LinkedList second = reverseList(slow.next);
        slow.next = null;

        LinkedList first = list;
        while(second!= null){
            LinkedList temp = first.next;
            LinkedList temp2 = second.next;

            first.next = second;
            second.next =temp;

            first = temp;
            second = temp2;
        }

        while (list!= null){
            System.out.println(list.d);
            list = list.next;
        }

    }

    private static LinkedList reverseList(LinkedList fast) {
        LinkedList prev = null;
        LinkedList curr = fast;
        while (curr!= null){
            LinkedList nextNode = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextNode;
        }
        return prev;
    }
}
