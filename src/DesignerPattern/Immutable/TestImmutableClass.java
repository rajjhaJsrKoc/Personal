package DesignerPattern.Immutable;

import java.util.Date;

public class TestImmutableClass {
    public static void main(String[] args) {
        Date d = new Date();
        ImmutableClass immutableClass = new ImmutableClass(1,"Rajat",d);
        System.out.println(immutableClass.toString());
        d.setTime(0);

        // Object still safe
        System.out.println(d);
        Date date2 = new Date();
        ImmutableClass obj2 = new ImmutableClass(102, "Second", date2);
        date2.setTime(123456789);
        System.out.println(obj2.getDate());
    }
}
