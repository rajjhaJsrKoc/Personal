Here are **all answers**, clean, concise, and **100% Java-focused** — exactly what interviewers expect.

---

# ✅ **1️⃣ Overloading vs Overriding (Trick Cases)**

### **1. Can you overload a method by changing only the return type?**

❌ No. Return type is not considered in method signature.

### **2. Can you override a static method?**

❌ No. Static methods are hidden, not overridden.

### **3. Can you overload a static method?**

✅ Yes. Overloading depends only on parameters.

### **4. Can a private method be overridden?**

❌ No. Private methods are not visible to child classes.

### **5. What if parent throws checked exception but child does not?**

✅ Valid. Child can throw fewer or no checked exceptions.

---

# ✅ **2️⃣ Polymorphism Trick Questions**

### **6. What will this print?**

```java
A a = new B();
a.show();
```

➡ Runtime polymorphism → executes **B’s version**.

### **7. If parent reference points to child object, which variable is used?**

➡ **Parent class variable.**
Variables do NOT participate in runtime polymorphism.

### **8. Can constructors be overridden?**

❌ No. They are not inherited.

### **9. Why no runtime polymorphism on data members?**

➡ Because variables are resolved at **compile-time**, not runtime.

### **10. Can you call a child method from parent reference?**

✅ Yes, by **downcasting**, if object is actually child:

```java
((B)a).childMethod();
```
Yes — **you CAN call a child method using a parent reference**, **BUT only if that method is overridden OR is defined in the parent class**.

This is one of the most common OOP interview questions.

---

# ✅ **Case 1: Parent Reference → Overridden Child Method (YES)**

If the method exists in the parent class and is **overridden** in the child,
**parent reference will call child implementation** (runtime polymorphism).

### Example:

```java
class Parent {
    void show() {
        System.out.println("Parent show");
    }
}

class Child extends Parent {
    @Override
    void show() {
        System.out.println("Child show");
    }
}

public class Test {
    public static void main(String[] args) {
        Parent p = new Child();
        p.show();   // Output: Child show ✔
    }
}
```

✔ **Allowed**
✔ Calls **child method** (dynamic dispatch)

---

# ❌ **Case 2: Parent Reference → Child-specific Method (NOT allowed)**

If the method is **only in child**, parent reference **cannot access it**.

### Example:

```java
class Child extends Parent {
    void play() {   // child-specific method
        System.out.println("Play");
    }
}

Parent p = new Child();
p.play();  // ❌ Compile-time error
```

Parent reference does not know `play()` exists.

---

# ✅ **Case 3: Access child-specific method by downcasting**

```java
Parent p = new Child();
((Child)p).play();   // ✔ Now works
```

**Downcasting** lets you call child-only methods.

---

# 🔥 Interview Answer (Perfect One-liner)

> **You can call child methods using a parent reference only when the method is overridden.
> Child-specific methods cannot be accessed unless you downcast.**

---

# ⭐ Example asked in many interviews

### Q: What will be the output of this?

```java
class A {
    void m1() { System.out.println("A"); }
}

class B extends A {
    void m1() { System.out.println("B"); }
    void m2() { System.out.println("B m2"); }
}

public class Test {
    public static void main(String[] args) {
        A a = new B();
        a.m1();  // ?
        // a.m2();  // ?
    }
}
```

### **Answer:**

```
B        // overridden method (allowed)
a.m2();  // compile error (child-specific method)
```

---

---

# ✅ **3️⃣ Inheritance**

### **11. Can a class extend multiple classes?**

❌ No. Java avoids diamond problem.

### **12. What if 2 interfaces have same default method?**

➡ Child must override it or specify which interface to use:

```java
Interface1.super.method();
```

### **13. Why multiple inheritance allowed via interfaces?**

➡ Because interfaces have no state → no diamond ambiguity.

### **14. Order of constructor calls?**

➡ Parent → Child
Top to bottom.

### **15. Can constructor call both another constructor and super()?**

❌ No. First statement must be either `this()` **OR** `super()`.

---

# ✅ **4️⃣ Encapsulation**

### **16. How can immutability be broken?**

➡ By exposing **mutable objects** (like Date, List) without deep copy.

### **17. Why String immutable but StringBuilder not?**

➡ String is used in caches, classloading, security, hashMap keys.
➡ Needs immutability for safety.
➡ StringBuilder meant for modification → mutable.

### **18. Is encapsulation only about access modifiers?**

❌ No. It is about **controlling access via getter/setter**.

### **19. Immutable class with setters?**

❌ Not possible. Mutability breaks immutability.

### **20. Two objects same state but different objects?**

✅ Yes. Identity ≠ equality.

---

# ✅ **5️⃣ Abstraction**

### **21. Can abstract classes have constructors?**

✅ Yes. To initialize common fields.

### **22. Can interface have a constructor?**

❌ No. Interfaces cannot be instantiated.

### **23. Can abstract class have final methods?**

✅ Yes. To prevent overriding specific behavior.

### **24. When choose abstract class over interface?**

➡ When shared **state**, **constructor**, or **partial implementation** is needed.

### **25. Can we have interface with no methods?**

✅ Yes (marker interface). Example: `Serializable`.

---

# ✅ **6️⃣ Interfaces**

### **26. Can interface have private methods?**

✅ Yes (Java 9+).

### **27. Why interface methods public?**

➡ They define a **contract** to be implemented publicly.

### **28. Can interface methods be protected?**

❌ No.

### **29. Can interfaces have variables?**

➡ Yes but they are:

### **30. Why interface variables static + final?**

➡ Because interface cannot have instance data.

---

# ✅ **7️⃣ Composition vs Inheritance**

### **31. Why prefer composition?**

➡ More flexible, runtime changeable, avoids tight coupling.

### **32. When is inheritance dangerous?**

➡ When parent changes → child breaks (fragile base class problem).

### **33. Can composition replace inheritance?**

➡ Yes in most cases.
➡ Except when you need polymorphism.

### **34. What is used by Java Collections Framework?**

➡ **Composition** (ArrayList uses internal array).

### **35. Why is composition flexible?**

➡ Behavior can be changed at runtime.

---

# ✅ **8️⃣ Constructors & Object Creation**

### **36. Why constructors can’t be final/static/abstract?**

✔ Final → cannot be overridden
✔ Static → belongs to class, constructors need object
✔ Abstract → must be overridden, but constructors don’t participate

### **37. Order of initialization**

➡ static block → instance block → constructor

### **38. Can constructor return a value?**

❌ No. It implicitly returns the object.

### **39. What if constructor throws exception?**

➡ Object creation fails; no object returned.

### **40. Can JVM create object without constructor?**

✅ Yes (via deserialization, reflection, `Unsafe`, cloning).

---

# ✅ **9️⃣ Core OOP Principles**

### **41. Which principle reduces tight coupling?**

➡ Encapsulation, Abstraction.

### **42. Which helps unit testing?**

➡ Abstraction, Interfaces, Dependency Injection.

### **43. Which enables dynamic method binding?**

➡ Polymorphism.

### **44. Which hides implementation details?**

➡ Abstraction.

### **45. Which enables polymorphism with interfaces?**

➡ Inheritance.

---

# ✅ **🔟 Real-World Scenarios**

### **46. How to make a class immutable?**

* Make fields `private final`
* No setters
* Deep copy mutable fields
* Class final
* Initialize via constructor only

### **47. Interface vs abstract class?**

✔ Interface → capabilities
✔ Abstract class → shared state + partial code

### **48. Explain OOP in real system?**

➡ Example: ATM

* Account → encapsulation
* Withdraw → abstraction
* Savings extends Account → inheritance
* Different withdraw logic → polymorphism

### **49. If class has 200 methods and you need 2?**

➡ Use **interface**, not the huge class.

### **50. Why overriding is essential in frameworks?**

➡ Framework calls your code → **Hollywood Principle**
(“Don’t call us; we call you”).

---

# ⭐ **Java-specific**

### **51. Why equals() and hashCode() in Object class?**

➡ All Java objects need hashing & equality checks.

### **52. Why clone() in Object but Cloneable is interface?**

➡ Marker interface ensures permission; Object provides implementation.

### **53. Why String final & immutable?**

➡ Security, caching, ClassLoader safety, HashMap keys stability.

### **54. Why no operator overloading?**

➡ To keep language simple & readable.

### **55. What if toString() not overridden?**

➡ Prints className@hashCode (meaningless output).

