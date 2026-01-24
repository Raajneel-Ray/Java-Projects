// adding an overloaded constructor
public class Book {
    private  String title;
    private String author;
    private float price;

    public void setTitle(String title) {
        this.title = title;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public void setPrice(float price) {
        this.price = price;
    }
    public String getTitle() {
        return this.title;
    }
    public String getAuthor() {
        return this.author;
    }
    public float getPrice() {
        return this.price;
    }
    // When you create an overloaded constructor, another constructor with the same name (using the class name) but different sets of parameters, you need to also create a default constructor.

    public Book() { // This is the default constructor, which will create an instance but the values will have to be explicitly set with the setters.
        this.title = null;
        this.author = null;
    }
    public Book(String title, String author, float price) {
        //This is the constructor of the class which takes three parameters for each of the attribute. The value of each of the attribute is set with the parameter passed.
        this.title = title;
        this.author = author;
        this.price = price;
    }
    public String toString() {
        return "Title - " + this.title + "\nAuthor - "
            + this.author + "\nPrice - "+ String.format("%.2f", this.price);
    }
}