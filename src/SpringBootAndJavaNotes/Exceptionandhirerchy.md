# Java Exception Handling: Checked vs Unchecked Exceptions

## 1. Understanding the exception example

``` java
public class SnippetException {

    static void test() throws RuntimeException {
        try {
            System.out.print("test ");
            throw new RuntimeException();
        } catch (Exception ex) {
            System.out.print("exception ");
        }
    }

    public static void main(String[] args) {
        try {
            test();
        } catch (RuntimeException ex) {
            System.out.print("runtime ");
        }

        System.out.print("end ");
    }
}
```

**Output:**

``` text
test exception end
```

### Step-by-step execution

1.  `main()` calls `test()`.
2.  `test()` prints `test`.
3.  `throw new RuntimeException()` throws an unchecked exception.
4.  `catch (Exception ex)` catches it because `RuntimeException` extends
    `Exception`.
5.  The catch block prints `exception`.
6.  The exception has been handled inside `test()`, so it does not
    propagate to `main()`.
7.  `main()` continues and prints `end`.

**Key point:** `throws RuntimeException` does not force the caller to
catch it. Runtime exceptions are unchecked.

------------------------------------------------------------------------

## 2. Does a caught exception propagate to the calling method?

**No. Once an exception is caught and handled, it does not automatically
propagate to the caller.**

### Case A: Caught inside the method

``` java
public class Main {

    static void test() {
        try {
            throw new RuntimeException("Error");
        } catch (Exception e) {
            System.out.println("Caught in test()");
        }
    }

    public static void main(String[] args) {
        try {
            test();
        } catch (RuntimeException e) {
            System.out.println("Caught in main()");
        }

        System.out.println("End");
    }
}
```

**Output:**

``` text
Caught in test()
End
```

Why?

1.  `test()` throws `RuntimeException`.
2.  `catch (Exception e)` catches it.
3.  The exception is handled inside `test()`.
4.  Control returns to `main()`.
5.  Main's catch block is skipped.

### Case B: Catch and rethrow

``` java
public class Main {

    static void test() {
        try {
            throw new RuntimeException("Error");
        } catch (Exception e) {
            System.out.println("Caught in test()");
            throw e;
        }
    }

    public static void main(String[] args) {
        try {
            test();
        } catch (RuntimeException e) {
            System.out.println("Caught in main()");
        }
    }
}
```

**Output:**

``` text
Caught in test()
Caught in main()
```

The exception is caught locally and then explicitly thrown again, so it
propagates to `main()`.

### Remember

-   Exception caught and not rethrown → propagation stops.
-   Exception not caught → propagates to the caller.
-   Exception caught and rethrown → propagates to the caller again.

------------------------------------------------------------------------

## 3. Checked vs unchecked exceptions

The main difference is whether Java requires the programmer to handle or
declare the exception at compile time.

  ------------------------------------------------------------------------------------
Feature                 Checked exception         Unchecked exception
  ----------------------- ------------------------- ----------------------------------
Checked by compiler     Yes                       No

Must catch or declare?  Yes                       No

Class hierarchy         `Exception` subclasses,   `RuntimeException` and its
excluding                 subclasses
`RuntimeException` and    
its subclasses

Typical cause           External or recoverable   Programming errors or invalid
conditions                operations

Examples                `IOException`,            `NullPointerException`,
`SQLException`,           `ArithmeticException`,
`FileNotFoundException`   `ArrayIndexOutOfBoundsException`
  ------------------------------------------------------------------------------------

### Checked exception example

``` java
import java.io.*;

public class CheckedExample {
    public static void main(String[] args) {
        try {
            FileReader reader = new FileReader("data.txt");
            reader.close();
        } catch (IOException e) {
            System.out.println("File error: " + e.getMessage());
        }
    }
}
```

`FileReader` can throw `FileNotFoundException`, which is a checked
exception. The compiler requires it to be caught or declared with
`throws`.

You can also declare it:

``` java
static void readFile() throws IOException {
    FileReader reader = new FileReader("data.txt");
    reader.close();
}
```

The caller must then catch or declare the checked exception.

### Unchecked exception example

``` java
public class UncheckedExample {
    public static void main(String[] args) {
        int result = 10 / 0;
        System.out.println(result);
    }
}
```

This throws `ArithmeticException` at runtime. The compiler does not
require a try-catch or `throws` declaration.

------------------------------------------------------------------------

## 4. Exception hierarchy

``` text
Object
  |
Throwable
  |
  +-- Error (unchecked)
  |     +-- OutOfMemoryError
  |     +-- StackOverflowError
  |
  +-- Exception
        |
        +-- Checked exceptions
        |     +-- IOException
        |     +-- SQLException
        |     +-- FileNotFoundException
        |
        +-- RuntimeException (unchecked)
              +-- NullPointerException
              +-- ArithmeticException
              +-- IllegalArgumentException
              +-- IndexOutOfBoundsException
```

**Important:** `Error` is not a subclass of `Exception`. Both `Error`
and `Exception` extend `Throwable`. Errors generally indicate serious
JVM or resource problems.

------------------------------------------------------------------------

## 5. When does StackOverflowError occur?

A `StackOverflowError` occurs when a thread's call stack runs out of
space. The most common cause is deep or infinite recursion.

### Example: Infinite recursion

``` java
public class StackOverflowExample {

    static void call() {
        call(); // No base condition: keeps calling itself
    }

    public static void main(String[] args) {
        call();
    }
}
```

Each method call creates a new stack frame. Because `call()` never stops
calling itself, stack frames keep accumulating until the thread's stack
is exhausted.

**Typical result:**

``` text
Exception in thread "main" java.lang.StackOverflowError
```

### Other possible causes

-   Recursion with an incorrect or missing base condition.
-   Very deep recursion even when it eventually has a base condition.
-   Circular method calls, such as `methodA()` calling `methodB()` and
    `methodB()` calling `methodA()`.

### How to prevent it

-   Ensure recursive methods have correct base conditions.
-   Reduce recursion depth.
-   Use iteration or an explicit stack for very deep traversals.
-   Investigate circular method calls.

**Interview point:** StackOverflowError is generally related to
excessive call-stack usage, not Java heap memory.

------------------------------------------------------------------------

## 6. When does OutOfMemoryError occur?

An `OutOfMemoryError` occurs when the JVM cannot allocate memory for an
object or another required JVM operation, even after attempting garbage
collection where applicable.

### Example: Continuously allocating objects

``` java
import java.util.ArrayList;
import java.util.List;

public class OutOfMemoryExample {

    public static void main(String[] args) {
        List<byte[]> list = new ArrayList<>();

        while (true) {
            // Retain each allocation so it cannot be garbage-collected
            list.add(new byte[10 * 1024 * 1024]);
        }
    }
}
```

Each iteration allocates approximately 10 MB and stores the reference in
the list. Since the list retains those arrays, they remain reachable and
cannot be garbage-collected. Eventually, the JVM may run out of heap
space.

**Typical result:**

``` text
java.lang.OutOfMemoryError: Java heap space
```

Do not run this example in a production environment.

### Common causes

-   Memory leaks: objects are no longer useful but remain referenced.
-   Loading huge files entirely into memory.
-   Keeping too much data in collections or caches.
-   Creating too many objects.
-   Too many threads or native-memory exhaustion.
-   Insufficient JVM memory limits for the workload.

### Common OutOfMemoryError messages

  -----------------------------------------------------------------------
Message                             Meaning
  ----------------------------------- -----------------------------------
`Java heap space`                   JVM cannot allocate an object in
the heap

`GC overhead limit exceeded`        Too much time spent garbage
collecting with little memory
reclaimed

`Metaspace`                         Class metadata memory is exhausted

`unable to create native thread`    OS/JVM cannot create another native
thread, often due to resource
limits

`Direct buffer memory`              Direct/off-heap buffer allocation
cannot be satisfied
  -----------------------------------------------------------------------

### How to troubleshoot OutOfMemoryError

1.  Inspect heap usage and garbage collection logs.
2.  Capture a heap dump, for example with
    `-XX:+HeapDumpOnOutOfMemoryError`.
3.  Analyze the dump using tools such as Eclipse MAT or VisualVM.
4.  Identify objects retaining large amounts of memory.
5.  Fix leaks or excessive allocations.
6.  Tune heap size (`-Xms` / `-Xmx`) only after understanding the memory
    requirement.

**Interview point:** OutOfMemoryError can involve heap, metaspace,
direct/native memory, or other JVM resource limits. Increasing `-Xmx`
only helps when the exhausted resource is the Java heap and the
environment has capacity.

------------------------------------------------------------------------

## 7. StackOverflowError vs OutOfMemoryError

  -----------------------------------------------------------------------
Feature                 StackOverflowError      OutOfMemoryError
  ----------------------- ----------------------- -----------------------
Main problem            Thread call stack is    JVM cannot allocate
exhausted               required memory

Common cause            Deep/infinite recursion Excessive or retained
allocations

Memory area             Thread stack            Often heap, but can be
other JVM/native memory

Typical message         `StackOverflowError`    `Java heap space`,
`Metaspace`, etc.

Common fix              Fix recursion or reduce Find memory
call depth              retention/allocation
issue; tune resources
appropriately
  -----------------------------------------------------------------------

------------------------------------------------------------------------

## 8. Catch block ordering

A child exception must be caught before its parent exception.

Correct:

``` java
try {
    // risky operation
} catch (RuntimeException e) {
    System.out.println("Runtime exception");
} catch (Exception e) {
    System.out.println("Other exception");
}
```

Incorrect:

``` java
try {
    // risky operation
} catch (Exception e) {
    System.out.println("Exception");
} catch (RuntimeException e) {
    System.out.println("Runtime exception");
}
```

The second version does not compile because `catch (Exception)` already
catches `RuntimeException`. The later catch block is unreachable.

------------------------------------------------------------------------

## 9. `throw` vs `throws`

  -----------------------------------------------------------------------
`throw`                             `throws`
  ----------------------------------- -----------------------------------
Actually throws an exception        Declares possible exceptions in a
method signature

Used inside a method or block       Used in a method declaration

Followed by an exception object     Followed by exception class names
-----------------------------------------------------------------------

Example:

``` java
static void validate(int age) throws Exception {
    if (age < 18) {
        throw new Exception("Not eligible");
    }
}
```

-   `throws Exception`: declares that the method may pass an exception
    to its caller.
-   `throw new Exception(...)`: creates and throws the exception.

------------------------------------------------------------------------

## 10. Common interview questions

**Q1. Is `RuntimeException` checked or unchecked?**

Unchecked. It extends `Exception`, but the compiler does not require it
to be caught or declared.

**Q2. Does `catch (Exception e)` catch `RuntimeException`?**

Yes. `RuntimeException` is a subclass of `Exception`.

**Q3. Does `throws RuntimeException` force the caller to use
try-catch?**

No. Runtime exceptions are unchecked.

**Q4. Can we catch an unchecked exception?**

Yes. Unchecked means catching or declaring it is not compulsory, not
that catching it is prohibited.

**Q5. Is `Error` an exception?**

`Error` and `Exception` are both subclasses of `Throwable`. `Error` is
not a subclass of `Exception`.

**Q6. What happens if an exception is caught?**

Execution continues after the catch block, unless the catch block throws
another exception or the method exits.

**Q7. What is the difference between StackOverflowError and
OutOfMemoryError?**

StackOverflowError usually means a thread has exhausted its call stack,
often because of deep recursion. OutOfMemoryError means the JVM cannot
allocate required memory, often because the heap is exhausted or another
memory resource is unavailable.

------------------------------------------------------------------------

## 11. Quick interview summary

-   **Checked:** Compiler-enforced handling. Example: `IOException`.
-   **Unchecked:** No compiler requirement to catch or declare. Example:
    `NullPointerException`.
-   `RuntimeException` extends `Exception`.
-   `catch (Exception)` catches `RuntimeException`.
-   Catch specific exceptions before general exceptions.
-   `throw` throws an exception; `throws` declares possible exceptions.
-   A caught exception stops propagating unless it is rethrown.
-   `StackOverflowError`: call stack exhausted, commonly due to
    recursion.
-   `OutOfMemoryError`: JVM cannot allocate required memory; investigate
    heap and other memory resources.
-   `Error` is separate from `Exception`, though both extend
    `Throwable`.
