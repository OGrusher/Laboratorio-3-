package edu.ufg.aulas;

import edu.ufg.aulas.dao.DatosIniciales;
import edu.ufg.aulas.util.HibernateUtil;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        try {
            // usa la prueba de conexion que hizo Jorge
            if (!HibernateUtil.probarConexion()) {
                throw new IllegalStateException("sin conexion");
            }
            DatosIniciales.sembrar();
        } catch (Throwable e) {
            Alert alerta = new Alert(Alert.AlertType.ERROR,
                    "No se pudo conectar con la base de datos\nRevise la configuracion de hibernate y que la base de datos este activa\n\n"
                            + e.getMessage());
            alerta.setHeaderText(null);
            alerta.showAndWait();
            Platform.exit();
            return;
        }
        Parent raiz = FXMLLoader.load(getClass().getResource("/vistas/Login.fxml"));
        stage.setTitle("Inicio de sesion");
        stage.setScene(new Scene(raiz));
        stage.show();
    }

    @Override
    public void stop() {
        try {
            HibernateUtil.cerrar();
        } catch (Throwable ignorado) {
            // puede que no se haya creado si no hay base de datos
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
