// Simple class hierarchy
// Contrasts interface and abstract class implementations

public class Animals2 {

    // interface
    interface Pet { 
        void talk();
    }

    interface Quadraped {
        void walk();
    }

    // inner classes
    class Cat implements Pet, Quadraped {
        Cat() { System.out.println("Creating a cat"); }
        public void talk() { System.out.println(" Meow!"); }
        public void walk() { System.out.println(" Sneaks around"); }
    }

    class Dog implements Pet, Quadraped {
        Dog() { System.out.println("Creating a dog"); }        
        public void talk() { System.out.println(" Woof!"); }
        public void walk() { System.out.println(" Lopes forward"); }
    }

    // container class methods
    Animals2() {
        Cat kitten = new Cat();
        speak(kitten);
        move(kitten);

        Dog puppy = new Dog();
        speak(puppy);
        move(puppy);
    }

    void speak(Pet p) {
        p.talk();
    }

    void move(Quadraped q) {
        q.walk();
    }

    // static main methods -- entry point
    public static void main(String[] args) {
        new Animals2();
    }
}

