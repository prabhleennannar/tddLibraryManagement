/**
 * Test class for UtilsTest.java
 */
public class UtilsTest {

    public static void startUtilsTest(){
        System.out.println("**** UTILS TEST CASES ****");
        getChoiceForMenuTest();
    }

    private static void getChoiceForMenuTest() {
        System.out.println("Choice for Menu Test Cases: ");
        System.out.println("1. Valid Choice");
        int choice = Utils.getChoiceForMenu("1", 8);
        if(choice == 1){
            System.out.println("TEST SUCCESS\n");
        }else{
            System.out.println("TEST FAILED\n");
        }

        System.out.print("2. Invalid Choice(Integer selected by user is invalid)");
        choice = Utils.getChoiceForMenu("5", 3);
        if(choice == -1){
            System.out.println("TEST SUCCESS\n");
        }else{
            System.out.println("TEST FAILED\n");
        }

        System.out.print("3. Invalid Choice(String input provided by user instead of Integer)");
        choice = Utils.getChoiceForMenu("abc", 4);
        if(choice == -1){
            System.out.println("TEST SUCCESS\n");
        }else{
            System.out.println("TEST FAILED\n");
        }
    }
}
