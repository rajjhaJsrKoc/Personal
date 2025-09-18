package LinkedList;

class LinkedList {
    int d;
    LinkedList next;

    public LinkedList(int d) {
        this.d = d;
        next = null;
    }
}
/*

public static int findDuplicateSorted(LinkedList head) {
    LinkedList curr = head;
    while (curr != null && curr.next != null) {
        if (curr.d == curr.next.d) {
            return curr.d; // duplicate
        }
        curr = curr.next;
    }
    return -1;
}

public static int findDuplicate(LinkedList head) {
        HashSet<Integer> seen = new HashSet<>();
        LinkedList curr = head;

        while (curr != null) {
            if (seen.contains(curr.d)) {
                return curr.d; // duplicate found
            }
            seen.add(curr.d);
            curr = curr.next;
        }
        return -1; // no duplicate
    }

*/
