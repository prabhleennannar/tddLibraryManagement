import java.util.List;

public interface IBookPersistence {

    public enum Status{
        BOOK_ADDED,
        BOOK_UPDATE_FAILED,
        BOOK_UPDATE_SUCCESS,
        BOOK_REMOVED_SUCCESS, BOOK_DOES_NOT_EXIST,
        BOOK_BORROWED_BY_USER;
    }

    /**
     *
     * Adds a new book to the database if it is not a duplicate
     */
    public int addBook(String bName, String bAuthor, String bGenre, int ageLimit, int bookQuantity);

    /**
     *
     * Returns the Book details based on bookId
     */
    public Book getBookByBookId(int bookId);

    /**
     * Updates the quantity of a specific book in the DB
     * based on bookId
     * @param bookId of the book
     * @param quantity of the book
     * @return update status of the book
     */
    public Status updateBookQuantity(int bookId, int quantity);

    /**
     * Deletes a book from the DB  based on bookId
     * if book is not associated to any user
     * @param bookId of the book
     * @return delete status of the book
     */
    public Status deleteBook(int bookId);

    /**
     *
     * @return list of books in the library DB
     */
    public List<Book> listAllBooks();

    /**
     *
     * @param bookAuthorName of the book
     * @return list of books of specific author
     */
    public List<Book> searchByBookAuthor(String bookAuthorName);

    /**
     *
     * @param bookGenre of the book
     * @return list of books of specific genre
     */
    public List<Book> searchByGenre(String bookGenre);

    /**
     *
     * @param bookName of the book
     * @return list of books with specific book name
     */
    public List<Book> searchByBookName(String bookName);
}
