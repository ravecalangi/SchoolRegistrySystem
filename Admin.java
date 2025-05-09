package com.mycompany.delete;

public class Admin extends User {

    private int id;
    private String name;
    private int age;
    private String position;

    public Admin(int id, String name, int age, String position) {
        super(id, name, age);
        this.position = position;
    }
    
    public int getId(){
        return id;
    }
    
    public String getName(){
        return name;
    }
    
    public int getAge(){
        return age;
    }
    
    public String getPosition(){
        return position;
    }

}

