package modernjavainaction.chap17;

import java.util.ArrayList;

public class Food {


    static class Meat extends Food {
    }

    static class Fruit extends Food {
    }


    static class Apple extends Fruit {
    }

    static class Banana extends Fruit {
    }

    static class BigApple extends Apple {
    }


    public static void main(String[] args) {
        Fruit fruit;
        fruit = new Fruit();
        fruit = new Apple();
        fruit = new BigApple();

        ArrayList<? extends Fruit> extentsList;
        extentsList = new ArrayList<Fruit>();
        extentsList = new ArrayList<Apple>();
        extentsList = new ArrayList<Banana>();
        extentsList = new ArrayList<BigApple>();

        ArrayList<Apple> apples = new ArrayList<>();
        apples.add(new Apple());
        extentsList = apples;
        Fruit f = extentsList.get(0);


        ArrayList<? super Fruit> superFruit;
        superFruit = new ArrayList<Fruit>();
        superFruit = new ArrayList<Food>();
        superFruit = new ArrayList<Object>();


        ArrayList<? super Fruit> s = new ArrayList<Fruit>();
        s.add(new Fruit());
        s.add(new Apple());
        s.add(new BigApple());
        Object object = s.get(0);


    }
}

