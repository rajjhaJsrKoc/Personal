# Modern Java Features (Java 17–21)

This document explains three major modern Java features introduced between **Java 17 and Java 21** — features that significantly simplify concurrency, inheritance, and control flow.

---

## 🧵 Virtual Threads (Java 21)

### Overview
**Virtual Threads** (from **Project Loom**) are lightweight threads introduced in **Java 21**.  
They allow developers to handle massive concurrency without the overhead of OS threads.

> **In short:** Virtual threads are managed by the JVM, not the operating system — allowing millions of concurrent tasks efficiently.

### Key Concepts
| Feature | Platform Thread | Virtual Thread |
|----------|----------------|----------------|
| Managed By | Operating System | Java Virtual Machine |
| Creation Cost | High | Very Low |
| Memory Usage | ~1MB stack | Few KB stack |
| Ideal For | CPU-bound work | I/O-bound work |
| Concurrency Limit | Thousands | Millions |

### How It Works
When a virtual thread performs a blocking operation (like waiting for I/O):
- It **yields** its carrier thread back to the JVM scheduler.
- Another virtual thread uses that carrier thread.
- Once the I/O finishes, the parked virtual thread **resumes**.

### Example
```java
ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor();

for (int i = 0; i < 1000; i++) {
    executor.submit(() -> {
        handleRequest(); // Simulated I/O work
    });
}
executor.shutdown();
````

✅ Each task gets its own thread — but they’re lightweight, so you can spawn **millions** without exhausting resources.

### Benefits

* **Massive scalability**
* **Readable, blocking-style code**
* **Efficient resource use**
* **Works with existing concurrency APIs**

---

## 🧱 Sealed Classes (Java 17)

### Overview

A **sealed class** restricts which classes can extend or implement it.
It provides a controlled inheritance hierarchy and improves type safety.

> **In short:** Sealed classes let you define a closed set of subclasses that can inherit from a parent.

### Syntax

```java
public sealed class Shape permits Circle, Rectangle, Square {}
```

### Subclass Options

Each permitted subclass must explicitly choose:

| Modifier     | Meaning                |
| ------------ | ---------------------- |
| `final`      | No further subclassing |
| `sealed`     | Restricted subclassing |
| `non-sealed` | Open subclassing again |

Example:

```java
public final class Circle extends Shape {}
public sealed class Rectangle extends Shape permits Square {}
public non-sealed class Square extends Shape {}
```

### Use Cases

1. **Domain modeling**

   ```java
   public sealed interface Payment permits Upi, Card, Cash {}
   ```
2. **Exhaustive pattern matching in switch**
3. **Secure and controlled API hierarchies**
4. **Alternative to enums for richer data modeling**

### Benefits

* Controlled inheritance
* Compiler exhaustiveness checks
* Cleaner domain models
* Enhanced pattern matching

---

## 🎛️ Switch Expressions and Pattern Matching (Java 17+)

### Switch Expressions

Enhanced `switch` that can **return a value** and avoids fall-through.

#### Before

```java
switch (day) {
    case "MONDAY": result = 1; break;
    default: result = 0;
}
```

#### Now

```java
int result = switch (day) {
    case "MONDAY", "FRIDAY" -> 6;
    case "TUESDAY" -> 7;
    default -> 8;
};
```

#### Multi-line Case with `yield`

```java
int result = switch (day) {
    case "MONDAY" -> {
        System.out.println("Start of week");
        yield 1;
    }
    default -> 0;
};
```

### Pattern Matching in Switch

Introduced in **Java 17 (preview)** — allows type testing and casting directly in `switch`.

#### Example

```java
static String format(Object obj) {
    return switch (obj) {
        case Integer i -> "Integer: " + i;
        case String s -> "String: " + s.toUpperCase();
        case null -> "null";
        default -> "Unknown";
    };
}
```

#### Works Beautifully with Sealed Classes

```java
sealed interface Shape permits Circle, Rectangle {}
record Circle(double radius) implements Shape {}
record Rectangle(double w, double h) implements Shape {}

String describe(Shape shape) {
    return switch (shape) {
        case Circle c -> "Circle of radius " + c.radius();
        case Rectangle r -> "Rectangle " + r.w() + "x" + r.h();
    };
}
```

Compiler ensures all subclasses are covered — no need for `default`.

---

## 🧠 Summary

| Feature                        | Introduced          | Purpose                                          |
| ------------------------------ | ------------------- | ------------------------------------------------ |
| **Virtual Threads**            | Java 21             | Lightweight concurrency for millions of tasks    |
| **Sealed Classes**             | Java 17             | Restrict inheritance and improve domain modeling |
| **Switch Expressions**         | Java 14 (finalized) | Simplify and return values from switch           |
| **Pattern Matching in Switch** | Java 17+            | Type-safe, cleaner branching                     |

---

## 🧩 Together

These features make modern Java:

* **More expressive** (switch & pattern matching)
* **More scalable** (virtual threads)
* **More predictable and safe** (sealed classes)

> Java 17–21 is a leap toward concise, readable, and high-performance code while keeping the strong type safety Java is known for.

---

