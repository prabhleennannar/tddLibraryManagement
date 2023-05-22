import java.util.ArrayList;
import java.util.List;

/**
 * Test class for User.java
 */
public class UserTest {

    public static void startUserTest(){
        System.out.println("** USER TEST CASES **");
        userSetterAndGetterTest();
    }

    /**
     * Performs tests for setter and getter of User
     */
    private static void userSetterAndGetterTest() {
        User user = new User();
        user.setUserId();
        user.setUserFirstName("UserTest");
        user.setUserLastName("Test");
        user.setUserEmail("userTest@test1.com");
        user.setUserPassword("userTest@123");
        user.setUserAge(25);

        List<BorrowedItems> borrowedItemsList = new ArrayList<>();
        BorrowedItems b = new BorrowedItems(user.getUserId(), 1, "book");
        borrowedItemsList.add(b);
        user.setBorrowedItemsByUser(borrowedItemsList);

        System.out.println("User Setter and Getter Test Cases: ");
        System.out.println("1. User Id");
        int userId = user.getUserId();
        if(user.getUserId() == userId){
            System.out.println("TEST SUCCESS\n");
        }else{
            System.out.println("TEST FAIL\n");
        }

        System.out.println("2. User First Name");
        if(user.getUserFirstName().equals("UserTest")){
            System.out.println("TEST SUCCESS\n");
        }else{
            System.out.println("TEST FAIL\n");
        }

        System.out.println("3. User Last Name");
        if(user.getUserLastName().equals("Test")){
            System.out.println("TEST SUCCESS\n");
        }else{
            System.out.println("TEST FAIL\n");
        }

        System.out.println("4. User Email");
        if(user.getUserEmail().equals("userTest@test1.com")){
            System.out.println("TEST SUCCESS\n");
        }else{
            System.out.println("TEST FAIL\n");
        }

        System.out.println("5. User Password");
        if(user.getUserPassword().equals("userTest@123")){
            System.out.println("TEST SUCCESS\n");
        }else{
            System.out.println("TEST FAIL\n");
        }

        System.out.println("6. User Age");
        if(user.getUserAge() == 25){
            System.out.println("TEST SUCCESS\n");
        }else{
            System.out.println("TEST FAIL\n");
        }

        System.out.println("7. User Borrowed Items");
        List<BorrowedItems> bI = user.getBorrowedItemsByUser();
        if(bI.size() == 1){
            System.out.println("TEST SUCCESS\n");
        }else{
            System.out.println("TEST FAIL\n");
        }
    }

}

