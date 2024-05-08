package model.dto;

public class LoginUserDto {
    private static String username;
    private String password;

    public LoginUserDto(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public static String getUsername() {

        return username;
    }

    public String getPassword() {
        return password;
    }
}
