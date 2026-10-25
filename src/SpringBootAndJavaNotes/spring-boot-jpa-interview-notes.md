# Spring Boot REST API, Spring Data JPA, and Transactions — Interview Notes

Practical interview notes with Java examples for Spring Boot, Spring MVC, Spring Data JPA, Hibernate, caching, locking, and transaction management.

## Table of Contents

1. [REST API and Spring MVC](#1-rest-api-and-spring-mvc)
   - [`@RequestMapping` vs `@GetMapping`](#11-requestmapping-vs-getmapping)
   - [JSON serialization](#12-how-spring-boot-handles-json-serialization)
   - [`ResponseEntity`](#13-what-is-responseentity)
   - [`@PathVariable` vs `@RequestParam`](#14-pathvariable-vs-requestparam)
   - [`@RequestBody`](#15-what-is-requestbody)
   - [Global exception handling](#16-exception-handling-with-controlleradvice)
2. [Spring Data JPA](#2-spring-data-jpa)
   - [Spring Data JPA](#21-what-is-spring-data-jpa)
   - [JPA vs Hibernate](#22-jpa-vs-hibernate)
   - [Repositories](#23-what-are-repositories)
   - [Repository interfaces](#24-crudrepository-vs-pagingandsortingrepository-vs-jparepository)
   - [Multiple data sources](#25-configuring-multiple-data-sources)
   - [Pagination](#26-pagination)
   - [Caching](#27-caching)
   - [Optimistic vs pessimistic locking](#28-optimistic-vs-pessimistic-locking)
   - [`save()` vs `saveAndFlush()`](#29-save-vs-saveandflush)
   - [JPQL vs native SQL](#210-jpql-vs-native-sql)
   - [Flush vs commit](#211-flush-vs-commit)
3. [Transactions and JPA performance](#3-transactions-and-jpa-performance)
   - [Propagation](#31-transaction-propagation-required-vs-requires_new)
   - [N+1 query problem](#32-the-n1-query-problem)
   - [Lazy vs eager loading](#33-lazy-vs-eager-loading)
   - [First-level vs second-level cache](#34-first-level-vs-second-level-cache)
   - [How `@Transactional` works internally](#35-how-transactional-works-internally)
4. [Quick revision](#4-quick-revision)

---

# 1. REST API and Spring MVC

## 1.1 `@RequestMapping` vs `@GetMapping`

Both annotations map HTTP requests to controller methods.

- `@RequestMapping` can map different HTTP methods, such as GET, POST, PUT, and DELETE.
- `@GetMapping` is a shortcut specifically for HTTP GET requests.

```java
@RestController
@RequestMapping("/users")
public class UserController {

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String getUser(@PathVariable Long id) {
        return "User ID: " + id;
    }

    @PostMapping
    public String createUser() {
        return "User created";
    }
}
```

**Interview point:** Use `@GetMapping`, `@PostMapping`, `@PutMapping`, and `@DeleteMapping` for readability and to make the HTTP method explicit. Class-level `@RequestMapping` is commonly used to define a shared URL prefix.

## 1.2 How Spring Boot handles JSON serialization

**Serialization** converts a Java object into JSON. **Deserialization** converts JSON into a Java object.

In a typical Spring MVC application, Spring Boot uses Jackson when it is on the classpath.

### Serialization flow

1. A controller returns a Java object.
2. Spring MVC selects a suitable `HttpMessageConverter`.
3. Jackson converts the object to JSON.
4. Spring sends the response, usually with `Content-Type: application/json`.

```java
@RestController
public class UserController {

    @GetMapping("/user")
    public User getUser() {
        return new User(1L, "Rajat");
    }
}

class User {
    private Long id;
    private String name;

    public User(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
```

Response:

```json
{
  "id": 1,
  "name": "Rajat"
}
```

**Interview point:** `@RestController` combines `@Controller` and `@ResponseBody`, so returned objects are written to the HTTP response body rather than treated as view names.

## 1.3 What is `ResponseEntity`?

`ResponseEntity<T>` represents an HTTP response and lets you control the response body, status code, and headers.

```java
@GetMapping("/users/{id}")
public ResponseEntity<String> getUser(@PathVariable Long id) {
    if (id == 1) {
        return ResponseEntity.ok("User found");
    }

    return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body("User not found");
}
```

Common methods:

| Method | Typical status |
|---|---|
| `ResponseEntity.ok(body)` | 200 OK |
| `ResponseEntity.status(HttpStatus.CREATED).body(body)` | 201 Created |
| `ResponseEntity.badRequest().body(body)` | 400 Bad Request |
| `ResponseEntity.notFound().build()` | 404 Not Found |
| `ResponseEntity.noContent().build()` | 204 No Content |

**Interview point:** Use `ResponseEntity` when the endpoint needs explicit control over the status code or headers. Its body can be a Java object that Spring serializes to JSON.

## 1.4 `@PathVariable` vs `@RequestParam`

Both extract values from an HTTP request, but from different parts of the URL.

| Feature | `@PathVariable` | `@RequestParam` |
|---|---|---|
| Source | URL path | Query parameter |
| Example | `/users/10` | `/users?id=10` |
| Typical use | Identify a resource | Filter, search, sort, pagination |
| Required by default | Yes | Yes, unless configured otherwise |

```java
@RestController
@RequestMapping("/users")
public class UserController {

    // GET /users/10
    @GetMapping("/{id}")
    public String getUser(@PathVariable Long id) {
        return "User ID: " + id;
    }

    // GET /users?city=Bangalore
    @GetMapping
    public String getUsersByCity(@RequestParam String city) {
        return "Users from " + city;
    }
}
```

An optional query parameter can be declared as:

```java
@RequestParam(required = false) String city
```

**Interview point:** Use a path variable to identify a specific resource; use query parameters for filters, sorting, pagination, and search criteria.

## 1.5 What is `@RequestBody`?

`@RequestBody` tells Spring to read the HTTP request body and convert it into a Java object using an appropriate `HttpMessageConverter`, typically Jackson for JSON.

Example request:

```http
POST /users
Content-Type: application/json
```

```json
{
  "id": 1,
  "name": "Rajat"
}
```

Controller:

```java
@PostMapping("/users")
public ResponseEntity<String> createUser(@RequestBody User user) {
    return ResponseEntity.status(HttpStatus.CREATED)
            .body("Created user: " + user.getName());
}
```

Spring deserializes the JSON into a `User` object.

Use `@Valid` with `@RequestBody` to trigger Bean Validation when the DTO has validation constraints:

```java
@PostMapping("/users")
public ResponseEntity<String> createUser(
        @Valid @RequestBody CreateUserRequest request) {
    return ResponseEntity.status(HttpStatus.CREATED).body("Created");
}
```

**Interview point:** `@RequestBody` is common for POST, PUT, and PATCH requests. It is distinct from `@RequestParam`, which reads request parameters.

## 1.6 Exception handling with `@ControllerAdvice`

`@ControllerAdvice` supports centralized exception handling across controllers. For REST APIs, `@RestControllerAdvice` is often convenient because it combines `@ControllerAdvice` and `@ResponseBody`.

```java
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleNotFound(
            ResourceNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGeneralException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Something went wrong");
    }
}
```

Custom exception:

```java
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
```

A service can throw it when a resource is missing:

```java
public User getUser(Long id) {
    return userRepository.findById(id)
            .orElseThrow(() ->
                    new ResourceNotFoundException("User not found"));
}
```

**Interview point:** Global exception handling avoids duplicated `try-catch` logic and helps return consistent error responses. In production, use a structured error DTO and avoid exposing internal exception details to clients.

---

# 2. Spring Data JPA

## 2.1 What is Spring Data JPA?

Spring Data JPA simplifies database access by providing repository abstractions, reducing boilerplate CRUD code.

It works on top of JPA and commonly uses Hibernate as its ORM implementation.

### Typical flow

```text
Controller
    |
    v
Service
    |
    v
Spring Data JPA Repository
    |
    v
JPA Provider (e.g., Hibernate)
    |
    v
Database (e.g., MySQL)
```

Entity example:

```java
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    // Constructors, getters, and setters
}
```

Repository:

```java
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByName(String name);
}
```

Spring Data JPA creates a repository implementation at runtime. It can derive a query from `findByName()`.

**Interview point:** Spring Data JPA reduces boilerplate and supports CRUD, derived queries, pagination, sorting, and custom queries.

## 2.2 JPA vs Hibernate

**JPA is a specification; Hibernate is an implementation of that specification.**

| JPA | Hibernate |
|---|---|
| Defines standard ORM APIs and behavior | Implements ORM functionality |
| Jakarta Persistence specification | ORM framework and JPA provider |
| Includes annotations such as `@Entity` and `@Id` | Adds Hibernate-specific features and APIs |
| Allows use of different compliant providers | One of the available providers |

When Hibernate is configured as the JPA provider, it handles object-relational mapping, SQL generation, and entity lifecycle operations.

**Remember:** Spring Data JPA = repository abstraction; JPA = specification; Hibernate = implementation.

## 2.3 What are repositories?

Repositories abstract database access. Spring creates their implementations, so application code can use database operations without writing repetitive DAO code.

```java
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    List<Employee> findByDepartment(String department);

    Optional<Employee> findByEmail(String email);
}
```

Common methods include:

- `save(entity)` — persist a new entity or merge an existing detached entity.
- `findById(id)` — retrieve by ID.
- `findAll()` — retrieve all entities.
- `deleteById(id)` — delete by ID.
- `existsById(id)` — check whether an entity exists.

**Interview point:** Repositories separate persistence operations from business logic and integrate with Spring's dependency injection and transaction management.

## 2.4 `CrudRepository` vs `PagingAndSortingRepository` vs `JpaRepository`

| Interface | Main features |
|---|---|
| `CrudRepository` | Basic CRUD operations |
| `PagingAndSortingRepository` | Pagination and sorting |
| `JpaRepository` | CRUD, paging/sorting integration, and JPA-specific methods such as flushing |

Example:

```java
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
```

Methods include:

```java
repository.save(employee);
repository.findById(1L);
repository.findAll();
repository.flush();
repository.saveAndFlush(employee);
repository.deleteById(1L);
```

**Version detail:** In Spring Data 3.x and later, `PagingAndSortingRepository` no longer extends `CrudRepository`. To get both sets of operations, extend both interfaces, or use `JpaRepository` for JPA applications.

**Interview point:** `JpaRepository` is a common choice in Spring Boot JPA applications because it combines the operations most applications need.

## 2.5 Configuring multiple data sources

Multiple data sources are useful when an application connects to separate databases, such as one for transactional data and another for reporting.

Typical steps:

1. Configure properties for each database.
2. Define a `DataSource` bean for each database.
3. Configure an `EntityManagerFactory` for each JPA persistence unit.
4. Configure a transaction manager for each database.
5. Associate each repository package with the appropriate entity manager and transaction manager using `@EnableJpaRepositories`.

Example properties:

```properties
app.datasource.primary.url=jdbc:mysql://localhost:3306/orders
app.datasource.primary.username=${ORDERS_DB_USER}
app.datasource.primary.password=${ORDERS_DB_PASSWORD}

app.datasource.secondary.url=jdbc:mysql://localhost:3306/reports
app.datasource.secondary.username=${REPORTS_DB_USER}
app.datasource.secondary.password=${REPORTS_DB_PASSWORD}
```

Example configuration structure:

```java
@Configuration
@EnableJpaRepositories(
    basePackages = "com.example.orders.repository",
    entityManagerFactoryRef = "ordersEntityManagerFactory",
    transactionManagerRef = "ordersTransactionManager"
)
public class OrdersDataSourceConfig {

    // Define the orders DataSource,
    // EntityManagerFactory and TransactionManager beans.
}
```

Create a separate configuration for the reporting database with its own beans and repository package.

**Interview point:** Each JPA data source generally needs its own `DataSource`, `EntityManagerFactory`, transaction manager, and repository configuration. Cross-database atomic transactions require additional coordination and are not automatically provided by configuring two local transaction managers.

## 2.6 Pagination

Spring Data JPA supports pagination through `Pageable`, `PageRequest`, and `Page`.

Repository:

```java
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
```

Service:

```java
public Page<Employee> getEmployees(int page, int size) {
    Pageable pageable = PageRequest.of(page, size);
    return employeeRepository.findAll(pageable);
}
```

Controller:

```java
@GetMapping("/employees")
public Page<Employee> getEmployees(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size) {
    return employeeService.getEmployees(page, size);
}
```

Example request:

```http
GET /employees?page=0&size=10
```

- `page=0` is the first page.
- `size=10` requests ten records.
- `Page` contains content and metadata such as total elements and total pages.

Sorting:

```java
Pageable pageable = PageRequest.of(
        page,
        size,
        Sort.by("name").ascending()
);
```

**Interview point:** Pagination avoids loading all rows at once. Validate page size and restrict sorting to allowed fields.

## 2.7 Caching

Spring's cache abstraction provides annotations to cache method results. A provider such as Caffeine or Redis can be configured depending on the use case.

Enable caching:

```java
@Configuration
@EnableCaching
public class CacheConfig {
}
```

Example service:

```java
@Service
public class EmployeeService {

    private final EmployeeRepository repository;

    public EmployeeService(EmployeeRepository repository) {
        this.repository = repository;
    }

    @Cacheable(value = "employees", key = "#id")
    public Employee getEmployee(Long id) {
        return repository.findById(id).orElseThrow();
    }

    @CachePut(value = "employees", key = "#result.id")
    public Employee updateEmployee(Employee employee) {
        return repository.save(employee);
    }

    @CacheEvict(value = "employees", key = "#id")
    public void deleteEmployee(Long id) {
        repository.deleteById(id);
    }
}
```

| Annotation | Purpose |
|---|---|
| `@Cacheable` | Returns a cached result when present; otherwise executes and caches the result |
| `@CachePut` | Executes the method and updates the cache with its result |
| `@CacheEvict` | Removes cache entries |

By default, Spring Boot may configure a simple in-memory cache if no other provider is configured. Redis is useful when multiple application instances need to share cache entries.

**Interview point:** Consider TTL, invalidation, consistency, concurrent updates, and cache failures. Spring cache annotations usually rely on proxy interception, so self-invocation can bypass them too.

## 2.8 Optimistic vs pessimistic locking

Both approaches help prevent conflicting concurrent updates.

| Optimistic locking | Pessimistic locking |
|---|---|
| Assumes conflicts are relatively rare | Uses locks to restrict conflicting operations |
| Detects conflicting writes, commonly with a version field | Acquires a database lock |
| Uses `@Version` | Commonly uses `LockModeType.PESSIMISTIC_WRITE` |
| Suitable for low-contention updates | Useful when conflicting updates must be serialized |
| May throw an optimistic locking exception | May block or fail due to timeout or deadlock |

### Optimistic locking

```java
@Entity
public class Employee {

    @Id
    private Long id;

    @Version
    private Long version;

    private String name;
}
```

The provider uses the version to detect if another transaction updated the entity before the current update.

### Pessimistic locking

```java
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT e FROM Employee e WHERE e.id = :id")
    Optional<Employee> findByIdForUpdate(@Param("id") Long id);
}
```

Call the method within a transaction. The database lock is generally held until that transaction completes.

**Interview point:** Optimistic locking works well when conflicts are uncommon. Pessimistic locking can help when conflicts are frequent, but may reduce throughput and introduce blocking or deadlocks.

## 2.9 `save()` vs `saveAndFlush()`

Both are provided by `JpaRepository`.

- `save()` persists a new entity or merges an existing detached entity. SQL may be deferred until a flush.
- `saveAndFlush()` saves the entity and flushes pending persistence-context changes to the database before returning.

```java
Employee employee = new Employee();
employee.setName("Rajat");

repository.save(employee);
```

The SQL may execute later.

```java
repository.saveAndFlush(employee);
```

The persistence context is flushed, sending pending SQL statements to the database.

| `save()` | `saveAndFlush()` |
|---|---|
| Does not force an immediate flush | Forces a flush before returning |
| Preferred for ordinary operations | Useful when SQL must execute before continuing |
| Database errors may be deferred | Some database errors may surface earlier |

**Important:** Flush is not commit. `saveAndFlush()` does not independently commit the transaction, and the transaction can still roll back.

## 2.10 JPQL vs native SQL

JPQL queries entities and their Java attributes. Native SQL queries actual database tables and columns.

### JPQL

```java
@Query("SELECT e FROM Employee e WHERE e.department = :dept")
List<Employee> findByDepartment(@Param("dept") String department);
```

Here, `Employee` is the entity name and `department` is a Java entity attribute.

### Native SQL

```java
@Query(
    value = "SELECT * FROM employee WHERE department = :dept",
    nativeQuery = true
)
List<Employee> findByDepartmentNative(@Param("dept") String department);
```

Here, `employee` is a database table and `department` is a database column.

| JPQL | Native SQL |
|---|---|
| Uses entity names and attributes | Uses table and column names |
| Generally more database-independent | May depend on the database dialect |
| Entity-oriented query language | Database SQL |
| Suitable for standard entity queries | Useful for database-specific features or specialized queries |

**Interview point:** Prefer JPQL for portable entity-based queries. Use native SQL when needed for database-specific functionality or when the required query is not practical in JPQL.

## 2.11 Flush vs commit

**Flush sends pending persistence-context changes to the database; commit completes the transaction.**

| Flush | Commit |
|---|---|
| Synchronizes the persistence context with the database | Finalizes the transaction |
| Executes pending INSERT, UPDATE, and DELETE SQL | Makes transaction changes permanent if successful |
| Can happen multiple times in a transaction | Normally occurs at successful transaction completion |
| Does not guarantee changes are permanent | Can be followed by no further rollback of that committed transaction |

Example:

```java
@Transactional
public void createEmployee() {
    Employee employee = new Employee();
    employee.setName("Rajat");

    repository.save(employee);
    repository.flush();

    // A later failure may still cause the transaction to roll back.
}
```

Flush can occur explicitly, automatically before certain queries, or at transaction commit depending on the flush mode and provider.

**Interview point:** Flushing is not committing. If a transaction is rolled back after a flush, its database changes are normally rolled back too.

---

# 3. Transactions and JPA performance

## 3.1 Transaction propagation: `REQUIRED` vs `REQUIRES_NEW`

Propagation defines what happens when a transactional method calls another transactional method.

### `REQUIRED`

- Joins the current transaction if one exists.
- Creates a transaction if none exists.
- This is the default propagation behavior.

```java
@Transactional
public void placeOrder() {
    orderRepository.save(new Order());
    paymentService.processPayment();
}

@Transactional(propagation = Propagation.REQUIRED)
public void processPayment() {
    paymentRepository.save(new Payment());
}
```

When the call goes through the Spring proxy and both use the same transaction manager, both methods participate in the same transaction. If that transaction rolls back, both changes roll back.

### `REQUIRES_NEW`

- Always starts an independent transaction.
- Suspends the outer transaction when one exists.
- The inner transaction can commit or roll back independently.

```java
@Transactional
public void placeOrder() {
    orderRepository.save(new Order());
    auditService.recordEvent();
}

@Transactional(propagation = Propagation.REQUIRES_NEW)
public void recordEvent() {
    auditRepository.save(new AuditEvent());
}
```

If the audit transaction commits successfully but the outer order transaction later rolls back, the audit record can remain committed.

| Feature | `REQUIRED` | `REQUIRES_NEW` |
|---|---|---|
| Existing transaction | Joins it | Suspends it |
| New transaction | Only if none exists | Always |
| Rollback behavior | Shared transaction | Independent inner transaction |
| Resource usage | Usually lower | May require an additional connection |
| Example use | Business changes that must succeed together | Independent audit/status update |

**Interview point:** Use `REQUIRES_NEW` carefully. It may consume additional connections, and the inner transaction can commit even when the outer operation fails. The inner method must be invoked through the Spring proxy for the annotation to take effect.

## 3.2 The N+1 query problem

The N+1 problem occurs when the application executes one query to retrieve a list of entities, then N additional queries to retrieve related data for each entity.

Suppose there are 100 employees, each with a department:

```java
List<Employee> employees = employeeRepository.findAll();

for (Employee employee : employees) {
    System.out.println(employee.getDepartment().getName());
}
```

With lazy loading, the SQL may look like this:

```sql
-- Query 1: Fetch employees
SELECT * FROM employee;

-- Query 2: Repeated for each employee when its department is fetched
SELECT * FROM department WHERE id = ?;
```

If each employee causes a separate department query, this can mean 101 queries instead of a small number. The actual count depends on the mappings, cache, and shared associations.

### How to solve it

**Option 1: JPQL `JOIN FETCH`**

```java
@Query("SELECT e FROM Employee e JOIN FETCH e.department")
List<Employee> findAllWithDepartment();
```

For this many-to-one example, the query fetches employees and their departments together.

**Option 2: `@EntityGraph`**

```java
@EntityGraph(attributePaths = {"department"})
List<Employee> findAll();
```

**Option 3: DTO projections**

Select only the fields required by the API instead of loading entire entity graphs.

**Interview point:** Do not solve N+1 by blindly making every association eager. Choose fetch joins, entity graphs, batch fetching, or DTO projections based on the use case. Collection fetch joins need special care with pagination because they can duplicate rows.

## 3.3 Lazy vs eager loading

These settings control when JPA fetches related entities.

### Lazy loading

```java
@ManyToOne(fetch = FetchType.LAZY)
private Department department;
```

Related data is fetched when accessed, provided the persistence context can still load it.

```java
Employee employee = employeeRepository.findById(1L).orElseThrow();
System.out.println(employee.getDepartment().getName());
```

### Eager loading

```java
@ManyToOne(fetch = FetchType.EAGER)
private Department department;
```

The provider must fetch the association as part of loading the entity, but it is not required to use a SQL join.

| Lazy | Eager |
|---|---|
| Fetches related data when accessed | Requires related data to be fetched eagerly |
| Can avoid unnecessary data loading | Can load data the application does not need |
| May contribute to N+1 queries | Can also cause excessive queries or large joins |
| Unfetched access outside the persistence context may fail | Does not guarantee all nested relationships are available |

JPA defaults:

- `@OneToMany` and `@ManyToMany`: `LAZY`.
- `@ManyToOne` and `@OneToOne`: `EAGER`.

**Interview point:** Prefer deliberate fetching based on the use case. Use DTOs or fetch joins when appropriate rather than changing every association to eager.

### What is `LazyInitializationException`?

Hibernate can throw this when code accesses an unfetched lazy association after the session or persistence context has closed.

For example, a service returns an entity, the transaction ends, and JSON serialization later tries to access `employee.getDepartment()`.

Solutions include fetching the required association within the transaction, returning a DTO, or using a suitable fetch join. Do not rely on Open Session in View as a substitute for a deliberate fetch plan.

## 3.4 First-level cache vs second-level cache

### First-level cache (L1)

- Associated with the JPA persistence context / `EntityManager`.
- Always present in a normal JPA persistence context.
- Reuses managed entity instances within that context.
- Does not require a separate cache provider.

```java
Employee e1 = entityManager.find(Employee.class, 1L);
Employee e2 = entityManager.find(Employee.class, 1L);
```

Within the same persistence context, both lookups normally return the same managed entity instance, and the second lookup does not require another database fetch.

### Second-level cache (L2)

- Optional cache shared across persistence contexts within the configured persistence unit.
- Requires an appropriate provider and configuration.
- Can reduce repeated database reads across transactions.
- Requires attention to cache invalidation and consistency.

| Feature | First-level cache | Second-level cache |
|---|---|---|
| Scope | One persistence context | Shared across persistence contexts |
| Default | Present by default | Optional |
| Configuration | No separate cache provider | Requires cache configuration |
| Lifetime | Persistence-context lifetime | Depends on cache configuration |
| Purpose | Reuse entities within a unit of work | Reuse cached data across units of work |

**Important:** Hibernate's second-level cache differs from Spring's `@Cacheable`. Spring caching is a separate abstraction commonly used to cache service method results or application data.

## 3.5 How `@Transactional` works internally

**Short answer:** Spring generally implements `@Transactional` using AOP proxies. The proxy starts or joins a transaction before the method executes, then commits or rolls back based on the outcome and transaction rules.

### Execution flow

```text
Client
  |
  v
Spring-managed service proxy
  |
  v
Transaction interceptor
  |
  +--> Check propagation and transaction attributes
  |
  v
Transaction manager starts or joins transaction
  |
  v
Target method executes database operations
  |
  v
Method completes
  |
  +--> Success: commit when appropriate
  |
  +--> Qualifying exception: rollback
```

Example:

```java
@Service
public class EmployeeService {

    private final EmployeeRepository repository;

    public EmployeeService(EmployeeRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public void createEmployee() {
        Employee employee = new Employee();
        employee.setName("Rajat");

        repository.save(employee);
    }
}
```

### Step-by-step explanation

1. Spring creates a proxy around the service bean when proxy-based transaction management is enabled.
2. The caller invokes the method through the proxy.
3. The transaction interceptor reads the transaction settings, including propagation and rollback rules.
4. The transaction manager starts a new transaction or joins an existing one.
5. Repository operations execute within that transaction.
6. If the method completes normally, Spring commits when the transaction boundary completes.
7. If a qualifying exception escapes, Spring rolls back according to the configured rules.

For JPA, `JpaTransactionManager` is commonly used. JDBC applications may use a suitable JDBC transaction manager. The actual manager depends on the application configuration.

### Does `@Transactional` roll back for every exception?

No. By default, Spring rolls back for `RuntimeException` and `Error`, but not for checked exceptions.

```java
@Transactional(rollbackFor = Exception.class)
public void processEmployee() throws Exception {
    // Database operations
    throw new Exception("Processing failed");
}
```

Here, `rollbackFor = Exception.class` configures rollback for checked exceptions as well.

**Important:** If a method catches an exception and returns normally, that alone does not trigger rollback. The transaction may still roll back if it has been marked rollback-only, for example by a participating inner transaction.

### Why might `@Transactional` not work?

**1. Self-invocation**

```java
@Service
public class EmployeeService {

    public void process() {
        saveEmployee(); // Direct call on the same object
    }

    @Transactional
    public void saveEmployee() {
        // Database operations
    }
}
```

In standard proxy-based transaction management, the internal call bypasses the proxy. Therefore, the annotation on `saveEmployee()` is not independently applied through the proxy.

**Solution:** Put the transaction boundary on the externally invoked method or move the transactional operation into another Spring-managed bean.

**2. Private methods**

With standard proxy-based transaction management, a private method cannot serve as an intercepted transactional entry point.

**3. Microservice boundaries**

`@Transactional` usually manages a transaction for one configured transaction manager and its resources. It does not make an entire multi-service workflow atomic. Distributed workflows often use patterns such as Saga.

**Interview point:** Explain proxies, transaction managers, propagation, rollback rules, and self-invocation.

---

# 4. Quick revision

| Topic | One-line answer |
|---|---|
| `@RequestMapping` | Maps requests and supports different HTTP methods. |
| `@GetMapping` | Shortcut for mapping GET requests. |
| JSON serialization | Jackson converts Java objects into JSON in typical Spring MVC applications. |
| `ResponseEntity` | Controls response body, HTTP status, and headers. |
| `@PathVariable` | Extracts a value from the URL path. |
| `@RequestParam` | Extracts a query parameter. |
| `@RequestBody` | Converts the HTTP request body into a Java object. |
| `@RestControllerAdvice` | Centralizes REST controller exception handling. |
| Spring Data JPA | Repository abstraction that simplifies persistence code. |
| JPA vs Hibernate | Specification vs implementation. |
| `CrudRepository` | Basic CRUD methods. |
| `PagingAndSortingRepository` | Pagination and sorting. |
| `JpaRepository` | CRUD, paging/sorting integration, and JPA-specific operations. |
| Pagination | Use `Pageable`, `PageRequest`, and `Page`. |
| Caching | Use `@EnableCaching`, `@Cacheable`, `@CachePut`, and `@CacheEvict`. |
| Optimistic locking | Detect conflicting updates using `@Version`. |
| Pessimistic locking | Use database locks to restrict conflicting operations. |
| `save()` vs `saveAndFlush()` | Save vs save and immediately flush pending persistence-context changes. |
| JPQL vs native SQL | Entity-oriented query language vs database SQL. |
| Flush vs commit | Synchronize pending changes vs finalize the transaction. |
| `REQUIRED` | Join an existing transaction or create one. |
| `REQUIRES_NEW` | Suspend an existing transaction and start an independent one. |
| N+1 problem | One query for parents plus additional queries for related entities. |
| Lazy loading | Fetch related data when accessed. |
| Eager loading | Require related data to be fetched as part of entity loading. |
| L1 cache | Scoped to a persistence context. |
| L2 cache | Optional cache shared across persistence contexts. |
| `@Transactional` | Spring proxy/interceptor manages transaction boundaries. |
| Default rollback | For unchecked exceptions and errors. |
| Self-invocation | Bypasses the standard Spring transaction proxy. |

## High-priority follow-up topics

- Transaction propagation and isolation levels
- `@Transactional` with checked exceptions
- N+1 queries and Hibernate fetch strategies
- Lazy loading and `LazyInitializationException`
- First-level vs second-level cache
- Database indexes and query optimization
- Connection pooling and transaction timeouts
- Saga pattern for distributed transactions

---

*These notes are intended as interview revision material. Check the exact Spring Boot, Spring Data, and Hibernate versions used by your project when relying on version-specific behavior.*
