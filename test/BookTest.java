/**
 * Test class for Book.java
 */
public class BookTest {

    public static void startBookTest(){
        System.out.println("**** BOOK TEST CASES ****");
        bookSetterAndGetterTest();
    }

    /**
     * Performs tests for setter and getter of Book
     */
    private static void bookSetterAndGetterTest() {
        Book book = new Book();
        book.setBookId();
        book.setBookName("BookTest");
        book.setBookAuthor("AuthorTest");
        book.setBookGenre("GenreTest");
        book.setBookAgeLimit(20);
        book.setBookAvailableQuantity(5);

        System.out.println("Book Setter and Getter Test Cases");
        System.out.println("1. Book Id");
        int bookId = book.getBookId();
        if(book.getBookId() == bookId){
            System.out.println("TEST SUCCESS\n");
        }else{
            System.out.println("TEST FAIL\n");
        }

        System.out.println("2. Book Name");
        if(book.getBookName().equals("BookTest")){
            System.out.println("TEST SUCCESS\n");
        }else{
            System.out.println("TEST FAIL\n");
        }

        System.out.println("3. Book Author Name");
        if(book.getBookAuthor().equals("AuthorTest")){
            System.out.println("TEST SUCCESS\n");
        }else{
            System.out.println("TEST FAIL\n");
        }

        System.out.println("4. Book Genre");
        if(book.getBookGenre().equals("GenreTest")){
            System.out.println("TEST SUCCESS\n");
        }else{
            System.out.println("TEST FAIL\n");
        }

        System.out.println("5. Book Age Limit");
        if(book.getBookAgeLimit() == 20){
            System.out.println("TEST SUCCESS\n");
        }else{
            System.out.println("TEST FAIL\n");
        }

        System.out.println("6. Book Available Quantity");
        if(book.getBookAvailableQuantity() == 5){
            System.out.println("TEST SUCCESS\n");
        }else{
            System.out.println("TEST FAIL\n");
        }
    }

}
