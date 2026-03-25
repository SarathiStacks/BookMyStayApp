import java.util.*;

// Domain Model: Room
class Room {
    private String roomType;
    private double price;
    private List<String> amenities;

    public Room(String roomType, double price, List<String> amenities) {
        this.roomType = roomType;
        this.price = price;
        this.amenities = amenities;
    }

    public String getRoomType() {
        return roomType;
    }

    public double getPrice() {
        return price;
    }

    public List<String> getAmenities() {
        return amenities;
    }

    public void displayDetails() {
        System.out.println("Room Type: " + roomType);
        System.out.println("Price: ₹" + price);
        System.out.println("Amenities: " + amenities);
        System.out.println("---------------------------");
    }
}

// Inventory (State Holder)
class Inventory {
    private Map<String, Integer> roomAvailability;

    public Inventory() {
        roomAvailability = new HashMap<>();
    }

    public void addRoom(String roomType, int count) {
        roomAvailability.put(roomType, count);
    }

    // Read-only access
    public int getAvailability(String roomType) {
        return roomAvailability.getOrDefault(roomType, 0);
    }

    public Set<String> getAllRoomTypes() {
        return roomAvailability.keySet();
    }
}

// Search Service (Read-Only Logic)
class SearchService {
    private Inventory inventory;
    private Map<String, Room> roomCatalog;

    public SearchService(Inventory inventory, Map<String, Room> roomCatalog) {
        this.inventory = inventory;
        this.roomCatalog = roomCatalog;
    }

    public void searchAvailableRooms() {
        System.out.println("Available Rooms:\n");

        for (String roomType : inventory.getAllRoomTypes()) {

            int availableCount = inventory.getAvailability(roomType);

            // Validation: Only show available rooms
            if (availableCount > 0) {

                Room room = roomCatalog.get(roomType);

                if (room != null) { // Defensive check
                    room.displayDetails();
                    System.out.println("Available Count: " + availableCount);
                    System.out.println("===========================\n");
                }
            }
        }
    }
}

// Main Class
public class BookMyStay {

    public static void main(String[] args) {

        // Step 1: Setup Inventory
        Inventory inventory = new Inventory();
        inventory.addRoom("Single", 5);
        inventory.addRoom("Double", 0); // Should be filtered out
        inventory.addRoom("Suite", 2);

        // Step 2: Setup Room Catalog (Domain Data)
        Map<String, Room> roomCatalog = new HashMap<>();

        roomCatalog.put("Single",
                new Room("Single", 2000,
                        Arrays.asList("WiFi", "TV")));

        roomCatalog.put("Double",
                new Room("Double", 3500,
                        Arrays.asList("WiFi", "TV", "AC")));

        roomCatalog.put("Suite",
                new Room("Suite", 6000,
                        Arrays.asList("WiFi", "TV", "AC", "Mini Bar")));

        // Step 3: Search Service
        SearchService searchService = new SearchService(inventory, roomCatalog);

        // Step 4: Guest initiates search
        searchService.searchAvailableRooms();

        // IMPORTANT: Inventory remains unchanged
    }
}