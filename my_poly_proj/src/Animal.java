public abstract class Animal {
    private String name;
    private String food;

    public Animal(String name) {
        this.name = name;
    }

    public String sound() {
        return null;
    }

    public void setFood(String food) {
        this.food = food;
    }

    public String getFood(){
        return this.food;
    }

    public String toString() {
        return name.concat(" says ").concat(sound().concat(" and eats ").concat(food));
    }
}

class Dog extends Animal {
    public Dog(String name) {
        super(name);
    }
    public String sound() {
        return "Woof";
    }
}

class Cat extends Animal {
    public Cat(String name) {
        super(name);
    }
    public String sound() {
        return "Meow";
    }
}

class Cow extends Animal {
    public Cow(String name) {
        super(name);
    }
    public String sound() {
        return "Moo";
    }
}

// Abstract methods are methods which are defined but not implemented. By defining a method, you give it a name, specify the parameters it will take, specify the access and also the return type. But the body of the method is empty. Abstract methods can only be defined as class methods. Meaning, they can't be static. Any class containting an abstract method, should be defined as an abstract class.

/**
 * public abstract static int absMeth1(String s) - This definition is not possible. It will throw a compilation error as abstract methods cannot be static
    private abstract String absMeth2() - This definition is not possible. It will throw a compilation error as abstract methods cannot be private. The purpose of these methods are for these to be implemented in the child class. Child classes do not have access to super class's private methods.
 */

//Please note that an abstract method has to be in an abstract class, but an abstract class doesn't have to have an abstract method. When you don't want a class to be instantiated and only be inherited, you define it as an abstract class. Consider the following example. There is Animal class. There are Dog,Cat and Cow classes which inherit. There is no point in creating an instance of Animal class. In such a case, you can define Animal class as an abstract class.

