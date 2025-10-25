package DesignerPattern.DecoratorPattern;

public class MargheritaPizza implements Pizza {
    @Override
    public String getDescription() {
        return "MargheritaPizza";
    }

    @Override
    public double getCost() {
        return 10;
    }
}
