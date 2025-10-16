package CodingNeetcodeLeetcode.LinkedList.LRUCache;

public class DriverLruCache {
    public static void main(String[] args){
        LruCache lruCache = new LruCache(3);
        lruCache.put(1,5);
        lruCache.put(2,6);
        lruCache.put(2,5);
        lruCache.get(1);
        lruCache.put(7,7);
        lruCache.put(8,8);
        System.out.println(lruCache.get(2));
    }
}
