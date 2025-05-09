package com.mycompany.delete;

import java.util.HashMap;
import java.util.Map;

public class PeopleRegistry {

    // Maps to hold the registered students, teachers, courses, and enrollment details
    Map<Integer, Student> students = new HashMap<>();
    Map<Integer, Teacher> teachers = new HashMap<>();
    Map<String, Course> courses = new HashMap<>();
    Map<Integer, Map<String, Course>> studentEnrollments = new HashMap<>();
    Map<Integer, Map<String, Course>> teacherEnrollments = new HashMap<>();
    Map<Integer, Map<String, Double>> studentGrades = new HashMap<>();

    // Method to add a new student to the registry
    public void addStudent(Student student) {
        try {
            if (students.containsKey(student.getId())) {
                System.out.println("This student ID is already in use.");
            } else {
                students.put(student.getId(), student);
                System.out.println("Student successfully added.");
            }
        } catch (Exception e) {
            System.out.println("Error adding student.");
            e.getMessage();
        }
    }

    // Method to add a new teacher to the registry
    public void addTeacher(Teacher teacher) {
        try {
            if (teachers.containsKey(teacher.getId())) {
                System.out.println("This teacher ID is already in use.");
            } else {
                teachers.put(teacher.getId(), teacher);
                System.out.println("Teacher successfully added.");
            }
        } catch (Exception e) {
            System.out.println("Error adding teacher.");
            e.getMessage();
        }
    }

    // Method to add a new course to the registry
    public void addCourse(Course course) {
        try {
            if (courses.containsKey(course.getCode())) {
                System.out.println("This course code is already in use.");
            } else {
                courses.put(course.getCode(), course);
                System.out.println("Course successfully added.");
            }
        } catch (Exception e) {
            System.out.println("Error adding course.");
            e.getMessage();
        }
    }

    // Retrieve a student by their ID
    public Student getStudent(int id) {
        try {
            return students.get(id);
        } catch (Exception e) {
            System.out.println("Error retrieving student with ID: " + id);
            e.getMessage();
            return null;
        }
    }

    // Retrieve a teacher by their ID
    public Teacher getTeacher(int id) {
        try {
            return teachers.get(id);
        } catch (Exception e) {
            System.out.println("Error retrieving teacher with ID: " + id);
            e.getMessage();
            return null;
        }
    }

    // Retrieve a course by its code
    public Course getCourse(String code) {
        try {
            return courses.get(code);
        } catch (Exception e) {
            System.out.println("Error retrieving course with code: " + code);
            e.getMessage();
            return null;
        }
    }
    
    // Map<Integer, Map<String, Course>> studentEnrollments = new HashMap<>();
    // Display all students and the courses they are enrolled in
    public void viewEnrolledStudentsWithCourses() {
        try {
            // Loop through each entry in the studentEnrollments map
            for (Map.Entry<Integer, Map<String, Course>> entry : studentEnrollments.entrySet()) {
                int studentId = entry.getKey();
                Student student = students.get(studentId);
                if (student == null) {
                    System.out.println("Student with ID " + studentId + " not found.");
                    continue;
                }
                System.out.println("Student: " + student.getName() + " (ID: " + studentId + ")");
                Map<String, Course> enrolledCourses = entry.getValue();
                if (enrolledCourses == null || enrolledCourses.isEmpty()) {
                    System.out.println("This student is not enrolled in any courses.");
                } else {
                    for (Course course : enrolledCourses.values()) {
                        System.out.println("Enrolled in: " + course.getName() + " (Code: " + course.getCode() + ")");
                    }
                }
                System.out.println();
            }
        } catch (Exception e) {
            System.out.println("Error viewing enrolled students with courses.");
        }
    }

    // Display all teachers and the courses they are assigned to
    public void viewEnrolledTeachersWithCourses() {
        try {
            // Loop through each entry in the teacherEnrollments map
            for (Map.Entry<Integer, Map<String, Course>> entry : teacherEnrollments.entrySet()) {
                // get the teacher id in the entry map
                int teacherId = entry.getKey();
                Teacher teacher = teachers.get(teacherId);
                if (teacher == null) {
                    System.out.println("Teacher with ID " + teacherId + " not found.");
                    continue;
                }
                System.out.println("Teacher: " + teacher.getName() + " (ID: " + teacherId + ")");
                Map<String, Course> assignedCourses = entry.getValue();
                if (assignedCourses == null || assignedCourses.isEmpty()) {
                    System.out.println("This teacher is not assigned to any courses.");
                } else {
                    for (Course course : assignedCourses.values()) {
                        System.out.println("Assigned to: " + course.getName() + " (Code: " + course.getCode() + ")");
                    }
                }
                System.out.println();
            }
        } catch (Exception e) {
            System.out.println("Error viewing enrolled teachers with courses.");
        }
    }

    // Assign a grade to a student for a particular course
    public void assignGrade(int studentId, String courseCode, double grade) {
        try {
            Map<String, Course> enrolledCourses = studentEnrollments.get(studentId);
            if (enrolledCourses == null || !enrolledCourses.containsKey(courseCode)) {
                System.out.println("Cannot assign grade. The student is not enrolled in course: " + courseCode);
                return;
            }
            Map<String, Double> courseGrades = studentGrades.getOrDefault(studentId, new HashMap<>());
            courseGrades.put(courseCode, grade);
            studentGrades.put(studentId, courseGrades);
            System.out.println("Grade successfully assigned.");
        } catch (Exception e) {
            System.out.println("Error assigning grade.");
            e.getMessage();
        }
    }

    // Display all student grades for each course they are enrolled in
    public void viewStudentGrades() {
        try {
            for (Map.Entry<Integer, Map<String, Double>> entry : studentGrades.entrySet()) {
                int studentId = entry.getKey();
                Student student = students.get(studentId);
                if (student == null) {
                    System.out.println("Student with ID " + studentId + " not found.");
                    continue;
                }
                Map<String, Double> enrolledStudentGrades = entry.getValue();
                if (enrolledStudentGrades == null || enrolledStudentGrades.isEmpty()) {
                    System.out.println("No grades available for this student.");
                } else {
                    System.out.println("Student ID: " + student.getId());
                    System.out.println("Student Name: " + student.getName());
                    for (Map.Entry<String, Double> gradeEntry : enrolledStudentGrades.entrySet()) {
                        String courseCode = gradeEntry.getKey();
                        Double grade = gradeEntry.getValue();
                        System.out.println("Course Code: " + courseCode);
                        System.out.println("Grade: " + grade);
                    }
                }
                System.out.println();
            }
        } catch (Exception e) {
            System.out.println("Error viewing student grades.");
            e.getMessage();
        }
    }

    // Display a list of all teachers with their corresponding IDs
    public void viewTeachersAndTheirClasses() {
        try {
            for (Teacher teacher : teachers.values()) {
                System.out.println("Teacher: " + teacher.getName() + " (ID: " + teacher.getId() + ")");
            }
        } catch (Exception e) {
            System.out.println("Error viewing teachers and their classes.");
        }
    }
}



    