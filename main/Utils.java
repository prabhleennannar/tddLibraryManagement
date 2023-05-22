import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;

/***************************************************************************************
 *    For printing the content in a table like format. Code is adapted from
 *    javatpoint example:
 *    Title: How to Print Table in Java Using Formatter?
 *    Author: javatpoint
 *    Availability: https://www.javatpoint.com/how-to-print-table-in-java-using-formatter
 ***************************************************************************************/
public class Utils {

    public static int getChoiceForMenu(String userInput, int maxLimit){
        int choice = -1;
        try{
            choice = Integer.parseInt(userInput);
            ArrayList<Integer> choiceList = new ArrayList<>();
            for(int i = 1; i<=maxLimit; i++){
                choiceList.add(i);
            }
            if(!choiceList.contains(choice)){
                System.out.println("\n** Invalid Selection. Please try again and choose any number between (1 - "
                        + maxLimit +") **");
                choice = -1;
            }
        }catch(Exception e){
            System.out.println("\n** Invalid Selection. Please try again and choose any number between (1 - "
                    + maxLimit +") **");
            choice = -1;
        }
        return choice;
    }

    public static void userListFormatter(List<User> listOfUsers){
        Formatter fmt= new Formatter();
        fmt.format("%15s %15s %15s %15s %15s %16s %15s %19s\n", "UserId", "UserFirstName", "UserLastName",
                "UserEmail", "UserAge", "BorrowedItemId", "BorrowedType", "BorrowedCurrentStatus");


        for(User u: listOfUsers){
            List<BorrowedItems> b = u.getBorrowedItemsByUser();
            if(b!=null && !b.isEmpty()){
                for(BorrowedItems borrowedItem: b){
                    fmt.format("%13s %13s %13s %25s %9s %11s %15s %17s\n", u.getUserId(), u.getUserFirstName(),
                            u.getUserLastName(), u.getUserEmail(), u.getUserAge(),
                            borrowedItem.getBorrowedItemId(), borrowedItem.getType(), borrowedItem.getCurrentStatus());
                }
            }else{
                fmt.format("%13s %13s %13s %25s %9s %11s %15s %17s\n", u.getUserId(), u.getUserFirstName(),
                        u.getUserLastName(), u.getUserEmail(), u.getUserAge(), "NA", "NA", "NA");

            }
        }
        System.out.println(fmt);
    }

    public static void bookListFormatter(List<Book> listOfBooks){
        Formatter fmt = new Formatter();
        fmt.format("%15s %15s %15s %14s %15s %15s\n", "BookId", "BookName", "BookAuthor", "BookGenre",
                "BookAgeLimit", "BookQuantityAvailable");
        for(Book b: listOfBooks){
            fmt.format("%14s %14s %14s %14s %14s %14s\n", b.getBookId(), b.getBookName(), b.getBookAuthor(),
                    b.getBookGenre(), b.getBookAgeLimit(), b.getBookAvailableQuantity());
        }
        System.out.println(fmt);
    }

    public static void borrowedItemsListFormatter(List<BorrowedItems> borrowedItems){
        Formatter fmt = new Formatter();
        fmt.format("%15s %15s %15s %15s %15s %15s\n", "ItemId", "ItemType", "CurrentStatus", "BorrowedDate",
                "DefaultReturnDate", "ActualReturnDate");
        for(BorrowedItems b: borrowedItems){
            fmt.format("%15s %15s %15s %15s %15s %15s\n", b.getBorrowedItemId(), b.getType(), b.getCurrentStatus(),
                    b.getBorrowDate(), b.getDefaultReturnDate(), b.getActualReturnDate());
        }
        System.out.println(fmt);
    }
}
