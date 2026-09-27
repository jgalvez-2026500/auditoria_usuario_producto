package org.samuelgalvez.system.repository;

import org.samuelgalvez.system.model.User;

public interface AuthenticationInterface {

    User login(String email, String password);
}
