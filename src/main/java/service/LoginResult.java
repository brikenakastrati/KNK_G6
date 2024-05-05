package service;

public class LoginResult {
    private boolean success;
    private boolean isAdmin;

    public LoginResult(boolean success, boolean isAdmin) {
        this.success = success;
        this.isAdmin = isAdmin;
    }

    public boolean isSuccess() {
        return success;
    }

    public boolean isAdmin() {
        return isAdmin;
    }
}