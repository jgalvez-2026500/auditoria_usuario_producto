package org.samuelgalvez.system.service;

import org.samuelgalvez.system.model.User;
import org.samuelgalvez.system.repository.AuthenticationRepository;

public class AuthenticationService {

//Atributos
    private AuthenticationRepository authRepo = new AuthenticationRepository();
    private UserService userService = new UserService();
    private User authenticatedUser;

//Metodos
    public AuthenticationStatus login(String email, String password) {
        //Antes de intentar loguear, se valida que exista un usuario con ese Email
        User existingUser = userService.findUserByEmail(email);

        if (existingUser == null) {
            return AuthenticationStatus.NOT_EXIST_USER;
        }

        //Si existe, se valida la contraseña contra el AuthenticationRepository
        User validatedUser = authRepo.login(email, password);

        if (validatedUser == null) {
            return AuthenticationStatus.INVALID_PASSWORD;
        }

        authenticatedUser = validatedUser;
        return AuthenticationStatus.LOGIN_SUCCESS;
    }

    public User getAuthenticatedUser() {
        return authenticatedUser;
    }
}
