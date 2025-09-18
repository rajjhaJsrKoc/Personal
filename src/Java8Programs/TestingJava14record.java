package Java8Programs;

public class TestingJava14record {
    public static void main(String[] args) {
        // Using Record
        Person record = new Person("Rajat", 29);
        System.out.println(record.name());    // Rajat
        System.out.println(record);           // Person[name=Rajat, age=29]
    }
}
/*
| Feature     | **POJO**                                                          | **Record**                                                            |
        | ----------- | ----------------------------------------------------------------- | --------------------------------------------------------------------- |
        | Boilerplate | Lots of code for constructor, getters, toString, equals, hashCode | Minimal (auto-generated)                                              |
        | Mutability  | Can be mutable (setters allowed)                                  | Immutable by default (all fields are `final`)                         |
        | Purpose     | General-purpose class (business logic + state)                    | Data carrier (state only, no extra behavior)                          |
        | Inheritance | Can extend other classes                                          | ❌ Cannot extend other classes (implicitly extends `java.lang.Record`) |
        | Getters     | User-defined (`getName()`)                                        | Auto-defined (`name()`, `age()`)                                      |
        | Fields      | Can be mutable or final                                           | Always `private final`                                                |
        | Use Case    | Rich domain models, business logic                                | DTOs, API responses, configs — where class is just data               |
*/
