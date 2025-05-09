package com.mycompany.delete;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class Student extends User {

    // Attendance records: date and presence status
    private final Map<LocalDateTime, Boolean> attendance = new HashMap<>();

    private int id;
    private String name;
    private int age;
    private double fees;
    private double feesPaid;

    // Constructor initializes student details and default fee amount
    public Student(int id, String name, int age) {
        super(id, name, age);
        this.fees = 50000;
    }

    // Marks attendance with current date and presence status
    public void markAttendance(boolean isPresent) {
        LocalDateTime today = LocalDateTime.now();
        attendance.put(today, isPresent);
    }

    // Returns full attendance history
    public Map<LocalDateTime, Boolean> getAttendance() {
        return attendance;
    }

    // Records a payment made by the student
    public void makePayment(double fees) {
        this.feesPaid += fees;
        AdminPortal.schoolTotalEarned += fees;
    }

    // Returns the total fee for the student
    public double getFees() {
        return fees;
    }

    // Returns the total amount the student has paid
    public double getFeesPaid() {
        return feesPaid;
    }

    // Returns the remaining balance the student has to pay
    public double getRemainingFees() {
        return fees - feesPaid;
    }
}

