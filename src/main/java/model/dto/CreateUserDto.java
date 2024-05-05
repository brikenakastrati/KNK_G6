package model.dto;

public class CreateUserDto {
    private String firstName;
    private String lastName;
    private String email;
    private String salt;
    private String passwordHash;
    private boolean isAdmin;

    public CreateUserDto(String firstName, String lastName, String email, String salt, String passwordHash) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.salt = salt;
        this.passwordHash = passwordHash;
        this.isAdmin=isAdmin;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
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
    public void set_admin_status(boolean isAdmin){
        this.isAdmin = isAdmin;
    }
}
