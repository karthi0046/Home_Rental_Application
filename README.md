# 🏠 Home Rental Application

A full-stack **Home Rental Management System** that allows tenants to find and book rental properties, while enabling property owners to list and manage their properties.
The application includes secure authentication, file uploads, role-based dashboards, and real-time booking workflow.

---

## 🔧 Technologies Used

- **Java 17**
- **Spring Boot 3.5.0**
- **JWT (JSON Web Tokens)** for Authentication
- **MySQL** for Data Storage
- **HTML**, **CSS**, **JavaScript** for Frontend
- **Postman** for API Testing

---

## 👤 User Roles

### 1. Tenant  
- View and search available properties  
- Book properties that are available (`Available: Yes`)  
- View all booking requests they’ve made  
- Cannot rebook or view properties they've already booked  
- Cancel bookings only when status is **Pending**

### 2. Owner  
- Add properties with multiple images  
- View, update (toggle status), or delete their own properties  
- View booking requests from tenants  
- Approve, reject, or delete booking requests  
  - Delete is only allowed if the booking status is **Pending** or **Rejected**

---

## 🔐 Authentication

- Secure **JWT-based login system**  
- Role-based redirection to **Tenant** or **Owner** dashboard  
- **Forgot Password** feature with OTP verification  
- Supports registration for both **Tenant** and **Owner**

---

## 🧰 Features

### 🌐 Welcome Page
- Aesthetic login page with luxury house background
- "Forgot Password?" → triggers OTP-based password reset
- "Don't have an account? Register" → opens registration form

---

### 🏘️ Tenant Dashboard
- **View All Available Properties**
  - Shows only properties with `Available: Yes`
  - "Book Now" button is disabled for unavailable properties
- **Search and Filter**
  - Search by **Title** or **Address**
  - Filter by **Max Rent** or **Room Count**
- **Book a Property**
  - Sends booking request to the property owner
  - Booked property disappears from home list
- **View Bookings**
  - Displays all booking requests made by the tenant
  - Shows booking status: `Pending`, `Approved`, `Rejected`
  - Allows **Cancel Booking** if status is `Pending`

---

### 🏢 Owner Dashboard
- **Add Property**
  - Submit property form with details and multiple image uploads
- **View My Properties**
  - List all properties added by the owner
  - Delete a property or toggle its availability (`Available: Yes/No`)
- **View Booking Requests**
  - See all booking requests on owned properties
  - Actions: Approve, Reject, Delete
    - Delete is disabled for `Approved` bookings
- **Logout**
  - Ends session and redirects to login

---

## 🔄 Booking Flow

```text
TENANT
  ↓ Clicks "Book Now" on available property
OWNER
  ↓ Views request in dashboard
     → Approve / Reject / Delete (if Pending or Rejected)
TENANT
  ↓ Sees status in "View Bookings"
     → Can cancel only if status is Pending
