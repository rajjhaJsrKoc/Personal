# SQL Indexing Notes

## 1. Clustered Index
- **Definition:** Table rows are physically stored in the order of the indexed column.
- **One per table**.

### Example:
```sql
CREATE CLUSTERED INDEX idx_emp_id ON Employees(emp_id);
```
#### Table before index:
| emp_id | name  | dept_id | salary |
|--------|-------|---------|--------|
| 3      | Rajat | 10      | 5000   |
| 1      | Alice | 20      | 7000   |
| 2      | Bob   | 10      | 6000   |

#### Table after clustered index on emp_id:
| emp_id | name  | dept_id | salary |
|--------|-------|---------|--------|
| 1      | Alice | 20      | 7000   |
| 2      | Bob   | 10      | 6000   |
| 3      | Rajat | 10      | 5000   |

- **Query:**
```sql
SELECT * FROM Employees WHERE emp_id = 2;
```
- **Behavior:** Fast, direct access.

---

## 2. Non-Clustered Index
- **Definition:** Separate from table, stores indexed column + pointer to table row.
- **Table rows stay in original order**.
- **Multiple allowed per table**.

### Example:
```sql
CREATE NONCLUSTERED INDEX idx_dept_id ON Employees(dept_id);
```

#### Table stays same:
| emp_id | name  | dept_id | salary |
|--------|-------|---------|--------|
| 3      | Rajat | 10      | 5000   |
| 1      | Alice | 20      | 7000   |
| 2      | Bob   | 10      | 6000   |

#### Index structure (simplified):
| dept_id | Row Pointer |
|---------|-------------|
| 10      | Row 1       |
| 10      | Row 3       |
| 20      | Row 2       |

- **Query:**
```sql
SELECT * FROM Employees WHERE dept_id = 10;
```
- **Behavior:** Uses index to find rows, then goes to table for full data.

---

## Key Differences
| Feature                  | Clustered Index             | Non-Clustered Index        |
|---------------------------|---------------------------|---------------------------|
| Table rows order          | Physically sorted          | Table order unchanged    |
| Number of indexes/table   | 1                         | Multiple allowed          |
| Full row lookup needed?   | No (data in index)        | Yes (pointer to table)   |
| Best for                  | Range queries, primary key | Flexible searches on other columns |



# SQL Indexing Notes

## 1. Clustered Index
- **Definition:** Table rows are physically stored in the order of the indexed column.
- **One per table**.

### Example:
```sql
CREATE CLUSTERED INDEX idx_emp_id ON Employees(emp_id);
```
#### Table after clustered index on emp_id:
| emp_id | name  | dept_id | salary |
|--------|-------|---------|--------|
| 1      | Alice | 20      | 7000   |
| 2      | Bob   | 10      | 6000   |
| 3      | Rajat | 10      | 5000   |

- **Query (widely asked in interviews):**
```sql
SELECT * FROM Employees WHERE emp_id BETWEEN 1 AND 3;
```
- **Behavior:** Fast, direct access due to row ordering.

---

## 2. Non-Clustered Index
- **Definition:** Separate from table, stores indexed column + pointer to table row.
- **Table rows stay in original order**.
- **Multiple allowed per table**.

### Example:
```sql
CREATE NONCLUSTERED INDEX idx_dept_id ON Employees(dept_id);
```
- **Query (common interview example):**
```sql
SELECT * FROM Employees WHERE dept_id = 10;
```
- **Behavior:** Uses index to find rows, then goes to table for full data.

---

## 3. Unique Index
- **Definition:** Ensures that all values in the indexed column(s) are unique.

### Example:
```sql
CREATE UNIQUE INDEX idx_email ON Employees(email);
```
- **Query:**
```sql
SELECT * FROM Employees WHERE email = 'alice@example.com';
```

---

## 4. Composite/Concatenated Index
- **Definition:** Index on multiple columns together. Column order matters.

### Example:
```sql
CREATE INDEX idx_dept_salary ON Employees(dept_id, salary);
```
- **Query:**
```sql
SELECT * FROM Employees WHERE dept_id = 10 AND salary > 5000;
```

---

## 5. Full-Text Index
- **Definition:** Optimized for text search in large text columns.

### Example:
```sql
CREATE FULLTEXT INDEX idx_content ON Documents(content);
```
- **Query:**
```sql
SELECT * FROM Documents WHERE MATCH(content) AGAINST ('database');
```

---

## 6. Spatial Index
- **Definition:** For geometric data types (points, polygons).

### Example:
```sql
CREATE SPATIAL INDEX idx_location ON Places(coordinates);
```
- **Query:**
```sql
SELECT * FROM Places WHERE ST_Contains(geometry, POINT(10,20));
```

---

## 7. Covering Index
- **Definition:** Index contains all columns needed for a query.

### Example:
```sql
CREATE INDEX idx_cover ON Employees(dept_id, name, salary);
```
- **Query:**
```sql
SELECT name, salary FROM Employees WHERE dept_id = 10;
```
- **Behavior:** Query satisfied entirely from index, no table lookup.

---

## 8. Bitmap Index (Mostly Oracle)
- **Definition:** Uses bitmap for each distinct value. Efficient for low-cardinality columns.

### Example:
```sql
CREATE BITMAP INDEX idx_status ON Employees(status);
```
- **Query:**
```sql
SELECT * FROM Employees WHERE status = 'Active';
```

---

## Key Differences Table
| Feature                  | Clustered Index             | Non-Clustered Index        | Unique Index | Composite Index | Full-Text | Spatial | Covering | Bitmap |
|---------------------------|---------------------------|---------------------------|--------------|----------------|-----------|--------|----------|--------|
| Table rows order          | Physically sorted          | Table order unchanged    | -            | -              | -         | -      | -        | -      |
| Multiple per table        | 1                         | Yes                       | Yes          | Yes            | Yes       | Yes    | Yes      | Yes    |
| Full row lookup needed?   | No                        | Yes                       | No           | Depends        | No        | Depends | No       | Depends |
| Best for                  | Range queries, PK          | Flexible searches        | Enforcing uniqueness | Multi-column queries | Text search | GIS/geometry | SELECT-heavy queries | Low-cardinality analytics |