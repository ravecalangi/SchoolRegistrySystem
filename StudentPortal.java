package com.mycompany.delete;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

public class StudentPortal {

    public void studentMenuChoice(Scanner scanner, PeopleRegistry peopleRegistry){
        System.out.print("Your Choice: ");
        try {
            int choice = scanner.nextInt();
            switch(choice){
                case 1 -> enrollInCourse(scanner, peopleRegistry);
                case 2 -> checkProgress(scanner, peopleRegistry);
                case 3 -> markAttendance(scanner, peopleRegistry);
                case 4 -> takeQuiz(scanner);
                case 5 -> accessMaterials();
                case 6 -> sendMessageToTeacher(scanner);
                case 7 -> viewAnnouncements();
                case 8 -> payFees(scanner, peopleRegistry);
                case 9 -> logout();
                default -> System.out.println("Invalid choice. Please try again.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input! Please enter a number.");
            scanner.nextLine(); // clear invalid input
        }
    }

    public void enrollInCourse(Scanner scanner, PeopleRegistry peopleRegistry){
        System.out.println("You've chosen to enroll in courses. Let's find the best fit for you!");
        try {
            System.out.print("Enter Student ID to enroll: ");
            int studentId = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Enter Course Code to enroll in: ");
            String courseCode = scanner.nextLine();

            Student student = peopleRegistry.getStudent(studentId);
            Course course = peopleRegistry.getCourse(courseCode);
            
            // Check if both the student and course exist
            if(student != null && course != null) { 
                // Retrieve the student's enrolled courses, or initialize an empty map if not found (avoiding null)
                Map<String, Course> enrolledCourses = peopleRegistry.studentEnrollments.getOrDefault(studentId, new HashMap<>());
                if(enrolledCourses.containsKey(courseCode)){
                    System.out.println("You are already enrolled in this course.");
                } else {
                    enrolledCourses.put(courseCode, course);
                    peopleRegistry.studentEnrollments.put(studentId, enrolledCourses);
                    System.out.println("Successfully enrolled in course: " + courseCode);
                }
            } else {
                System.out.println("Student or course not found.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please try again.");
            scanner.nextLine();
        }
    }

    public void checkProgress(Scanner scanner, PeopleRegistry peopleRegistry){
        System.out.println("Checking your academic progress and grades...");
        try {
            System.out.println("Enter your student ID: ");
            int studentId = scanner.nextInt();
            scanner.nextLine();
            Student student = peopleRegistry.getStudent(studentId);
            if(student != null){
                peopleRegistry.viewStudentGrades();
            } else {
                System.out.println("Student not found.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid student ID.");
            scanner.nextLine();
        }
    }

    public void markAttendance(Scanner scanner, PeopleRegistry peopleRegistry){
        System.out.println("Time to mark your attendance. Please be honest!");
        try {
            System.out.println("Enter your student ID: ");
            int studentId = scanner.nextInt();
            scanner.nextLine();
            Student student = peopleRegistry.getStudent(studentId);
            if(student != null){
                System.out.println("Were you present today? (yes/no):");
                String input = scanner.nextLine().toLowerCase();
                boolean isPresent = input.equals("yes"); // boolean expression
                student.markAttendance(isPresent);
                System.out.println("Attendance marked as " + (isPresent ? "Present" : "Absent"));
            } else {
                System.out.println("Student not found.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please try again.");
            scanner.nextLine();
        }
    }

    public void takeQuiz(Scanner scanner){
        System.out.println("Get ready to challenge yourself! Let's start the quiz!");
        try {
            System.out.println("Please choose a quiz topic: ");
            System.out.println("1. Computer Hardware");
            System.out.println("2. Programming Basics");
            System.out.println("3. Cybersecurity");
            System.out.println("4. Computer Software");
            System.out.print("Your Choice: ");
            int quizChoice = scanner.nextInt();
            scanner.nextLine();

            int[] correctAnswers;
            int quizPoint = 0;
            String[] questions;
            String[][] options;

            switch(quizChoice){
                case 1 -> {
                    correctAnswers = new int[]{1, 1, 2, 2, 2};
                    questions = new String[]{
                        "What is the main function of the CPU?",
                        "What does RAM stand for?",
                        "Which component is responsible for cooling the computer?",
                        "What is the function of the motherboard?",
                        "Which of the following is an example of secondary storage?"
                    };
                    options = new String[][]{
                        {"1. Perform calculations and execute instructions", "2. Store data permanently", "3. Output data to the screen", "4. To display graphical output"},
                        {"1. Random Access Memory", "2. Read Access Memory", "3. Real-time Application Memory", "4. Reliable Access Memory"},
                        {"1. Power supply", "2. Fan", "3. Hard disk drive", "4. Monitor"},
                        {"1. To store all the data", "2. To connect all the hardware components", "3. To control the operating system", "4. To display graphical output"},
                        {"1. RAM", "2. Hard Disk Drive", "3. CPU", "4. Graphics card"}
                    };
                }
                case 2 -> {
                    correctAnswers = new int[]{2, 3, 1, 4, 1};
                    questions = new String[]{
                        "What does a variable store in programming?",
                        "Which data type is used to store true or false values?",
                        "What does 'int' stand for in Java?",
                        "Which symbol is used to end a statement in Java?",
                        "What is the purpose of a loop in programming?"
                    };
                    options = new String[][]{
                        {"1. Functions", "2. Data", "3. Programs", "4. Errors"},
                        {"1. String", "2. Integer", "3. Boolean", "4. Double"},
                        {"1. Integer", "2. Integration", "3. Internal", "4. Interactive"},
                        {"1. :", "2. .", "3. ,", "4. ;"},
                        {"1. To repeat a block of code", "2. To exit the program", "3. To define a function", "4. To comment code"}
                    };
                }
                case 3 -> {
                    correctAnswers = new int[]{3, 2, 4, 1, 1};
                    questions = new String[]{
                        "What is the main goal of cybersecurity?",
                        "What does a firewall do?",
                        "What kind of attack involves tricking people into giving up confidential information?",
                        "Which of the following is a strong password?",
                        "What is malware?"
                    };
                    options = new String[][]{
                        {"1. To increase internet speed", "2. To manage computer memory", "3. To protect systems and data from threats", "4. To develop software"},
                        {"1. Cleans your computer", "2. Blocks unauthorized access", "3. Makes websites load faster", "4. Repairs hardware issues"},
                        {"1. Brute force", "2. DDoS", "3. Ransomware", "4. Phishing"},
                        {"1. P@ssW0rd!2024", "2. 12345678", "3. password", "4. qwerty"},
                        {"1. Malicious software", "2. A type of hardware", "3. A coding language", "4. A backup tool"}
                    };
                }
                case 4 -> {
                    correctAnswers = new int[]{1, 2, 3, 4, 2};
                    questions = new String[]{
                        "What is system software?",
                        "Which of the following is an example of application software?",
                        "What does an operating system do?",
                        "Which software translates code written in high-level language to machine code?",
                        "What is the main function of utility software?"
                    };
                    options = new String[][]{
                        {"1. Software that manages hardware and system resources", "2. Software used for video editing", "3. Software used to browse the internet", "4. Games and entertainment software"},
                        {"1. Linux", "2. Microsoft Word", "3. BIOS", "4. Windows Defender"},
                        {"1. Writes programs", "2. Displays documents", "3. Manages hardware and software resources", "4. Stores multimedia files"},
                        {"1. Text editor", "2. Antivirus", "3. Word processor", "4. Compiler"},
                        {"1. To create presentations", "2. To maintain and optimize the computer", "3. To compile code", "4. To edit videos"}
                    };
                }
                default -> {
                    System.out.println("Invalid quiz selection.");
                    return;
                }
            }

            for(int i = 0; i < questions.length; i++){
                System.out.println(questions[i]);
                for(String option: options[i]){
                    System.out.println(option);
                }
                System.out.print("Your choice: ");
                int answer = scanner.nextInt();
                if(answer == correctAnswers[i]){
                    quizPoint++;
                }
            }

            System.out.println("You scored " + quizPoint + " correct answers.");
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a number.");
            scanner.nextLine();
        }
    }

    public void accessMaterials(){
        System.out.println("Accessing learning materials to enhance your studies...");
        String filePath = "educationalMaterials.txt";
        try(BufferedReader reader = new BufferedReader(new FileReader(filePath))){
            String line;
            while((line = reader.readLine()) != null){
                System.out.println(line);
            }
        }
        catch(FileNotFoundException e){
            System.out.println("No materials have been sent yet. Please try again later.");
        }
        catch(IOException e){
            System.out.println("An error occurred while accessing the materials.");
        }
    }

    public void sendMessageToTeacher(Scanner scanner){
        System.out.println("Please enter your message to the teacher below:");
        scanner.nextLine();
        System.out.print("Message: ");
        String messageContext = scanner.nextLine();
        String filePath = "studentMessage.txt";
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))){
            writer.write(messageContext);
            System.out.println("Message sent to the teacher successfully!");
        }catch(IOException e){
            System.out.println("An error occurred while sending the message.");
        }
    }

    public void viewAnnouncements(){
        System.out.println("Here are the latest school announcements:");
        String filePath = "announcements.txt";
        try(BufferedReader reader = new BufferedReader(new FileReader(filePath))){
            String line;
            while((line = reader.readLine()) != null){
                System.out.println(line);
            }
        } catch (IOException e){
            System.out.println("There are no announcements at the moment.");
        }
    }

    public void payFees(Scanner scanner, PeopleRegistry peopleRegistry){
        try {
            System.out.println("Enter your student ID to proceed with payment: ");
            int studentId = scanner.nextInt();
            scanner.nextLine();
            Student student = peopleRegistry.getStudent(studentId);
            if(student != null){
                System.out.println("Paying fees for " + student.getName() + ".");
                System.out.println("Fees successfully paid!");
            } else {
                System.out.println("Student not found.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid student ID.");
            scanner.nextLine();
        }
    }

    public void logout(){
        System.out.println("Logging out... Goodbye!");
    }
}



