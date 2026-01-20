/**When creating a custom function for a class, it is important to decide on whether the function has to be static or not. 
 * A static method, doesn't require an object of the class to be created. An example for a method which can be static is a method 
 * which returns the pi value. They're associated with the class itself, not with objects of the class. */

import java.util.Scanner;
public class AreaCalculator {

    private  static float pi = 3.142f;

    private static float circle(float radius) {
        return (pi * radius * radius);
    }
    private static float square(float length) {
        return length * length;
    }
    private static float rectangle(float length, float breadth) {
        return length * breadth;
    }
    private static float sphereVolume(float radius) {
        return (4/3) * pi * (radius * radius * radius);
    }
    private static float cubeVolume(float sideLength) {
        return sideLength * sideLength * sideLength;
    }
    private static float cuboidVolume(float length, float breadth, float height) {
        return length * breadth * height;
    }
    // private -> method can be only called within the class
    // static -> no need to create the instance of the class to invoke the method

 
    public static void main(String[] args) {
        System.out.println("Welcome to the area calculator!");
        while(true) {
            // add the menu to get user input and invoke method here
            System.out.println("\nEnter 1 for circle\n" +
                                "Enter 2 for square\n" + 
                                "Ener 3 for rectangle" +
                                "Enter 4 for sphere volume\n" +
                                "Enter 5 for cube volume\n" +
                                "Enter 6 for cuboid volume");
            Scanner scanner = new Scanner(System.in);
            int choice = Integer.parseInt(scanner.nextLine());
            if(choice == 1) {
                System.out.println("Enter the value of the circle radius :");
                float radiius = Float.parseFloat(scanner.nextLine());
                System.out.println("The area of the circle is : " + circle(radiius));
            } else if (choice == 2) {
                System.out.println("Enter the length of the square : ");
                float length = Float.parseFloat(scanner.nextLine());
                System.out.println("The area of the square is : " + square(length));
            } else if (choice == 3) {
                System.out.println("Enter the length of the square : ");
                float length = Float.parseFloat(scanner.nextLine());
                System.out.println("Enter the breadth of the square : ");
                float breadth = Float.parseFloat(scanner.nextLine());
                System.out.println("The area of the rectangle is : " + rectangle(length, breadth));
            } else if (choice == 4) {
                System.out.println("Enter the radius of the sphere");
                float radius = Float.parseFloat(scanner.nextLine());
                System.out.println("The volume of sphere of radius "+ radius + "is " + sphereVolume(radius));
            } else if (choice == 5) {
                System.out.println("Enter the side length of the cube");
                float sideLength = Float.parseFloat(scanner.nextLine());
                System.out.println("The volume of cube of side length "+ sideLength + "is " + cubeVolume(sideLength));
            } else if (choice == 6) {
                System.out.println("Enter the length of the cuboid");
                float length = Float.parseFloat(scanner.nextLine());
                System.out.println("Enter the breadth of the cuboid");
                float breadth = Float.parseFloat(scanner.nextLine());
                System.out.println("Enter the height of the cuboid");
                float height = Float.parseFloat(scanner.nextLine());
                System.out.println("The volume of cuboid of length "+ length + ", breadth "+breadth+ ", and height "+height+ " is " + cuboidVolume(length,breadth,height));
            } 
            else {
                System.out.println("Invalid Choice!");
                break;
            }
        }
    }
}
// TO set the class path
//$env:CLASSPATH = "$env:CLASSPATH;D:\Practice-Dev\Java-Projects\my_array_proj\classes"