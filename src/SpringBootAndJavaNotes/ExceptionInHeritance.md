In **Java inheritance**, whether the **child method must throw the same exception as the parent** depends on the type of exception.

---

# ✅ **Case 1: Checked Exceptions (e.g., IOException, SQLException)**

If the **parent method throws a checked exception**, then:

### ✔ Child can:

* Throw **the same exception**, OR
* Throw a **subclass of that exception**, OR
* Throw **no exception**

### ❌ Child **cannot** throw a *broader* checked exception.

---

### **Example**

```java
class Parent {
    void show() throws IOException {
        System.out.println("Parent");
    }
}

class Child extends Parent {
    @Override
    void show() throws FileNotFoundException {  // ✔ Allowed (subclass)
        System.out.println("Child");
    }
}
```

---

### ❌ NOT Allowed (broader exception)

```java
@Override
void show() throws Exception {  // ❌ ERROR: broader exception
}
```

---

# ✅ **Case 2: Unchecked Exceptions (RuntimeException)**

For **runtime exceptions**, there are **no restrictions**.

Child can throw:

* Same exception
* More exception
* No exception
* Broader exception

Example:

```java
class Parent {
    void display() throws RuntimeException {}
}

class Child extends Parent {
    @Override
    void display() throws ArithmeticException {}  // ✔ Allowed
}
```

---

# 🔥 Summary Table

| Parent throws         | Child can throw         | Child cannot throw        |
| --------------------- | ----------------------- | ------------------------- |
| **Checked Exception** | Same, subclass, or none | Broader checked exception |
| **Runtime Exception** | Anything                | Nothing is restricted     |
| **No exception**      | Only runtime exception  | Checked exception         |

---

# ⭐ Final Answer

**If a parent method throws a checked exception, the child method is NOT required to throw it**, but it must follow the rules:
✔ Same or narrower exception allowed.
❌ Broader checked exception not allowed.

If it’s an **unchecked exception**, the child can throw anything.

