package DesignerPattern.FactoryPattern;

public class FactoryIntiliazer {
    public Shape getShape(String shape) {
        if (shape.equals("Rectangle")) {
            return new Rectangle();
        }
        else if (shape.equals("Circle")) {
            System.out.println("Not Implemented");
        }
        return null;
    }
}
