package LLD.HashMap;

import java.util.*;

class MyHashMap<K, V> {
    // Node structure (Linked List for collisions)
    static class Node<K, V> {
        K key;
        V value;
        Node<K, V> next;

        Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private int capacity; // number of buckets
    private int size;     // number of key-value pairs
    private Node<K, V>[] buckets;

    // Constructor with default capacity
    public MyHashMap() {
        this.capacity = 16;
        this.buckets = new Node[capacity];
        this.size = 0;
    }

    // Hash function
    private int getBucketIndex(K key) {
        return (key == null) ? 0 : Math.abs(key.hashCode()) % capacity;
    }

    // Put key-value pair
    public void put(K key, V value) {
        int index = getBucketIndex(key);
        Node<K, V> head = buckets[index];

        // check if key already exists
        Node<K, V> curr = head;
        while (curr != null) {
            if (curr.key.equals(key)) {
                curr.value = value; // update value
                return;
            }
            curr = curr.next;
        }

        // insert at head
        Node<K, V> newNode = new Node<>(key, value);
        newNode.next = head;// .next is just an indicator simple as that
        buckets[index] = newNode;
        size++;
    }

    // Get value
    public V get(K key) {
        int index = getBucketIndex(key);
        Node<K, V> curr = buckets[index];

        while (curr != null) {
            if (curr.key.equals(key)) {
                return curr.value;
            }
            curr = curr.next;
        }
        return null; // not found
    }

    public V remove(K key) {
        int index = getBucketIndex(key);
        Node<K, V> curr = buckets[index];
        Node<K, V> prev = null;

        while (curr != null) {
            if (curr.key.equals(key)) {
                if (prev == null) {
                    buckets[index] = curr.next;
                } else {
                    prev.next = curr.next;
                }
               // Head →  [Apple] → [Banana] → [Mango] → null
                // Head → [Apple] → [Mango] → null
                size--;
                return curr.value;
            }
            prev = curr;
            curr = curr.next;
        }
        return null; // not found
    }

    public int size() {
        return size;
    }

    public boolean containsKey(K key) {
        return get(key) != null;
    }
}
