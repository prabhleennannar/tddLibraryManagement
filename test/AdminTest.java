/**
 * Test class for Admin.java
 */
public class AdminTest {

    public static void startAdminTest(){
        System.out.println("**** ADMIN TEST CASES ****");
        adminLoginTest();

        System.out.println("===============================================================");
        adminSetterAndGetterTest();
    }

    /**
     * Performs tests for setter and getter of Admin
     */
    public static void adminLoginTest(){
        Admin admin = new Admin();

        System.out.println("Admin Login Test Cases:");
        System.out.println("1. Invalid Admin");
        String status = String.valueOf(admin.adminLogin(2, "abc@123"));
        if(status.equalsIgnoreCase(String.valueOf(Admin.AdminLoginStatus.INVALID_ADMIN))) {
            System.out.println("TEST SUCCESS\n");
        }else{
            System.out.println("TEST FAIL\n");
        }

        System.out.println("2. Incorrect Password");
        status = String.valueOf(admin.adminLogin(1, "abc@123"));
        if(status.equalsIgnoreCase(String.valueOf(Admin.AdminLoginStatus.INCORRECT_PASSWORD))) {
            System.out.println("TEST SUCCESS\n");
        }else{
            System.out.println("TEST FAIL\n");
        }

        System.out.println("3. Successful Login");
        status = String.valueOf(admin.adminLogin(1, "admin@123"));
        if(status.equalsIgnoreCase(String.valueOf(Admin.AdminLoginStatus.LOGIN_SUCCESSFUL))){
            System.out.println("TEST SUCCESS\n");
        }else{
            System.out.println("TEST FAIL\n");
        }
    }

    public static void adminSetterAndGetterTest(){
        Admin admin = new Admin();
        admin.setAdminId();
        admin.setAdminFirstName("Admin2");
        admin.setAdminLastName("Test2");
        admin.setAdminPassword("admin2@123");

        System.out.println("Admin Setter and Getter Test Cases: ");
        System.out.println("1. Admin Id");
        int adminId = admin.getAdminId();
        if(admin.getAdminId() == adminId){
            System.out.println("TEST SUCCESS\n");
        }else{
            System.out.println("TEST FAIL\n");
        }

        System.out.println("2. Admin First Name");
        if(admin.getAdminFirstName().equals("Admin2")){
            System.out.println("TEST SUCCESS\n");
        }else{
            System.out.println("TEST FAIL\n");
        }

        System.out.println("3. Admin Last Name");
        if(admin.getAdminLastName().equals("Test2")){
            System.out.println("TEST SUCCESS\n");
        }else{
            System.out.println("TEST FAIL\n");
        }

        System.out.println("4. Admin Password");
        if(admin.getAdminPassword().equals("admin2@123")){
            System.out.println("TEST SUCCESS\n");
        }else{
            System.out.println("TEST FAIL\n");
        }
    }
}
