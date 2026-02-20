package DesignerPattern.Singleton;

public class Driver {
    public static void main(String[] args) {

        Singleton obj1 = Singleton.getInstance();
        Singleton obj2 = Singleton.getInstance();

        //obj1.showMessage();
        System.out.println(obj1 == obj2);
    }
}
