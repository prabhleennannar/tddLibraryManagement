import java.util.List;
import java.util.Scanner;

/**
 * Displays the User Menu and performs the actions as per user's selection
 */
public class UserMenu {

    public static void getUserMenu(int userId){
        Scanner sc = new Scanner(System.in);
        System.out.println("\n=======================================");

        int userChoice = -1;
        while(userChoice == -1){
            System.out.println("= Please choose an option from User Menu =\n");
            System.out.println("""
                    Choose 1: List All Books
                    Choose 2: Borrow a Book
                    Choose 3: Search Books
                    Choose 4: Return a Book
                    Choose 5: List of Borrowed & Returned Books
                    Choose 6: Go back to previous Menu
                    Choose 7: Exit""");
            System.out.println("=======================================");
            System.out.print("\nPlease enter your choice: ");
            String inputInString = sc.next();

            userChoice = Utils.getChoiceForMenu(inputInString, 7);
        }
        performActionForUserMenu(userChoice, userId);
    }

    private static void performActionForUserMenu(int userChoice, int userId){
        Scanner sc = new Scanner(System.in);
        BookDB bDB = new BookDB();
        UserDB uDB = new UserDB();

        switch(userChoice){
            case 1 -> {
                System.out.println("= List of Books in the Library =");
                Utils.bookListFormatter(bDB.listAllBooks());
                getUserMenu(userId);
            }
            case 2 -> {
                System.out.println("= List of Books in the Library =");
                Utils.bookListFormatter(bDB.listAllBooks());

                System.out.println("Please enter the bookId of the book to borrow: ");

                int bookId = 0;
                try{
                    bookId = Integer.parseInt(sc.next());
                } catch (Exception e){
                    System.out.println("** Please enter bookId in correct format **");
                    getUserMenu(userId);
                }

                String borrowStatus = String.valueOf(uDB.borrowBook(userId, bookId));
                if(borrowStatus.equalsIgnoreCase(String.valueOf(IUserPersistence.BorrowedStatus.BORROWED_SUCCESS))){
                    System.out.println("Book with Book Id: " + bookId + " is successfully issued.");
                }else if(borrowStatus.equalsIgnoreCase(String.valueOf(IUserPersistence.BorrowedStatus.ALREADY_BORROWED))){
                    System.out.println("** Book with Book Id: " + bookId + " is already borrowed by you **");
                }else if(borrowStatus.equalsIgnoreCase(String.valueOf(IUserPersistence.BorrowedStatus.UNDER_AGE_BOOK))){
                    System.out.println("** Sorry! Please select a book within your age limit **");
                }else{
                    System.out.println("** Sorry! Book with Book Id: " + bookId + " is not available." +
                            " Please try again! **");
                }
                getUserMenu(userId);
            }
            case 3 -> {
                BookSearchMenu.getBookSearchMenu("user", userId);
            }
            case 4 -> {
                System.out.println("= List of borrowed & returned items are: ");
                List<BorrowedItems> borrowedItems = uDB.getBorrowedItemsByUserId(userId);
                if(!borrowedItems.isEmpty()){
                    Utils.borrowedItemsListFormatter(borrowedItems);
                    System.out.println("Please enter the Book Id to return a book: ");
                    int bookId = 0;

                    try{
                        bookId = Integer.parseInt(sc.next());
                    }catch (Exception e){
                        System.out.println("** Please enter Book Id in correct format **");
                        getUserMenu(userId);
                    }

                    String returnStatus = String.valueOf(uDB.returnBook(userId, bookId));
                    if(returnStatus.equalsIgnoreCase(String.valueOf(IUserPersistence.ReturnStatus.RETURN_SUCCESS))){
                        System.out.println("Book is successfully returned.");
                    }else{
                        System.out.println("** Something went wrong while returning the book **");
                    }
                }else{
                    System.out.println("** No item has been borrowed yet **");
                }
                getUserMenu(userId);
            }
            case 5 -> {
                System.out.println("= List of borrowed & returned items are: ");
                List<BorrowedItems> borrowedItems = uDB.getBorrowedItemsByUserId(userId);
                if(!borrowedItems.isEmpty()){
                    Utils.borrowedItemsListFormatter(borrowedItems);
                }else{
                    System.out.println("** No item has been borrowed yet **");
                }
                getUserMenu(userId);
            }
            case 6 -> {
                MainMenu.getMainMenu();
            }
            case 7 -> {
                System.out.println("** System exited Successfully **");
                System.exit(0);
            }
        }
    }
}
