package org.samuelgalvez.system.service;

import org.samuelgalvez.system.model.User;
import org.samuelgalvez.system.repository.UserRepository;

public class UserService {
    private UserRepository userRepo = new UserRepository();


    public UserStatus createUser(String user, String name, String lastName, String email, String password) {
        try {
            if (findUserByEmail(email) != null) {
                return UserStatus.USER_EXISTS;
            }

            User newUser = new User(name, lastName, email, user, password);
            userRepo.create(newUser);
            return UserStatus.USER_CREATED;
        } catch (Exception e) {
            return UserStatus.ERROR_USER_CREATE;
        }
    }

    public User findUserByEmail(String email) {
        return userRepo.findByEmail(email);
    }

}
