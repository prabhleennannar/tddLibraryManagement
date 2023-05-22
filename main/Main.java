public class Main {
    public static void main(String[] args){
        if(args.length > 0 && args[0].equalsIgnoreCase("test")){
            System.out.println("""
                    ====================================================================================================
                    This is a console output based library management system where the user needs to
                    provide the input from the console to interact with the system. 
                    **THE SYSTEM PROVIDED A NUMBER OF TEST CASES FOR ALL THE FEATURES AVAILABLE IN THE SYSTEM
                    TO ENSURE THAT EVERYTHING IS WORKING AS EXPECTED.
                    
                    **** DatabaseWithMockData.getMockDataTest() is responsible for loading below test mock data ****  
                                 
                    ## Data available for ADMIN:
                    --------------------------------------------------------
                    AdminId   AdminFirstName  AdminLastName  AdminPassword 
                    --------------------------------------------------------
                        1        Admin            Test         admin@123
                    --------------------------------------------------------    
                                                
                    ## Data available for BOOKS:
                    ----------------------------------------------------------------------------------
                    BookId   BookName  BookAuthor   BookGenre    BookAgeLimit   BookAvailableQuantity
                    ----------------------------------------------------------------------------------
                     5551     Book1      Author1      Horror        25                  5
                     5552     Book2      Author2      Comedy        29                  7
                     5553     Book3      Author2      Comic         22                  0
                     5554     Book3      Author3     Romance        25                  5
                     5555     Book8      Author8     Romance        28                  2
                    ----------------------------------------------------------------------------------
                            
                    ## Data available for USER:
                    --------------------------------------------------------------------------------
                    UserId   UserFirstName  UserLastName     UserEmail        UserPassword   UserAge
                    --------------------------------------------------------------------------------
                     1111       User1          Test1       user1@test1.com     user1@123       28
                     1112       User4          Test4       user4@test4.com     user4@123       23
                     1113       User5          Test5       user5@test5.com     user5@123       26
                     1114       User6          Test6       user6@test6.com     user6@123       27
                     1115       User7          Test7       user7@test7.com     user7@123       23
                    --------------------------------------------------------------------------------
                     
                    ## Data available for BORROWED ITEMS by Users:
                    ---------------------------------------------------------------------------------------------------
                    UserId   BorrowedItemId   Type    BorrowDate    DefaultReturnDate  ActualReturnDate  CurrentStatus
                    ---------------------------------------------------------------------------------------------------
                     1111       5551          book        *              **                 null            BORROWED
                     1113       5554          book        *              **                 ***             RETURNED
                     1114       5551          book        *              **                 null            BORROWED
                    ---------------------------------------------------------------------------------------------------                
                     * - Current/Today's Date will get stored for BorrowDate
                     ** - In Current/Today's Date 3 days will be added and the date will get stored for 
                          DefaultReturnDate
                     *** - Current/Today's Date will get stored for ActualReturnDate
                     
                   All the test cases will run considering the above data. THERE WILL BE SOME ALTERATIONS MADE TO THE
                   ABOVE DATA WHEN VARIOUS SPECIFIC TEST CASES WILL RUN eg- Delete operations, update operations etc.
                   =====================================================================================================
                    """);
            System.out.println("\n\nInside Test cases");
            LibraryManagementTest.startServiceTest();
        }else{
            System.out.println("""
                    ====================================================================================================
                    This is a console output based library management system where the user needs to 
                    provide the input from the console to interact with the system.A number of menus 
                    will display with a number of options to choose from. 
                    The system has two main actors:
                    1. Admin - Mainly responsible for:
                               * Logging into the System(Existing Admin)
                               * Adding new books
                               * Modifying books(delete, update quantity)
                               * Search specific books
                               * Monitoring all Library Users(With or without borrowed items)
                               * Removing Users
                                 
                    2. User/Member - All members can:
                                     * Login into Library System(Existing Users)
                                     * Register themselves(New Users)
                                     * List all the Books available in the Library
                                     * Borrow Books
                                     * Return Books
                                     * Search specific Books
                                     * List their borrowed Items
                       
                    **** DatabaseWithMockData.getMockData() is responsible for loading below mock data ****  
                                 
                    ## Data available for ADMIN:
                    --------------------------------------------------------
                    AdminId   AdminFirstName  AdminLastName  AdminPassword 
                    --------------------------------------------------------
                        1        Admin            Test         admin@123
                    --------------------------------------------------------
                        
                    ## Data available for BOOKS:
                    ----------------------------------------------------------------------------------
                    BookId   BookName  BookAuthor   BookGenre    BookAgeLimit   BookAvailableQuantity
                    ----------------------------------------------------------------------------------
                     5551     Book1      Author1      Comedy        20                  5
                     5552     Book2      Author2      Horror        26                  6
                     5553     Book3      Author2     Thriller       24                  9
                    ----------------------------------------------------------------------------------     
                            
                    ## Data available for USER:
                    --------------------------------------------------------------------------------
                    UserId   UserFirstName  UserLastName     UserEmail        UserPassword   UserAge
                    --------------------------------------------------------------------------------
                     1111       User1          Test1       user1@test1.com     user1@123       25
                     1112       User2          Test2       user2@test2.com     user2@123       24
                     1113       User3          Test3       user3@test3.com     user3@123       30
                    --------------------------------------------------------------------------------
                     
                    ## Data available for BORROWED ITEMS by Users:
                    ---------------------------------------------------------------------------------------------------
                    UserId   BorrowedItemId   Type    BorrowDate    DefaultReturnDate  ActualReturnDate  CurrentStatus
                    ---------------------------------------------------------------------------------------------------
                     1111       5551          book        *              **                 null            BORROWED
                     1112       5553          book        *              **                 ***             RETURNED
                    ---------------------------------------------------------------------------------------------------               
                     
                     * - Current/Today's Date will get stored for BorrowDate
                     ** - In Current/Today's Date 3 days will be added and the date will get stored for 
                          DefaultReturnDate
                     *** - Current/Today's Date will get stored for ActualReturnDate
                     
                   ##An individual can use the above data to test different features of the system. Apart from that,the 
                   person will need to choose various option from the menu getting displayed(along with their brief 
                   action description) on the console to test all the features as per their choice##
                   =====================================================================================================
                   
                    """);
            System.out.println("\n\n===== Welcome to Library ====\n");
            LibraryManagement.startService();
        }
    }
}
