import java.util.*;

// Add-On Service (Represents optional service)
class AddOnService {
    private String serviceName;
    private double price;

    public AddOnService(String serviceName, double price) {
        this.serviceName = serviceName;
        this.price = price;
    }

    public String getServiceName() {
        return serviceName;
    }

    public double getPrice() {
        return price;
    }

    public void display() {
        System.out.println(serviceName + " - ₹" + price);
    }
}

// Add-On Service Manager
class AddOnServiceManager {

    // Map: Reservation ID → List of Services
    private Map<String, List<AddOnService>> serviceMap;

    public AddOnServiceManager() {
        serviceMap = new HashMap<>();
    }

    // Add service to reservation
    public void addService(String reservationId, AddOnService service) {

        serviceMap
                .computeIfAbsent(reservationId, k -> new ArrayList<>())
                .add(service);

        System.out.println("Added service '" + service.getServiceName()
                + "' to Reservation ID: " + reservationId);
    }

    // View services for a reservation
    public void viewServices(String reservationId) {

        System.out.println("\nServices for Reservation ID: " + reservationId);

        List<AddOnService> services = serviceMap.get(reservationId);

        if (services == null || services.isEmpty()) {
            System.out.println("No add-on services selected.");
            return;
        }

        for (AddOnService s : services) {
            s.display();
        }
    }

    // Calculate total additional cost
    public double calculateTotalCost(String reservationId) {

        List<AddOnService> services = serviceMap.get(reservationId);

        if (services == null) return 0;

        double total = 0;
        for (AddOnService s : services) {
            total += s.getPrice();
        }

        return total;
    }
}

// Main Class
public class UseCase7AddOnServiceSelection {

    public static void main(String[] args) {

        // Step 1: Assume reservation IDs from previous use case
        String reservation1 = "S1"; // Example room ID
        String reservation2 = "D2";

        // Step 2: Create Add-On Services
        AddOnService breakfast = new AddOnService("Breakfast", 500);
        AddOnService spa = new AddOnService("Spa", 1500);
        AddOnService airportPickup = new AddOnService("Airport Pickup", 800);

        // Step 3: Create Service Manager
        AddOnServiceManager manager = new AddOnServiceManager();

        // Step 4: Guest selects services
        manager.addService(reservation1, breakfast);
        manager.addService(reservation1, spa);

        manager.addService(reservation2, airportPickup);

        // Step 5: View selected services
        manager.viewServices(reservation1);
        manager.viewServices(reservation2);

        // Step 6: Calculate additional cost
        double cost1 = manager.calculateTotalCost(reservation1);
        double cost2 = manager.calculateTotalCost(reservation2);

        System.out.println("\nTotal Add-On Cost for " + reservation1 + ": ₹" + cost1);
        System.out.println("Total Add-On Cost for " + reservation2 + ": ₹" + cost2);

        // IMPORTANT:
        // No changes to booking or inventory (separation maintained)
    }
}