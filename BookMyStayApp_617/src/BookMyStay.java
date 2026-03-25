import java.util.*;

// Reservation (Represents booking intent)
class Reservation {
    private String guestName;
    private String roomType;

    public Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }

    public String getGuestName() {
        return guestName;
    }

    public String getRoomType() {
        return roomType;
    }

    public void display() {
        System.out.println("Guest: " + guestName + " | Requested Room: " + roomType);
    }
}

// Booking Request Queue (FIFO handling)
class BookingRequestQueue {
    private Queue<Reservation> queue;

    public BookingRequestQueue() {
        queue = new LinkedList<>();
    }

    // Add booking request (enqueue)
    public void addRequest(Reservation reservation) {
        queue.offer(reservation);
        System.out.println("Booking request added for " + reservation.getGuestName());
    }

    // View all requests (without removing)
    public void viewRequests() {
        System.out.println("\nCurrent Booking Queue (FIFO Order):\n");

        if (queue.isEmpty()) {
            System.out.println("No booking requests available.");
            return;
        }

        for (Reservation r : queue) {
            r.display();
        }
    }

    // Peek next request (no removal)
    public Reservation peekNextRequest() {
        return queue.peek();
    }
}

// Main Class
public class BookMyStay {

    public static void main(String[] args) {

        // Step 1: Create Booking Queue
        BookingRequestQueue bookingQueue = new BookingRequestQueue();

        // Step 2: Simulate Guest Booking Requests
        Reservation r1 = new Reservation("Alice", "Single");
        Reservation r2 = new Reservation("Bob", "Double");
        Reservation r3 = new Reservation("Charlie", "Suite");

        // Step 3: Add requests to queue (FIFO order maintained)
        bookingQueue.addRequest(r1);
        bookingQueue.addRequest(r2);
        bookingQueue.addRequest(r3);

        // Step 4: View queued requests
        bookingQueue.viewRequests();

        // Step 5: Show next request to be processed (without removing)
        Reservation next = bookingQueue.peekNextRequest();
        if (next != null) {
            System.out.println("\nNext request to process:");
            next.display();
        }



        // IMPORTANT: No inventory update or booking allocation here
    }
}