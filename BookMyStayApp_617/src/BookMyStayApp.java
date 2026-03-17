import java.util.HashMap;
import java.util.Map;

// Inventory Class (Centralized Management)
class RoomInventory {

    private HashMap<String, Integer> inventory;

    // Constructor → Initialize inventory
    public RoomInventory() {
        inventory = new HashMap<>();

        // Register room types with availability
        inventory.put("Single Room", 5);
        inventory.put("Double Room", 3);
        inventory.put("Suite Room", 2);
    }

    // Get availability of a specific room
    public int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }

    // Update availability (controlled update)
    public void updateAvailability(String roomType, int newCount) {
        if (inventory.containsKey(roomType)) {
            inventory.put(roomType, newCount);
        } else {
            System.out.println("Room type not found: " + roomType);
        }
    }

    // Display full inventory
    public void displayInventory() {
        System.out.println("---- Current Room Inventory ----");
        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            System.out.println(entry.getKey() + " → Available: " + entry.getValue());
        }
    }
}


// Main Class (Version 3.0)
public class BookMyStayApp {

    public static void main(String[] args) {

        System.out.println("===== Book My Stay App (Version 3.0) =====\n");

        // Initialize Inventory
        RoomInventory inventory = new RoomInventory();

        // Display Initial Inventory
        inventory.displayInventory();

        System.out.println();

        // Example: Check availability
        System.out.println("Checking availability for Suite Room:");
        System.out.println("Available: " + inventory.getAvailability("Suite Room"));

        System.out.println();

        // Example: Update availability
        System.out.println("Updating Suite Room availability to 1...\n");
        inventory.updateAvailability("Suite Room", 1);

        // Display Updated Inventory
        inventory.displayInventory();

        System.out.println("\nSystem execution completed.");
    }
}