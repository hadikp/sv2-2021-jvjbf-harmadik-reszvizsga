package vehiclerental;

import java.time.LocalTime;
import java.util.*;

public class RentService {

    private Set<Rentable> rentables = new HashSet<>();
    private Set<User> users = new HashSet<>();
    private Map<Rentable, User> actualRenting = new TreeMap<>();

    public void addRentable(Rentable rentable) {
        rentables.add(rentable);
    }

    public void registerUser(User user) {
        if (isUserNameTake(user.getUserName())) {
            throw new UserNameIsAlreadyTakenException("Username is taken!");
        }
            users.add(user);
    }

    private boolean isUserNameTake(String name) {
        return users.stream().map(User::getUserName).anyMatch(p -> p.equals(name));
    }

    public void rent(User user, Rentable rentable, LocalTime time) { //3 órára bérelhet
        if (rentable.getRentingTime() != null || user.getBalance() < rentable.calculateSumPrice(180)) {
            throw new IllegalStateException("Rentable is taken or User isn't enough money!");
        } else {
            rentable.rent(time);
            actualRenting.put(rentable, user);
        }
    }

    public void closeRent(Rentable rentable, int minutes) {
        if (!actualRenting.containsKey(rentable)) {
            throw new IllegalStateException("Rentable is not taken!");
        }
        User user = actualRenting.get(rentable);
        user.minusBalance(rentable.calculateSumPrice(minutes));
        actualRenting.remove(rentable);
        rentable.closeRent();
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
