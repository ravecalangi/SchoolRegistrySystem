package com.mycompany.delete;

public abstract class User {
    int id;
    String name;
    int age;
    
    public User(int id, String name, int age){
        this.id = id;
        this.name = name;
        this.age = age;
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
    
}
