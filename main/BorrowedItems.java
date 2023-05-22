import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/***************************************************************************************
 *    For adding days to current day and the conversion from LocalDateTime to Date, Code
 *    is adapted from:
 *    Title: How to add days to current date?
 *    Author: MKYong
 *    Date: November 15, 2016,
 *    Availability: https://mkyong.com/java/java-how-to-add-days-to-current-date/
 ***************************************************************************************/
public class BorrowedItems {

    public enum BorrowedItemStatus{
        BORROWED,
        OLD_RECORD, RETURNED
    }

    private int userId;
    private int borrowedItemId;

    private String type;

    private String borrowDate;

    private String defaultReturnDate;

    private String actualReturnDate = null;

    private String currentStatus;

    public BorrowedItems(){}

    public BorrowedItems(int userId, int borrowedItemId, String type) {
        this.userId = userId;
        this.borrowedItemId = borrowedItemId;
        this.type = type;

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyy");
        Date date = new Date();
        this.borrowDate = formatter.format(date);

        LocalDateTime localDateTime = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        localDateTime = localDateTime.plusDays(3);
        this.defaultReturnDate = formatter.format(Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant()));

        this.currentStatus = String.valueOf(BorrowedItemStatus.BORROWED);
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getBorrowedItemId() {
        return borrowedItemId;
    }

    public void setBorrowedItemId(int borrowedItemId) {
        this.borrowedItemId = borrowedItemId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(String borrowDate) {
        this.borrowDate = borrowDate;
    }

    public String getDefaultReturnDate() {
        return defaultReturnDate;
    }

    public void setDefaultReturnDate(String defaultReturnDate) {
        this.defaultReturnDate = defaultReturnDate;
    }

    public String getActualReturnDate() {
        return actualReturnDate;
    }

    public void setActualReturnDate(String actualReturnDate) {
        this.actualReturnDate = actualReturnDate;
    }

    public String getCurrentStatus() {
        return currentStatus;
    }

    public void setCurrentStatus(String currentStatus) {
        this.currentStatus = currentStatus;
    }
}
