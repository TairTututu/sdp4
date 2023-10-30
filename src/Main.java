import java.util.ArrayList;
import java.util.List;

// Observer interface
interface Observer {
    void update(String message);
}

// Subject class
class MeatFarm {
    private List<Observer> customers = new ArrayList<>();
    private String latestMeat;

    public void addObserver(Observer customer) {
        customers.add(customer);
    }

    public void removeObserver(Observer customer) {
        customers.remove(customer);
    }

    public void produceMeat(String meat) {
        latestMeat = meat;
        notifyCustomers("New meat available: " + meat);
    }

    private void notifyCustomers(String message) {
        for (Observer customer : customers) {
            customer.update(message);
        }
    }
}

// Customer class implementing the Observer interface
class Customer implements Observer {
    private String name;

    public Customer(String name) {
        this.name = name;
    }

    @Override
    public void update(String message) {
        System.out.println(name + " received a notification: " + message);
    }
}

public class Main {
    public static void main(String[] args) {
        MeatFarm meatFarm = new MeatFarm();

        Customer customer1 = new Customer("Customer 1");
        Customer customer2 = new Customer("Customer 2");

        meatFarm.addObserver(customer1);
        meatFarm.addObserver(customer2);

        meatFarm.produceMeat("Fresh Beef");
        meatFarm.produceMeat("Chicken Fillet");

        meatFarm.removeObserver(customer1);

        meatFarm.produceMeat("Pork Chops");
    }
}