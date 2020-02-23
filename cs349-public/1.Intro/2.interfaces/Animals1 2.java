// Simple class hierarchy
// Demonstrates abstract classes, containment

public class Animals1 {

    // base class
    abstract class Pet {
        abstract void talk();
    }

    // derived classes
    class Cat extends Pet {
        Cat() { System.out.println("Creating a cat"); }
        void talk() { System.out.println(" Meow!"); }
    }

    class Dog extends Pet {
        Dog() { System.out.println("Creating a dog"); }        
        void talk() { System.out.println(" Woof!"); }
    }

    // container class methods
    Animals1() {
        Cat kitten = new Cat();
        speak(kitten);

        Dog puppy = new Dog();
        speak(puppy); 
    }

    void speak(Pet a) {
        a.talk();
    }

    // static main methods -- entry point
    public static void main(String[] args) {
        new Animals1();
    }
}

