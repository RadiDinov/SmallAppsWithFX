package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class MainController {

    //GUI
    private Stage stage;
    private Scene scene;
    private Parent root;
    //GUI

    public void openCalculatorApp(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../FXML/calculator.fxml"));
        this.root = loader.load();
        this.stage = (Stage) (((Node) event.getSource()).getScene().getWindow());
        this.scene = new Scene(root, 423, 499);
        //Add .css file
        URL url = this.getClass().getResource("../CSS/calculator.css");
        String css = url.toExternalForm();
        scene.getStylesheets().add(css);
        //Add .css file
        this.stage.setScene(scene);
        this.stage.centerOnScreen();
        scene.getRoot().requestFocus();
        stage.show();
    }
}
