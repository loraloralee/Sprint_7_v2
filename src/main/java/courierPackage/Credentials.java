package courierPackage;

public class Credentials {
    private String login;
    private String password;

    private  Courier courier;

    public Credentials(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public static Credentials from (Courier courier){
        return new Credentials(courier.getLogin(), courier.getPassword());
    }

    public static Credentials credentialsWithoutLogin(Courier courier){
        return new Credentials("", courier.getPassword());
    }
    public static Credentials credentialsWithoutPassword(Courier courier){
        return new Credentials(courier.getLogin(), "");
    }

    public static Credentials credentialsWithInvalidLogin(Courier courier) {
        return new Credentials(courier.getLogin() + "ll", courier.getPassword());
    }
    public static Credentials credentialsWithInvalidPassword(Courier courier) {
        return new Credentials(courier.getLogin() , courier.getPassword()+ "ww");
    }


    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }


    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}

