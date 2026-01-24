import java.util.Scanner;

class ItemNotFoundException extends Exception {
    public ItemNotFoundException(String message) {
        super(message);
    }
}

public class GroceryShopping {

    public static void searchItem(String[] items, String itemName) {
        boolean found = false;
        for (int i = 0; i < items.length; i++) {
            if (items[i].equalsIgnoreCase(itemName)) {
                found = true;
                System.out.println("The item is found at index " + i);
                break;
            }
        }
        if (!found) {
            System.out.println("Item not found");
        }
    }

    public static float calculateAveragePrice(float[] prices) {
        float sum = 0;
        for (float p : prices) sum += p;
        return sum / prices.length;
    }

    public static void filterItemsBelowPrice(String[] items, float[] prices, float threshold) {
        System.out.println("\nItems below $" + threshold + ":");
        for (int i = 0; i < prices.length; i++) {
            if (prices[i] < threshold) {
                System.out.println("- " + items[i] + " ($" + prices[i] + ")");
            }
        }
    }

    public static void main(String[] args) {

        String[] item = {"Apple","Banana","Bread","Milk","Eggs","Cheese","Chicken","Rice","Pasta","Tomato"};
        float[] price = {0.50f,0.30f,2.00f,1.50f,2.50f,3.00f,5.00f,1.00f,1.20f,0.80f};
        int[] stock = {10,15,8,10,12,6,5,20,18,25};

        System.out.println("Average price: $" + calculateAveragePrice(price));
        filterItemsBelowPrice(item, price, 1.0f);
        searchItem(item, "Milk");
        searchItem(item, "Mango");

        Scanner scanner = new Scanner(System.in);
        float totalBill = 0.0f;

        while (true) {
            try {
                System.out.println("\nEnter item name (or 'finish' to checkout):");
                String inputItem = scanner.nextLine();

                if (inputItem.equalsIgnoreCase("finish")) {
                    System.out.println("Original Total Bill: $" + totalBill);
                    if (totalBill > 100) {
                        float discountedTotal = totalBill * 0.9f;
                        System.out.println("Discount Applied! New Total: $" + discountedTotal);
                    } else {
                        System.out.println("No discount applied.");
                    }
                    System.out.println("Thank you for shopping!");
                    break;
                }

                int itemIdx = -1;
                for (int i = 0; i < item.length; i++) {
                    if (item[i].equalsIgnoreCase(inputItem)) {
                        itemIdx = i;
                        break;
                    }
                }

                if (itemIdx == -1) {
                    throw new ItemNotFoundException("Item not found.");
                }

                System.out.println("Enter quantity:");
                int quantity = Integer.parseInt(scanner.nextLine());

                if (quantity > stock[itemIdx]) {
                    System.out.println("Sorry, only " + stock[itemIdx] + " left.");
                    continue;
                }

                float cost = quantity * price[itemIdx];
                totalBill += cost;
                stock[itemIdx] -= quantity;

                System.out.println("Added to cart. Current total: $" + totalBill);

            } catch (ItemNotFoundException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println("Invalid input! Try again.");
            }
        }

        scanner.close();
    }
}
