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
    private Courier credentialsWithSameLoginAndPassword;


    @Before
    public void setUp() {
        courierClient = new CourierClient();
        courier = CourierGenerator.getDefault();
        credentialsWithDoubleLogin_1 = CourierGenerator.createWithDoubleLogin_1();
        //credentialsWithSameLoginAndPassword=Credentials.createWithSameLoginAndPassword();

    }

    @After
    public void cleanUp() {
        courierClient.delete(id);
    }

    @Test
    @DisplayName("Check is courier can be created")
    public void courierСanBeCreatedTest() {
        ValidatableResponse responseCreate = courierClient.create(courier);
        ValidatableResponse responseLogin = courierClient.login(Credentials.from(courier));
        int statusCode = responseCreate.extract().statusCode();
        id = responseLogin.extract().path("id");
        boolean isCourierCreated = responseCreate.extract().path("ok");
        Assert.assertEquals("Курьер не создан", 201, statusCode);
    }

    @DisplayName("Courier without login")
    @Test
    public void courierWithoutLoginTest() {
        ValidatableResponse responseWithoutLogin = courierClient.login(Credentials.сredentialsWithoutLogin(courier));
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

   /* @DisplayName("Can't create two same couriers")
    @Test
    public void twoSameCourierTest() {
        ValidatableResponse responseTwoSameCourier = courierClient.create(courier);
        int statusCode = responseTwoSameCourier.extract().statusCode();
        ValidatableResponse responseLogin = courierClient.login(Credentials.from(courier));
        id = responseLogin.extract().path("id");
        //boolean isCourierCreated = responseCreate.extract().path("ok");
        Assert.assertEquals("Не может быть создан один и тот же курьер", 201, statusCode);

    }*/
}
