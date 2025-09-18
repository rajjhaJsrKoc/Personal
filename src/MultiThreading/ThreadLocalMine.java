package MultiThreading;

public class ThreadLocalMine {
    public static ThreadLocal<String> threadLocal = new  ThreadLocal<>();
    //In a web server, each incoming request is handled by a separate thread.
    //You can store user-specific data like session info, transaction context, or database connection in a ThreadLocal.
    //This way, multiple requests from different users don’t interfere.
    public static void setUser(String user) {
        threadLocal.set(user);
    }

    public static String getUser() {
        return threadLocal.get();
    }

    public static void clear() {
        threadLocal.remove();
    }
}
