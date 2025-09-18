# SQL Interview Queries Notes

## 1. 2nd Highest Salary / Value
- **Query using `LIMIT` / `OFFSET` (MySQL):**
```sql
SELECT salary FROM Employees ORDER BY salary DESC LIMIT 1 OFFSET 1;
```
- **Query using `TOP` (SQL Server):**
```sql
SELECT TOP 1 salary FROM (
                             SELECT TOP 2 salary FROM Employees ORDER BY salary DESC
                         ) AS temp ORDER BY salary ASC;
```
- **Query using `RANK()` (SQL Server / Oracle / PostgreSQL):**
```sql
SELECT salary FROM (
                       SELECT salary, RANK() OVER (ORDER BY salary DESC) AS rnk
                       FROM Employees
                   ) AS temp WHERE rnk = 2;
```

---

## 2. Nth Highest Salary / Value
- **Query using `RANK()` / `DENSE_RANK()`:**
```sql
SELECT salary FROM (
                       SELECT salary, RANK() OVER (ORDER BY salary DESC) AS rnk
                       FROM Employees
                   ) AS temp WHERE rnk = N;
```
- Replace `N` with desired rank (e.g., 3 for 3rd highest).

---

## 3. `RANK()` vs `DENSE_RANK()`
| Feature                   | RANK()                               | DENSE_RANK()                         |
|----------------------------|--------------------------------------|--------------------------------------|
| Handling ties              | Skips next rank(s) after tie         | No gaps in ranking                   |
| Example values             | 100, 100, 90 → ranks 1,1,3          | 100, 100, 90 → ranks 1,1,2          |
| Use case                   | When relative rank gaps matter       | When consecutive ranks are needed    |

**Example:**
```sql
SELECT name, salary,
       RANK() OVER (ORDER BY salary DESC) AS rnk,
        DENSE_RANK() OVER (ORDER BY salary DESC) AS dense_rnk
FROM Employees;
```

---

## 4. Common Interview Queries

### a) Find Employees with Highest Salary per Department
```sql
SELECT dept_id, MAX(salary) AS max_salary
FROM Employees
GROUP BY dept_id;
```

### b) Find Employees Who Joined Most Recently
```sql
SELECT * FROM Employees
WHERE hire_date = (SELECT MAX(hire_date) FROM Employees);
```

### c) Delete Duplicate Records Keeping Latest
```sql
WITH CTE AS (
    SELECT *, ROW_NUMBER() OVER (PARTITION BY emp_id ORDER BY hire_date DESC) AS rn
    FROM Employees
)
DELETE FROM CTE WHERE rn > 1;
```

### d) Cumulative Sum / Running Total
```sql
SELECT name, salary,
       SUM(salary) OVER (ORDER BY hire_date) AS running_total
FROM Employees;
```

---

## 5. Common Table Expressions (CTE)
- **Definition:** A temporary result set that can be referenced within a `SELECT`, `INSERT`, `UPDATE`, or `DELETE` statement.
- **Benefits:** Makes queries **more readable**, supports **recursive queries**, and is reusable within the main query.

### Example of CTE:
```sql
WITH RecentHires AS (
    SELECT * FROM Employees
    WHERE hire_date >= '2025-01-01'
)
SELECT name, dept_id FROM RecentHires WHERE salary > 5000;
```
- Can also be used with `ROW_NUMBER()`, `RANK()`, or aggregation queries to simplify complex logic.
