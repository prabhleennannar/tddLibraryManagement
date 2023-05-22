import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Responsible for handling all the
 * user related database operations
 */
public class UserDB implements IUserPersistence{

    @Override
    public LoginStatus loginUser(int userId, String password) {
        if(!DatabaseWithMockData.users.isEmpty()){
            for(User user: DatabaseWithMockData.users){
                int uId = user.getUserId();
                String uPass = user.getUserPassword();

                if(uId == userId){
                    if(uPass.equals(password)){
                        return LoginStatus.LOGIN_SUCCESSFUL;
                    }else{
                        return LoginStatus.INCORRECT_PASSWORD;
                    }
                }
            }
        }
        return LoginStatus.INVALID_USER;
    }

    @Override
    public int registerNewUser(String fName, String lName,String email, String pass, int age) {
        if(!DatabaseWithMockData.users.isEmpty()) {
            for (User u : DatabaseWithMockData.users) {
                if (fName.equalsIgnoreCase(u.getUserFirstName()) && lName.equalsIgnoreCase(u.getUserLastName()) &&
                        email.equalsIgnoreCase(u.getUserEmail())) {
                    return -1;
                }
            }
        }
        User user = new User(fName, lName, email, pass, age);
        DatabaseWithMockData.users.add(user);
        return user.getUserId();
    }

    @Override
    public DeleteStatus deleteUser(int userId) {
        if(!DatabaseWithMockData.users.isEmpty()){
            if(DatabaseWithMockData.users.removeIf(
                    user -> (user.getUserId() == userId && areAllBorrowedItemsReturned(user)))) {
                return DeleteStatus.USER_DELETION_SUCCESS;
            }
        }
        return DeleteStatus.USER_DELETION_FAILED;
    }
    public boolean areAllBorrowedItemsReturned(User user) {
        List<BorrowedItems> b = user.getBorrowedItemsByUser();

        if(b != null && !b.isEmpty()) {
            for(BorrowedItems borrowedItem: b){
                String status = borrowedItem.getCurrentStatus();
                if(status.equalsIgnoreCase(String.valueOf(BorrowedItems.BorrowedItemStatus.BORROWED))){
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public List<User> listUsers() {
        return DatabaseWithMockData.users;
    }

    @Override
    public User getUserById(int userId) {
        if(!DatabaseWithMockData.users.isEmpty()){
            for(User u: DatabaseWithMockData.users) {
                if (userId == u.getUserId()) {
                    return u;
                }
            }
        }
        return null;
    }

    @Override
    public List<User> getUsersWithOnlyBorrowedBooks() {
        List<User> usersWithBorrowedBooks = new ArrayList<>();
        List<User> userBorrowedItems = getUsersWithBorrowedItemsOnly();
        if(!userBorrowedItems.isEmpty()){
            for(User u: userBorrowedItems){
                if(u.getBorrowedItemsByUser() != null){
                    List<BorrowedItems> b = u.getBorrowedItemsByUser();

                    int others = 0;
                    int book = 0;
                    for(BorrowedItems borrowedItem: b){
                        if(borrowedItem.getType().equalsIgnoreCase("book")){
                            book++;
                        }else{
                            others++;
                        }
                    }

                    if(book > 0 && others == 0){
                        usersWithBorrowedBooks.add(u);
                    }
                }
            }
        }
        return usersWithBorrowedBooks;
    }

    @Override
    public List<User> getUsersWithBorrowedItemsOnly(){
        List<User> usersWithBorrowedItems = new ArrayList<>();
        if(!DatabaseWithMockData.users.isEmpty()){
            for(User u: DatabaseWithMockData.users){
                if(u.getBorrowedItemsByUser() != null){
                    List<BorrowedItems> b = u.getBorrowedItemsByUser();
                    for(BorrowedItems borrowedItem: b){
                        if(borrowedItem.getCurrentStatus().equalsIgnoreCase(
                                String.valueOf(BorrowedItems.BorrowedItemStatus.BORROWED))){
                            usersWithBorrowedItems.add(u);
                            break;
                        }
                    }
                }
            }
        }
        return usersWithBorrowedItems;
    }

    @Override
    public BorrowedStatus borrowBook(int userId, int bookId) {
        User u = getUserById(userId);

        BookDB bDB = new BookDB();
        Book book = bDB.getBookByBookId(bookId);

        if(book != null && book.getBookAvailableQuantity() != 0){
            List<BorrowedItems> borrowedItems = u.getBorrowedItemsByUser();
            if(borrowedItems != null && !borrowedItems.isEmpty()){
                for(BorrowedItems borrowedItem: borrowedItems){
                    if(userId == borrowedItem.getUserId() && bookId == borrowedItem.getBorrowedItemId()
                       && borrowedItem.getCurrentStatus().equalsIgnoreCase(
                               String.valueOf(BorrowedItems.BorrowedItemStatus.BORROWED))){
                        return BorrowedStatus.ALREADY_BORROWED;
                    }
                }
                if(u.getUserAge() < book.getBookAgeLimit()){
                    return BorrowedStatus.UNDER_AGE_BOOK;
                }else{
                    BorrowedItems b = new BorrowedItems(userId, bookId, "book");
                    book.setBookAvailableQuantity(book.getBookAvailableQuantity() - 1);
                    borrowedItems.forEach(bI -> {
                        if(userId == bI.getUserId() && bookId == bI.getBorrowedItemId() &&
                                bI.getCurrentStatus().equalsIgnoreCase(
                                        String.valueOf(BorrowedItems.BorrowedItemStatus.RETURNED))){
                            bI.setCurrentStatus(String.valueOf(BorrowedItems.BorrowedItemStatus.OLD_RECORD));
                        }
                    });
                    borrowedItems.add(b);
                }
            } else {
                if(u.getUserAge() < book.getBookAgeLimit()){
                    return BorrowedStatus.UNDER_AGE_BOOK;
                }else{
                    BorrowedItems b = new BorrowedItems(userId, bookId, "book");
                    book.setBookAvailableQuantity(book.getBookAvailableQuantity() - 1);
                    borrowedItems.add(b);
                }
            }
            u.setBorrowedItemsByUser(borrowedItems);
            return BorrowedStatus.BORROWED_SUCCESS;
        }else{
            return BorrowedStatus.ITEM_NOT_FOUND;
        }
    }
    @Override
    public ReturnStatus returnBook(int userId, int bookId){
        BookDB book = new BookDB();
        List<BorrowedItems> borrowedItems = getBorrowedItemsByUserId(userId);

        for(BorrowedItems b: DatabaseWithMockData.borrowedItems){
            if(b.getUserId() == userId && b.getType().equalsIgnoreCase("book") &&
                    b.getCurrentStatus().equalsIgnoreCase(String.valueOf(BorrowedItems.BorrowedItemStatus.BORROWED)) &&
                    bookId == b.getBorrowedItemId()){
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyy");
                Date date = new Date();
                b.setActualReturnDate(formatter.format(date));
                b.setCurrentStatus(String.valueOf(BorrowedItems.BorrowedItemStatus.RETURNED));
                borrowedItems.add(b);

                book.updateBookQuantity(bookId, 1);
                return ReturnStatus.RETURN_SUCCESS;
            }
        }
        return ReturnStatus.RETURN_FAILED;
    }

    @Override
    public List<BorrowedItems> getBorrowedItemsByUserId(int userId) {
        List<BorrowedItems> userBorrowedItems = new ArrayList<>();
        if(!DatabaseWithMockData.borrowedItems.isEmpty()){
            for(BorrowedItems b: DatabaseWithMockData.borrowedItems){
                if(b.getUserId() == userId) {
                    userBorrowedItems.add(b);
                }
            }
        }
        return userBorrowedItems;
    }
}
