
package generatepojo;


public class Loginclass {

    private String userName;
    private String password;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Loginclass() {
    }

    /**
     * 
     * @param password
     * @param userName
     */
    public Loginclass(String userName, String password) {
        super();
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
