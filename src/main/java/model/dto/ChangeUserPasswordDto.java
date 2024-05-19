package model.dto;

import javafx.event.ActionEvent;
import service.UserService;

public class ChangeUserPasswordDto {
    private int id;
    private String currentPassword;
    private String newPassword;
    private String confirmNewPassword;

    public ChangeUserPasswordDto(int id, String currentPassword, String newPassword, String confirmNewPassword) {
        this.id = id;
        this.currentPassword = currentPassword;
        this.newPassword = newPassword;
        this.confirmNewPassword = confirmNewPassword;
    }

    public int getId() {

        return id;
    }

    public String getCurrentPassword() {
        return currentPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public String getConfirmNewPassword() {
        return confirmNewPassword;
    }

    public void saveChanges(ActionEvent actionEvent) {
        // Merrni vlerat nga ChangeUserPasswordDto
        int userId = getId();
        String currentPassword = getCurrentPassword();
        String newPassword = getNewPassword();
        String confirmNewPassword = getConfirmNewPassword();

        // Kontrolloni validitetin e fjalëkalimeve
        if (!newPassword.equals(confirmNewPassword)) {
            // Nëse fjalëkalimet e reja nuk përputhen, shfaqni një mesazh ose veproni sipas kushteve tuaja
            System.out.println("Fjalëkalimi i ri nuk përputhet me konfirmimin.");
            return;
        }

        // Thirrni metodën për ndryshimin e fjalëkalimit në UserService
        UserService userService = new UserService();
        boolean changed = userService.changePassword(currentPassword, newPassword, userId);
        if (changed) {
            // Ndryshimi i fjalëkalimit është kryer me sukses
            System.out.println("Fjalëkalimi u ndryshua me sukses.");
        } else {
            // Ndryshimi i fjalëkalimit dështoi, shfaqni një mesazh ose veproni sipas kushteve tuaja
            System.out.println("Ndryshimi i fjalëkalimit dështoi.");
        }
    }

}
