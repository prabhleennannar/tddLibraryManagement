import java.util.concurrent.atomic.AtomicInteger;

public class Book {

    private static final AtomicInteger count = new AtomicInteger(5550);

    private int bookId;

    private String bookName;

    private String bookAuthor;

    private String bookGenre;

    private int bookAgeLimit;

    private int bookAvailableQuantity;

    public Book(){}

    public Book(String bookName, String bookAuthor, String bookGenre, int bookAgeLimit, int bookAvailableQuantity) {
        this.bookId = count.incrementAndGet();
        this.bookName = bookName;
        this.bookAuthor = bookAuthor;
        this.bookGenre = bookGenre;
        this.bookAgeLimit = bookAgeLimit;
        this.bookAvailableQuantity = bookAvailableQuantity;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId() {
        this.bookId = count.incrementAndGet();;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public String getBookGenre() {
        return bookGenre;
    }

    public void setBookGenre(String bookGenre) {
        this.bookGenre = bookGenre;
    }

    public int getBookAgeLimit() {
        return bookAgeLimit;
    }

    public void setBookAgeLimit(int bookAgeLimit) {
        this.bookAgeLimit = bookAgeLimit;
    }

    public int getBookAvailableQuantity() {
        return bookAvailableQuantity;
    }

    public void setBookAvailableQuantity(int bookAvailableQuantity) {
        this.bookAvailableQuantity = bookAvailableQuantity;
    }
}
