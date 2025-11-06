package LLD.Queue;

public class MyQueue {
    private int[] arr;
    private int capacity;
    int front;
    int rear;
    int size ;
    public  MyQueue(int capacity) {
        this.capacity = capacity;
        this.arr = new int[capacity];
        front = 0;
        rear = 0;
    }

    public void push(int data){
        arr[rear] = data;
        rear++;
    }

    public int pop(){
        int value = arr[front];
        front++;
        return value;
    }
}

