package com.mycompany.delete;

import java.util.InputMismatchException;
import java.util.Scanner;

public class SchoolManagementSystemOOP {

    public static void runMainMenu() {

        AuthManager authManager = new AuthManager();
        PeopleRegistry peopleRegistry = new PeopleRegistry();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n+-----------------------------+");
            System.out.println("|        Main Menu            |");
            System.out.println("+-----------------------------+");
            System.out.println("| 1. Log In                   |");
            System.out.println("| 2. Register                 |");
            System.out.println("| 3. Exit                     |");
            System.out.println("+-----------------------------+");
            System.out.print("\nPlease select an option (1-3): ");

            int menuChoice = 0;
            try {
                menuChoice = scanner.nextInt();
                scanner.nextLine();
            } catch (InputMismatchException e) {
                System.out.println(menuChoice + " was not a number.");
                System.out.println("Invalid input! Please enter a number.");
                scanner.nextLine(); // clear buffer
                continue; // Skip the rest of the code and go back to the top of the loop
            }

            switch (menuChoice) {
                case 1 -> showLoginMenu(authManager, scanner, peopleRegistry);
                case 2 -> showRegisterMenu(authManager, scanner, peopleRegistry);
                case 3 -> {
                    System.out.println("Thank you for using the system.");
                    scanner.close();
                    return;
                }
                default -> System.out.println("Invalid choice! Please select between 1 and 3.");
            }
        }
    }

    public static String getValidEmail(Scanner scanner) {
        String email;
        // Print valid domains for the user to know
        System.out.println("Valid email domains:");
        System.out.println("- @bulsu.edu.ph");
        System.out.println("- @outlook.com");
        System.out.println("- @yahoo.com");
        System.out.println("- @gmail.com");

        while (true) {
            System.out.print("Please enter your email: ");
            email = scanner.nextLine();

            if (email.length() >= 14 &&
                (email.endsWith("@bulsu.edu.ph") || email.endsWith("@outlook.com") ||
                 email.endsWith("@yahoo.com") || email.endsWith("@gmail.com"))) {
                break;
            } else {
                System.out.println("Invalid email! The email must be at least 14 characters long and have a valid domain.");
            }
        }
        return email; // Return the valid email entered by the user.
    }


    public static void showLoginMenu(AuthManager authManager, Scanner scanner, PeopleRegistry peopleRegistry) {
        System.out.println("\n+-------------------------------+");
        System.out.println("|         Log in as:            |");
        System.out.println("+-------------------------------+");
        System.out.println("| 1. Student                    |");
        System.out.println("| 2. Teacher                    |");
        System.out.println("| 3. Admin                      |");
        System.out.println("| 4. Back                       |");
        System.out.println("+-------------------------------+");
        System.out.print("\nPlease select an option (1-4): ");

        int secondMenuChoice = 0;
        try {
            secondMenuChoice = scanner.nextInt();
            scanner.nextLine();
        } catch (InputMismatchException e) {
            System.out.println("Invalid input! Please enter a number.");
            scanner.nextLine(); // clear buffer
            return;
        }

        switch (secondMenuChoice) {
            case 1 -> loginAsStudent(authManager, scanner, peopleRegistry);
            case 2 -> loginAsTeacher(authManager, scanner, peopleRegistry);
            case 3 -> loginAsAdmin(authManager, scanner, peopleRegistry);
            case 4 -> System.out.println("Going back to the previous menu...");
            default -> System.out.println("Invalid choice! Please select between 1 and 4.");
        }
    }

    public static void showStudentMenu(Scanner scanner, PeopleRegistry peopleRegistry) {
        System.out.println("\n+--------------------------------------------------+");
        System.out.println("|             Student Portal Menu                  |");
        System.out.println("+--------------------------------------------------+");
        System.out.println("| 1. Enroll in Available Courses                   |");
        System.out.println("| 2. View Academic Performance (Grades)            |");
        System.out.println("| 3. Mark Attendance                               |");
        System.out.println("| 4. Participate in Quizzes                        |");
        System.out.println("| 5. Download Educational Resources                |");
        System.out.println("| 6. Send a Message to the Teacher                 |");
        System.out.println("| 7. View Latest Announcements                     |");
        System.out.println("| 8. View and Pay Fees                             |");
        System.out.println("| 9. Log Out                                       |");
        System.out.println("+--------------------------------------------------+");
        System.out.print("\nPlease select an option (1-9): ");

        StudentPortal studentPortal = new StudentPortal();
        studentPortal.studentMenuChoice(scanner, peopleRegistry);
    }

    public static void showTeacherMenu(Scanner scanner, PeopleRegistry peopleRegistry) {
        System.out.println("\n+-----------------------------------------------+");
        System.out.println("|               Teacher Portal Menu             |");
        System.out.println("+-----------------------------------------------+");
        System.out.println("| 1. Enroll in Courses to Teach Students        |");
        System.out.println("| 2. View List of Enrolled Students by Subject  |");
        System.out.println("| 3. Upload Educational Materials               |");
        System.out.println("| 4. Assign Grades to Students                  |");
        System.out.println("| 5. Mark Attendance                            |");
        System.out.println("| 6. View Student Messages                      |");
        System.out.println("| 7. View Salary Information                    |");
        System.out.println("| 8. Send Announcements to Students             |");
        System.out.println("| 9. Log Out                                    |");
        System.out.println("+-----------------------------------------------+");
        System.out.print("\nPlease select an option (1-9): ");

        TeacherPortal teacherPortal = new TeacherPortal();
        teacherPortal.teacherMenuChoice(scanner, peopleRegistry);
    }

    public static void showAdminMenu(Scanner scanner, PeopleRegistry peopleRegistry) {
        System.out.println("\n+--------------------------------------------------+");
        System.out.println("|                  Admin Portal Menu               |");
        System.out.println("+--------------------------------------------------+");
        System.out.println("| 1. Add a New Course                              |");
        System.out.println("| 2. Register a New Student                        |");
        System.out.println("| 3. Hire a New Teacher                            |");
        System.out.println("| 4. View All Teachers and Their Assigned Classes  |");
        System.out.println("| 5. View Complete Student Enrollment List         |");
        System.out.println("| 6. Assign Teacher to a Class                     |");
        System.out.println("| 7. Set Teacher Salary                            |");
        System.out.println("| 8. View Comprehensive Attendance Records         |");
        System.out.println("| 9. Log Out                                       |");
        System.out.println("+--------------------------------------------------+");
        System.out.print("\nPlease select an option (1-9): ");

        AdminPortal adminPortal = new AdminPortal();
        adminPortal.adminMenuChoice(scanner, peopleRegistry);
    }

    public static void showRegisterMenu(AuthManager authManager, Scanner scanner, PeopleRegistry peopleRegistry) {
        System.out.println("\n+---------------------------+");
        System.out.println("|       Register as:        |");
        System.out.println("+---------------------------+");
        System.out.println("| 1. Student                |");
        System.out.println("| 2. Teacher                |");
        System.out.println("| 3. Admin                  |");
        System.out.println("| 4. Back                   |");
        System.out.println("+---------------------------+");
        System.out.print("\nPlease select an option: ");

        int thirdMenuChoice = 0;
        try {
            thirdMenuChoice = scanner.nextInt();
            scanner.nextLine();
        } catch (InputMismatchException e) {
            System.out.println("Invalid input! Please enter a number.");
            scanner.nextLine(); // clear buffer
            return;
        }

        switch (thirdMenuChoice) {
            case 1 -> registerAsStudent(authManager, scanner, peopleRegistry);
            case 2 -> registerAsTeacher(authManager, scanner, peopleRegistry);
            case 3 -> registerAsAdmin(authManager, scanner);
            case 4 -> System.out.println("Going back to the previous menu...");
            default -> System.out.println("Invalid choice! Please select between 1 and 4.");
        }
    }

    public static void loginAsStudent(AuthManager authManager, Scanner scanner, PeopleRegistry peopleRegistry) {
        System.out.print("Student email: ");
        String email = scanner.nextLine();
        System.out.print("Student password: ");
        String password = scanner.nextLine();
        boolean hasAccess = authManager.loginAsStudent(email, password);
        if (hasAccess) {
            showStudentMenu(scanner, peopleRegistry);
        }
    }

    public static void loginAsTeacher(AuthManager authManager, Scanner scanner, PeopleRegistry peopleRegistry) {
        System.out.print("Teacher email: ");
        String email = scanner.nextLine();
        System.out.print("Teacher password: ");
        String password = scanner.nextLine();
        boolean hasAccess = authManager.loginAsTeacher(email, password);
        if (hasAccess) {
            showTeacherMenu(scanner, peopleRegistry);
        }
    }

    public static void loginAsAdmin(AuthManager authManager, Scanner scanner, PeopleRegistry peopleRegistry) {
        System.out.print("Admin email: ");
        String email = scanner.nextLine();
        System.out.print("Admin password: ");
        String password = scanner.nextLine();
        boolean hasAccess = authManager.loginAsAdmin(email, password);
        if (hasAccess) {
            showAdminMenu(scanner, peopleRegistry);
        }
    }

    public static void registerAsStudent(AuthManager authManager, Scanner scanner, PeopleRegistry peopleRegistry) {
        String email = getValidEmail(scanner);

        String password;
        while (true) {
            System.out.print("Enter your password: ");
            password = scanner.nextLine();
            if (password.length() >= 8) {
                break;
            } else {
                System.out.println("Password too short! It must be at least 8 characters long.");
            }
        }

        authManager.registerAsStudent(email, password);

        int id = 0;
        while (true) {
            try {
                System.out.print("Student ID: ");
                id = scanner.nextInt();
                scanner.nextLine();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid ID! Please enter a number.");
                scanner.nextLine(); // clear buffer
            }
        }

        System.out.print("Full Name: ");
        String name = scanner.nextLine();

        int age = 0;
        while (true) {
            try {
                System.out.print("Age: ");
                age = scanner.nextInt();
                scanner.nextLine();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid age! Please enter a number.");
                scanner.nextLine();
            }
        }

        Student student = new Student(id, name, age);
        peopleRegistry.addStudent(student);
    }

    public static void registerAsTeacher(AuthManager authManager, Scanner scanner, PeopleRegistry peopleRegistry) {
        String email = getValidEmail(scanner);

        String password;
        while (true) {
            System.out.print("Enter your password: ");
            password = scanner.nextLine();
            if (password.length() >= 8) {
                break;
            } else {
                System.out.println("Password too short! It must be at least 8 characters long.");
            }
        }

        authManager.registerAsTeacher(email, password);

        int id = 0;
        while (true) {
            try {
                System.out.print("Teacher ID: ");
                id = scanner.nextInt();
                scanner.nextLine();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid ID! Please enter a number.");
                scanner.nextLine();
            }
        }

        System.out.print("Full Name: ");
        String name = scanner.nextLine();

        int age = 0;
        while (true) {
            try {
                System.out.print("Age: ");
                age = scanner.nextInt();
                scanner.nextLine();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid age! Please enter a number.");
                scanner.nextLine();
            }
        }

        Teacher teacher = new Teacher(id, name, age);
        peopleRegistry.addTeacher(teacher);
    }

    public static void registerAsAdmin(AuthManager authManager, Scanner scanner) {
        String email = getValidEmail(scanner);

        String password;
        while (true) {
            System.out.print("Enter your password: ");
            password = scanner.nextLine();
            if (password.length() >= 8) {
                break;
            } else {
                System.out.println("Password too short! It must be at least 8 characters long.");
            }
        }

        authManager.registerAsAdmin(email, password);
    }
}


