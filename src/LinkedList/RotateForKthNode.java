package LinkedList;

public class RotateForKthNode {

    public static void main(String[] args) {
        LinkedList list = new LinkedList(1);
        list.next = new LinkedList(2);
        list.next.next = new LinkedList(3);
        list.next.next.next = new LinkedList(4);
        list.next.next.next.next = new LinkedList(5);
        RotateForKthNode(list,5);


        printListNew(removeNthFromEnd(list,2));
    }

    private static void printListNew(LinkedList linkedList) {
        LinkedList list = linkedList;
        while (list != null) {
            System.out.println(list.d);
            list = list.next;
        }
    }

    private static LinkedList RotateForKthNode(LinkedList head, int i) {
        if (head == null || head.next == null || i==0) {
            return head;
        }
        // counting the no of list in the node
        LinkedList tail = head;
        int count = 1;
        while (tail.next != null) {
            tail = tail.next;
            count++;
        }
        i = i%count;
    return head;
    }

    private static LinkedList removeNthFromEnd(LinkedList head, int n) {
        LinkedList fast = head;
        LinkedList slow = head;
        // Two pointer first
        for (int i = 0; i < n; i++)
            fast = fast.next;

        // If fast becomes NULL, then head
        // is the nth node from end.
        if (fast == null)
            return head.next;

        // Move both pointers until fast reaches the end
        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }

        // Remove the nth node from the end
        slow.next = slow.next.next;
        return head;
    }
}
