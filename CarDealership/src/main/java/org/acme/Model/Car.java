package org.acme.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Car {
    @Id
    @GeneratedValue
    private int CarID;
    private String CarModel;
    private String CarProducer;
    private double Price;
    private boolean IsBooked = false;
    private boolean IsBought = false;

    public Car() {
    }

    public Car(String carModel, String carProducer, double price) {
        setCarModel(carModel);
        setCarProducer(carProducer);
        setPrice(price);
    }

    public Car(String carType, String carModel, String carProducer, double price, boolean isBooked, boolean isBought) {
        setCarModel(carModel);
        setCarProducer(carProducer);
        setPrice(price);
        setIsBooked(isBooked);
        setIsBought(isBought);
    }

    public int getCarID() {
        return CarID;
    }

    public void setCarID(int carID) {
        CarID = carID;
    }

    public String getCarModel() {
        return CarModel;
    }

    public void setCarModel(String carModel) {
        CarModel = carModel;
    }

    public String getCarProducer() {
        return CarProducer;
    }

    public void setCarProducer(String carProducer) {
        CarProducer = carProducer;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double price) {
        Price = price;
    }

    public boolean getIsBooked() {
        return IsBooked;
    }

    public void setIsBooked(boolean booked) {
        IsBooked = booked;
    }

    public boolean getIsBought() {
        return IsBought;
    }

    public void setIsBought(boolean bought) {
        IsBought = bought;
    }
}
