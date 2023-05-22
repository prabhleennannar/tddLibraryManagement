/**
 * Test class for LibraryManagement.java
 */
public class LibraryManagementTest {
    public static void startServiceTest() {
        UtilsTest.startUtilsTest();
        System.out.println("===============================================================\n");

        DatabaseWithMockData.getMockDataTest();
        AdminTest.startAdminTest();
        System.out.println("===============================================================\n");

        UserDBTest.startUserDBTest();
        System.out.println("===============================================================\n");

        BookDBTest.startBookDBTest();
        System.out.println("===============================================================\n");

        UserTest.startUserTest();
        System.out.println("===============================================================\n");

        BookTest.startBookTest();
        System.out.println("===============================================================\n");

        BorrowedItemsTest.startBorrowedItemsTest();
        System.out.println("===============================================================\n");
    }
}
