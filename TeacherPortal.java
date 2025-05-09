package com.mycompany.delete;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class TeacherPortal {

    // Handle the teacher's menu choices
    public void teacherMenuChoice(Scanner scanner, PeopleRegistry peopleRegistry){
        try {
            System.out.print("Your Choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch(choice){
                case 1 -> enrollCourse(scanner, peopleRegistry);
                case 2 -> viewEnrolledStudents(peopleRegistry);
                case 3 -> uploadEducationalMaterials();
                case 4 -> assignGrades(scanner, peopleRegistry);
                case 5 -> markAttendance(scanner, peopleRegistry);
                case 6 -> viewStudentMessages();
                case 7 -> viewSalaryDetails(scanner, peopleRegistry);
                case 8 -> composeAnnouncement(scanner);
                case 9 -> logout();
                default -> System.out.println("Invalid Choice!");
            }
        } catch (Exception e) {
            System.out.println("Invalid input. Please enter a number.");
            System.out.println(e.getMessage());
            scanner.nextLine(); // clear the wrong input
        }
    }

    // Method for a teacher to enroll in a course
    public void enrollCourse(Scanner scanner, PeopleRegistry peopleRegistry){
        try {
            System.out.println("You chose to enroll in available courses. Let's find the right one for you!");
            System.out.print("Enter Teacher ID to enroll: ");
            int teacherId = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Enter Course Code to enroll in: ");
            String courseCode = scanner.nextLine();

            Teacher teacher = peopleRegistry.getTeacher(teacherId);
            Course course = peopleRegistry.getCourse(courseCode);

            if(teacher != null && course != null){
                Map<String, Course> enrolledCourses = peopleRegistry.teacherEnrollments.getOrDefault(teacherId, new HashMap<>());
                if(enrolledCourses.containsKey(courseCode)){
                    System.out.println("You are already enrolled in this course.");
                } else {
                    enrolledCourses.put(courseCode, course);
                    peopleRegistry.teacherEnrollments.put(teacherId, enrolledCourses);
                    System.out.println("Enrolled successfully in course: " + courseCode);
                }
            } else {
                System.out.println("Teacher or Course not found.");
            }
        } catch (Exception e) {
            System.out.println("Something went wrong during enrollment.");
            System.out.println(e.getMessage());
        }
    }

    // View the list of students enrolled in each subject
    public void viewEnrolledStudents(PeopleRegistry peopleRegistry){
        try {
            System.out.println("Here's the list of students enrolled in each subject.");
            peopleRegistry.viewEnrolledStudentsWithCourses();
        } catch (Exception e) {
            System.out.println("Unable to view enrolled students.");
            System.out.println(e.getMessage());
        }
    }

    // Upload educational materials for students
    public void uploadEducationalMaterials(){
        System.out.println("Uploading educational materials... Knowledge is power!");

        String filePath = "educationalMaterials.txt";
        String educationalMaterial = """
                                     Lesson 1: What is a Parrot?
                                     
                                     What is a Parrot?
                                     A parrot is a colorful bird known for its ability to mimic sounds and human speech.
                                     Parrots are intelligent, social animals often kept as pets and found in warm climates.
                                     
                                     Why Learn About Parrots?
                                     Learning about parrots helps us understand more about bird behavior, communication, and the environment they live in.
                                     Parrots are fascinating creatures with unique traits that make them special among birds.
                                     
                                     Example:
                                     
                                     A parrot can repeat words you say.
                                     
                                     It can have bright feathers like green, red, or blue.
                                     
                                     It can live in the wild or in your home as a pet.
                                     
                                     Knowing about parrots helps us appreciate nature and take better care of animals.
        """;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(educationalMaterial);
            System.out.println("Educational Material successfully sent to Students");
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println("Something went wrong");
            System.out.println(e.getMessage());
        }
    }

    // Method to assign grades to students
    public void assignGrades(Scanner scanner, PeopleRegistry peopleRegistry) {
        try {
            System.out.println("Let's assign grades and recognize your students' hard work!");

            System.out.print("Enter Student ID: ");
            int studentId = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Enter Course Code: ");
            String courseCode = scanner.nextLine();

            System.out.print("Enter Grade: ");
            double grade = scanner.nextDouble();

            peopleRegistry.assignGrade(studentId, courseCode, grade);
        } catch (Exception e) {
            System.out.println("Something went wrong while assigning grades.");
            System.out.println(e.getMessage());
            scanner.nextLine(); // clear invalid input
        }
    }

    // Mark attendance for the teacher
    public void markAttendance(Scanner scanner, PeopleRegistry peopleRegistry){
        try {
            System.out.println("Time to mark your attendance. Be honest!");
            System.out.println("Enter your teacher Id: ");
            int teacherId = scanner.nextInt();
            scanner.nextLine();

            Teacher teacher = peopleRegistry.getTeacher(teacherId);

            if(teacher != null){
                System.out.println("Were you present today? (yes/no) :");
                String input = scanner.nextLine().toLowerCase();

                boolean isPresent = input.equals("yes");

                teacher.markAttendance(isPresent);
                System.out.println("Attendance Marked as " + (isPresent ? "Present" : "Absent"));
            } else {
                System.out.println("Teacher not Found.");
            }
        } catch (Exception e) {
            System.out.println("Something went wrong while marking attendance.");
            System.out.println(e.getMessage());
            scanner.nextLine(); // clear invalid input
        }
    }

    // View messages from students
    public void viewStudentMessages(){
        System.out.println("View the messages from the student.");

        String filePath = "studentMessage.txt";
        String line;
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            while((line = reader.readLine()) != null){
                System.out.println(line);
            }
        } catch (FileNotFoundException e) {
            System.out.println("There is no message from the students yet.");
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println("Something went wrong");
            System.out.println(e.getMessage());
        }
    }

    // View teacher salary details
    public void viewSalaryDetails(Scanner scanner, PeopleRegistry peopleRegistry){
        try {
            System.out.println("Accessing salary details... You’ve earned it!");
            System.out.println("Enter your Teacher Id: ");
            int teacherId = scanner.nextInt();
            scanner.nextLine();

            Teacher teacher = peopleRegistry.getTeacher(teacherId);

            if(teacher != null){
                System.out.println("Total Salary: " + teacher.getSalary());
            } else {
                System.out.println("Teacher not Found");
            }
        } catch (Exception e) {
            System.out.println("Something went wrong while viewing salary details.");
            System.out.println(e.getMessage());
            scanner.nextLine(); // clear invalid input
        }
    }

    // Compose an announcement
    public void composeAnnouncement(Scanner scanner){
        System.out.println("Composing your announcement... Make it inspiring!");
        String filePath = "composedAnnouncement.txt";

        try {
            System.out.println("Announcement: ");
            String announcement = scanner.nextLine();

            try (PrintWriter writer = new PrintWriter(filePath)) {
                writer.write(announcement);
                System.out.println("Announcement was delivered successfully.");
            }
        } catch (IOException e) {
            System.out.println("Something went wrong");
            System.out.println(e.getMessage());
        }
    }

    // Logout the teacher from the portal
    public void logout(){
        System.out.println("Logging out... Have a great day, teacher!");
    }
}


