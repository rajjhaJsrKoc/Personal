# 📘 Hibernate Interview Questions & Answers

---

## 🔹 Basics

1. **What is Hibernate?**
   ➝ Hibernate is an ORM (Object-Relational Mapping) framework in Java. It maps Java objects to relational database tables, eliminating JDBC boilerplate code.

2. **Difference between JDBC and Hibernate?**

    * JDBC: manual SQL, result set handling, exception handling.
    * Hibernate: automatically translates objects ↔ tables, SQL is generated internally, provides caching & lazy loading.

3. **What is ORM?**
   ➝ ORM (Object Relational Mapping) is the technique of mapping Java objects to DB tables and vice versa.

4. **What is a Session in Hibernate?**
   ➝ A Session is the interface between Java application and DB. It represents a unit of work. It’s lightweight and not thread-safe.

---

## 🔹 Core Concepts

5. **What is a SessionFactory?**
   ➝ Heavyweight object that creates and manages sessions. Usually created once per DB per application. It’s thread-safe.

6. **Difference between openSession() and getCurrentSession()?**

    * `openSession()` → always creates a new Session, must close explicitly.
    * `getCurrentSession()` → provides a session bound to context (usually closes automatically).

7. **What is Transaction in Hibernate?**
   ➝ Ensures ACID properties. Managed using `session.beginTransaction()` and `commit()`.

8. **Difference between save(), persist(), and saveOrUpdate()?**

    * `save()` → returns generated identifier, inserts immediately.
    * `persist()` → doesn’t return id, follows JPA spec, may delay insert.
    * `saveOrUpdate()` → inserts new if id not present, else updates.

9. **Difference between merge() and update()?**

    * `update()` → reattaches a detached object, fails if another instance with same id exists in session.
    * `merge()` → copies state of detached entity into a managed entity, safer.

---

## 🔹 Mapping

10. **How do you map relationships in Hibernate?**

* One-to-One → `@OneToOne`
* One-to-Many → `@OneToMany` + `@JoinColumn`
* Many-to-One → `@ManyToOne`
* Many-to-Many → `@ManyToMany` + `@JoinTable`

11. **Difference between unidirectional and bidirectional mapping?**

* Unidirectional → only one side knows the relationship.
* Bidirectional → both entities know each other via mappedBy.

12. **What are the types of inheritance mapping in Hibernate?**

* Single Table → All classes in one table.
* Table per Class → Each class has its own table.
* Joined Table → Parent and child stored in separate tables joined by foreign keys.

---

## 🔹 Fetching & Caching

13. **What is Lazy and Eager fetching?**

* Lazy → Fetches associations only when accessed. Default in collections.
* Eager → Fetches associations immediately with main entity.

14. **How does caching work in Hibernate?**

* First-level Cache → Session scoped. Always enabled.
* Second-level Cache → SessionFactory scoped. Needs providers like EhCache, Infinispan.
* Query Cache → Caches query results.

15. **Difference between First-level and Second-level cache?**

* First-level: default, per-session, mandatory.
* Second-level: optional, across sessions, needs external provider.

---

## 🔹 HQL, Criteria & SQL

16. **What is HQL?**
    ➝ Hibernate Query Language. Similar to SQL but works with objects instead of tables.

Example:

```java
Query query = session.createQuery("from Employee where id = :id");
query.setParameter("id", 101);
```

17. **Difference between HQL and SQL?**

* HQL → object-oriented, database-independent.
* SQL → database-specific, table-oriented.

18. **What is Criteria API?**
    ➝ Programmatic query building (type-safe).

Example:

```java
Criteria criteria = session.createCriteria(Employee.class);
criteria.add(Restrictions.eq("name", "Rajat"));
```

19. **Native SQL in Hibernate?**
    ➝ Hibernate allows execution of raw SQL using `createSQLQuery()`.

---

## 🔹 Performance

20. **How to improve performance in Hibernate?**

* Use lazy loading.
* Enable second-level and query cache.
* Use batch fetching.
* Avoid N+1 select problem (use `join fetch`).
* Use pagination for large result sets.

21. **What is N+1 problem?**
    ➝ When fetching parent entities triggers extra queries for each child (N queries).
    Fix → use `join fetch` in HQL or entity graph.

22. **What is dirty checking?**
    ➝ Hibernate automatically detects changes in persistent objects and updates only modified fields at flush/commit.

---

## 🔹 Advanced

23. **What is optimistic and pessimistic locking in Hibernate?**

* Optimistic → assumes low conflicts, uses version column to detect conflicts.
* Pessimistic → locks rows in DB (`SELECT FOR UPDATE`).

24. **Difference between flush() and commit()?**

* `flush()` → synchronizes session with DB but doesn’t end transaction.
* `commit()` → ends transaction (calls flush internally).

25. **What is a proxy object in Hibernate?**
    ➝ For lazy loading, Hibernate creates proxy objects instead of fetching real data until accessed.

26. **What is difference between get() and load()?**

* `get()` → hits DB immediately, returns null if not found.
* `load()` → returns proxy, throws `ObjectNotFoundException` if accessed and not found.

27. **What is cascading in Hibernate?**
    ➝ Determines how operations (save, delete, update) on one entity are cascaded to related entities (`CascadeType.ALL`, `PERSIST`, `REMOVE`, etc.).

---

## 🔹 Integration

28. **How does Hibernate integrate with Spring?**
    ➝ Using `LocalSessionFactoryBean` or `EntityManagerFactory`. Spring manages transactions (`@Transactional`).

29. **Difference between JPA and Hibernate?**

* JPA → specification (API).
* Hibernate → implementation of JPA (with extra features).

30. **Can Hibernate work without XML configuration?**
    ➝ Yes, using annotations and `hibernate.cfg.xml` can be replaced by Java-based config.

---
