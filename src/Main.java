import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        File curDir = new File("src/Assets");
        getAllFiles(curDir);


        primaryStage.setTitle("Apps with JavaFX");
        Parent root = FXMLLoader.load(getClass().getResource("FXML/main.fxml"));
        Scene scene = new Scene(root, 600, 455);

        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.centerOnScreen();
        scene.getRoot().requestFocus();
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

    private static void getAllFiles(File curDir) {

        File[] filesList = curDir.listFiles();
        for (File f : filesList) {
            if (f.isDirectory())
                System.out.println(f.getName());
            if (f.isFile()) {
                System.out.println(f.getName());
            }
        }
    }
}
