import java.util.Scanner;

public class MainMenu{

    public static void getMainMenu(){
        Scanner sc = new Scanner(System.in);
        int userChoice = -1;

        while(userChoice == -1){
            System.out.println("==== Please choose from below menu ====\n");
            System.out.println("""
                    Choose 1: Admin Login
                    Choose 2: Existing User? Login
                    Choose 3: New User? Register
                    Choose 4: Exit
                    """);
            System.out.println("=======================================");
            System.out.print("\nPlease enter your choice: ");
            String userInputInString = sc.next();

            userChoice = Utils.getChoiceForMenu(userInputInString, 4);
        }
        performActionForMainMenu(userChoice);
    }

    private static void performActionForMainMenu(int choice){
        Scanner sc = new Scanner(System.in);
        UserDB uDB = new UserDB();

        switch (choice) {
            case 1 -> {
                System.out.println("Enter Admin Id: ");
                int adminId = 0;
                try{
                    adminId = sc.nextInt();
                } catch(Exception e){
                    System.out.println("** Please enter Admin Id in correct format. **");
                    performActionForMainMenu(1);
                }
                System.out.println("Enter Password: ");
                String adminPass = sc.next();
                Admin admin = new Admin();
                String adminLoginStatus = String.valueOf(admin.adminLogin(adminId, adminPass));
                if (adminLoginStatus.equalsIgnoreCase(String.valueOf(Admin.AdminLoginStatus.LOGIN_SUCCESSFUL))) {
                    System.out.println("Admin Login Successful!");
                    AdminMenu.getAdminMenu();
                } else if (adminLoginStatus.equalsIgnoreCase(String.valueOf(Admin.AdminLoginStatus.INCORRECT_PASSWORD))) {
                    System.out.println("** Password is Incorrect! Please try again! **");
                    getMainMenu();
                } else {
                    System.out.println("** Admin does not exit! Please try again! **");
                    getMainMenu();
                }
            }
            case 2 -> {
                System.out.println("Enter User Id: ");
                int userId = 0;
                try{
                    userId = sc.nextInt();
                }catch(Exception e){
                    System.out.println("** Please enter User Id in correct format **");
                    performActionForMainMenu(2);
                }

                System.out.println("Enter Password: ");
                String userPass = sc.next();
                String userLoginStatus = String.valueOf(uDB.loginUser(userId, userPass));
                if (userLoginStatus.equalsIgnoreCase(String.valueOf(IUserPersistence.LoginStatus.LOGIN_SUCCESSFUL))) {
                    System.out.println("User Login Successful!");
                    UserMenu.getUserMenu(userId);
                } else if (userLoginStatus.equalsIgnoreCase(String.valueOf(IUserPersistence.LoginStatus.INCORRECT_PASSWORD))) {
                    System.out.println("** Password is Incorrect! Please try again!**");
                    getMainMenu();
                } else {
                    System.out.println("** User does not exits, Kindly Register! **");
                    getMainMenu();
                }
            }
            case 3 -> {
                System.out.println("== Please Register ==\n");
                System.out.print("Enter First Name: ");
                String fName = sc.next();
                System.out.print("Enter Last Name: ");
                String lName = sc.next();
                System.out.print("Enter Email: ");
                String email = sc.next();
                System.out.print("Enter Password: ");
                String pass = sc.next();
                System.out.print("Enter Age: ");

                int age = 0;
                try{
                    age = sc.nextInt();
                }catch (Exception e){
                    System.out.println("** Please enter age in correct format **");
                    performActionForMainMenu(3);
                }

                int userId = uDB.registerNewUser(fName, lName, email, pass, age);
                if (userId == -1) {
                    System.out.println("** Sorry! User Already Exists! Please try again!**");
                    getMainMenu();
                } else {
                    System.out.println("New User Registered Successfully with userId: " + userId);
                    UserMenu.getUserMenu(userId);
                }
            }
            case 4 -> {
                System.out.println("** System exited Successfully **");
                System.exit(0);
            }
        }
    }
}
