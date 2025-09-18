package DesignerPattern.BuilderPattern;

public class BuilderDriverFunction {
    public static void main(String[] args) {
        Student bs = new BuilderStudent("name").setAge(5).build();
        System.out.println(bs);
    }
}
