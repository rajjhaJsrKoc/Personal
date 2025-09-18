
# SOLID Principles in Java

The **SOLID principles** are design guidelines that help developers write clean, maintainable, and scalable object-oriented code.  
They are:  

1. **S**ingle Responsibility Principle (SRP)  
2. **O**pen/Closed Principle (OCP)  
3. **L**iskov Substitution Principle (LSP)  
4. **I**nterface Segregation Principle (ISP)  
5. **D**ependency Inversion Principle (DIP)  

---

## 1. Single Responsibility Principle (SRP)

**Definition:** A class should have only **one reason to change**, meaning it should only do **one job**.  

**Example (Bad):**
```java
class User {
    void saveToDatabase() { /* save logic */ }
    void generateReport() { /* report logic */ }
}
````

Here, `User` is handling **persistence** and **reporting** → violates SRP.

**Example (Good):**

```java
class UserRepository {
    void save(User user) { /* save logic */ }
}

class UserReport {
    void generate(User user) { /* report logic */ }
}
```

---

## 2. Open/Closed Principle (OCP)

**Definition:** Classes should be **open for extension but closed for modification**.

**Example (Bad):**

```java
class AreaCalculator {
    double calculate(Object shape) {
        if (shape instanceof Circle) {
            Circle c = (Circle) shape;
            return Math.PI * c.radius * c.radius;
        } else if (shape instanceof Rectangle) {
            Rectangle r = (Rectangle) shape;
            return r.length * r.width;
        }
        return 0;
    }
}
```

Every new shape requires modifying this class.

**Example (Good):**

```java
interface Shape {
    double area();
}

class Circle implements Shape {
    double radius;
    Circle(double r) { this.radius = r; }
    public double area() { return Math.PI * radius * radius; }
}

class Rectangle implements Shape {
    double length, width;
    Rectangle(double l, double w) { this.length = l; this.width = w; }
    public double area() { return length * width; }
}

class AreaCalculator {
    double calculate(Shape shape) {
        return shape.area();
    }
}
```

Now we can add new shapes without modifying `AreaCalculator`.

---

## 3. Liskov Substitution Principle (LSP)

**Definition:** Subtypes must be substitutable for their base types without breaking the program.

**Example (Bad):**

```java
class Bird {
    void fly() {}
}

class Ostrich extends Bird {
    @Override
    void fly() { throw new UnsupportedOperationException("Can't fly"); }
}
```

`Ostrich` violates LSP because it can’t behave like a `Bird`.

**Example (Good):**

```java
interface Bird {}

interface FlyableBird extends Bird {
    void fly();
}

class Sparrow implements FlyableBird {
    public void fly() { System.out.println("Flying..."); }
}

class Ostrich implements Bird {
    // Ostrich does not fly, but still valid Bird
}
```

---

## 4. Interface Segregation Principle (ISP)

**Definition:** Clients should not be forced to depend on interfaces they do not use.

**Example (Bad):**

```java
interface Worker {
    void work();
    void eat();
}

class Robot implements Worker {
    public void work() {}
    public void eat() { /* meaningless for robots */ }
}
```

`Robot` is forced to implement `eat()`, which doesn’t make sense.

**Example (Good):**

```java
interface Workable {
    void work();
}

interface Eatable {
    void eat();
}

class Human implements Workable, Eatable {
    public void work() {}
    public void eat() {}
}

class Robot implements Workable {
    public void work() {}
}
```

---

## 5. Dependency Inversion Principle (DIP)

**Definition:** High-level modules should not depend on low-level modules; both should depend on **abstractions**.

**Example (Bad):**

```java
class MySQLDatabase {
    void connect() {}
}

class UserService {
    private MySQLDatabase db = new MySQLDatabase(); // tightly coupled
    void saveUser() { db.connect(); }
}
```

`UserService` is tightly coupled with `MySQLDatabase`.

**Example (Good):**

```java
interface Database {
    void connect();
}

class MySQLDatabase implements Database {
    public void connect() {}
}

class OracleDatabase implements Database {
    public void connect() {}
}

class UserService {
    private Database db;
    UserService(Database db) { this.db = db; }
    void saveUser() { db.connect(); }
}
```

Now `UserService` depends on the abstraction `Database`, not a specific database.

---

# ✅ Summary

* **SRP** → One responsibility per class.
* **OCP** → Extend behavior without modifying existing code.
* **LSP** → Subclasses should behave like their parent type.
* **ISP** → Split large interfaces into smaller ones.
* **DIP** → Depend on abstractions, not concrete implementations.

These principles make code **maintainable, flexible, and testable**.

---
