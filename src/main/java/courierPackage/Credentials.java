package courierPackage;

public class Credentials {
    private String login;
    private String password;

    private  Courier courier;
    private int id;

    public Credentials(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public static Credentials from (Courier courier){
        return new Credentials(courier.getLogin(), courier.getPassword());
    }

    public static Credentials сredentialsWithoutLogin(Courier courier){
        return new Credentials("", courier.getPassword());
    }

    public static Credentials credentialsWithInvalidLoginPassword(Courier courier) {
        return new Credentials(courier.getLogin() + "ll", courier.getPassword());
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

