package Drawing.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Scene2Controller {

    @FXML
    Label username;
    @FXML
    private Button logoutButton;
    @FXML
    private AnchorPane scene2;

    Stage stage;


    public void displayName(String username) {
        this.username.setText("Hello: " + username);
    }

    public void logout() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Logout");
        alert.setHeaderText("You are about to logout!");
        alert.setContentText("Do you want to save before exiting?");

        if(alert.showAndWait().get() == ButtonType.OK) {
            stage = (Stage) scene2.getScene().getWindow();
            System.out.println("You successfully logged out!");
            stage.close();
        }
    }
}
