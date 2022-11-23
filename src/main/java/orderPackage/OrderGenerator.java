package orderPackage;

import java.util.List;

public class OrderGenerator {

    public static Order getOrderWithoutColor() {
        return new Order(
                "Alla",
                "Alekovna",
                "Tashkent, Chehov street",
                "4",
                "+7 800 355 35 35",
                "5",
                "2020-06-06",
                "Saske, come back to Konoha",
                List.of());
    }
    public static Order getColorBlack() {
        Order order=OrderGenerator.getOrderWithoutColor();
        order.setColor(List.of("BLACK"));
        return  order;


    }
    public static Order getColorGrey() {
        Order order=OrderGenerator.getOrderWithoutColor();
        order.setColor(List.of("GREY"));
        return  order;

    }

    public static Order getColorBlackAndGrey() {
        Order order=OrderGenerator.getOrderWithoutColor();
        order.setColor(List.of("BLACK,GREY"));
        return order;

    }
}

