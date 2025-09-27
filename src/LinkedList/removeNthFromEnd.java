package LinkedList;


public class removeNthFromEnd {
    public static void main(String[] args) {
        LinkedList list = new LinkedList(1);
        list.next = new LinkedList(2);
        list.next.next = new LinkedList(3);
        list.next.next.next = new LinkedList(4);
        list.next.next.next.next = new LinkedList(5);
        removeNthFromEnd(list, 2);
        printList(list);
    }

    public static LinkedList removeNthFromEnd(LinkedList head, int n) {
        LinkedList dummy = new LinkedList(0);
        dummy.next = head;
        LinkedList fast = dummy;
        LinkedList slow = dummy;

        // Move fast ahead by n+1 steps
        for (int i = 0; i <= n; i++) {
            fast = fast.next;
        }

        // Move both pointers
        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }

        // Delete nth node from end
        slow.next = slow.next.next;

        return dummy.next;
    }

    public static void printList(LinkedList head) {
        LinkedList curr = head;
        while (curr != null) {
            System.out.print(curr.d + " ");
            curr = curr.next;
        }
        System.out.println();
    }
}
