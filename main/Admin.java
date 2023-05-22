import java.util.concurrent.atomic.AtomicInteger;

public class Admin {

    private static final AtomicInteger count = new AtomicInteger(0);

    private int adminId;

    private String adminLastName;

    private String adminFirstName;

    private String adminPassword;

    public Admin(){}
    public Admin(String adminFirstName, String adminLastName, String adminPassword) {
        this.adminId = count.incrementAndGet();
        this.adminFirstName = adminFirstName;
        this.adminLastName = adminLastName;
        this.adminPassword = adminPassword;
    }

    public int getAdminId() {
        return adminId;
    }

    public void setAdminId() {
        this.adminId = count.incrementAndGet();
    }

    public String getAdminLastName() {
        return adminLastName;
    }

    public void setAdminLastName(String adminLastName) {
        this.adminLastName = adminLastName;
    }

    public String getAdminFirstName() {
        return adminFirstName;
    }

    public void setAdminFirstName(String adminFirstName) {
        this.adminFirstName = adminFirstName;
    }

    public String getAdminPassword() {
        return adminPassword;
    }

    public void setAdminPassword(String adminPassword) {
        this.adminPassword = adminPassword;
    }

    public enum AdminLoginStatus{
        INCORRECT_PASSWORD,
        INVALID_ADMIN,
        LOGIN_SUCCESSFUL
    }

    /**
     * To verify the login credentials of Admin
     * @param adminId of the admin
     * @param password of the admin
     * @return admin login status
     */
    public AdminLoginStatus adminLogin(int adminId, String password){
        if(DatabaseWithMockData.admin != null){
            if(adminId == DatabaseWithMockData.admin.getAdminId()){
                if(password.equals(DatabaseWithMockData.admin.getAdminPassword())){
                    return AdminLoginStatus.LOGIN_SUCCESSFUL;
                }else{
                    return AdminLoginStatus.INCORRECT_PASSWORD;
                }
            }
        }
        return AdminLoginStatus.INVALID_ADMIN;
    }
}
