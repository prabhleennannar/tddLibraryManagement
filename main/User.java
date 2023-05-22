import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class User {

    private static final AtomicInteger count = new AtomicInteger(1110);

    private int userId;

    private String userFirstName;

    private String userLastName;

    private String userEmail;

    private String userPassword;

    private int userAge;

    private List<BorrowedItems> borrowedItemsByUser;

    public User(){}

    public User(String userFirstName, String userLastName, String userEmail, String userPassword,
                int userAge) {
        this.userId = count.incrementAndGet();
        this.userFirstName = userFirstName;
        this.userLastName = userLastName;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.userAge = userAge;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId() {
        this.userId = count.incrementAndGet();
    }

    public String getUserFirstName() {
        return userFirstName;
    }

    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }

    public String getUserLastName() {
        return userLastName;
    }

    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public int getUserAge() {
        return userAge;
    }

    public void setUserAge(int userAge) {
        this.userAge = userAge;
    }

    public List<BorrowedItems> getBorrowedItemsByUser() {
        return borrowedItemsByUser;
    }

    public void setBorrowedItemsByUser(List<BorrowedItems> borrowedItemsByUser) {
        this.borrowedItemsByUser = borrowedItemsByUser;
    }
}