package org.samuelgalvez.system.repository;
import org.samuelgalvez.system.model.User;
import java.sql.CallableStatement;
import org.samuelgalvez.system.config.ConexionDB;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRepository implements UserInterface {

//Atributos
    private CallableStatement callSP;
    private ConexionDB conexionDB = ConexionDB.getInstanciaConexionDB();

    @Override
    public void create(User user) {
        try {
            callSP = conexionDB.getConnection().prepareCall("{call sp_crear_users(?, ?, ?, ?, ?)}");
            callSP.setString(1, user.getName());
            callSP.setString(2, user.getLastname());
            callSP.setString(3, user.getEmail());
            callSP.setString(4, user.getUser());
            callSP.setString(5, user.getPassword());

            callSP.execute(); //Antes faltaba ejecutar el statement, por eso nunca se guardaba el usuario

        } catch (SQLException sqlException) {
            System.out.println("Error al crear el usuario: " + sqlException.getMessage());
        } catch (Exception e) {
        }
    }

    @Override
    public User findByEmail(String email) {
        //Ajusta el nombre de la tabla y de las columnas a los de tu Base de Datos real
        String sql = "SELECT name, lastname, email, user, password, idUser FROM users WHERE email = ?";

        try (PreparedStatement statement = conexionDB.getConnection().prepareStatement(sql)) {
            statement.setString(1, email);

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
            System.out.println("Error al buscar el usuario por email: " + sqlException.getMessage());
        }

        return null;
    }
}
