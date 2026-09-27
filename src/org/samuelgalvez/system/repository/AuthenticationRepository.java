package org.samuelgalvez.system.repository;

import org.samuelgalvez.system.config.ConexionDB;
import org.samuelgalvez.system.model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthenticationRepository implements AuthenticationInterface {

//Atributos
    private ConexionDB conexionDB = ConexionDB.getInstanciaConexionDB();

//Metodos
    @Override
    public User login(String email, String password) {
        //Ajusta el nombre de la tabla y de las columnas a los de tu Base de Datos real
        String sql = "SELECT name, lastname, email, user, password, idUser FROM users WHERE email = ? AND password = ?";

        try (PreparedStatement statement = conexionDB.getConnection().prepareStatement(sql)) {
            statement.setString(1, email);
            statement.setString(2, password);

            try (ResultSet result = statement.executeQuery()) {
                if (result.next()) {
                    return new User(
                            result.getString("name"),
                            result.getString("lastname"),
                            result.getString("email"),
                            result.getString("user"),
                            result.getString("password"),
                            result.getString("idUser")
                    );
                }
            }
        } catch (SQLException sqlException) {
            System.out.println("Error al iniciar sesion: " + sqlException.getMessage());
        }

        return null;
    }
}
