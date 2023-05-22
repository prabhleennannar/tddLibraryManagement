import java.util.ArrayList;
import java.util.List;

/**
 * Responsible for handling all the
 * book related database operations
 */
public class BookDB implements IBookPersistence{

    @Override
    public int addBook(String bName, String bAuthor, String bGenre, int ageLimit, int bookQuantity) {
        if(!DatabaseWithMockData.books.isEmpty()) {
            for (Book b : DatabaseWithMockData.books) {
                if (bName.equalsIgnoreCase(b.getBookName()) && bAuthor.equalsIgnoreCase(b.getBookAuthor())) {
                    return -1;
                }
            }
        }
        Book book = new Book(bName, bAuthor, bGenre, ageLimit, bookQuantity);
        DatabaseWithMockData.books.add(book);
        return book.getBookId();
    }

    @Override
    public Book getBookByBookId(int bookId){
        if(!DatabaseWithMockData.books.isEmpty()){
            for(Book b: DatabaseWithMockData.books) {
                if (bookId == b.getBookId()) {
                    return b;
                }
            }
        }
        return null;
    }

    @Override
    public Status updateBookQuantity(int bookId, int quantity) {
        if(!DatabaseWithMockData.books.isEmpty()){
            for(Book b: DatabaseWithMockData.books){
                if(b.getBookId() == bookId){
                    int q = b.getBookAvailableQuantity();
                    b.setBookAvailableQuantity(q + quantity);
                    return Status.BOOK_UPDATE_SUCCESS;
                }
            }
        }
        return Status.BOOK_UPDATE_FAILED;
    }

    @Override
    public Status deleteBook(int bookId) {
        if(!DatabaseWithMockData.books.isEmpty()){
            if(!DatabaseWithMockData.borrowedItems.isEmpty()){
                for(BorrowedItems borrowedItem: DatabaseWithMockData.borrowedItems){
                    if("book".equalsIgnoreCase(borrowedItem.getType()) && bookId == borrowedItem.getBorrowedItemId()
                            && borrowedItem.getCurrentStatus().equalsIgnoreCase(
                            String.valueOf(BorrowedItems.BorrowedItemStatus.BORROWED))){
                        return Status.BOOK_BORROWED_BY_USER;
                    }
                }
                if(DatabaseWithMockData.books.removeIf(
                        book -> book.getBookId() == bookId)){
                    return Status.BOOK_REMOVED_SUCCESS;
                }
            }else{
                if(DatabaseWithMockData.books.removeIf(
                        book -> book.getBookId() == bookId)){
                    return Status.BOOK_REMOVED_SUCCESS;
                }
            }
        }
        return Status.BOOK_DOES_NOT_EXIST;
    }

    @Override
    public List<Book> listAllBooks() {
        return DatabaseWithMockData.books;
    }

    @Override
    public List<Book> searchByBookAuthor(String bookAuthorName) {
        List<Book> requiredBooks = new ArrayList<>();
        if(!DatabaseWithMockData.books.isEmpty()){
            for(Book b: DatabaseWithMockData.books){
                if(b.getBookAuthor().equalsIgnoreCase(bookAuthorName)){
                    requiredBooks.add(b);
                }
            }
        }
        return requiredBooks;
    }

    @Override
    public List<Book> searchByGenre(String bookGenre) {
        List<Book> requiredBooks = new ArrayList<>();
        if(!DatabaseWithMockData.books.isEmpty()){
            for(Book b: DatabaseWithMockData.books){
                if(b.getBookGenre().equalsIgnoreCase(bookGenre)){
                    requiredBooks.add(b);
                }
            }
        }
        return requiredBooks;
    }

    @Override
    public List<Book> searchByBookName(String bookName) {
        List<Book> requiredBooks = new ArrayList<>();
        if(!DatabaseWithMockData.books.isEmpty()){
            for(Book b: DatabaseWithMockData.books){
                if(b.getBookName().equalsIgnoreCase(bookName)){
                    requiredBooks.add(b);
                }
            }
        }
        return requiredBooks;
    }
}