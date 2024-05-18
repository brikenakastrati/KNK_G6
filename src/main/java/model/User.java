package model;

import java.util.Date;

public class User {
    private int id;
    private String username;
    private String email;
    private String salt;
    private String passwordHash;
    private boolean isAdmin;
    private Date dateJoined;  // New column

    public User(int id, String username, String email, String salt, String passwordHash, boolean isAdmin, Date dateJoined) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.salt = salt;
        this.passwordHash = passwordHash;
        this.isAdmin = isAdmin;
        this.dateJoined = dateJoined;
    }

    // Getters and Setters

    public Date getDateJoined() {
        return dateJoined;
    }

    public void setDateJoined(Date dateJoined) {
        this.dateJoined = dateJoined;
    }



    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }


    public String getEmail() {
        return email;
    }

    public String getSalt() {
        return salt;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public boolean isAdmin() {
        return isAdmin;
    }
}
