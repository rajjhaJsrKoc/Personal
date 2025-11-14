package CodingNeetcodeLeetcode.stack;
public class MyStack {
    private int[] arr;
    private int top;
    private int capacity;
    private int max = Integer.MIN_VALUE;
    private int[] maxArr;

    // push 1,2,3
    // 2,4,1,7

    public MyStack(int value) {
        arr = new int[value];
        maxArr = new int[value];
        capacity = value;
        top = -1;
    }

    public void push(int value) {
        if (isFull()) {
            return;
        }
        arr[++top] = value;
        if (top == 0) {
            maxArr[top] = value;
        } else {
            maxArr[top] = Math.max(value, maxArr[top - 1]);
        }


    }

    public int pop() {
        if (isEmpty())
            return -1;
        return arr[top--];
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public boolean isFull() {
        return top == capacity - 1;
    }

    public int maxVaraible() {

        return maxArr[top];
    }
}
