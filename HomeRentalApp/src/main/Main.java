package main;

import dao.BookingDAO;
import dao.PropertyDAO;
import dao.UserDAO;
import model.Booking;
import model.Property;
import model.User;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws SQLException {

        UserDAO userDAO = new UserDAO();
        PropertyDAO propertyDAO = new PropertyDAO();
        BookingDAO bookingDAO = new BookingDAO();
        while (true) {
            System.out.println("Welcome to Home Rental System");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.println("Enter your choice :");
            int op = Integer.parseInt(sc.nextLine());

            switch (op) {
                //Register Only
                case 1: {
                    System.out.println("Enter  name : ");
                    String name = sc.nextLine();
                    System.out.println("Enter  email : ");
                    String email = sc.nextLine();
                    System.out.println("Enter  password : ");
                    String password = sc.nextLine();
                    System.out.println("Enter  role : Owner/Tenant");
                    String role = sc.nextLine();
                    System.out.println("Enter  phone : ");
                    long phone = Long.parseLong(sc.nextLine());

                    if (userDAO.getUserByEmail(email) != null) {
                        System.out.println("User already exists");
                    } else {
                        User user = new User(name, email, password, role, phone);
                        userDAO.registerUser(user);
                        System.out.println("User registered successfully");
                    }
                    break;
                }

                //Login with email name validation
                case 2: {
                    System.out.println("Enter email : ");
                    String email = sc.nextLine();

                    System.out.println("Enter password : ");
                    String password = sc.nextLine();

                    User user = userDAO.userLogin(email, password);

                    if (user != null) {
                        System.out.println("Login successful! Welcome, " + user.getName());
                        // userDAO.getUserByEmail(email);

                        if (user.getRole().equalsIgnoreCase("owner")) {
                            boolean ownerLoggedIn = true;
                            while (ownerLoggedIn) {

                                System.out.println("Owner Menu : ");
                                System.out.println("1. Add Property");
                                System.out.println("2. View My Properties");
                                System.out.println("3. View Booking Requests");
                                System.out.println("4. Update Booking Status ");
                                System.out.println("5. Logout");
                                System.out.println("Enter your choice :");
                                int ownerMenu = Integer.parseInt(sc.nextLine());

                                switch (ownerMenu) {
                                    case 1: {

                                        System.out.println("Enter Tittle :");
                                        String tittle = sc.nextLine();

                                        System.out.println("Enter Decription :");
                                        String desc = sc.nextLine();

                                        System.out.println("Enter Address :");
                                        String add = sc.nextLine();

                                        System.out.println("Enter Rent Amount :");
                                        double rent = Double.parseDouble(sc.nextLine());

                                        System.out.println("Enter Rooms : ");
                                        int room = Integer.parseInt(sc.nextLine());

                                        System.out.println("Is property available? (yes/no): ");
                                        String input = sc.nextLine();
                                        boolean availability = input.equalsIgnoreCase("yes");


                                        Property property = new Property();
                                        property.setUserId(user.getUser_id());  // user ID from the user table
                                        property.setTitle(tittle);
                                        property.setDescription(desc);
                                        property.setAddress(add);
                                        property.setRent(rent);
                                        property.setRooms(room);
                                        property.setAvailable(availability);  // or false
                                        boolean result = propertyDAO.addProperty(property);

                                        if (result) {
                                            System.out.println("Property inserted successfully!");
                                        } else {
                                            System.out.println("Failed to insert property.");
                                        }
                                        break;
                                    }
                                    case 2: {
                                        List<Property> property = propertyDAO.getPropertiesByOwnerId(user.getUser_id());

                                        if (property.isEmpty()){
                                            System.out.println("No Properties Available");
                                        }else {
                                            for (Property p : property) {
                                                System.out.println(p);
                                            }
                                        }
                                        break;
                                    }
                                    case 3: {
                                        List<Property> propertyList = propertyDAO.getPropertiesByOwnerId(user.getUser_id());

                                        if (propertyList.isEmpty()) {
                                            System.out.println("property have not been added yet");
                                        } else {
                                            for (Property prop : propertyList) {
                                                System.out.println("\nProperty ID: " + prop.getPropertyId() + " | Title: " + prop.getTitle());
                                                List<Booking> bookings = bookingDAO.getBookingByPropertyId(prop.getPropertyId());

                                                if (bookings.isEmpty()) {
                                                    System.out.println("  No booking requests for this property.");
                                                } else {
                                                    for (Booking booking : bookings) {
                                                        System.out.println("  Booking ID: " + booking.getBookingId());
                                                        System.out.println("  User ID: " + booking.getUserId());
                                                        System.out.println("  Status: " + booking.getStatus());
                                                        System.out.println("  Date: " + booking.getBookingDate());
                                                        System.out.println("----------------------------------");
                                                    }
                                                }
                                            }
                                        }
                                        break;
                                    }

                                    case 4: {
                                        System.out.println("Do you want to update status of any booking? (yes/no)");
                                        String update = sc.nextLine();
                                        if (update.equalsIgnoreCase("yes")) {
                                            System.out.println("Enter Booking ID:");
                                            int bookingId = Integer.parseInt(sc.nextLine());
                                            System.out.println("Enter New Status (Approved/Rejected):");
                                            String newStatus = sc.nextLine();
                                            boolean updated = bookingDAO.updateBookingStatus(bookingId, newStatus);
                                            if (updated) {
                                                System.out.println("Booking status updated.");
                                            } else {
                                                System.out.println("Failed to update booking status.");
                                            }
                                        }
                                        break;
                                    }

                                    case 5: {
                                        System.out.println("Logout successful.\n");
                                        ownerLoggedIn = false;
                                        break; //out of loop
                                    }
                                    default: {
                                        System.out.println("Invalid Option");
                                        break;
                                    }
                                }
                            }
                        } else if (user.getRole().equalsIgnoreCase("tenant")) {
                            boolean tenantLoggedIn = true;

                            while (tenantLoggedIn) {
                                System.out.println("Tenant Menu : ");
                                System.out.println("1.Search Properties");//all available properties
                                System.out.println("2.Book a Property");
                                System.out.println("3.View My Bookings");
                                System.out.println("4.Logout");
                                System.out.println("Enter your choice :");
                                int tenantMenu = Integer.parseInt(sc.nextLine());

                                switch (tenantMenu) {
                                    case 1: {
                                        List<Property> propertyList = propertyDAO.getAllAvailableProperties();
                                        if (propertyList.isEmpty()) {
                                            System.out.println("No properties available at the moment.");
                                        } else {
                                            for (Property property : propertyList) {
                                                System.out.println(property);
                                            }
                                        }
                                        break;
                                    }
                                    case 2: {
                                        System.out.println("Enter Property ID to book:");
                                        int propertyId = Integer.parseInt(sc.nextLine());

                                        Date bookingDate = new Date(System.currentTimeMillis());
                                        String status = "Pending";

                                        Booking booking = new Booking();
                                        booking.setUserId(user.getUser_id()); // current logged-in tenant
                                        booking.setPropertyId(propertyId);
                                        booking.setBookingDate(bookingDate);
                                        booking.setStatus(status);

                                        boolean booked = bookingDAO.bookProperty(booking);

                                        if (booked) {
                                            System.out.println("Booking request submitted successfully.");
                                        } else {
                                            System.out.println("Failed to book the property.");
                                        }
                                        break;
                                    }

                                    case 3: {

                                        List<Booking> myBookings = bookingDAO.getBookingsByUserId(user.getUser_id());
                                        if (myBookings.isEmpty()) {
                                            System.out.println("No bookings found.");
                                        } else {
                                            for (Booking b : myBookings) {
                                                System.out.println(b);
                                            }
                                        }
                                        break;
                                    }
                                    case 4: {
                                        System.out.println("Logout successful.\n");
                                        tenantLoggedIn = false;
                                        break;
                                    }
                                    default: {
                                        System.out.println("Invalid Option");
                                        break;
                                    }
                                }
                            }
                        }

                    } else {
                        System.out.println("invalid credientials");
                    }
                    break;
                }
                case 3: {
                    System.out.println("Thank you for using the Home Rental System!");
                    break;
                }
                default: {
                    System.out.println("Invaild Option");
                    break;
                }
            }
        }
    }
}

