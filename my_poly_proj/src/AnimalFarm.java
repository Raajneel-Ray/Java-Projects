import java.util.Scanner;

public class AnimalFarm {
    public static void main(String s[]) {
        Scanner scanner = new Scanner(System.in);
        Animal[] animals = new Animal[10];
        int anmlIdx = 0;
        while(true) {
            System.out.println(	"Press 1 to view the animals, " +
                                "\n2 to add animals, "+
                                "\nany other key to exit");
            String userAction = scanner.nextLine();
            if (userAction.equals("1")) {
                for(int i=0;i<animals.length;i++) {
                    if(animals[i] != null) {
                        System.out.println(animals[i]);
                    }
                }
            } else if (userAction.equals("2")) {
                if(anmlIdx == 10) {
                    System.out.println("10 animals added already. Cannot add any more animals!");
                    continue;
                }
                System.out.println("Which animal do you want to create? \nPress 1 for dog,"+ 
                                    "\n2 for cat " +
                                    "\n3 for cow" );
                String animalChoice = scanner.nextLine();
                if (animalChoice.equals("1")) {
                    System.out.println("Enter the name of the dog");
                    String dogName = scanner.nextLine();
                    Animal anmlTmp = new Dog(dogName);
                    System.out.println("Enter what the dog eats");
                    String dogFood = scanner.nextLine();
                    anmlTmp.setFood(dogFood);
                    animals[anmlIdx++] = anmlTmp;
                } else if (animalChoice.equals("2")) {
                    System.out.println("Enter the name of the cat");
                    String catName = scanner.nextLine();
                    Animal anmlTmp = new Cat(catName);
                    System.out.println("Enter what the cat eats");
                    String catFood = scanner.nextLine();
                    anmlTmp.setFood(catFood);
                    animals[anmlIdx++] = anmlTmp;
                } else if (animalChoice.equals("3")) {
                    System.out.println("Enter the name of the cow");
                    String cowName = scanner.nextLine();
                    Animal anmlTmp = new Cow(cowName);
                    System.out.println("Enter what the cow eats");
                    String cowFood = scanner.nextLine();
                    anmlTmp.setFood(cowFood);
                    animals[anmlIdx++] = anmlTmp;
                }
            } else {
                break;
            }
        }
    }
}
/**Three objects are created: animal1, animal2, and animal3, each of type Animal. However, they are instantiated with different classes: Dog, Cat, and Cow, respectively.

The key aspect of this code is polymorphism. Although the objects are declared as type Animal, they are actually instances of specific animal classes (Dog, Cat, Cow). When the sound() method is called on each object, the correct implementation is invoked based on the actual object type.

Method Invocation

The sound() method is called on each object, and the corresponding implementation is executed:

animal1.sound() calls the sound() method implemented in the Dog class.
animal2.sound() calls the sound() method implemented in the Cat class.
animal3.sound() calls the sound() method implemented in the Cow class. */