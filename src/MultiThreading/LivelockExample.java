package MultiThreading;

public class LivelockExample {
    static class Worker {
        private String name;
        private boolean active;

        public Worker(String name, boolean active) {
            this.name = name;
            this.active = active;
        }

        public String getName() {
            return name;
        }

        public boolean isActive() {
            return active;
        }

        public void work(Worker other) {
            while (active) {
                if (other.isActive()) {
                    System.out.println(name + " says: You go first, " + other.getName());
                    try { Thread.sleep(100); } catch (InterruptedException ignored) {}
                    continue; // gives way to the other worker
                }
                System.out.println(name + " is working!");
                break; // actually starts working
            }
        }
    public static void main(String[] args) {
        Worker worker1 = new Worker("Worker1", true);
        Worker worker2 = new Worker("Worker2", true);

        // Both workers politely let the other go first
        new Thread(() -> worker1.work(worker2)).start();
        new Thread(() -> worker2.work(worker1)).start();

    }
}
}
