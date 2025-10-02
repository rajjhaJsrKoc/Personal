package CodingNeetcodeLeetcode.LinkedList;

public class AddTwoNumbers {
    public static void main(String[] args) {
        LinkedList l1 = new LinkedList(2);
        l1.next = new LinkedList(4);
        l1.next.next = new LinkedList(3);

        LinkedList l2 = new LinkedList(5);
        l2.next = new LinkedList(6);
        l2.next.next = new LinkedList(4);

        addTwoNumbers(l1, l2);

    }
    public static LinkedList addTwoNumbers(LinkedList l1, LinkedList l2) {
        LinkedList dummy = new LinkedList(0);
        LinkedList current = dummy;
        int carry = 0;

        while (l1 != null || l2 != null) {
            int x = (l1 != null) ? l1.d : 0;
            int y = (l2 != null) ? l2.d : 0;

            int sum = carry + x + y;
            carry = sum / 10;

            current.next = new LinkedList(sum % 10);
            current = current.next;

            if (l1 != null) l1 = l1.next;
            if (l2 != null) l2 = l2.next;
        }

        if (carry > 0) {
            current.next = new LinkedList(carry);
        }

        return dummy.next;
    }
}
