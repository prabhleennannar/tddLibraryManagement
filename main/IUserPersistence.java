import java.util.List;

public interface IUserPersistence {

    public enum LoginStatus {
        LOGIN_SUCCESSFUL,
        INVALID_USER,
        INCORRECT_PASSWORD
    }

    public enum DeleteStatus{
        USER_DELETION_SUCCESS,
        USER_DELETION_FAILED
    }

    public enum BorrowedStatus{
        BORROWED_SUCCESS,
        ITEM_NOT_FOUND,
        UNDER_AGE_BOOK, ALREADY_BORROWED
    }

    public enum ReturnStatus{
        RETURN_SUCCESS,
        RETURN_FAILED
    }

    /**
     *
     * To verify the login credentials of Admin
     * @param userId of the user
     * @param password of the user
     * @return login status of the user
     */
    public LoginStatus loginUser(int userId, String password);

    /**
     *
     * To register a new user into the library DB
     */
    public int registerNewUser(String fName, String lName,String email, String pass, int age);

    /**
     *
     * Deletes the user from the library DB
     * @param userId of the user
     * @return delete status
     */
    public DeleteStatus deleteUser(int userId);

    /**
     * @return list of users in the library
     */
    public List<User> listUsers();

    /**
     *
     * @param id - userId of the user
     * @return required user based on userId
     */
    public User getUserById(int id);

    /**
     *
     * @return list of users who has only borrowed books
     */
    public List<User> getUsersWithOnlyBorrowedBooks();

    /**
     *
     * @return list of users who has borrowed
     * any item from library
     */
    public List<User> getUsersWithBorrowedItemsOnly();

    /**
     *
     * @param userId of the user
     * @param bookId of the book
     * @return borrowed status of the book borrowed by the user
     */
    public BorrowedStatus borrowBook(int userId, int bookId);

    /**
     * User returns the books
     * @param userId of the user
     * @param bookId of the bookId
     * @return return status of the book returned by the user
     */
    public ReturnStatus returnBook(int userId, int bookId);

    /**
     *
     * @param userId of the user
     * @return list of borrowed items by the user based on
     * userid
     */
    public List<BorrowedItems> getBorrowedItemsByUserId(int userId);

}
