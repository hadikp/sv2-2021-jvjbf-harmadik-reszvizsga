package vehiclerental;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class RentService {

    private Set<Rentable> rentables = new HashSet<>();
    private Set<User> users = new HashSet<>();
    //private Map<Rentable, User> rentables;
    private Map<Rentable, User> actualRenting = new HashMap<>();

    public void addRentable(Rentable vehicle) {
        rentables.add(vehicle);
    }

    public void registerUser(User user) {
        if (users.contains(user)) {
            throw new UserNameIsAlreadyTakenException("Username is taken!");
        } else {
            users.add(user);
        }

    }

    public void rent(User user, Rentable rentable, LocalTime time) { //3 órára bérelhet
        if (rentable.getRentingTime() != null) {
            throw new IllegalStateException("The vehicle isn't empty!");
        } else if (user.getBalance() >= rentable.calculateSumPrice(180)) {
            System.out.println(rentable.calculateSumPrice(180));
            throw new IllegalStateException("User isn't enough money!");
        } else {
            rentable.rent(time);
            actualRenting.put(rentable, user);
        }
    }

    public void closeRent(Rentable rentable, int minutes) {
        actualRenting.remove(rentable);
    }

    public Set<Rentable> getRentables() {
        return rentables;
    }

    public Set<User> getUsers() {
        return users;
    }


    public Map<Rentable, User> getActualRenting() {
        return actualRenting;
    }
}
