package com.example.monogenicdiseases;

public class User {
    String Fullname;
    String email;

    public User(){

    }

    public User(String Fullname, String email){
        this.Fullname=Fullname;
        this.email=email;
    }

    public String getFullname() {
        return Fullname;
    }

    public void setFullname(String fullname) {
        Fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
