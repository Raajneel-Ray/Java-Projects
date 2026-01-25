import java.util.Scanner;

public class BooksMenu {

    private static Book getExpensiveBook(Book book1, Book book2) {
        return (book1.getPrice() < book2.getPrice()) ? book2 : book1;
    }

    private static boolean isValidIndex(int index, Book[] books) {
        return index >= 0 && index < books.length && books[index] != null;
    }

    public static void main(String[] s) throws CloneNotSupportedException {
        Scanner scanner = new Scanner(System.in);
        Book[] books = new Book[10];
        int bkIdx = 0;

        while (true) {
            System.out.println("\nPress 1 to view books," +
                    "\n2 to add books," +
                    "\n3 to change the price of a book" +
                    "\n4 to compare two books (details)" +
                    "\n5 to find the more expensive book" +
                    "\nAny other key to exit");

            String userAction = scanner.nextLine();

            // VIEW BOOKS
            if (userAction.equals("1")) {
                for (int i = 0; i < books.length; i++) {
                    if (books[i] != null) {
                        System.out.println("Index " + i + ":\n" + books[i]);
                    }
                }
            }

            // ADD BOOK
            else if (userAction.equals("2")) {
                if (bkIdx == books.length) {
                    System.out.println("10 books already added. Cannot add more!");
                    continue;
                }

                System.out.println("How do you want to create a book?");
                System.out.println("1 - Default constructor");
                System.out.println("2 - Clone an existing book");
                System.out.println("Any other key - Overloaded constructor");

                String constructor = scanner.nextLine();

                // DEFAULT CONSTRUCTOR
                if (constructor.equals("1")) {
                    Book bkTmp = new Book();

                    System.out.println("Enter book title:");
                    bkTmp.setTitle(scanner.nextLine());

                    System.out.println("Enter book author:");
                    bkTmp.setAuthor(scanner.nextLine());

                    System.out.println("Enter book price:");
                    bkTmp.setPrice(Float.parseFloat(scanner.nextLine()));

                    books[bkIdx++] = bkTmp;
                }

                // CLONE CONSTRUCTOR
                else if (constructor.equals("2")) {
                    System.out.println("Enter index of the book to clone:");
                    int cloneIdx = Integer.parseInt(scanner.nextLine());

                    if (isValidIndex(cloneIdx, books)) {
                        Book clonedBook = (Book) books[cloneIdx].clone();

                        System.out.println("Enter NEW title for cloned book:");
                        clonedBook.setTitle(scanner.nextLine());

                        System.out.println("Enter NEW author for cloned book:");
                        clonedBook.setAuthor(scanner.nextLine());

                        System.out.println("Enter NEW price for cloned book:");
                        clonedBook.setPrice(Float.parseFloat(scanner.nextLine()));

                        books[bkIdx++] = clonedBook;
                    } else {
                        System.out.println("Invalid index to clone!");
                    }
                }

                // OVERLOADED CONSTRUCTOR
                else {
                    System.out.println("Enter book title:");
                    String title = scanner.nextLine();

                    System.out.println("Enter book author:");
                    String author = scanner.nextLine();

                    System.out.println("Enter book price:");
                    float price = Float.parseFloat(scanner.nextLine());

                    books[bkIdx++] = new Book(title, author, price);
                }
            }

            // CHANGE PRICE
            else if (userAction.equals("3")) {
                System.out.println("Enter index of the book to change price:");
                int idx = Integer.parseInt(scanner.nextLine());

                if (isValidIndex(idx, books)) {
                    System.out.println("Enter new price:");
                    float newPrice = Float.parseFloat(scanner.nextLine());
                    books[idx].setPrice(newPrice);
                } else {
                    System.out.println("Invalid book index!");
                }
            }

            // COMPARE BOOK DETAILS
            else if (userAction.equals("4")) {
                System.out.println("Enter index of first book:");
                int b1 = Integer.parseInt(scanner.nextLine());

                System.out.println("Enter index of second book:");
                int b2 = Integer.parseInt(scanner.nextLine());

                if (isValidIndex(b1, books) && isValidIndex(b2, books)) {
                    if (books[b1].equals(books[b2])) {
                        System.out.println("The books are equal.");
                    } else {
                        System.out.println("The books are NOT equal.");
                    }
                } else {
                    System.out.println("One or both indices are invalid!");
                }
            }

            // FIND MORE EXPENSIVE BOOK
            else if (userAction.equals("5")) {
                System.out.println("Enter index of first book:");
                int b1 = Integer.parseInt(scanner.nextLine());

                System.out.println("Enter index of second book:");
                int b2 = Integer.parseInt(scanner.nextLine());

                if (isValidIndex(b1, books) && isValidIndex(b2, books)) {
                    Book expensive = getExpensiveBook(books[b1], books[b2]);
                    System.out.println("More expensive book:\n" + expensive);
                } else {
                    System.out.println("Invalid indices!");
                }
            }

            // EXIT
            else {
                System.out.println("Exiting program...");
                break;
            }
        }

        scanner.close();
    }
}