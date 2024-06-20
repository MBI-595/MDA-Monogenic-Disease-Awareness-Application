package com.example.monogenicdiseases;

public class ReadWriteUserDetails {
    public String Name;
    public String email;

    public ReadWriteUserDetails(){

    }

    public ReadWriteUserDetails(String Name, String email) {
        this.Name = Name;
        this.email=email;
    }

    public String getName() {
        return Name;
    }

    public void setDisplayName(String Name) {
        Name = Name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
