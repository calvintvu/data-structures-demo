/**
 * @author Ryan Puah Zhi Yuan
 * @author Calvin Vu
 */

import java.util.Comparator;

public class TechProduct {
    private String deviceName; //primary key
    private String brand; 
    private String modelNumber; //secondary key
    private double msrp;
    private int yearReleased;
    private String productDescription;

    TechProduct(String name, String brand, String modelnum, double msrp, int year, String desc){
        this.deviceName = name;
        this.brand = brand;
        this.modelNumber = modelnum;
        this.msrp = msrp;
        this.yearReleased = year;
        this.productDescription = desc;
    }

    TechProduct(String name){
        this.deviceName = name;
        this.brand = "";
        this.modelNumber = "";
        this.msrp = -1;
        this.yearReleased = 0;
        this.productDescription = "";
    }

    TechProduct(String name, String modelNumber) {
    	this.deviceName = name;
    	this.brand = "";
    	this.modelNumber = modelNumber;
    	this.msrp = -1;
    	this.yearReleased = 0;
    	this.productDescription = "";
    }

    String getDeviceName(){
        return deviceName;
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

    void setDeviceName(String n){
        this.deviceName = n;
    }
    void setBrand(String n){
        this.brand = n;
    }
    void setModelNum(String n){
        this.modelNumber = n;
    }
    void setMSRP(double n){
        this.msrp = n;
    }
    void setYearReleased(int n){
        this.yearReleased = n;
    }
    void setDescription(String n){
        this.productDescription = n;
    }

    	/** ADDITIONAL OPERATIONS */

	public String toString2() {
		String result = "";
		result += deviceName + "\n";
		result += brand + "\n";
        result += modelNumber + "\n";
        result += msrp + "\n";
        result += yearReleased + "\n";
        result += productDescription+ "\n";
        //result += "\n";
		return result;
	}

    @Override
	public String toString() {
		String result = "";
		result += "Name: " + deviceName + "\n";
		result += "Brand: " + brand + "\n";
        result += "Model Number: " + modelNumber + "\n";
        result += "Price: " + msrp + "\n";
        result += "Year Released: " + yearReleased + "\n";
        result += "Description: " + productDescription+ "\n";
        result += "\n";
		return result;
	}


}

class NameComparator implements Comparator<TechProduct> {
    @Override
    public int compare(TechProduct product1, TechProduct product2) {
        return product1.getDeviceName().compareTo(product2.getDeviceName());
    }
} // end class NameComparator

class modelNumComparator implements Comparator<TechProduct> {
    @Override
    public int compare(TechProduct product1, TechProduct product2) {
        return product1.getModelNumber().compareTo(product2.getModelNumber());
    }
}