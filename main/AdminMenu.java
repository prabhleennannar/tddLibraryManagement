import java.util.List;
import java.util.Scanner;

/**
 * Displays the Admin Menu and performs the actions as per admins' selection
 */
public class AdminMenu {

    public static void getAdminMenu(){
        Scanner sc = new Scanner(System.in);
        System.out.println("\n=======================================");

        int adminChoice = -1;
        while(adminChoice == -1){
            System.out.println("= Please choose an option from Admin Menu =\n");
            System.out.println("""
                    Choose 1: Add a New Book
                    Choose 2: View All Books
                    Choose 3: Delete a Book
                    Choose 4: Update Book's Quantity
                    Choose 5: Search Books
                    Choose 6: List All Users
                    Choose 7: List Users only with Borrowed Books
                    Choose 8: Delete a User
                    Choose 9: Go back to previous Menu
                    Choose 10: Exit""");
            System.out.println("=======================================");
            System.out.print("\nPlease enter your choice: ");
            String inputInString = sc.next();

            adminChoice = Utils.getChoiceForMenu(inputInString, 10);
        }
        performActionForAdminMenu(adminChoice);
    }

    private static void performActionForAdminMenu(int choice){
        Scanner sc = new Scanner(System.in);
        UserDB uDB = new UserDB();
        BookDB bDB = new BookDB();

        switch(choice){
            case 1 ->{
                System.out.println("== Please enter details to add a New Book ==\n");
                System.out.print("Enter Book Name: ");
                String bName = sc.next();
                System.out.print("Enter Book Author: ");
                String bAuthor = sc.next();
                System.out.print("Enter Book Genre: ");
                String bGenre = sc.next();

                int ageLimit = 0;
                int bookQuantity = 0;
                try{
                    System.out.print("Enter Age limit for Book: ");
                    ageLimit = Integer.parseInt(sc.next());
                    System.out.print("Enter Number of books in stock: ");
                    bookQuantity = Integer.parseInt(sc.next());
                }catch (Exception e){
                    System.out.println("** Please enter values in correct format. Try again!");
                    performActionForAdminMenu(1);
                }

                int bookId = bDB.addBook(bName, bAuthor, bGenre, ageLimit, bookQuantity);
                if (bookId == -1) {
                    System.out.println("** Sorry! Book Already Exists! Please try again!**");
                    getAdminMenu();
                } else {
                    System.out.println("New Book Added Successfully with bookId: " + bookId);
                    getAdminMenu();
                }
            }
            case 2 -> {
                System.out.println("= List of Books in the Library =");
                Utils.bookListFormatter(bDB.listAllBooks());
                getAdminMenu();
            }

            case 3 -> {
                System.out.println("= List of Books in the Library =");
                Utils.bookListFormatter(bDB.listAllBooks());
                System.out.println("=======================================\n");

                System.out.print("Please enter the book ID to delete a book: ");
                int bookId = 0;
                try{
                    bookId = Integer.parseInt(sc.next());
                }catch (Exception e){
                    System.out.println("** Please enter book Id in correct format **");
                    getAdminMenu();
                }

                String deleteStatus = String.valueOf(bDB.deleteBook(bookId));
                if(deleteStatus.equalsIgnoreCase(String.valueOf(IBookPersistence.Status.BOOK_REMOVED_SUCCESS))){
                    System.out.println("Book removed successfully!");
                }else if(deleteStatus.equalsIgnoreCase(String.valueOf(IBookPersistence.Status.BOOK_BORROWED_BY_USER))){
                    System.out.println("** Book with bookId: " + bookId + " cannot be deleted as it is already " +
                            "borrowed by a user **");
                }else{
                    System.out.println("** Book with bookId: " + bookId + " does not exists. Please try again!");
                }
                getAdminMenu();
            }
            case 4 ->{
                System.out.println("= List of Books in the Library =");
                Utils.bookListFormatter(bDB.listAllBooks());
                System.out.println("=======================================\n");

                System.out.print("Please enter the book ID: ");
                int bookId = 0;
                try{
                    bookId = Integer.parseInt(sc.next());
                }catch (Exception e){
                    System.out.println("** Please enter book Id in correct format **");
                    getAdminMenu();
                }

                System.out.print("Please enter the number of books to add: ");
                int quantity = 0;
                try{
                    quantity = Integer.parseInt(sc.next());
                }catch (Exception e){
                    System.out.println("** Please enter quantity of books in correct format **");
                    getAdminMenu();
                }

                String quantityUpdateStatus = String.valueOf(bDB.updateBookQuantity(bookId, quantity));
                if(quantityUpdateStatus.equalsIgnoreCase(String.valueOf(IBookPersistence.Status.BOOK_UPDATE_SUCCESS))){
                    System.out.println("Book's quantity updated successfully!");
                }else{
                    System.out.println("** Book with bookId: " + bookId + " update failed. Please check the bookId!");
                }
                getAdminMenu();
            }
            case 5 -> {
                BookSearchMenu.getBookSearchMenu("admin", 0);
            }
            case 6 -> {
                System.out.println("== List of Users of the Library ==");
                Utils.userListFormatter(uDB.listUsers());
                getAdminMenu();
            }
            case 7 -> {
                List<User> requiredUsers = uDB.getUsersWithOnlyBorrowedBooks() ;
                if(!requiredUsers.isEmpty()){
                    System.out.println("= List of Users with Borrowed items: =");
                    Utils.userListFormatter(requiredUsers);
                }else{
                    System.out.println("** No User has borrowed any book from the library **");
                }
                getAdminMenu();
            }
            case 8 -> {
                System.out.println("== List of Users of the Library ==");
                Utils.userListFormatter(uDB.listUsers());

                System.out.print("Please enter the user ID to delete a user: ");
                int userId = 0;
                try{
                    userId = Integer.parseInt(sc.next());
                }catch(Exception e){
                    System.out.print("** Please enter user ID in correct format. Please try again!");
                    getAdminMenu();
                }

                String deleteStatus = String.valueOf(uDB.deleteUser(userId));
                if(deleteStatus.equalsIgnoreCase(String.valueOf(IUserPersistence.DeleteStatus.USER_DELETION_SUCCESS))){
                    System.out.println("User deleted Successfully!");
                }else{
                    System.out.println("** User has some borrowed items. Please check the User details for more Info **");
                }
                getAdminMenu();
            }
            case 9 -> {
                MainMenu.getMainMenu();
            }
            case 10 -> {
                System.out.println("** System exited Successfully **");
                System.exit(0);
            }
        }
    }
}
