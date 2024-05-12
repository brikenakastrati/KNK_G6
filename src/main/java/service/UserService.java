package service;

import model.User;
import model.dto.ChangeUserPasswordDto;
import model.dto.CreateUserDto;
import model.dto.LoginUserDto;
import model.dto.UserDto;
import Repository.UserRepository;

public class UserService {
    public static boolean signUp(UserDto userData){
        String password = userData.getPassword();
        String confirmPassword = userData.getConfirmPassword();

        if(!password.equals(confirmPassword)){
            return false;
        }

        String salt = PasswordHasher.generateSalt();
        String passwordHash = PasswordHasher.generateSaltedHash(
                password, salt
        );

        CreateUserDto createUserData = new CreateUserDto(
                userData.getUsername(),
                userData.getEmail(),
                salt,
                passwordHash

        );

        return UserRepository.create(createUserData);
    }

    public static boolean createclientbutton(UserDto UserCreateClientData){
        String password = UserCreateClientData.getPassword();
        String confirmPassword = UserCreateClientData.getConfirmPassword();

        if(!password.equals(confirmPassword)){
            return false;
        }

        String salt = PasswordHasher.generateSalt();
        String passwordHash = PasswordHasher.generateSaltedHash(
                password, salt
        );

        CreateUserDto createUserData = new CreateUserDto(
                UserCreateClientData.getUsername(),
                UserCreateClientData.getEmail(),
                salt,
                passwordHash

        );

        return UserRepository.create(createUserData);
    }


        public static LoginResult login(LoginUserDto loginData) {
            User user = UserRepository.getByUsername(loginData.getUsername());
            if (user == null) {
                return new LoginResult(false, false);
            }

            String password = loginData.getPassword();
            String salt = user.getSalt();
            String passwordHash = user.getPasswordHash();

            boolean passwordMatches = PasswordHasher.compareSaltedHash(password, salt, passwordHash);
            return new LoginResult(passwordMatches, passwordMatches && user.isAdmin());

    }


}

