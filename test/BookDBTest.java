import java.util.List;

/**
 * Test class for BookDB.java
 */
public class BookDBTest {

    public static void startBookDBTest() {
        System.out.println("** BOOK DB TEST CASES **");

        listAllBooksTest();

        System.out.println("===============================================================");
        searchByBookAuthorTest();

        System.out.println("===============================================================");
        searchByGenreTest();

        System.out.println("===============================================================");
        searchByBookNameTest();

        System.out.println("===============================================================");
        addBookTest();

        System.out.println("===============================================================");
        getBookByBookIdTest();

        System.out.println("===============================================================");
        updateBookQuantityTest();

        System.out.println("===============================================================");
        deleteBookTest();
    }

    private static void addBookTest() {
        BookDB bDB = new BookDB();

        System.out.println("Add Book Test Cases:");
        System.out.println("1. Add Book Successful(Different Name & Different Author)");
        int bookId = bDB.addBook("Book4", "Author4", "Comedy", 28, 5);
        showAddStatus(bookId);

        System.out.println("2. Add Book Successful(Same Name & Different Author)");
        bookId = bDB.addBook("Book3", "Author5", "Comic", 20, 9);
        showAddStatus(bookId);

        System.out.println("3. Add Book Successful(Different Name & Same Author)");
        bookId = bDB.addBook("Book5", "Author5", "Comic", 28, 13);
        showAddStatus(bookId);

        System.out.println("4. Add Book Fail(Same Name & Same Author)");
        bookId = bDB.addBook("Book1", "Author1", "Horror", 25, 5);
        if(bookId == -1){
            System.out.println("TEST SUCCESS\n");
        }else{
            System.out.println("TEST FAIL\n");
        }
    }

    private static void showAddStatus(int bookId) {
        if(bookId != -1){
            System.out.println("TEST SUCCESS\n");
        }else{
            System.out.println("TEST FAIL\n");
        }
    }

    private static void getBookByBookIdTest() {
        BookDB bDB = new BookDB();

        System.out.println("Get Book by Book Id Test Cases:");
        System.out.println("1. No Book is present with provided bookId");
        Book book = bDB.getBookByBookId(200);
        if(book == null){
            System.out.println("TEST SUCCESS\n");
        }else{
            System.out.println("TEST FAILED\n");
        }

        System.out.println("2. Book is present with given bookId");
        book = bDB.getBookByBookId(5551);
        if(book != null){
            System.out.println("TEST SUCCESS\n");
        }else{
            System.out.println("TEST FAILED\n");
        }
    }

    private static void updateBookQuantityTest() {
        BookDB bDB = new BookDB();

        System.out.println("Update Book Quantity Test Cases:");
        System.out.println("1. Book Quantity Updated Success(Provided BookId is valid)");
        String status = String.valueOf(bDB.updateBookQuantity(5551, 4));
        if(status.equalsIgnoreCase(String.valueOf(IBookPersistence.Status.BOOK_UPDATE_SUCCESS))){
            System.out.println("TEST SUCCESS\n");
        }else{
            System.out.println("TEST FAILED\n");
        }

        System.out.println("Update Book Quantity Test Cases:");
        System.out.println("2. Book Quantity Updated Fail(Provided BookId is invalid)");
        status = String.valueOf(bDB.updateBookQuantity(200, 8));
        if(status.equalsIgnoreCase(String.valueOf(IBookPersistence.Status.BOOK_UPDATE_FAILED))){
            System.out.println("TEST SUCCESS\n");
        }else{
            System.out.println("TEST FAILED\n");
        }
    }

    private static void deleteBookTest() {
        BookDB bDB = new BookDB();

        System.out.println("Delete Book Test Cases:");
        System.out.println("1. Book Delete Success");
        String status = String.valueOf(bDB.deleteBook(5555));
        if(status.equalsIgnoreCase(String.valueOf(IBookPersistence.Status.BOOK_REMOVED_SUCCESS))){
            System.out.println("TEST SUCCESS\n");
        }else{
            System.out.println("TEST FAILED\n");
        }

        System.out.println("2. Book Delete Fail(Book does not exist)");
        status = String.valueOf(bDB.deleteBook(222));
        if(status.equalsIgnoreCase(String.valueOf(IBookPersistence.Status.BOOK_DOES_NOT_EXIST))){
            System.out.println("TEST SUCCESS\n");
        }else{
            System.out.println("TEST FAILED\n");
        }

        System.out.println("3. Book Delete Fail(Book is borrowed by user)");
        status = String.valueOf(bDB.deleteBook(5551));
        if(status.equalsIgnoreCase(String.valueOf(IBookPersistence.Status.BOOK_BORROWED_BY_USER))){
            System.out.println("TEST SUCCESS\n");
        }else{
            System.out.println("TEST FAILED\n");
        }
    }

    private static void listAllBooksTest() {
        BookDB bDB = new BookDB();

        System.out.println("List Books Test Cases:");
        System.out.println("1. Books Present in DB");
        List<Book> books = bDB.listAllBooks();
        if(!books.isEmpty()){
            System.out.println("TEST SUCCESS\n");
        }else{
            System.out.println("TEST FAILED\n");
        }
    }

    private static void searchByBookNameTest() {
        BookDB bDB = new BookDB();

        System.out.println("Search Books with Particular Book Name Test Cases:");
        System.out.println("1. Book Name Present");
        List<Book> books = bDB.searchByBookName("Book1");
        if(!books.isEmpty()){
            System.out.println("TEST SUCCESS\n");
        }else{
            System.out.println("TEST FAILED\n");
        }

        System.out.println("1. Book Name Not Present");
        books = bDB.searchByBookName("Book9");
        if(books.isEmpty()){
            System.out.println("TEST SUCCESS\n");
        }else{
            System.out.println("TEST FAILED\n");
        }
    }

    private static void searchByGenreTest() {
        BookDB bDB = new BookDB();

        System.out.println("Search Books with Particular Genre Test Cases:");
        System.out.println("1. Book Genre Present");
        List<Book> books = bDB.searchByGenre("Horror");
        if(!books.isEmpty()){
            System.out.println("TEST SUCCESS\n");
        }else{
            System.out.println("TEST FAILED\n");
        }

        System.out.println("1. Book Genre Not Present");
        books = bDB.searchByGenre("Thriller");
        if(books.isEmpty()){
            System.out.println("TEST SUCCESS\n");
        }else{
            System.out.println("TEST FAILED\n");
        }
    }

    private static void searchByBookAuthorTest() {
        BookDB bDB = new BookDB();

        System.out.println("Search Books with Particular Book Author Test Cases:");
        System.out.println("1. Book Author Present");
        List<Book> books = bDB.searchByBookAuthor("Author1");
        if(!books.isEmpty()){
            System.out.println("TEST SUCCESS\n");
        }else{
            System.out.println("TEST FAILED\n");
        }

        System.out.println("1. Book Author Not Present");
        books = bDB.searchByBookAuthor("Author9");
        if(books.isEmpty()){
            System.out.println("TEST SUCCESS\n");
        }else{
            System.out.println("TEST FAILED\n");
        }
    }
}
