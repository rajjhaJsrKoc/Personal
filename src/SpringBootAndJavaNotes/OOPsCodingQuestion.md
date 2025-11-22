Sure Rajat — here is a **clean, perfectly formatted `.md` file content** containing **all 30 tricky Java OOP coding questions with answers**, ready to copy-paste directly into a Markdown file.

---

# 🟦 **Java OOP – 30 Tricky Coding Questions With Answers**

---

## **1️⃣ Overriding vs Variable Hiding**

### **Question**

```java
class A { int i = 10; void show(){System.out.println("A");} }
class B extends A { int i = 20; void show(){System.out.println("B");} }

A a = new B();
System.out.println(a.i);
a.show();
```

### **Answer**

```
10
B
```

**Explanation:**

* Variables → compile-time binding (A).
* Methods → runtime polymorphism (B).

---

## **2️⃣ Static Method Hiding**

### **Question**

```java
class A { static void test(){System.out.println("A");} }
class B extends A { static void test(){System.out.println("B");} }

A a = new B();
a.test();
```

### **Answer**

```
A
```

Static methods are *hidden*, not overridden.

---

## **3️⃣ Constructor Order in Inheritance**

### **Question**

```java
class A { A(){System.out.println("A");} }
class B extends A { B(){System.out.println("B");} }
class C extends B { C(){System.out.println("C");} }

new C();
```

### **Answer**

```
A
B
C
```

---

## **4️⃣ Overloading Null Argument**

### **Question**

```java
void call(Object o){System.out.println("Object");}
void call(String s){System.out.println("String");}

call(null);
```

### **Answer**

```
String
```

String is more specific.
Yes — **in this context, `null` is treated as a `String`**, because Java chooses the **most specific applicable method** during method overloading.

Let’s break it down properly.

---

# 🟦 **Your Code**

```java
void call(Object o){ System.out.println("Object"); }
void call(String s){ System.out.println("String"); }

call(null);
```

# ✅ **Output**

```
String
```

---

# ✅ **Why does Java choose call(String)?**

`null` can match **any reference type**:

* Object
* String
* Integer
* Your custom class

So technically, `null` is compatible with both:

```
call(Object o)
call(String s)
```

But Java selects the **most specific** method.

### **String is a subclass of Object**

```
String  ⟶ is more specific than ⟶  Object
```

Therefore, Java chooses:

```java
call(String s)
```

---

# 🔥 What if two methods are equally specific?

Example:

```java
void call(String s){}
void call(StringBuilder sb){}

call(null);
```

### ❌ Compile-time error:

```
reference to call is ambiguous
```

Because neither is more specific than the other.

---

# ⭐ Final Answer

**Null is not a String**, BUT…

➡ When resolving overloaded methods,
➡ Java picks the **most specific** matching method,
➡ which is **String** (because String ⟶ Object).

That’s why:

```
call(null) → picks call(String)
```

---
---

## **5️⃣ Final Method Cannot Be Overridden**

### **Question**

```java
class A { final void show(){} }
class B extends A { void show(){} }
```

### **Answer**

❌ Compile-time error
Final method cannot be overridden.

---

## **6️⃣ Default Method Conflict in Interfaces**

### **Question**

```java
interface X { default void test(){System.out.println("X");} }
interface Y { default void test(){System.out.println("Y");} }

class Z implements X, Y {}
```
You're getting an error because **both interfaces X and Y provide the SAME default method**, and Java doesn't know **which one Z should inherit**.

Here is what happens:

---

# ❌ **Why the Error Occurs?**

### Your code:

```java
interface X { 
    default void test(){ System.out.println("X"); } 
}

interface Y { 
    default void test(){ System.out.println("Y"); } 
}

class Z implements X, Y {}
```

Both `X` and `Y` provide:

```java
default void test()
```

### Now Z inherits **two identical methods**:

* `X.test()`
* `Y.test()`

Java cannot automatically choose which one to use.

This is called the **Diamond Problem** for default methods.

---

# 📌 **Compiler Error**

```
class Z inherits unrelated defaults for test() from X and Y
```

Java forces you to **resolve the conflict** manually.

---

# ✅ **How to Fix? Provide your own implementation in Z**

```java
class Z implements X, Y {
    @Override
    public void test() {
        X.super.test(); // or Y.super.test();
    }
}
```

Now you explicitly choose which default method to use.

---

# ⭐ **Why does Java enforce this?**

To avoid ambiguity.

If Java didn't give an error, we would have no idea what `new Z().test()` should print:

* `"X"` ?
* `"Y"` ?

So the programmer must resolve the conflict.

---

# ⭐ Final Answer

> The error occurs because both interfaces provide the same default method, causing ambiguity.
> Java forces the implementing class (Z) to override the method and choose which one to call.

---

If you want, I can also show you a diagram of how default methods cause the diamond problem.

### **Answer**

❌ Compile-time error
Must override test().

---

## **7️⃣ Wrong Downcasting**

### **Question**

```java
A a = new A();
B b = (B)a;
b.show();
```

### **Answer**

```
ClassCastException
```

---

## **8️⃣ Abstract Class Constructor Gets Executed**

### **Question**

```java
abstract class A { A(){System.out.println("A");} }
class B extends A { B(){System.out.println("B");} }

new B();
```

### **Answer**

```
A
B
```

---

## **9️⃣ Order of Static & Instance Blocks**

### **Question**

```java
class A {
    static { System.out.println("A static"); }
    { System.out.println("A instance"); }
}

class B extends A {
    static { System.out.println("B static"); }
    { System.out.println("B instance"); }
}

new B();
```

### **Answer**

```
A static
B static
A instance
B instance
```

---

## **🔟 Overload Resolution with Primitives**

### **Question**

```java
void test(int x){System.out.println("int");}
void test(long x){System.out.println("long");}
void test(Integer x){System.out.println("Integer");}

test(5);
```

### **Answer**

```
int
```

---

## **1️⃣1️⃣ Interface Diamond Conflict**

### **Question**

Two interfaces have same default method.

### **Answer**

❌ Must override in the implementing class.

---

## **1️⃣2️⃣ Covariant Return Types**

### **Question**

```java
class A { A test(){return this;} }
class B extends A { B test(){return this;} }
```

### **Answer**

✔ Allowed.
Return type can be a subclass.

---

## **1️⃣3️⃣ Anonymous Inner Class Override**

### **Question**

```java
A a = new A() {
   void show(){System.out.println("Inside");}
};
a.show();
```

### **Answer**

```
Inside
```

---

## **1️⃣4️⃣ Private Methods Are Not Overridden**

### **Question**

```java
class A { private void show(){} }
class B extends A { private void show(){} }

A a = new B();
a.show();
```

### **Answer**

❌ Compile error
Private methods are not visible outside class.

---

## **1️⃣5️⃣ Variable Shadowing**

### **Question**

```java
class A { int x=10; }
class B extends A { int x=20; }

A a = new B();
System.out.println(a.x);
```

### **Answer**

```
10
```

Variables do not support polymorphism.

---

## **1️⃣6️⃣ Overridden Method Called Inside Constructor**

### **Question**

```java
class A {
    A(){ show(); }
    void show(){ System.out.println("A"); }
}

class B extends A {
    int x = 10;
    void show(){ System.out.println(x); }
}

new B();
```

### **Answer**

```
0
```

B.x is not initialized yet during call from A().

---

## **1️⃣7️⃣ Static Block Executes Without main()**

### **Question**

```java
class Test {
    static {
        System.out.println("Hi");
        System.exit(0);
    }
}
```

### **Answer**

```
Hi
```

---

## **1️⃣8️⃣ Does clone() call constructor?**

### **Answer**

❌ No
clone() allocates memory without constructor.

---

## **1️⃣9️⃣ Overriding and Exceptions**

### **Question**

Parent throws `Exception`, child throws `RuntimeException`.

### **Answer**

✔ Legal
Unchecked exceptions are allowed.

---

## **2️⃣0️⃣ Fields Do Not Participate in Polymorphism**

### **Question**

```java
A a = new B();
System.out.println(a.value);
```

### **Answer**

Parent's field is used.

---

## **2️⃣1️⃣ Two Unrelated Classes, Same Method Signature**

### **Answer**

✔ Compiles
❌ No polymorphism.

---

## **2️⃣2️⃣ Overriding in Inner Class**

### **Question**

```java
class A { void show(){} }
class B {
   class Inner extends A { void show(){} }
}
```

### **Answer**

✔ Valid.

---

## **2️⃣3️⃣ Abstract Class with All Abstract Methods — When Needed?**

### **Answer**

* Need constructor
* Need fields
* Want partial implementation in future

---

## **2️⃣4️⃣ Constructor Chaining + Inheritance**

### **Question**

```java
class A { A(){System.out.println("A");} }
class B extends A {
   B(){ this(10); System.out.println("B"); }
   B(int x){ System.out.println("B-int"); }
}

new B();
```

### **Answer**

```
A
B-int
B
```

---

## **2️⃣5️⃣ Composition vs Inheritance Breakage**

### **Answer**

Inheritance breaks if parent logic changes;
Composition does not break.

---

## **2️⃣6️⃣ Interface Fields are final**

### **Question**

```java
interface A { int x = 10; }
A.x = 20;
```

### **Answer**

❌ Compile error
Interface fields are `public static final`.

---

## **2️⃣7️⃣ Can enum extend another enum?**

### **Answer**

❌ No
All enums extend `java.lang.Enum`.

---

## **2️⃣8️⃣ super() in Multi-Level Inheritance**

### **Answer**

Always calls parent constructor first.

---

## **2️⃣9️⃣ Constructor Calling this() + super() Together**

### **Question**

```java
B(){ super(); this(10); }
```
Here is a **clean Java example** showing the exact chain:

```
B()  →  this(10) → B(int) → super() is called automatically
```

---

# ✅ **Example Showing Constructor Chaining → this() → super()**

```java
class A {
    A() {
        System.out.println("A constructor");
    }
}

class B extends A {

    B() {                  // (1)
        this(10);          // calls B(int) first
        System.out.println("B() constructor");
    }

    B(int x) {             // (2)
        // super() is inserted here automatically by Java
        System.out.println("B(int) constructor with x = " + x);
    }
}

public class Test {
    public static void main(String[] args) {
        new B();           // start here
    }
}
```

---

# ✅ **OUTPUT**

```
A constructor
B(int) constructor with x = 10
B() constructor
```

---

# 🔥 **Understanding the Chain Step-by-Step**

### Step 1: `new B()`

Calls:

```
B()
```

### Step 2: Inside B()

First line is:

```
this(10)
```

So now it calls:

```
B(int)
```

### Step 3: Inside B(int)

There is **no explicit super()**, so Java automatically inserts:

```
super();
```

So it calls A's constructor:

```
A constructor
```

Then continues:

```
B(int) constructor with x = 10
```

### Step 4: Return to original B()

Now prints:

```
B() constructor
```

---

# ⭐ Final Execution Flow

```
B() 
   → this(10)
       → B(int)
           → super()   (added automatically)
               → A()
           → B(int) body
   → B() body
```

---


### **Answer**

❌ Compile error
First statement must be either super() or this().

---

## **3️⃣0️⃣ Runtime Polymorphism with Collections**

### **Question**

```java
List<String> list = new ArrayList<>();
```

### **Answer**

* Reference type (List) → allowed methods
* Object type (ArrayList) → actual implementation

---

# ✅ Want More?

I can give you:

🔥 **50 more advanced OOP tricky questions**
📌 **A complete Java OOP interview handbook (.md format)**
📚 **Design patterns tricky questions with Java examples**

Just tell me!
