# Java Concurrency Concepts

## 1. `volatile` Keyword

### 🔹 What is `volatile`?
- `volatile` is a keyword in Java used to ensure **visibility** of variables across threads.
- Guarantees that:
  1. A write to a volatile variable is immediately **flushed to main memory**.
  2. A read of a volatile variable is always done from **main memory**, not from CPU cache.

### 🔹 How it works internally?
- **Store barrier**: Before writing to a volatile variable, all changes are flushed to main memory.
- **Load barrier**: Before reading a volatile variable, it fetches the latest value from main memory.
- Prevents **instruction reordering** for reads/writes of that variable.

### 🔹 Example
```java
class VolatileExample {
    volatile boolean flag = false;

    void writer() {
        flag = true; // Visible immediately to all threads
    }

    void reader() {
        while (!flag) {
            // Without volatile → may loop forever
        }
        System.out.println("Flag updated!");
    }
}
````

---

## 2. `AtomicInteger`

### 🔹 What is `AtomicInteger`?

* A class in `java.util.concurrent.atomic` package.
* Provides **atomic operations** on an `int` without using synchronization.
* Achieved using **CAS (Compare-And-Swap)** at the CPU level.

### 🔹 Key Methods

* `incrementAndGet()`
* `decrementAndGet()`
* `compareAndSet(expectedValue, newValue)`

### 🔹 Example

```java
import java.util.concurrent.atomic.AtomicInteger;

class Counter {
    private AtomicInteger count = new AtomicInteger(0);

    public void increment() {
        count.incrementAndGet();
    }

    public int getValue() {
        return count.get();
    }
}
```

---

## 3. Pessimistic Locking

### 🔹 What is it?

* Assumes **conflict will occur**.
* Locks the resource before accessing to ensure **mutual exclusion**.
* Example: `synchronized`, `ReentrantLock`.

### 🔹 Example

```java
class PessimisticExample {
    private int value = 0;

    public synchronized void increment() {
        value++; // Full lock acquired
    }
}
```

---

## 4. Optimistic Locking

### 🔹 What is it?

* Assumes **conflict is rare**.
* Reads the value without locking.
* When updating, it checks if the value has changed using **CAS (Compare-And-Swap)**.
* If another thread updated in between → retry.

### 🔹 Example

```java
class OptimisticExample {
    private AtomicInteger value = new AtomicInteger(0);

    public void increment() {
        int oldValue, newValue;
        do {
            oldValue = value.get();
            newValue = oldValue + 1;
        } while (!value.compareAndSet(oldValue, newValue));
    }
}
```

---

## 5. ReentrantLock

### 🔹 What is `ReentrantLock`?

* A class in `java.util.concurrent.locks`.
* Works like `synchronized` but with **extra features**.
* "Reentrant" → Same thread can acquire the lock multiple times without deadlocking.
* Must explicitly **lock()** and **unlock()** (unlike `synchronized`).

### 🔹 Features

* **Fairness**: Option to create a fair lock (`new ReentrantLock(true)`) that grants access in FIFO order.
* **TryLock**: Non-blocking attempt to acquire a lock.
* **Interruptible Locks**: A thread can be interrupted while waiting for a lock.

### 🔹 Example

```java
import java.util.concurrent.locks.ReentrantLock;

class ReentrantLockExample {
    private final ReentrantLock lock = new ReentrantLock();
    private int value = 0;

    public void increment() {
        lock.lock(); // Acquire lock
        try {
            value++;
        } finally {
            lock.unlock(); // Always release in finally
        }
    }

    public int getValue() {
        return value;
    }
}
```

### 🔹 Using `tryLock`

```java
if (lock.tryLock()) {
    try {
        // Do work
    } finally {
        lock.unlock();
    }
} else {
    System.out.println("Couldn't acquire lock");
}
```

---

## 6. Summary

| Feature          | Volatile                | AtomicInteger              | Pessimistic Locking                | Optimistic Locking                  | ReentrantLock                   |
| ---------------- | ----------------------- | -------------------------- | ---------------------------------- | ----------------------------------- | ------------------------------- |
| Purpose          | Visibility guarantee    | Atomic operations          | Prevent conflicts by locking       | Assume rare conflict, retry on fail | Advanced locking mechanism      |
| Lock Used?       | ❌ No                    | ❌ No                       | ✅ Yes                              | ❌ No (uses CAS)                     | ✅ Yes                           |
| Performance      | High (but no atomicity) | High (uses CAS)            | Lower (blocking threads)           | Very High if conflicts are rare     | Medium (explicit lock handling) |
| Fairness Option  | ❌ No                    | ❌ No                       | ❌ No                               | ❌ No                                | ✅ Yes                           |
| Example Use Case | Flags, config values    | Counters, sequence numbers | Bank transactions, shared resource | Concurrent counters, retries        | Complex concurrency control     |

---

