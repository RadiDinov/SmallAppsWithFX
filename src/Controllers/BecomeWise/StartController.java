package Controllers.BecomeWise;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class StartController {

    //GUI
    private Stage stage;
    private Scene scene;
    private Parent root;
    //GUI

    public void startGame(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/BecomeWise/gameStage.fxml"));
        this.root = loader.load();
        this.stage = (Stage) (((Node) event.getSource()).getScene().getWindow());
        this.scene = new Scene(root, 1391, 768);
        //Add .css file
        scene.getStylesheets().add("/CSS/BecomeWise/game.css");
        //Add .css file
        this.stage.setScene(scene);
        this.stage.centerOnScreen();
        scene.getRoot().requestFocus();
        stage.show();
    }

    public void openSettings(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/BecomeWise/settingsStage.fxml"));
        this.root = loader.load();
        this.stage = (Stage) (((Node) event.getSource()).getScene().getWindow());
        this.scene = new Scene(root, 600, 600);
        this.stage.setScene(scene);
        this.stage.centerOnScreen();
        scene.getRoot().requestFocus();
        stage.show();
    }

}
