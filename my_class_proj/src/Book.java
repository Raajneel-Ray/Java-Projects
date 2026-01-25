// adding an overloaded constructor
/**
 *     // When you create an overloaded constructor, another constructor with the same name (using the class name) but different sets of parameters, you need to also create a default constructor.

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
 */
public class Book implements Cloneable {
    private String title;
    private String author;
    private float price;

    // Default constructor
    public Book() {
        this.title = "Unknown";
        this.author = "Unknown";
        this.price = 0.0f;
    }

    // Overloaded constructor
    public Book(String title, String author, float price) {
        this.title = title;
        this.author = author;
        this.price = price;
    }

    // Clone method
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    // Setters
    public void setTitle(String title) { this.title = title; }
    public void setAuthor(String author) { this.author = author; }
    public void setPrice(float price) { this.price = price; }

    // Getters
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public float getPrice() { return price; }

    // toString
    @Override
    public String toString() {
        return "Title - " + title +
               "\nAuthor - " + author +
               "\nPrice - " + String.format("%.2f", price);
    }

    // Proper equals method
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Book book = (Book) obj;

        return Float.compare(book.price, price) == 0 &&
               title.equals(book.title) &&
               author.equals(book.author);
    }

    // hashCode (required when equals is overridden)
    @Override
    public int hashCode() {
        int result = title.hashCode();
        result = 31 * result + author.hashCode();
        result = 31 * result + Float.hashCode(price);
        return result;
    }
}
