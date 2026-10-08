package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "registrations")
public class Registration {

    @Id
    private String id;

    @Column(name = "user_id", nullable = false)
    private String userId;

    @Column(name = "event_id", nullable = false)
    private String eventId;

    @Column(nullable = false)
    private String status; // 'Confirmed' | 'Pending'

    @Column(name = "booking_date", nullable = false)
    private String bookingDate;

    @Column(name = "payment_status")
    private String paymentStatus; // 'Completed' | 'Pending' | 'Failed'

    @Column(name = "amount_paid")
    private Double amountPaid;

    @Column(name = "tickets_count")
    private Integer ticketsCount;

    @Column(name = "payment_method")
    private String paymentMethod;

    @Column(name = "transaction_id")
    private String transactionId;

    public Registration() {
    }

    public Registration(String id, String userId, String eventId, String status, String bookingDate,
                        String paymentStatus, Double amountPaid, Integer ticketsCount,
                        String paymentMethod, String transactionId) {
        this.id = id;
        this.userId = userId;
        this.eventId = eventId;
        this.status = status;
        this.bookingDate = bookingDate;
        this.paymentStatus = paymentStatus;
        this.amountPaid = amountPaid;
        this.ticketsCount = ticketsCount;
        this.paymentMethod = paymentMethod;
        this.transactionId = transactionId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(String bookingDate) {
        this.bookingDate = bookingDate;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public Double getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(Double amountPaid) {
        this.amountPaid = amountPaid;
    }

    public Integer getTicketsCount() {
        return ticketsCount;
    }

    public void setTicketsCount(Integer ticketsCount) {
        this.ticketsCount = ticketsCount;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }
}
