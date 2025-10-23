Here’s a clean and well-structured **README.md** you can use to understand and demonstrate how to use **Mockito Spy**, and how to **mock data in Spring Boot tests**.

---

## 🧪 Mockito Spy & Mocking Data in Spring Boot

### 📘 Overview

This guide explains how to:

1. Use **Mockito Spy** to partially mock real objects.
2. Mock dependencies and data in **Spring Boot tests**.
3. Combine **JUnit 5**, **Mockito**, and **Spring Boot Test** for integration and unit testing.

---

## ⚙️ Prerequisites

Add these dependencies to your `pom.xml` (for Maven):

```xml
<dependencies>
    <!-- Spring Boot Test -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-test</artifactId>
        <scope>test</scope>
    </dependency>

    <!-- Mockito Core (optional, comes with spring-boot-starter-test) -->
    <dependency>
        <groupId>org.mockito</groupId>
        <artifactId>mockito-core</artifactId>
        <scope>test</scope>
    </dependency>
</dependencies>
```

---

## 🧩 What’s the Difference Between `@Mock` and `@Spy`?

| Annotation | Description                                          | Behavior                                                |
| ---------- | ---------------------------------------------------- | ------------------------------------------------------- |
| `@Mock`    | Creates a *fake* object with no real logic.          | Returns default values (null, 0, false) unless stubbed. |
| `@Spy`     | Wraps a *real* object but allows selective stubbing. | Executes real methods unless explicitly stubbed.        |

---

## 🧠 Example 1: Using `@Mock` and `@InjectMocks`

### ✅ Scenario

You want to test a `UserService` that depends on a `UserRepository`.

### 🧱 Code Example

**UserService.java**

```java
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public String getUserEmail(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        return user.getEmail();
    }
}
```

**UserRepository.java**

```java
@Repository
public interface UserRepository extends JpaRepository<User, Long> { }
```

**UserServiceTest.java**

```java
@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void testGetUserEmail() {
        User mockUser = new User(1L, "rajat@example.com");

        when(userRepository.findById(1L)).thenReturn(Optional.of(mockUser));

        String result = userService.getUserEmail(1L);

        assertEquals("rajat@example.com", result);
        verify(userRepository, times(1)).findById(1L);
    }
}
```

---

## 🕵️ Example 2: Using `@Spy`

### ✅ Scenario

You want to **partially mock** behavior — for instance, testing logic but bypassing one specific method call.

### 🧱 Code Example

**Calculator.java**

```java
public class Calculator {
    public int add(int a, int b) {
        return a + b;
    }

    public int compute(int a, int b) {
        return add(a, b) * 2;
    }
}
```

**CalculatorTest.java**

```java
@ExtendWith(MockitoExtension.class)
class CalculatorTest {

    @Spy
    private Calculator calculator;

    @Test
    void testComputeWithSpy() {
        // Real method add() will be called
        assertEquals(10, calculator.compute(2, 3));

        // Stub the add() method
        when(calculator.add(2, 3)).thenReturn(100);

        // Now compute() will use the stubbed value
        assertEquals(200, calculator.compute(2, 3));

        // Verify interaction
        verify(calculator, times(2)).add(2, 3);
    }
}
```

---

## 🌱 Mocking Data in Spring Boot Tests

If you’re using **Spring Boot** with **repositories** or **services**, you can use:

* `@MockBean` — replaces the bean in the Spring context with a Mockito mock.
* `@DataJpaTest` — for JPA repository layer tests with an in-memory database.
* `@SpringBootTest` — for full application context tests.

### 🧱 Example

**UserController.java**

```java
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<String> getUserEmail(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserEmail(id));
    }
}
```

**UserControllerTest.java**

```java
@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    void testGetUserEmail() throws Exception {
        when(userService.getUserEmail(1L)).thenReturn("rajat@example.com");

        mockMvc.perform(get("/users/1"))
               .andExpect(status().isOk())
               .andExpect(content().string("rajat@example.com"));
    }
}
```

---

## 💡 Tips

* Use `@MockBean` for Spring-managed beans.
* Use `@SpyBean` if you want to call real methods but stub specific ones.
* Combine `@DataJpaTest` with H2 DB for repository tests.
* Always verify behavior using `verify()` in Mockito.

---

## 🧾 Summary

| Use Case                       | Annotation  | Example                                         |
| ------------------------------ | ----------- | ----------------------------------------------- |
| Mock simple dependencies       | `@Mock`     | Unit tests with `@InjectMocks`                  |
| Partial mocking                | `@Spy`      | Partially override real behavior                |
| Replace bean in Spring context | `@MockBean` | Mock service or repository in controller tests  |
| Spy on Spring-managed bean     | `@SpyBean`  | Partially mock a real bean in integration tests |

---
