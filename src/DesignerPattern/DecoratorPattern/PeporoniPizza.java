package DesignerPattern.DecoratorPattern;

public class PeporoniPizza implements Pizza{
    @Override
    public String getDescription() {
        return "Peporoni Pizza";
    }

    @Override
    public double getCost() {
        return 100;
    }
}
