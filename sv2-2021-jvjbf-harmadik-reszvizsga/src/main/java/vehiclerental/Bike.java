package vehiclerental;

import java.time.LocalTime;

public class Bike implements Rentable {

    private String id;
    private LocalTime rentingTime;
    public static final int MINUTES_FEE = 15; //15Ft per perc

    public Bike(String id) {
        this.id = id;
    }

    @Override
    public int calculateSumPrice(long minutes) {
            return (int) (minutes * MINUTES_FEE);
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
