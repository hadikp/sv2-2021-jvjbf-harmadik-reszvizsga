package vehiclerental;

import java.time.LocalTime;

public class Car implements Rentable {

    private String id;
    private LocalTime rentingTime;
    public int minutesFee; //45Ft per perc

    public Car(String id, int minutesFee) {
        this.id = id;
        this.minutesFee = minutesFee;
    }

    @Override
    public int calculateSumPrice(long minutes) {
            return (int) minutes * minutesFee;
    }

    @Override
    public LocalTime getRentingTime() {
        return rentingTime;
    }

    @Override
    public void rent(LocalTime time) {
        setRentingTime(time);
    }

    @Override
    public void closeRent() {
        setRentingTime(null);
    }

    public void setRentingTime(LocalTime rentingTime) {
        this.rentingTime = rentingTime;
    }
}
