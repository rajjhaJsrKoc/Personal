package CodingNeetcodeLeetcode.LinkedList;
public class DetectCycleAndMiddleNode {

    public static void main(String[] args) {
        LinkedList head = new LinkedList(1);
        head.next = new LinkedList(2);
        head.next.next = new LinkedList(3);
        head.next.next.next = new LinkedList(4);
        head.next.next.next.next = new LinkedList(5);
        PrintMiddleNode(middleNode(head));
        //head.next.next.next.next.next = head.next.next;
        System.out.println(isCycle(head));

    }

    private static void PrintMiddleNode(LinkedList head) {
        System.out.println("Middle Node:" + head.d);
    }

    private static LinkedList middleNode(LinkedList head) {
        LinkedList fast = head;
        LinkedList slow = head;
        while (fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    private static boolean isCycle(LinkedList head) {
        LinkedList fast = head;
        LinkedList slow = head;
        while (fast.next != null && slow.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                return true;
            }
        }

    return false;}
}
