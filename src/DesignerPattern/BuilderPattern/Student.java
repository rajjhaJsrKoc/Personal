package DesignerPattern.BuilderPattern;

public class Student {
    public String name;
    public int age;
    public int rollNo;
    public Student(BuilderStudent builderStudent) {
        this.name = builderStudent.name;
        this.age = builderStudent.age;
        this.rollNo= builderStudent.rollNo;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", rollNo=" + rollNo +
                '}';
    }
}
