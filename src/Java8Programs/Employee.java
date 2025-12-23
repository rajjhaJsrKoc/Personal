package Java8Programs;
public class Employee {
    public Employee(String test1, int i, String department) {
        this.name = test1;
        this.salary= i;
        this.department = department;
    }

    public Employee(String name, Deparment deparmentNew, double salary) {
        this.name = name;
        this.deparmentNew = deparmentNew;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    private String name;

    private double salary;

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    private String department;
    private Deparment deparmentNew;

    public Deparment getDeparmentNew() {
        return deparmentNew;
    }

    public void setDeparmentNew(Deparment deparmentNew) {
        this.deparmentNew = deparmentNew;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", salary=" + salary +
                '}';
    }
}
