package CodingNeetcodeLeetcode.LinkedList;

public class RotateLinkedList {
     public static void main(String[] args) {
         LinkedList list = new LinkedList(5);
         list.next = new LinkedList(4);
         list.next.next = new LinkedList(3);
         list.next.next.next = new LinkedList(2);
         list.next.next.next.next = new LinkedList(1);
         printList(list);
         System.out.println("Reversed lIST");
         printList(rotateList(list));;

     }

    private static LinkedList rotateList(LinkedList head) {
         LinkedList prev = null;
         LinkedList curr  = head;

         while (curr != null) {
             LinkedList next = curr.next;
             curr.next = prev;
             prev = curr;
             curr = next;
         }
         return prev;
    }
    private static void printList(LinkedList head) {
        LinkedList curr = head;
        while (curr != null) {
            System.out.print(curr.d + " ");
            curr = curr.next;
        }
        System.out.println();
    }
}
