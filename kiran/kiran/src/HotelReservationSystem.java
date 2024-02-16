import java.util.*;

class Room {
    private int roomNumber;
    private String category;
    private boolean isAvailable;
    
    public Room(int roomNumber, String category) {
        this.roomNumber = roomNumber;
        this.category = category;
        this.isAvailable = true;
    }
    
    public int getRoomNumber() {
        return roomNumber;
    }
    
    public String getCategory() {
        return category;
    }
    
    public boolean isAvailable() {
        return isAvailable;
    }
    
    public void bookRoom() {
        isAvailable = false;
    }
    
    public void releaseRoom() {
        isAvailable = true;
    }
}

class Booking {
    private int bookingId;
    private Room room;
    private String guestName;
    private Date checkInDate;
    private Date checkOutDate;
    
    public Booking(int bookingId, Room room, String guestName, Date checkInDate, Date checkOutDate) {
        this.bookingId = bookingId;
        this.room = room;
        this.guestName = guestName;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        room.bookRoom();
    }
    
    public int getBookingId() {
        return bookingId;
    }
    
    public Room getRoom() {
        return room;
    }
    
    public String getGuestName() {
        return guestName;
    }
    
    public Date getCheckInDate() {
        return checkInDate;
    }
    
    public Date getCheckOutDate() {
        return checkOutDate;
    }
}

class Hotel {
    private List<Room> rooms;
    private List<Booking> bookings;
    private int bookingIdCounter;
    
    public Hotel() {
        rooms = new ArrayList<>();
        bookings = new ArrayList<>();
        bookingIdCounter = 1;
        // Initialize rooms
        initializeRooms();
    }
    
    private void initializeRooms() {
        // Suppose we have 3 categories of rooms: Standard, Deluxe, Suite
        for (int i = 1; i <= 10; i++) {
            rooms.add(new Room(i, "Standard"));
        }
        for (int i = 11; i <= 20; i++) {
            rooms.add(new Room(i, "Deluxe"));
        }
        for (int i = 21; i <= 30; i++) {
            rooms.add(new Room(i, "Suite"));
        }
    }
    
    public List<Room> searchAvailableRooms(String category) {
        List<Room> availableRooms = new ArrayList<>();
        for (Room room : rooms) {
            if (room.isAvailable() && room.getCategory().equalsIgnoreCase(category)) {
                availableRooms.add(room);
            }
        }
        return availableRooms;
    }
    
    public void makeReservation(Room room, String guestName, Date checkInDate, Date checkOutDate) {
        Booking booking = new Booking(bookingIdCounter++, room, guestName, checkInDate, checkOutDate);
        bookings.add(booking);
    }
    
    public List<Booking> getBookings() {
        return bookings;
    }
}

public class HotelReservationSystem {
    public static void main(String[] args) {
        Hotel hotel = new Hotel();
        // Example usage
        List<Room> availableRooms = hotel.searchAvailableRooms("Standard");
        if (!availableRooms.isEmpty()) {
            Room roomToBook = availableRooms.get(0); // Suppose we just book the first available room
            hotel.makeReservation(roomToBook, "John Doe", new Date(), new Date()); // Provide actual check-in and check-out dates
            System.out.println("Reservation made successfully!");
        } else {
            System.out.println("No available rooms in the specified category.");
        }
        
        // View bookings
        List<Booking> bookings = hotel.getBookings();
        for (Booking booking : bookings) {
            System.out.println("Booking ID: " + booking.getBookingId());
            System.out.println("Guest Name: " + booking.getGuestName());
            System.out.println("Room Number: " + booking.getRoom().getRoomNumber());
            System.out.println("Check-in Date: " + booking.getCheckInDate());
            System.out.println("Check-out Date: " + booking.getCheckOutDate());
            System.out.println("--------------------");
        }
    }
}