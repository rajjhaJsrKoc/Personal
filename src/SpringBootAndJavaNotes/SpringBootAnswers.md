

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

---
