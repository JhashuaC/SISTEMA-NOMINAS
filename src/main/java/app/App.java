package app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Clase principal de la aplicación JavaFX.
 *
 * <p>
 * Esta clase extiende {@link javafx.application.Application} y se encarga de
 * arrancar la aplicación, cargar la vista principal desde FXML y aplicar la
 * hoja de estilos correspondiente.
 * </p>
 *
 * <p>
 * Se asume que existe un recurso FXML llamado {@code primary.fxml} en el paquete
 * {@code app} y una hoja de estilos en {@code /app/app.css}.
 * </p>
 *
 * @author  y jhashua
 * @see javafx.application.Application
 */
public class App extends Application {

    private static Scene scene;
 /**
     * Inicia y muestra la ventana principal.
     *
     * <p>
     * Carga la vista principal mediante {@link #loadFXML(String)}, asigna la escena
     * al {@link Stage} proporcionado y aplica la hoja de estilos.
     * </p>
     *
     * @param stage Ventana principal provista por JavaFX.
     * @throws IOException Si ocurre un error al cargar el recurso FXML.
     */
    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("primary"));
        stage.setScene(scene);
        stage.show();
        scene.getStylesheets().add(getClass().getResource("/app/app.css").toExternalForm());

    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("primary.fxml"));
        return fxmlLoader.load();
    }
  /**
     * Punto de entrada de la aplicación.
     *
     * @param args Argumentos de línea de comandos.
     */
    public static void main(String[] args) {
        launch();
    }

}
