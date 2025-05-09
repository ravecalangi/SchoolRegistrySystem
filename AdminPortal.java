package com.mycompany.delete;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class AdminPortal {

    public static double schoolTotalEarned = 0;
    public static double schoolTotalSpent = 0;

    // Method to handle admin menu choices
    public void adminMenuChoice(Scanner scanner, PeopleRegistry peopleRegistry) {
        System.out.print("Please select an option: ");
        try {
            int choice = scanner.nextInt();
            scanner.nextLine();  // consume the newline
            switch (choice) {
                case 1 -> addCourse(scanner, peopleRegistry);
                case 2 -> addStudent(scanner, peopleRegistry);
                case 3 -> addTeacher(scanner, peopleRegistry);
                case 4 -> viewTeachersAndClasses(peopleRegistry);
                case 5 -> viewStudentsAndClasses(peopleRegistry);
                case 6 -> enrollTeacherToAClass(scanner, peopleRegistry);
                case 7 -> updateTeacherSalary(scanner, peopleRegistry);
                case 8 -> viewAllAttendanceRecords(peopleRegistry);
                case 9 -> logout();
                default -> System.out.println("Invalid choice. Please try again.");
            }
        } catch (Exception e) {
            System.out.println("Invalid input. Please enter a valid number.");
            scanner.nextLine(); // clear the buffer
        }
    }

    // Method to update the total money earned by the school
    public void updateTotalMoneyEarned(double feesPaid) {
        schoolTotalEarned += feesPaid;
    }

    // Method to add a new course
    public void addCourse(Scanner scanner, PeopleRegistry peopleRegistry) {
        System.out.println("\n--- Add New Course ---");
        System.out.print("Enter course code: ");
        String code = scanner.nextLine();
        System.out.print("Enter course name: ");
        String name = scanner.nextLine();
        Course course = new Course(code, name);
        peopleRegistry.addCourse(course);
    }

    // Method to register a new student
    public void addStudent(Scanner scanner, PeopleRegistry peopleRegistry) {
        System.out.println("\n--- Register New Student ---");
        try {
            System.out.print("Enter student ID: ");
            int id = scanner.nextInt();
            scanner.nextLine();  // consume the newline
            System.out.print("Enter student name: ");
            String name = scanner.nextLine();
            System.out.print("Enter student age: ");
            int age = scanner.nextInt();
            scanner.nextLine();  // consume the newline
            Student student = new Student(id, name, age);
            peopleRegistry.addStudent(student);
            System.out.println("Student registered successfully.\n");
        } catch (Exception e) {
            System.out.println("Invalid input. Please enter valid information.");
            scanner.nextLine(); // clear the buffer
        }
    }

    // Method to hire a new teacher
    public void addTeacher(Scanner scanner, PeopleRegistry peopleRegistry) {
        System.out.println("\n--- Hire New Teacher ---");
        try {
            System.out.print("Enter teacher ID: ");
            int id = scanner.nextInt();
            scanner.nextLine();  // consume the newline
            System.out.print("Enter teacher name: ");
            String name = scanner.nextLine();
            System.out.print("Enter teacher age: ");
            int age = scanner.nextInt();
            scanner.nextLine();  // consume the newline
            Teacher teacher = new Teacher(id, name, age);
            peopleRegistry.addTeacher(teacher);
            System.out.println("Teacher hired successfully.\n");
        } catch (Exception e) {
            System.out.println("Invalid input. Please enter valid information.");
            scanner.nextLine(); // clear the buffer
        }
    }

    // Method to view teachers and their assigned courses
    public void viewTeachersAndClasses(PeopleRegistry peopleRegistry) {
        System.out.println("\n--- Teachers and Assigned Courses ---");
        peopleRegistry.viewTeachersAndTheirClasses();
        System.out.println();
    }

    // Method to view students and their enrolled courses
    public void viewStudentsAndClasses(PeopleRegistry peopleRegistry) {
        System.out.println("\n--- Students and Enrolled Courses ---");
        peopleRegistry.viewEnrolledStudentsWithCourses();
        System.out.println();
    }

    // Method to enroll a teacher in a course
    public void enrollTeacherToAClass(Scanner scanner, PeopleRegistry peopleRegistry) {
        System.out.println("\n--- Enroll Teacher to a Course ---");
        try {
            System.out.print("Enter teacher ID: ");
            int teacherId = scanner.nextInt();
            scanner.nextLine();  // consume the newline
            System.out.print("Enter course code: ");
            String courseCode = scanner.nextLine();

            Teacher teacher = peopleRegistry.getTeacher(teacherId);
            Course course = peopleRegistry.getCourse(courseCode);

            if (teacher != null && course != null) {
                Map<String, Course> enrolledCourses = peopleRegistry.teacherEnrollments.getOrDefault(courseCode, new HashMap<>());
                if (enrolledCourses.containsKey(courseCode)) {
                    System.out.println("This teacher is already assigned to the course.");
                } else {
                    enrolledCourses.put(courseCode, course);
                    peopleRegistry.teacherEnrollments.put(teacherId, enrolledCourses);
                    System.out.println("Teacher successfully enrolled in course: " + courseCode);
                }
            } else {
                System.out.println("Error: Teacher or Course not found.");
            }
        } catch (Exception e) {
            System.out.println("Invalid input. Please enter valid teacher ID and course code.");
            scanner.nextLine(); // clear the buffer
        }
        System.out.println();
    }

    // Method to update a teacher's salary
    public void updateTeacherSalary(Scanner scanner, PeopleRegistry peopleRegistry) {
        System.out.println("\n--- Update Teacher Salary ---");
        try {
            System.out.print("Enter teacher ID: ");
            int teacherId = scanner.nextInt();
            scanner.nextLine();  // consume the newline

            Teacher teacher = peopleRegistry.getTeacher(teacherId);
            if (teacher != null) {
                System.out.println("Current salary for " + teacher.getName() + ": " + teacher.getSalary());
                System.out.print("Enter new salary amount: ");
                double newSalary = scanner.nextDouble();
                teacher.setSalary(newSalary);
                schoolTotalSpent += newSalary;
                System.out.println("Salary updated successfully.\n");
            } else {
                System.out.println("Teacher not found.\n");
            }
        } catch (Exception e) {
            System.out.println("Invalid input. Please enter valid salary information.");
            scanner.nextLine(); // clear the buffer
        }
    }

    // Method to view all attendance records for students and teachers
    public void viewAllAttendanceRecords(PeopleRegistry peopleRegistry) {
        System.out.println("\n=== Attendance Records ===\n");

        System.out.println(">> Student Attendance:\n");
        for (Student student : peopleRegistry.students.values()) {
            System.out.println("Student: " + student.getName() + " (ID: " + student.getId() + ")");
            Map<LocalDateTime, Boolean> attendance = student.getAttendance();

            if (attendance.isEmpty()) {
                System.out.println("  No attendance records available.");
            } else {
                for (Map.Entry<LocalDateTime, Boolean> entry : attendance.entrySet()) {
                    String status = entry.getValue() ? "Present" : "Absent";
                    System.out.println("  " + entry.getKey() + " - " + status);
                }
            }
            System.out.println();
        }

        System.out.println(">> Teacher Attendance:\n");
        for (Teacher teacher : peopleRegistry.teachers.values()) {
            System.out.println("Teacher: " + teacher.getName() + " (ID: " + teacher.getId() + ")");
            Map<LocalDateTime, Boolean> attendance = teacher.getAttendance();

            if (attendance.isEmpty()) {
                System.out.println("  No attendance records available.");
            } else {
                for (Map.Entry<LocalDateTime, Boolean> entry : attendance.entrySet()) {
                    String status = entry.getValue() ? "Present" : "Absent";
                    System.out.println("  " + entry.getKey() + " - " + status);
                }
            }
            System.out.println();
        }
    }

    // Method to log out of the admin portal
    public void logout() {
        System.out.println("\nYou have successfully logged out. See you next time!");
    }
}



