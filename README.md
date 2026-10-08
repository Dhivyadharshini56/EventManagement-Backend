# 🚀 Eventify - Event Management System (Backend API)

A robust, enterprise-ready **Spring Boot REST API** backend for the **Event Management System**, built with **Java 21**, **Spring Boot 4 / 3**, **Spring Data JPA**, **Hibernate**, and **MySQL**.

---

## 🌟 Key Features

### 1. 🔐 Authentication & Role Management (`/api/auth`, `/api/users`)
- Role-based authorization for **Registered Users / Attendees**, **Event Organizers**, and **System Administrators**.
- User profile management, password verification, and registration validation.

### 2. 🎪 Event Management API (`/api/events`)
- Complete CRUD operations for event listings (Title, Date, Venue, Description, Category, Price, Image, and Seat Capacity).
- Status filtering and admin approval support (`Pending`, `Approved`, `Rejected`).
- Max ticket pricing constraints.

### 3. 🎟️ Registrations & Ticket Booking API (`/api/registrations`)
- Real-time seat inventory reservation and capacity tracking.
- Multi-payment confirmation handling (Card, UPI, Net Banking, Wallets).
- Automated 100% refund recording, security passcodes, and tax invoice generation.

### 4. 💬 Feedback & Reviews API (`/api/feedbacks`)
- 5-Star attendee ratings, event reviews, and recommendation stats.

### 5. 🔔 Notifications & Announcements API (`/api/notifications`)
- Push alerts for event approvals, schedule changes, booking confirmations, and organizer broadcasts.

---

## 🛠️ Tech Stack

- **Language**: Java 21
- **Framework**: Spring Boot (Spring Web MVC, Spring Data JPA, Spring Validation)
- **Database**: MySQL 8.0+
- **ORM**: Hibernate
- **Build Tool**: Apache Maven (`mvnw`)
- **CORS Support**: Configured for `http://localhost:3000` (Next.js frontend)

---

## 📁 Backend Project Structure

```
backend/
├── src/
│   ├── main/
│   │   ├── java/com/example/demo/
│   │   │   ├── config/              # CORS & DataInitializer configurations
│   │   │   ├── controller/          # REST Controllers (Auth, Event, Registration, User, etc.)
│   │   │   ├── model/               # JPA Entities (Event, User, Registration, Notification, Feedback)
│   │   │   ├── repository/          # Spring Data JPA Repositories
│   │   │   └── DemoApplication.java # Spring Boot Main Entry Point
│   │   └── resources/
│   │       ├── application.properties.example # DB Configuration template
│   │       └── application.properties         # Local configuration
│   └── test/                        # Unit and integration tests
├── .mvn/                            # Maven wrapper files
├── mvnw / mvnw.cmd                  # Maven wrapper scripts
├── pom.xml                          # Maven dependencies & build plugins
└── README.md                        # Backend documentation
```

---

## 🚀 Getting Started

### 1. Prerequisites
- **Java Development Kit (JDK)**: Java 21 or higher
- **MySQL Server**: Running on port `3306`

### 2. Database Setup
Create the MySQL database:
```sql
CREATE DATABASE IF NOT EXISTS eventdb;
```

### 3. Configuration
Copy the configuration template:
```bash
cp src/main/resources/application.properties.example src/main/resources/application.properties
```
Update `src/main/resources/application.properties` with your MySQL username and password:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/eventdb?createDatabaseIfNotExist=true&useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true
spring.datasource.username=root
spring.datasource.password=YOUR_PASSWORD
```

### 4. Run the Backend Server
```bash
# On Windows
.\mvnw.cmd spring-boot:run

# On Linux / macOS
./mvnw spring-boot:run
```

The Spring Boot backend will start on **http://localhost:8080**.

---

## 📡 Core API Endpoints

| Method | Endpoint | Description |
| :--- | :--- | :--- |
| `POST` | `/api/auth/login` | User / Admin / Organizer login |
| `POST` | `/api/auth/register` | Register new user account |
| `GET` | `/api/events` | List all events (supports category filter) |
| `GET` | `/api/events/{id}` | Get event details by ID |
| `POST` | `/api/events` | Create a new event (Organizer / Admin) |
| `PUT` | `/api/events/{id}` | Update event details / Approval status |
| `DELETE` | `/api/events/{id}` | Delete an event |
| `GET` | `/api/registrations` | List all bookings & passes |
| `POST` | `/api/registrations` | Book ticket pass with payment confirmation |
| `PUT` | `/api/registrations/{id}` | Update registration / Cancel & Refund |
| `GET` | `/api/feedbacks` | Fetch attendee reviews & ratings |
| `POST` | `/api/feedbacks` | Submit event feedback |
| `GET` | `/api/notifications` | Fetch system notifications |

---

## 📄 License
Developed for MCA Final Year Project demonstrations.

