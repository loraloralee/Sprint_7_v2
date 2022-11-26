package courierPackage;

import io.restassured.response.ValidatableResponse;
import org.junit.Assert;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;


public class CourierCreateTest {
    private CourierClient courierClient = new CourierClient();
    private Courier courier = new Courier();
    private int id;
    private Courier credentialsWithDoubleLogin_1;




    @Before
    public void setUp() {
        courierClient = new CourierClient();
        courier = CourierGenerator.getDefault();
        credentialsWithDoubleLogin_1 = CourierGenerator.createWithDoubleLogin_1();

    }

    @After

    public void cleanUp() {
        courierClient.delete(id);
    }

    @Test
    @DisplayName("Check is courier can be created")
    public void courierCanBeCreatedTest() {
        ValidatableResponse responseCreate = courierClient.create(courier);
        ValidatableResponse responseLogin = courierClient.login(Credentials.from(courier));
        int statusCode = responseCreate.extract().statusCode();
        id = responseLogin.extract().path("id");
        boolean isCourierCreated = responseCreate.extract().path("ok");
        Assert.assertEquals("Курьер не создан", 201, statusCode);
        Assert.assertTrue(isCourierCreated);
    }

    @DisplayName("Courier without login")
    @Test
    public void courierWithoutLoginTest() {
        ValidatableResponse responseWithoutLogin = courierClient.login(Credentials.credentialsWithoutLogin(courier));
        int statusCode = responseWithoutLogin.extract().statusCode();
        Assert.assertEquals("Недостаточно данных для создания учетной записи", 400, statusCode);
    }

    @DisplayName("Courier with double login")
    @Test
    public void courierWithDoubleLoginTest() {

        ValidatableResponse responseWithDoubleLogin = courierClient.create(credentialsWithDoubleLogin_1);
        int statusCode = responseWithDoubleLogin.extract().statusCode();
        Assert.assertEquals("Этот логин уже используется", 409, statusCode);
    }


}
