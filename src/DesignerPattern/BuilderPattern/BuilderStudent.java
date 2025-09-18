package DesignerPattern.BuilderPattern;

public class BuilderStudent {
    public String name;
    public int age;
    public int rollNo;

    public BuilderStudent(String name) {
        this.name = name;
    }

    public BuilderStudent setAge(int age) {
        this.age = age;
        return this;
    }

    public BuilderStudent setRollNo(int rollNo) {
        this.rollNo = rollNo;
        return this;
    }

    public BuilderStudent setName(String name) {
        this.name = name;
        return this;
    }
    public Student build() {
        return new Student(this);
    }
}
