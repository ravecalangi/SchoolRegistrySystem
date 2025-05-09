package com.mycompany.delete;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class Teacher extends User{
    Map<LocalDateTime, Boolean> attendance = new HashMap<>();
    
    private int id;
    private String name;
    private int age;
    private double salary;
    
    public Teacher(int id, String name, int age){
        super (id, name, age);
        this.salary = 30000;
    }
    
    public void markAttendance(boolean isPresent){
        LocalDateTime today = LocalDateTime.now();
        attendance.put(today, isPresent);
    }
    
    public Map<LocalDateTime, Boolean> getAttendance() {
        return attendance;
    }

    
    public double getSalary(){
        return salary;
    }
    
    public void setSalary(double salary) {
        this.salary = salary;
    }

}
