public class TechProduct{
    private String deviceType; //primary key
    private String brand; //secondary key
    private String modelNumber;
    private double msrp;
    private int yearReleased;
    private String productDescription;

    TechProduct( String type, String brand, String modelnum, double msrp, int year, String desc){
        this.deviceType = type;
        this.brand = brand;
        this.modelNumber = modelnum;
        this.msrp = msrp;
        this.yearReleased = year;
        this.productDescription = desc;
    }

    String getDeviceType(){
        return deviceType;
    }
    String getBrand(){
        return brand;
    }
    String getModelNumber(){
        return modelNumber;
    }
    double getMSRP(){
        return msrp;
    }
    int getYearReleased(){
        return yearReleased;
    }
    String getDescription(){
        return productDescription;
    }

}