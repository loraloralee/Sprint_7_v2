package orderPackage;

import io.restassured.response.ValidatableResponse;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


@RunWith(Parameterized.class)
public class OrderCreateTest {

    private Order order;
    int track;
    private OrderClient orderClient;
    private OrderGenerator orderGenerator=new OrderGenerator();

    public OrderCreateTest(Order order) {
        this.order = order;
    }

    @Parameterized.Parameters
    public static Object[] getData() {
        return new Object[][] {
                {OrderGenerator.getOrderWithoutColor()},
                {OrderGenerator.getColorBlack()},
                {OrderGenerator.getColorGrey()},
                {OrderGenerator.getColorBlackAndGrey()},

        };
    }
    @Before
    public void setUp() {
        orderClient = new OrderClient();
    }

    @DisplayName("Order can be created with different colors")
    @Test
    public void orderСanBeCreatedWithDifferentColorsTest() {
        ValidatableResponse responseCreateOrder = orderClient.orderCreate(order);
        int track = responseCreateOrder.extract().path("track");
        int statusCode= responseCreateOrder.extract().statusCode();
        Assert.assertTrue(track!=0);
        Assert.assertEquals("Заказ не создан", 201, statusCode);
    }

}
