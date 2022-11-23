package orderPackage;

import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.Matchers.*;
import java.util.ArrayList;

public class GetOrderListTest {

    private OrderClient orderClient;

    @Before
    public void setUp() {
        orderClient = new OrderClient();
    }

    @Test
    @DisplayName("Get order List")
    public void getOrderList(){
        ValidatableResponse getResponseOrderList=orderClient.getList();
        int statusCode= getResponseOrderList.extract().statusCode();
        ArrayList id = getResponseOrderList.extract().path("orders.id");
        Assert.assertEquals("Список не создан", 200, statusCode);
        Assert.assertThat("OrderList is Empty", id.isEmpty(), is(false));

    }

}

