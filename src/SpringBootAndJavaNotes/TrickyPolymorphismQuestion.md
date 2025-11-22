Here are **10 tricky and high-frequency polymorphism interview questions** with **clear Java answers**.
These are asked in **Amazon, Microsoft, Cognizant, Infosys, TCS, and Barclays**.

---

# ✅ **Q1. What is the output?**

```java
class A {
    void m1() { System.out.println("A m1"); }
}

class B extends A {
    void m1() { System.out.println("B m1"); }
}

public class Test {
    public static void main(String[] args) {
        A a = new B();
        a.m1();
    }
}
```

### **Answer:**

```
B m1
```

✔ Runtime polymorphism → child method executes.

---

# ✅ **Q2. Which method is called? (Overloading + Overriding Mix)**

```java
class A {
    void show(Number n) { System.out.println("A Number"); }
}

class B extends A {
    void show(Integer i) { System.out.println("B Integer"); }
}

public class Test {
    public static void main(String[] args) {
        A a = new B();
        a.show(10);
    }
}
```

### **Answer:**

```
A Number
```

✔ Overloading is resolved at **compile-time** based on reference type → A.

---

# ✅ **Q3. Can a parent reference call a child-only method?**

```java
A a = new B();
a.childMethod(); 
```

### **Answer:**

❌ **Compile-time error**
Parent does not know child-only methods.

---

# ✅ **Q4. Which version runs?**

```java
class A {
    static void test() { System.out.println("A static"); }
}

class B extends A {
    static void test() { System.out.println("B static"); }
}

public class Test {
    public static void main(String[] args) {
        A a = new B();
        a.test();
    }
}
```

### **Answer:**

```
A static
```

✔ Static methods do **NOT** participate in runtime polymorphism.

---

# ✅ **Q5. What happens? (Instance + Static)**

```java
class A {
    void m1() { System.out.println("A m1"); }
    static void m2() { System.out.println("A m2"); }
}

class B extends A {
    void m1() { System.out.println("B m1"); }
    static void m2() { System.out.println("B m2"); }
}

public class Test {
    public static void main(String[] args) {
        A a = new B();
        a.m1();
        a.m2();
    }
}
```

### **Answer:**

```
B m1
A m2
```

✔ Instance method → runtime
✔ Static → compile-time (based on reference)

---

# ✅ **Q6. Can constructors be overridden?**

### **Answer:**

❌ **No.** Constructors cannot be overridden.
They are not inherited → no polymorphism.

---

# ✅ **Q7. What is the output? (Upcasting + Downcasting)**

```java
A a = new B();
B b = (B) a;
b.m1();
```

### **Answer:**

```
B m1
```

✔ After downcasting, child methods become accessible.
✔ Safe because `a` actually refers to `B`.

---

# ❌ **Q8. What happens? Unsafe Downcasting**

```java
A a = new A();
B b = (B) a;
```

### **Answer:**

❌ **Runtime Exception — ClassCastException**

Cannot cast parent object to child.

---

# ✅ **Q9. Final method overriding?**

```java
class A {
    final void m1() {}
}

class B extends A {
    void m1() {}  // ?
}
```

### **Answer:**

❌ Compile-time error
Final methods **cannot be overridden** → no runtime polymorphism.

---

# ✅ **Q10. Which method prints? (Return type covariance)**

```java
class A {
    A show() { return this; }
}

class B extends A {
    @Override
    B show() { return this; }
}

public class Test {
    public static void main(String[] args) {
        A a = new B();
        a.show();
    }
}
```

### **Answer:**

✔ **B’s show() executes** because return type is covariant.

Output:
(No print statement, but child method runs)

---

# 🔥 Bonus: Want 10 more **super tricky polymorphism MCQs** with output prediction?
