package org.samuelgalvez.system.controller;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import org.samuelgalvez.system.service.AuthenticationService;
import org.samuelgalvez.system.service.AuthenticationStatus;
import org.samuelgalvez.system.utils.AlertInformation;
import org.samuelgalvez.system.utils.Validations;
import org.samuelgalvez.system.utils.ViewFactory;

public class LoginController implements Initializable {

//Atributos
    private AuthenticationService authService = new AuthenticationService();
    private Validations validate = new Validations();
    private AlertInformation alertInfo = new AlertInformation();

    @FXML private TextField txtUserOrEmail;
    @FXML private PasswordField pwdPassword;

//Metodos
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }


    @FXML
    public void onRegister(MouseEvent event) {
        ViewFactory viewFacto = new ViewFactory();
        viewFacto.viewRegister();
    }

    @FXML
    public void onLogin(MouseEvent event) {
        String email = txtUserOrEmail.getText().trim();
        String password = pwdPassword.getText().trim();

        if (validate.validateTextEmpty(email) == true || validate.validateTextEmpty(password) == true) {
            alertInfo.viewAlert("Error de Campo", "Campos Vacios", "Ingresa tu Usuario o Correo y tu Contraseña", "Error");
            return;
        }

        AuthenticationStatus status = authService.login(email, password);
        ViewFactory viewFacto = new ViewFactory();

        switch (status) {
            case NOT_EXIST_USER ->
                alertInfo.viewAlert("Cuenta no Encontrada", "El Usuario no Existe", "No existe una cuenta con ese Usuario o Correo, debes registrarte", "Error");
            case INVALID_PASSWORD ->
                alertInfo.viewAlert("Contraseña Incorrecta", "Credenciales Invalidas", "La contraseña ingresada es incorrecta", "Error");
            case LOGIN_SUCCESS ->
                viewFacto.viewDashboard();
        }
    }

}
