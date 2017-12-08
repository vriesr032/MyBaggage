package com.mybaggage;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    private static Stage stage;
    private static Scene scene;
    private static Database database;

    @Override
    public void start(Stage stage) throws Exception {
        //Utilities.setMySQLConnectionParameters("", "", "");
        
        Main.stage = stage;

        database = new Database();

        Parent root = FXMLLoader.load(getClass().getResource("/com/mybaggage/controllers/Inlogscherm.fxml"));

        scene = new Scene(root);
        scene.getStylesheets().add("/styles/Styles.css");

        stage.setTitle("My Baggage");
        stage.setScene(scene);
        stage.show();
    }

    public static Stage getStage() {
        return stage;
    }

    public static Scene getScene() {
        return scene;
    }

    public static Database getDatabase() {
        return database;
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
