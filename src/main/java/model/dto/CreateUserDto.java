package model.dto;

public class CreateUserDto {
    private String username;
    private String email;
    private String salt;
    private String passwordHash;
    private boolean isAdmin;

    public CreateUserDto(String username, String email, String salt, String passwordHash) {
        this.username = username;
        this.email = email;
        this.salt = salt;
        this.passwordHash = passwordHash;
        this.isAdmin=isAdmin;
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


    public boolean get_admin_status(){
        return this.isAdmin;
    }

}
