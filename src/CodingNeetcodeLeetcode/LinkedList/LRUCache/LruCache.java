package CodingNeetcodeLeetcode.LinkedList.LRUCache;

import java.util.HashMap;
import java.util.Map;

public class LruCache {
    private Map<Integer,Node> cacheMap = new HashMap<>();
    int capacity;
    private Node head;
    private Node tail;

    public LruCache(int capacity) {
        this.cacheMap = new HashMap<>();
        this.capacity = capacity;
        this.head = new Node(-1,-1);
        this.tail = new Node(-1,-1);
        this.head.next= this.tail;
        this.tail.next = this.head;
    }

    int get (int key){
        if(!cacheMap.containsKey(key)){
            return -1;
        }
        Node node = cacheMap.get(key);
        remove(node);
        add(node);
        return node.value;
    }
    void put (int key, int value){
        if (cacheMap.containsKey(key)){
            Node node = cacheMap.get(key);
            remove(node);
        }
        Node node = new Node(key,value);
        cacheMap.put(key,node);
        add(node);
        if (cacheMap.size()> capacity){
            Node nodeToDelete = tail.prev;
            remove(nodeToDelete);
            cacheMap.remove(nodeToDelete.key);
        }
    }

    public void add(Node node){
        Node nextNode = head.next;
        node.prev= head;
        head.next = node;
        node.next =nextNode;
        nextNode.prev= node;
    }
    public void remove(Node node){
        Node prevNode = node.prev;
        Node nextNode = node.next;
        prevNode.next = nextNode;
        nextNode.prev = prevNode;
    }
}
