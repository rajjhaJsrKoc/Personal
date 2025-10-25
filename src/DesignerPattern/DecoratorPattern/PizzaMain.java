package DesignerPattern.DecoratorPattern;

public class PizzaMain {
    public static void main(String[] args){
        Pizza pizza = new MargheritaPizza();
        System.out.println(pizza.getDescription() + " -> $" + pizza.getCost());

        pizza = new CheeseDecorator(pizza);
        System.out.println(pizza.getDescription() + " -> $" + pizza.getCost());

        pizza = new OliveDecorator(pizza);
        System.out.println(pizza.getDescription() + " -> $" + pizza.getCost());

    }
}
