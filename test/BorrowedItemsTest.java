import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * Test class for BorrowedItems.java
 */
public class BorrowedItemsTest {

    public static void startBorrowedItemsTest(){
        System.out.println("**** BORROWED ITEMS TEST CASES ****");
        borrowedItemsSetterAndGetterTest();
    }

    private static void borrowedItemsSetterAndGetterTest() {
        BorrowedItems bI = new BorrowedItems();
        bI.setUserId(8812);
        bI.setBorrowedItemId(10);
        bI.setType("book");

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyy");
        Date date = new Date();
        LocalDateTime localDateTime = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        localDateTime = localDateTime.plusDays(3);

        bI.setBorrowDate(formatter.format(date));
        bI.setDefaultReturnDate(formatter.format(Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant())));
        bI.setActualReturnDate(formatter.format(date));
        bI.setCurrentStatus(String.valueOf(BorrowedItems.BorrowedItemStatus.BORROWED));

        System.out.println("Borrowed Items Setter and Getter Test Cases: ");
        System.out.println("1. Borrowed Item User Id");
        if(bI.getUserId() == 8812){
            System.out.println("TEST SUCCESS\n");
        }else{
            System.out.println("TEST FAIL\n");
        }

        System.out.println("2. Borrowed Item Id");
        if(bI.getBorrowedItemId() == 10){
            System.out.println("TEST SUCCESS\n");
        }else{
            System.out.println("TEST FAIL\n");
        }

        System.out.println("3. Borrowed Item Type");
        if(bI.getType().equalsIgnoreCase("book")){
            System.out.println("TEST SUCCESS\n");
        }else{
            System.out.println("TEST FAIL\n");
        }

        System.out.println("4. Borrowed Item Borrow Date");
        if(bI.getBorrowDate().equals(formatter.format(date))){
            System.out.println("TEST SUCCESS\n");
        }else{
            System.out.println("TEST FAIL\n");
        }

        System.out.println("5. Borrowed Item Default Return Date");
        if(bI.getDefaultReturnDate().equals(formatter.format(
                Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant())))){
            System.out.println("TEST SUCCESS\n");
        }else{
            System.out.println("TEST FAIL\n");
        }

        System.out.println("5. Borrowed Item Actual Return Date");
        if(bI.getActualReturnDate().equals(formatter.format(date))){
            System.out.println("TEST SUCCESS\n");
        }else{
            System.out.println("TEST FAIL\n");
        }

        System.out.println("6. Borrowed Item Current Status");
        if(bI.getCurrentStatus().equalsIgnoreCase(String.valueOf(BorrowedItems.BorrowedItemStatus.BORROWED))){
            System.out.println("TEST SUCCESS\n");
        }else{
            System.out.println("TEST FAIL\n");
        }
    }

}
