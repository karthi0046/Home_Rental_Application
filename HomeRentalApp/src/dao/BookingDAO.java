package dao;

import model.Booking;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookingDAO {

    String url = "jdbc:mysql://localhost:3306/homerentalapp";
    String user = "root";
    String pass = "root";

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, pass);
    }

    //requestBooking(Booking booking)
    public boolean bookProperty(Booking booking) throws SQLException {
        String query = "insert into bookings (property_id, user_id, status, booking_date) values(?,?,?,?)";

        Connection con = getConnection();
        PreparedStatement ps = con.prepareStatement(query);

        ps.setInt(1, booking.getPropertyId());
        ps.setInt(2, booking.getUserId());
        ps.setString(3, booking.getStatus());
        ps.setDate(4, booking.getBookingDate());

        int row = ps.executeUpdate();
        return row > 0;
    }

    public List<Booking> getBookingByPropertyId(int property_id) throws SQLException {

        String query = "select * from bookings where property_id = ?";
        List<Booking> bookingList = new ArrayList<>();
        Connection con = getConnection();
        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, property_id);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Booking booking = new Booking(
                    rs.getInt("booking_id"),
                    rs.getInt("property_id"),
                    rs.getInt("user_id"),
                    rs.getString("status"),
                    rs.getDate("booking_date")
            );
            bookingList.add(booking);
        }
        return bookingList;
    }

    //update booking status
    public boolean updateBookingStatus(int bookingId, String newStatus) throws SQLException {
        String query = "UPDATE bookings SET status = ? WHERE booking_id = ?";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, newStatus);
            ps.setInt(2, bookingId);

            int row = ps.executeUpdate();
            return row > 0;
        }
    }

    public List<Booking> getBookingsByUserId(int userId) throws SQLException {
        String query = "select * from bookings where user_id = ? ";
        List<Booking> bookingList = new ArrayList<>();
        Connection con = getConnection();
        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1,userId);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Booking booking = new Booking(
                    rs.getInt("booking_id"),
                    rs.getInt("property_id"),
                    rs.getInt("user_id"),
                    rs.getString("status"),
                    rs.getDate("booking_date")
            );
            bookingList.add(booking);
        }
        return bookingList;
    }
}
