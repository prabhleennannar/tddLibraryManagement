import java.util.List;
import java.util.Scanner;

/**
 * Displays the Book Search Menu and performs the actions as per the selection
 */
public class BookSearchMenu {

    public static void getBookSearchMenu(String type, int id){
        Scanner sc = new Scanner(System.in);
        System.out.println("\n=======================================");

        int choice = -1;
        while(choice == -1){
            System.out.println("= Please choose an option from Book Search Menu =\n");
            System.out.println("""
                    Choose 1: Search book by Book Name
                    Choose 2: Search book by Book Author
                    Choose 3: Search book by Genre
                    Choose 4: Go back to previous Menu
                    Choose 5: Exit""");
            System.out.println("=======================================");
            System.out.print("\nPlease enter your choice: ");
            String inputInString = sc.next();

            choice = Utils.getChoiceForMenu(inputInString, 5);
        }
        performActionForBookSearch(choice, type, id);
    }

    private static void performActionForBookSearch(int choice, String type, int id){
        Scanner sc = new Scanner(System.in);
        BookDB bDB = new BookDB();

        switch(choice){
            case 1 -> {
                System.out.println("=======================================");
                System.out.println("Please enter the BookName: ");

                String bookName = sc.next();
                List<Book> requiredBooks = bDB.searchByBookName(bookName);

                if (!requiredBooks.isEmpty()){
                    System.out.println("== List of Books with Book Name: " + bookName + " ==");
                    Utils.bookListFormatter(requiredBooks);
                }else{
                    System.out.println("** No book found with book Name: " + bookName + " == Please try Again");
                }
                selectPreviousMenu(type, id);
            }
            case 2 -> {
                System.out.println("=======================================");
                System.out.println("Please enter the BookAuthor: ");

                String bookAuthor = sc.next();
                List<Book> requiredBooks = bDB.searchByBookAuthor(bookAuthor);

                if (!requiredBooks.isEmpty()){
                    System.out.println("== List of Books with Book Author: " + bookAuthor + " ==");
                    Utils.bookListFormatter(requiredBooks);
                }else{
                    System.out.println("** No book found with Book Author: " + bookAuthor + " == Please try Again");
                }
                selectPreviousMenu(type, id);
            }
            case 3 -> {
                System.out.println("=======================================");
                System.out.println("Please enter the BookGenre: ");

                String bookGenre = sc.next();
                List<Book> requiredBooks = bDB.searchByGenre(bookGenre);

                if (!requiredBooks.isEmpty()){
                    System.out.println("== List of Books with Book Genre: " + bookGenre + " ==");
                    Utils.bookListFormatter(requiredBooks);
                }else{
                    System.out.println("** No book found with Book Genre: " + bookGenre + " == Please try Again");
                }
                selectPreviousMenu(type, id);
            }
            case 4 -> {
                if(type.equalsIgnoreCase("user")){
                    UserMenu.getUserMenu(id);
                }else{
                    AdminMenu.getAdminMenu();
                }
            }
            case 5 -> {
                System.out.println("** System exited Successfully **");
                System.exit(0);            }
        }
    }

    private static void selectPreviousMenu(String type, int id) {
        if(type.equalsIgnoreCase("user")){
            getBookSearchMenu("user", id);
        }else{
            getBookSearchMenu("admin", 0);
        }
    }

}
