package edu.ufg.aulas.controladores;

import edu.ufg.aulas.dao.UsuarioDAO;
import edu.ufg.aulas.entidades.Usuario;
import edu.ufg.aulas.util.HashUtil;
import edu.ufg.aulas.util.Navegador;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.util.Optional;

public class LoginController {

    @FXML private TextField txtCorreo;
    @FXML private PasswordField txtClave;
    @FXML private Label lblError;

    private final UsuarioDAO usuarioDAO = new UsuarioDAO();

    @FXML
    private void ingresar() {
        lblError.setText("");
        String correo = txtCorreo.getText().trim();
        String clave = txtClave.getText();

        if (correo.isEmpty() || clave.isEmpty()) {
            lblError.setText("Ingrese correo y clave");
            return;
        }

        try {
            Optional<Usuario> usuario = usuarioDAO.buscarPorEmail(correo);
            if (usuario.isPresent() && usuario.get().getContrasena().equals(HashUtil.sha256(clave))) {
                Navegador.cambiar(txtCorreo, "/vistas/Home.fxml", "Registro de aulas");
            } else {
                // mensaje general para no dar pistas
                lblError.setText("Correo o clave incorrectos");
                txtClave.clear();
            }
        } catch (Exception e) {
            lblError.setText("No se pudo completar el inicio de sesion " + e.getMessage());
        }
    }
}
