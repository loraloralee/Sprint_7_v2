package courierPackage;

import io.restassured.response.ValidatableResponse;
import static io.restassured.RestAssured.*;

public class CourierClient extends Client{
    private static final String COURIER_PATH= "/api/v1/courier";
    private static final String LOGIN_PATH= "/api/v1/courier/login";
    private static final String DELETE_PATH= "/api/v1/courier/";


    private Courier courier;
    private Credentials credentials;

    //create
    public ValidatableResponse create(Courier courier){
        return given()
                .spec(getSpec())
                .log().all()
                .body(courier)
                .when()
                .post(COURIER_PATH)
                .then()
                .log().all();
    }
    public ValidatableResponse login(Credentials credentials){
        return given()
                .spec(getSpec())
                .log().all()
                .body(credentials)
                .when()
                .post(LOGIN_PATH)
                .then()
                .log().all();

    }
    public ValidatableResponse delete(int id){
        String str = Integer.toString(id);
        return given()
                .spec(getSpec())
                .log().all()
                .when()
                .delete(DELETE_PATH+str)
                .then()
                .log().all();
    }
}
