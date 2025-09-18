package DesignerPattern.FactoryPattern;

public class DriverFactory {
   public static void main(String[] args){
       Shape s = new FactoryIntiliazer().getShape("Rectangle");
       s.draw();
   }
}
