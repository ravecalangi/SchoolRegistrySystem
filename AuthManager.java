package com.mycompany.delete;

import java.util.HashMap;
import java.util.Map;

public class AuthManager {
    
    private final Map<String, String> studentAccounts = new HashMap<>();
    private final Map<String, String> teacherAccounts = new HashMap<>();
    private final Map<String, String> adminAccounts = new HashMap<>();
    
    // Register a student account
    public void registerAsStudent(String email, String password) {
        try {
            if (email.isEmpty() || password.isEmpty()) {
                throw new IllegalArgumentException("Email or password cannot be empty.");
            }
            if (studentAccounts.containsKey(email)) {
                System.out.println("The email is already registered.");
            } else {
                studentAccounts.put(email, password);
                System.out.println("Student account registered successfully.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    // Register a teacher account
    public void registerAsTeacher(String email, String password) {
        try {
            if (email.isEmpty() || password.isEmpty()) {
                throw new IllegalArgumentException("Email or password cannot be empty.");
            }
            if (teacherAccounts.containsKey(email)) {
                System.out.println("The email is already registered.");
            } else {
                teacherAccounts.put(email, password);
                System.out.println("Teacher account registered successfully.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    // Register an admin account
    public void registerAsAdmin(String email, String password) {
        try {
            if (email.isEmpty() || password.isEmpty()) {
                throw new IllegalArgumentException("Email or password cannot be empty.");
            }
            if (adminAccounts.containsKey(email)) {
                System.out.println("The email is already registered.");
            } else {
                adminAccounts.put(email, password);
                System.out.println("Admin account registered successfully.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    // Log in as a student
    public boolean loginAsStudent(String email, String password) {
        try {
            if (email.isEmpty() || password.isEmpty()) {
                throw new IllegalArgumentException("Email or password cannot be empty.");
            }
            if (studentAccounts.containsKey(email)) {
                String storedPassword = studentAccounts.get(email);
                if (storedPassword.equals(password)) {
                    System.out.println("Login successful.");
                    return true;
                } else {
                    System.out.println("Incorrect password.");
                    return false;
                }
            } else {
                System.out.println("No account found with the provided email.");
                return false;
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        }
    }
    
    // Log in as a teacher
    public boolean loginAsTeacher(String email, String password) {
        try {
            if (email.isEmpty() || password.isEmpty()) {
                throw new IllegalArgumentException("Email or password cannot be empty.");
            }
            if (teacherAccounts.containsKey(email)) {
                String storedPassword = teacherAccounts.get(email);
                if (storedPassword.equals(password)) {
                    System.out.println("Login successful.");
                    return true;
                } else {
                    System.out.println("Incorrect password.");
                    return false;
                }
            } else {
                System.out.println("No account found with the provided email.");
                return false;
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        }
    }
    
    // Log in as an admin
    public boolean loginAsAdmin(String email, String password) {
        try {
            if (email.isEmpty() || password.isEmpty()) {
                throw new IllegalArgumentException("Email or password cannot be empty.");
            }
            if (adminAccounts.containsKey(email)) {
                String storedPassword = adminAccounts.get(email);
                if (storedPassword.equals(password)) {
                    System.out.println("Login successful.");
                    return true;
                } else {
                    System.out.println("Incorrect password.");
                    return false;
                }
            } else {
                System.out.println("No account found with the provided email.");
                return false;
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        }
    }
}

 
