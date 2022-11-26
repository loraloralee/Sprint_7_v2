package courierPackage;

public class CourierGenerator {
    public static Courier getDefault(){
        return new Courier(Generator.generateNew(),"Pass_1","Name_1");
    }


    public static Courier createWithDoubleLogin_1(){
        return new Courier("log_x","Pass_4","Name_4");
    }


}
