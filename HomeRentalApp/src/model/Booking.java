package model;

import java.sql.Date;

public class Booking {
    private int bookingId;
    private int propertyId;
    private int userId;
    private String status;
    private Date bookingDate;

    public Booking() {
    }

    public Booking(int bookingId, int propertyId, int userId, String status, Date bookingDate) {
        this.bookingId = bookingId;
        this.propertyId = propertyId;
        this.userId = userId;
        this.status = status;
        this.bookingDate = bookingDate;
    }

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public int getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(int propertyId) {
        this.propertyId = propertyId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(Date bookingDate) {
        this.bookingDate = bookingDate;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "bookingId=" + bookingId +
                ", propertyId=" + propertyId +
                ", userId=" + userId +
                ", status='" + status + '\'' +
                ", bookingDate=" + bookingDate +
                '}';
    }
}
