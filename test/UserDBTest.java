import java.util.ArrayList;
import java.util.List;

public class UserDBTest {

    public static void startUserDBTest(){
        System.out.println("**** USER DB TEST CASES ****");

        listUsersTest();

        System.out.println("===============================================================");
        loginUserTest();

        System.out.println("===============================================================");
        registerNewUserTest();

        System.out.println("===============================================================");
        getUserByIdTest();

        System.out.println("===============================================================");
        deleteUserTest();

        System.out.println("===============================================================");
        areAllBorrowedItemsReturnedTest();

        System.out.println("===============================================================");
        getUsersWithOnlyBorrowedBooksTest();

        System.out.println("===============================================================");
        getUsersWithBorrowedItemsOnlyTest();

        System.out.println("===============================================================");
        getBorrowedItemsByUserIdTest();

        System.out.println("===============================================================");
        borrowBookTest();

        System.out.println("===============================================================");
        returnBookTest();
    }

    private static void loginUserTest() {
        UserDB uDB = new UserDB();

        System.out.println("User Login Test Cases:");
        System.out.println("1. Invalid User");
        String status = String.valueOf(uDB.loginUser(2333, "user1@123"));
        if(status.equalsIgnoreCase(String.valueOf(IUserPersistence.LoginStatus.INVALID_USER))) {
            System.out.println("TEST SUCCESS\n");
        }else{
            System.out.println("TEST FAILED\n");
        }

        System.out.println("2. Incorrect Password");
        status = String.valueOf(uDB.loginUser(1111, "user@123"));
        if(status.equalsIgnoreCase(String.valueOf(IUserPersistence.LoginStatus.INCORRECT_PASSWORD))) {
            System.out.println("TEST SUCCESS\n");
        }else{
            System.out.println("TEST FAILED\n");
        }

        System.out.println("3. Successful Login");
        status = String.valueOf(uDB.loginUser(1111, "user1@123"));
        if(status.equalsIgnoreCase(String.valueOf(IUserPersistence.LoginStatus.LOGIN_SUCCESSFUL))) {
            System.out.println("TEST SUCCESS\n");
        }else{
            System.out.println("TEST FAILED\n");
        }
    }

    private static void registerNewUserTest() {
        UserDB uDB = new UserDB();

        System.out.println("Register User Test Cases:");
        System.out.println("1. Register Successful");
        int userId = uDB.registerNewUser("User2", "Test2", "user2@test2.com",
                "user2@123", 25);
        if(userId != -1){
            System.out.println("TEST SUCCESS\n");
        }else{
            System.out.println("TEST FAILED\n");
        }

        System.out.println("1. Registration Failed, User Already exists.");
        userId = uDB.registerNewUser("User1", "Test1", "user1@test1.com",
                "user2@123", 25);
        if(userId == -1){
            System.out.println("TEST SUCCESS\n");
        }else{
            System.out.println("TEST FAILED\n");
        }
    }

    private static void listUsersTest() {
        UserDB uDB = new UserDB();
        List<User> users = uDB.listUsers();

        System.out.println("List User Test Cases:");
        System.out.println("1. User Present in Database");
        if(!users.isEmpty()){
            System.out.println("TEST SUCCESS\n");
        }else{
            System.out.println("TEST FAILED\n");
        }

        System.out.println("2. Count of Users Present in Database");
        if(users.size() == 5){
            System.out.println("TEST SUCCESS\n");
        }else{
            System.out.println("TEST FAILED\n");
        }
    }

    private static void getUserByIdTest() {
        UserDB uDB = new UserDB();

        System.out.println("Get User by User Id Test Cases:");
        System.out.println("1. No User is present with given userId");
        User user = uDB.getUserById(1);
        if(user == null){
            System.out.println("TEST SUCCESS\n");
        }else{
            System.out.println("TEST FAILED\n");
        }

        System.out.println("2. User is present with given userId");
        user = uDB.getUserById(1112);
        if(user != null){
            System.out.println("TEST SUCCESS\n");
        }else{
            System.out.println("TEST FAILED\n");
        }
    }

    private static void deleteUserTest() {
        UserDB uDB = new UserDB();

        System.out.println("Delete Test Cases:");
        System.out.println("1. User Delete Success");
        String status = String.valueOf(uDB.deleteUser(1115));
        if(status.equalsIgnoreCase(String.valueOf(IUserPersistence.DeleteStatus.USER_DELETION_SUCCESS))){
            System.out.println("TEST SUCCESS\n");
        }else{
            System.out.println("TEST FAILED\n");
        }

        System.out.println("2. User Delete Fail(User does not exist)");
        status = String.valueOf(uDB.deleteUser(2));
        showDeleteStatus(status);

        System.out.println("2. User Delete Fail(User has still borrowed items)");
        status = String.valueOf(uDB.deleteUser(1111));
        showDeleteStatus(status);
    }

    private static void showDeleteStatus(String status) {
        if(status.equalsIgnoreCase(String.valueOf(IUserPersistence.DeleteStatus.USER_DELETION_FAILED))){
            System.out.println("TEST SUCCESS\n");
        }else{
            System.out.println("TEST FAILED\n");
        }
    }

    private static void areAllBorrowedItemsReturnedTest() {
        UserDB uDB = new UserDB();

        System.out.println("Check Borrowed Items if Returned Test Cases:");
        System.out.println("1. User with no borrowed items");
        User user = new User("User3", "Test3", "user3@test3.com",
                "user3@123", 25);
        boolean status = uDB.areAllBorrowedItemsReturned(user);
        if(status){
            System.out.println("TEST SUCCESS\n");
        }else{
            System.out.println("TEST FAILED\n");
        }

        System.out.println("2. User with borrowed Items and Not Returned");
        List<BorrowedItems> borrowedItems = new ArrayList<>();
        BorrowedItems bI = new BorrowedItems(user.getUserId(), 5552, "book");
        borrowedItems.add(bI);
        user.setBorrowedItemsByUser(borrowedItems);
        status = uDB.areAllBorrowedItemsReturned(user);
        if(!status){
            System.out.println("TEST SUCCESS\n");
        }else{
            System.out.println("TEST FAILED\n");
        }

        System.out.println("3. User with borrowed Items and All Returned");
        List<BorrowedItems> borrowedItemsList = user.getBorrowedItemsByUser();
        borrowedItemsList.forEach(borrowedItem -> {
            if (borrowedItem.getCurrentStatus().equalsIgnoreCase(
                    String.valueOf(BorrowedItems.BorrowedItemStatus.BORROWED))){
                borrowedItem.setCurrentStatus(String.valueOf(BorrowedItems.BorrowedItemStatus.RETURNED));
            }
        });
        status = uDB.areAllBorrowedItemsReturned(user);
        if(status){
            System.out.println("TEST SUCCESS\n");
        }else{
            System.out.println("TEST FAILED\n");
        }
    }

    private static void getUsersWithOnlyBorrowedBooksTest() {
        UserDB uDB = new UserDB();

        System.out.println("Get Users with Borrowed Books Only Test Cases:");
        System.out.println("1. User with Borrowed Books Only");
        List<User> users = uDB.getUsersWithOnlyBorrowedBooks();
        if(users.size() == 2){
            System.out.println("TEST SUCCESS\n");
        }else{
            System.out.println("TEST FAIL\n");
        }
    }

    private static void getUsersWithBorrowedItemsOnlyTest() {
        UserDB uDB = new UserDB();

        System.out.println("Get Users with Borrowed Items where status is BORROWED Test Cases:");
        System.out.println("1. User with Borrowed Items Only");
        List<User> users = uDB.getUsersWithBorrowedItemsOnly();
        users.forEach(user -> System.out.println(user.getUserId()));
        if(users.size() == 2){
            System.out.println("TEST SUCCESS\n");
        }else{
            System.out.println("TEST FAIL\n");
        }
    }

    private static void getBorrowedItemsByUserIdTest() {
        UserDB uDB = new UserDB();

        System.out.println("Get Borrowed Items By User Id Test Cases:");
        System.out.println("1. User with Borrowed Items");
        List<BorrowedItems> borrowedItemsList = uDB.getBorrowedItemsByUserId(1111);
        if(borrowedItemsList.size() == 1){
            System.out.println("TEST SUCCESS\n");
        }else{
            System.out.println("TEST FAIL\n");
        }

        System.out.println("2. User with No Borrowed Items");
        borrowedItemsList = uDB.getBorrowedItemsByUserId(1112);
        if(borrowedItemsList.size() == 0){
            System.out.println("TEST SUCCESS\n");
        }else{
            System.out.println("TEST FAIL\n");
        }
    }

    private static void borrowBookTest() {
        UserDB uDB = new UserDB();

        System.out.println("Borrow Book Test Cases:");
        System.out.println("1. Book is already Borrowed");
        String status = String.valueOf(uDB.borrowBook(1111, 5551));
        System.out.println("Borrrrowedd " + status);
        if(status.equalsIgnoreCase(String.valueOf(IUserPersistence.BorrowedStatus.ALREADY_BORROWED))){
            System.out.println("TEST SUCCESS\n");
        }else{
            System.out.println("TEST FAIL\n");
        }

        System.out.println("2. Book Borrowed Successfully");
        status = String.valueOf(uDB.borrowBook(1111, 5554));
        if(status.equalsIgnoreCase(String.valueOf(IUserPersistence.BorrowedStatus.BORROWED_SUCCESS))){
            System.out.println("TEST SUCCESS\n");
        }else{
            System.out.println("TEST FAIL\n");
        }

        System.out.println("3. Under Age Book");
        status = String.valueOf(uDB.borrowBook(1111, 5552));
        if(status.equalsIgnoreCase(String.valueOf(IUserPersistence.BorrowedStatus.UNDER_AGE_BOOK))){
            System.out.println("TEST SUCCESS\n");
        }else{
            System.out.println("TEST FAIL\n");
        }

        System.out.println("4. Book does not exist(Invalid bookId)");
        status = String.valueOf(uDB.borrowBook(1114, 7753));
        if(status.equalsIgnoreCase(String.valueOf(IUserPersistence.BorrowedStatus.ITEM_NOT_FOUND))){
            System.out.println("TEST SUCCESS\n");
        }else{
            System.out.println("TEST FAIL\n");
        }

        System.out.println("5. Book does not exist(Book is out of stock)");
        status = String.valueOf(uDB.borrowBook(1114, 5553));
        if(status.equalsIgnoreCase(String.valueOf(IUserPersistence.BorrowedStatus.ITEM_NOT_FOUND))){
            System.out.println("TEST SUCCESS\n");
        }else{
            System.out.println("TEST FAIL\n");
        }

        System.out.println("6. If same book is borrowed again (earlier the book was returned). " +
                "Change Status from RETURNED to OLD_RECORD");
        status = String.valueOf(uDB.borrowBook(1113, 5554));
        if(status.equalsIgnoreCase(String.valueOf(IUserPersistence.BorrowedStatus.BORROWED_SUCCESS))){
            System.out.println("TEST SUCCESS\n");
        }else{
            System.out.println("TEST FAIL\n");
        }
    }

    private static void returnBookTest() {
        UserDB uDB = new UserDB();

        System.out.println("Return Book Test Cases:");
        System.out.println("1. Book Return Successful");
        String status = String.valueOf(uDB.returnBook(1111, 5551));
        if(status.equalsIgnoreCase(String.valueOf(IUserPersistence.ReturnStatus.RETURN_SUCCESS))){
            System.out.println("TEST SUCCESS\n");
        }else{
            System.out.println("TEST FAIL\n");
        }

        System.out.println("2. Book Return Failed(Book is already Returned)");
        status = String.valueOf(uDB.returnBook(1111, 5551));
        if(status.equalsIgnoreCase(String.valueOf(IUserPersistence.ReturnStatus.RETURN_FAILED))){
            System.out.println("TEST SUCCESS\n");
        }else{
            System.out.println("TEST FAIL\n");
        }

        System.out.println("3. Book Return Failed(Book with wrong bookId is being returned)");
        status = String.valueOf(uDB.returnBook(1111, 7755));
        if(status.equalsIgnoreCase(String.valueOf(IUserPersistence.ReturnStatus.RETURN_FAILED))){
            System.out.println("TEST SUCCESS\n");
        }else{
            System.out.println("TEST FAIL\n");
        }
    }

}
