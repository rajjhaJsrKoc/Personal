Great topic, Rajat 👏 — this one’s super common in Java interviews and real-world coding alike.

Let’s break down **Shallow vs Deep Cloning** with clarity and examples 👇

---

## 🧩 What is Cloning?

In Java, **cloning** means creating a **copy of an existing object**.
It’s done using the `clone()` method from the `Cloneable` interface.

But — not all clones are equal.
The key difference lies in **how deeply the object’s contents are copied**.

---

## ⚖️ Difference Between Shallow and Deep Cloning

| Feature                | **Shallow Cloning**                                                          | **Deep Cloning**                                                 |
| ---------------------- | ---------------------------------------------------------------------------- | ---------------------------------------------------------------- |
| **Definition**         | Copies only the **top-level object**; inner objects (references) are shared. | Copies the object **and all objects it references** recursively. |
| **Reference Behavior** | Inner objects still **refer to the same memory**.                            | Inner objects are **completely duplicated**.                     |
| **Independence**       | Changes in one object affect the other.                                      | Changes are fully independent.                                   |
| **Performance**        | Faster, less memory usage.                                                   | Slower, more memory usage.                                       |
| **Implementation**     | Default `Object.clone()`                                                     | Manual deep copy or serialization                                |

---

## 🧠 Example:

### 🧍 Shallow Clone Example

```java
class Address {
    String city;

    Address(String city) {
        this.city = city;
    }
}

class Person implements Cloneable {
    String name;
    Address address;

    Person(String name, Address address) {
        this.name = name;
        this.address = address;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone(); // Shallow copy
    }
}

public class Main {
    public static void main(String[] args) throws CloneNotSupportedException {
        Address addr = new Address("Delhi");
        Person p1 = new Person("Rajat", addr);
        Person p2 = (Person) p1.clone();

        p2.name = "Aman";
        p2.address.city = "Mumbai"; // modifies shared reference

        System.out.println(p1.address.city); // 👉 Output: "Mumbai"
    }
}
```

🔴 Both `p1` and `p2` share the **same Address object** — that’s **shallow cloning**.

---

### 🧍‍♂️ Deep Clone Example

```java
class Address implements Cloneable {
    String city;

    Address(String city) {
        this.city = city;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}

class Person implements Cloneable {
    String name;
    Address address;

    Person(String name, Address address) {
        this.name = name;
        this.address = address;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        Person cloned = (Person) super.clone();
        cloned.address = (Address) address.clone(); // deep copy of inner object
        return cloned;
    }
}

public class Main {
    public static void main(String[] args) throws CloneNotSupportedException {
        Address addr = new Address("Delhi");
        Person p1 = new Person("Rajat", addr);
        Person p2 = (Person) p1.clone();

        p2.address.city = "Mumbai";

        System.out.println(p1.address.city); // 👉 Output: "Delhi"
    }
}
```

✅ Both have **separate Address objects** — that’s **deep cloning**.

---

## ⚙️ Alternative ways to do Deep Clone

1. **Serialization**

   ```java
   ByteArrayOutputStream bos = new ByteArrayOutputStream();
   ObjectOutputStream out = new ObjectOutputStream(bos);
   out.writeObject(obj);
   ObjectInputStream in = new ObjectInputStream(
       new ByteArrayInputStream(bos.toByteArray())
   );
   Object deepCopy = in.readObject();
   ```

2. **Copy constructors**
   Create a new object and manually copy all fields.

3. **Libraries** like:

    * Apache Commons `SerializationUtils.clone()`
    * ModelMapper or Dozer

---

## 🧭 Summary

| Aspect            | Shallow Clone         | Deep Clone                           |
| ----------------- | --------------------- | ------------------------------------ |
| Copies            | Only top-level fields | All nested objects                   |
| Shared references | Yes                   | No                                   |
| Speed             | Fast                  | Slower                               |
| Independence      | Partial               | Full                                 |
| Example           | `super.clone()`       | Recursive `clone()` or serialization |

---

Would you like me to show a **copy-constructor-based deep cloning** example (which is usually preferred in production code over `clone()`)?
