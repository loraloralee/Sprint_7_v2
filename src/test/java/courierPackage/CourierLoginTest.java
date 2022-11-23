package courierPackage;

import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;


public class CourierLoginTest {
    private CourierClient courierClient = new CourierClient();
    private Courier courier = new Courier();
    private int id;

    @Before
    public void setUp() {
        courierClient = new CourierClient();
        courier = CourierGenerator.getDefault();
    }
    @After
    public void cleanUp(){
        if(id!=0);
        courierClient.delete(id);
    }
    @DisplayName("Courier can be login")
    @Test
    public void courierСanBeLoginTest() {
        ValidatableResponse responseCreate = courierClient.create(courier);
        ValidatableResponse responseLogin = courierClient.login(Credentials.from(courier));
        id = responseLogin.extract().path("id");
        Assert.assertTrue("Учетная запись не найдена",id!=0);
    }

    @DisplayName("Courier without login")
    @Test
    public void courierWithoutLoginTest(){
        ValidatableResponse responseWithoutLogin = courierClient.login(Credentials.сredentialsWithoutLogin(courier));
        int statusCode = responseWithoutLogin.extract().statusCode();
        Assert.assertEquals("Недостаточно данных для создания учетной записи",400,statusCode);
    }

    @DisplayName("Courier with invalid login-password")
    @Test
    public void courierWithInvalidLoginPasswordTest(){
        ValidatableResponse responseWithInvalidLoginPassword = courierClient.login(Credentials.credentialsWithInvalidLoginPassword(courier));
        int statusCode = responseWithInvalidLoginPassword.extract().statusCode();
        Assert.assertEquals("Учетная запись не найдена",404,statusCode);
    }
}


