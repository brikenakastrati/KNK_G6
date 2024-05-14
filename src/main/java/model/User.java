package model;

public class User {
    private int id;
    private String username;
    private String email;
    private String salt;
    private String passwordHash;

    private boolean isAdmin;  // New field

    public User(int id, String username, String email, String salt, String passwordHash,boolean isAdmin) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.salt = salt;
        this.passwordHash = passwordHash;
        this.isAdmin=isAdmin;
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
