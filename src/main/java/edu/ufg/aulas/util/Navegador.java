package edu.ufg.aulas.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public final class Navegador {

    private Navegador() {
    }

    // cambia de pantalla
    public static void cambiar(Node origen, String fxml, String titulo) throws IOException {
        Parent raiz = FXMLLoader.load(Navegador.class.getResource(fxml));
        Stage stage = (Stage) origen.getScene().getWindow();
        stage.setScene(new Scene(raiz));
        stage.setTitle(titulo);
        stage.sizeToScene();
        stage.centerOnScreen();
    }
}
