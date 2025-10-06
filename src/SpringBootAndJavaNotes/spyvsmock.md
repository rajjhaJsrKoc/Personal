Sure 👍 — here’s a **concise comparison** of `@Mock` vs `@Spy` in Mockito:

| Feature              | **@Mock**                                                  | **@Spy**                                           |
| -------------------- | ---------------------------------------------------------- | -------------------------------------------------- |
| **Type**             | Fake object (no real behavior)                             | Real object (partial mock)                         |
| **Default behavior** | Methods return default values (`null`, `0`, `false`, etc.) | Calls real methods unless stubbed                  |
| **When to use**      | When testing **interactions only**                         | When testing **real logic** but mocking some parts |
| **Creation**         | `@Mock MyClass obj;`                                       | `@Spy MyClass obj = new MyClass();`                |
| **Stubbing**         | `when(mock.method()).thenReturn(val)`                      | `doReturn(val).when(spy).method()` *(safer)*       |
| **Example use**      | Mock dependencies (e.g. DB, API calls)                     | Spy service objects for partial testing            |

**TL;DR:**
👉 Use `@Mock` for **fake dependencies**.
👉 Use `@Spy` for **real objects** where you want **partial mocking**.
