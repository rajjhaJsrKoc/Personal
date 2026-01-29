

# 🚀 Spring Boot Interview Q&A

---

## 🔹 **Basics**

**1. What is Spring Boot? How is it different from Spring Framework?**

* Spring Boot is an opinionated framework built on top of Spring that simplifies development.
* Provides **auto-configuration**, **starter dependencies**, and **embedded servers** (Tomcat, Jetty).
* Spring (core) requires lots of boilerplate XML/Java config, while Boot reduces that.

---

**2. Advantages of Spring Boot**

* Faster development (auto-config).
* Embedded servers → no need to deploy WARs.
* Opinionated starters → less dependency management.
* Actuator for monitoring.

---

**3. Convention over configuration**

* Instead of manually configuring everything, Boot uses **sensible defaults** (e.g., H2 for dev DB).

---

**4. How does auto-configuration work?**

* Based on **classpath scanning + conditions**.
* Uses `@EnableAutoConfiguration` and `spring.factories` under the hood.
* Example: If `spring-webmvc` is in classpath → Boot auto-configures DispatcherServlet.

---

**5. What is spring-boot-starter? Examples?**

* A **starter** is a curated dependency set for a particular feature.
* Examples:

    * `spring-boot-starter-web` (Spring MVC + Tomcat)
    * `spring-boot-starter-data-jpa` (Hibernate + JPA)
    * `spring-boot-starter-security`

---

**6. Difference: `spring-boot-starter-web` vs `spring-boot-starter-webflux`**

* Web → uses **Spring MVC** (Servlet-based, synchronous).
* WebFlux → uses **Reactor** (non-blocking, reactive).

---

## 🔹 **Configuration & Annotations**

**7. Difference: `@SpringBootApplication` vs `@EnableAutoConfiguration`**

* `@SpringBootApplication` = combo of:

    * `@EnableAutoConfiguration`
    * `@ComponentScan`
    * `@Configuration`

---

**8. @Component vs @Service vs @Repository vs @Controller vs @RestController**

* `@Component` → generic bean.
* `@Service` → business logic layer.
* `@Repository` → DAO layer + exception translation.
* `@Controller` → MVC controller (returns views).
* `@RestController` → `@Controller + @ResponseBody` (returns JSON/XML).

---

**9. @Configuration vs @Component**

* Both register beans, but `@Configuration` is specifically for defining **@Bean methods**.

---

**10. @Bean vs @Component**

* `@Bean` → explicitly define bean in a config class.
* `@Component` → auto-detected via scanning.

---

**11. How to configure external properties?**

* `application.properties` or `application.yml`.
* Example:

  ```yaml
  server:
    port: 8081
  ```

---

**12. @Value vs @ConfigurationProperties**

* `@Value("${key}")` → single property injection.
* `@ConfigurationProperties` → bind a whole POJO to a group of properties.

---

## 🔹 **Dependency Injection & Beans**

**13. Dependency injection in Spring Boot**

* Managed by Spring **IoC Container**.
* Uses `@Autowired`, constructor injection preferred.

---

**14. Singleton vs Prototype beans**

* Singleton → one instance per container.
* Prototype → new instance every request.

---

**15. @Primary vs @Qualifier**

* `@Primary` → default bean when multiple candidates exist.
* `@Qualifier` → explicitly pick one bean.

---

**16. Circular dependencies**

* Boot tries to resolve via proxies (constructor injection may fail).
* Can be solved using **setter injection** or `@Lazy`.

---

## 🔹 **REST & Web**

**17. Create REST API**

```java
@RestController
@RequestMapping("/users")
public class UserController {
    @GetMapping("/{id}")
    public User getUser(@PathVariable int id) { return service.find(id); }
}
```

---

**18. @Controller vs @RestController**

* `@Controller` → returns views.
* `@RestController` → returns JSON/XML.

---

**19. JSON handling**

* Boot auto-configures **Jackson**.

---

**20. Exception handling**

```java
@ControllerAdvice
class GlobalExceptionHandler {
   @ExceptionHandler(Exception.class)
   public ResponseEntity<String> handleEx(Exception e) {
       return ResponseEntity.badRequest().body(e.getMessage());
   }
}
```

---

**21. Request validation**

```java
public class User {
   @NotNull private String name;
}
@PostMapping
public void create(@Valid @RequestBody User user) { }
```

---

## 🔹 **Data Access**

**22. Spring Boot + JPA/Hibernate**

* Boot auto-configures `EntityManager`, `DataSource`, `TransactionManager`.

---

**23. Spring Data JPA**

* Simplifies JPA with repository interfaces.
* Example: `UserRepository extends JpaRepository<User, Long>`

---

**24. Custom queries**

```java
@Query("SELECT u FROM User u WHERE u.email = :email")
User findByEmail(@Param("email") String email);
```

---

**25. CrudRepository vs JpaRepository vs PagingAndSortingRepository**

* Crud → basic CRUD.
* Paging → CRUD + pagination/sorting.
* JPA → all + batch ops, flush, etc.

---

**26. Multiple datasources**

* Configure multiple `DataSource` beans and mark one as `@Primary`.

---

## 🔹 **Security**

**27. Spring Security in Boot**

* Auto-configured filters for login, auth, etc.

---

**28. JWT Authentication**

* Generate JWT on login → attach in `Authorization: Bearer <token>`.
* Validate in filter.

---

**29. Authentication vs Authorization**

* Authentication → verify identity (login).
* Authorization → check permissions.

---

**30. CSRF**

* Protection against cross-site request forgery.
* Enabled by default in Spring Security (disable for stateless REST APIs).

---

## 🔹 **Actuator & Monitoring**

**31. What is Actuator?**

* Provides health, metrics, info endpoints.

---

**32. Secure actuator endpoints**

```yaml
management.endpoints.web.exposure.include=health,info
management.security.enabled=true
```

---

**33. Monitoring integration**

* Actuator + Micrometer → Prometheus → Grafana dashboards.

---

## 🔹 **Advanced**

**34. ApplicationContext**

* Central Spring IoC container holding beans.

---

**35. CommandLineRunner vs ApplicationRunner**

* Both run code at startup.
* Difference → ApplicationRunner gives access to ApplicationArguments.

---

**36. Async processing**

```java
@EnableAsync
@Async
public void sendEmail() { }
```

---

**37. Kafka / RabbitMQ**

* Boot has `spring-kafka` & `spring-boot-starter-amqp`.

---

**38. Transactions**

```java
@Transactional
public void updateUser(User u) { ... }
```

---

**39. Caching**

```java
@EnableCaching
@Cacheable("users")
public User findById(Long id) { ... }
```

---

**40. Docker/K8s deployment**

* Build JAR → package into Docker image → deploy in Kubernetes with scaling.

---

## 🔹 **Scenario-Based**

**41. Debug auto-config**

* Use `--debug` flag → shows condition reports.

---

**42. API Versioning**

* URI: `/api/v1/users`
* Header-based: `X-API-VERSION: 1`

---

**43. Rate limiting**

* Use Redis + Bucket4J / Guava RateLimiter.

---

**44. HA microservice design**

* Use load balancer, service registry (Eureka/Consul), circuit breakers, Kafka for async.

---

**45. Monolithic vs Microservices**

* Monolith → single deployable.
* Microservices → independent services with APIs.

Got it, Rajat 👍
Here are **clear, interview-ready answers** for **each question**, explained **simply + with Spring Boot context** (Java / microservices friendly).

---

## 🌱 **Basics**

### **1. What is Spring Boot?**

Spring Boot is an extension of the Spring Framework that **simplifies building production-ready applications** by providing:

* Auto-configuration
* Embedded servers (Tomcat/Jetty)
* Opinionated defaults
* Minimal XML / boilerplate

👉 You can run a Spring Boot app using just `main()`.

---

### **2. Difference between Spring and Spring Boot**

| Spring                        | Spring Boot          |
| ----------------------------- | -------------------- |
| Requires manual configuration | Auto-configuration   |
| Needs external server         | Embedded server      |
| More boilerplate              | Minimal setup        |
| No defaults                   | Opinionated defaults |

---

### **3. What is `@SpringBootApplication`?**

It is a **meta-annotation** combining:

```java
@SpringBootConfiguration
@EnableAutoConfiguration
@ComponentScan
```

👉 It marks the **main entry point** of a Spring Boot app.

---

### **4. What are Spring Boot starters?**

Starters are **predefined dependency bundles** that simplify Maven/Gradle configuration.

Example:

```xml
spring-boot-starter-web
```

Includes:

* Spring MVC
* Jackson
* Embedded Tomcat

---

### **5. How does Spring Boot auto-configuration work?**

Spring Boot:

1. Reads classpath dependencies
2. Applies conditional configurations using `@ConditionalOnClass`
3. Creates beans automatically

Example:

* If `spring-webmvc` exists → MVC auto-configured

---

### **6. Difference between `@Controller` and `@RestController`**

| @Controller             | @RestController          |
| ----------------------- | ------------------------ |
| Returns view (HTML/JSP) | Returns JSON/XML         |
| Needs `@ResponseBody`   | Includes `@ResponseBody` |
| Used in MVC apps        | Used in REST APIs        |

---

### **7. What is Dependency Injection (DI)?**

DI is a design principle where **objects are provided dependencies instead of creating them**.

Example:

```java
@Autowired
private UserService userService;
```

👉 Improves testability and loose coupling.

---

### **8. What is Inversion of Control (IoC)?**

IoC means **Spring manages object creation and lifecycle**, not the developer.

* You define beans
* Spring injects them

DI is a **type of IoC**.

---

### **9. What is `@Bean` annotation?**

Used to define a **Spring-managed bean** manually.

```java
@Bean
public ObjectMapper objectMapper() {
    return new ObjectMapper();
}
```

---

### **10. What is `@Configuration` annotation?**

Marks a class as a **source of bean definitions**.

```java
@Configuration
public class AppConfig {
}
```

👉 Works like XML config but in Java.

---

## 🌐 **REST & APIs**

### **11. How to create a REST API in Spring Boot?**

Steps:

1. Add `spring-boot-starter-web`
2. Create a controller
3. Map endpoints

```java
@RestController
@RequestMapping("/api/users")
public class UserController {
    @GetMapping
    public List<User> getUsers() {
        return userService.getAllUsers();
    }
}
```

---

### **12. Difference between GET, POST, PUT, DELETE**

| Method | Purpose                |
| ------ | ---------------------- |
| GET    | Fetch data             |
| POST   | Create resource        |
| PUT    | Update entire resource |
| DELETE | Remove resource        |

---

### **13. `@RequestMapping` vs `@GetMapping`**

| @RequestMapping                 | @GetMapping     |
| ------------------------------- | --------------- |
| Generic                         | Specific to GET |
| Verb needs explicit declaration | Cleaner         |
| Older style                     | Preferred       |

```java
@GetMapping("/users")
```

---

### **14. How does Spring Boot handle JSON serialization?**

Spring Boot uses **Jackson** by default.

* Converts Java objects ↔ JSON automatically
* Uses `HttpMessageConverters`

No extra configuration needed.

---

### **15. What is `ResponseEntity`?**

It represents the **entire HTTP response**.

```java
return ResponseEntity
       .status(HttpStatus.CREATED)
       .body(user);
```

Allows:

* Status code
* Headers
* Body

---

### **16. `@PathVariable` vs `@RequestParam`**

| PathVariable     | RequestParam    |
| ---------------- | --------------- |
| Part of URL path | Query parameter |
| Mandatory        | Optional        |
| RESTful          | Filtering       |

```java
/users/{id}
```

vs

```java
/users?id=10
```

---

### **17. What is `@RequestBody`?**

Binds incoming JSON request body to a Java object.

```java
@PostMapping
public User save(@RequestBody User user) {
}
```

---

### **18. Exception handling in REST APIs (`@ControllerAdvice`)**

Global exception handling across controllers.

```java
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handle() {
        return ResponseEntity.status(404).body("Not Found");
    }
}
```

---

### **19. What is HATEOAS in Spring Boot?**

HATEOAS = **Hypermedia As The Engine Of Application State**

* API responses contain **links**
* Helps clients navigate dynamically

Example:

```json
{
  "id": 1,
  "links": [
    { "rel": "self", "href": "/users/1" }
  ]
}
```

---

### **20. What is CORS and how to configure it?**

CORS allows **cross-origin requests** (frontend ↔ backend).

#### Method level:

```java
@CrossOrigin(origins = "http://localhost:3000")
```

#### Global:

```java
@Bean
public WebMvcConfigurer corsConfigurer() {
    return new WebMvcConfigurer() {
        public void addCorsMappings(CorsRegistry registry) {
            registry.addMapping("/**").allowedOrigins("*");
        }
    };
}
```

---

If you want, I can:

* 🔥 Convert this into **quick one-liner answers**
* 📌 Add **real interview follow-up questions**
* 🧠 Map these to **microservices scenarios**
* 📝 Create a **PDF / notes format**

Just tell me 👌

---
