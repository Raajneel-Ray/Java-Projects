
import java.util.Scanner;

class ItemNotFoundException extends Exception {

    public ItemNotFoundException(String message) {
        super(message);
    }
}

public class GroceryShopping {

    public static void main(String[] args) {
        String[] item = new String[10];
        float[] price = new float[10];
        item[0] = "Apple";
        price[0] = 0.50f;
        item[1] = "Banana";
        price[1] = 0.30f;
        item[2] = "Bread";
        price[2] = 2.00f;
        item[3] = "Milk";
        price[3] = 1.50f;
        item[4] = "Eggs";
        price[4] = 2.50f;
        item[5] = "Cheese";
        price[5] = 3.00f;
        item[6] = "Chicken";
        price[6] = 5.00f;
        item[7] = "Rice";
        price[7] = 1.00f;
        item[8] = "Pasta";
        price[8] = 1.20f;
        item[9] = "Tomato";
        price[9] = 0.80f;
        Scanner scanner = new Scanner(System.in);

        while (true) {
            float totalBill = 0.0f;
            String userinput = scanner.nextLine();
            if (userinput.equalsIgnoreCase("exit")) {
                System.out.println("Thank you for using the shopping cart! Please visit again.");
                break;
            }
            while (true) {
                try {
                    System.out.println("Enter the name of the item( or type 'finish' to end shopping.)");
                    String inputItem = scanner.nextLine();

                    if (inputItem.equalsIgnoreCase("finish")) {
                        System.out.println("Your Total Bill is : $" + totalBill);
                        System.out.println("Thank you for shopping with us!");
                        break;
                    }
                    // find the index of the item 
                    int itemIdx = -1;
                    for (int i = 0; i < item.length; i++) {
                        if (item[i].equalsIgnoreCase(inputItem)) {
                            itemIdx = i;
                            break;
                        }
                    }
                    if (itemIdx == -1) {
                        throw new ItemNotFoundException("Item '" + inputItem + "' not found. Please try again");
                    }
                    // ask for the quantity of item
                    System.out.println("Enter the quantity of " + item[itemIdx] + " you want of purchase.");
                    int quantity = Integer.parseInt(scanner.nextLine());
                    scanner.nextLine();
                    float itemCost = quantity * price[itemIdx];
                    totalBill += itemCost;
                    System.out.println("Added " + quantity + " x " + item[itemIdx] + " to the bill. Current total : $" + totalBill);
                } catch (ItemNotFoundException e) {
                    System.out.println(e.getMessage());
                } catch (Exception e) {
                    System.out.println("Invalid Input! Please try again.");
                    scanner.nextLine();
                }
            }
        }
    }
}
