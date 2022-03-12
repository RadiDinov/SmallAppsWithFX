package Drawing.Controllers;

import Drawing.Controllers.Scene2Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Scene1Controller {
    @FXML
    TextField usernameField;
    @FXML
    TextField ageField;
    @FXML
    Button loginButton;
    @FXML
    Label helloMessage;
    @FXML
    Arc circle;



    int circleX;

    private Stage stage;
    private Scene scene;
    private Parent root;


    int age;

    public void goLeft() {
        circle.setCenterX(circleX -= 10);
    }

    public void goRight() {
        circle.setCenterX(circleX += 10);
    }

    public void login(ActionEvent event) throws IOException {
        try {
            age = Integer.parseInt(ageField.getText());
            String username = usernameField.getText();
            Pattern pattern = Pattern.compile("[A-Za-z]{2}");
            Matcher matcher = pattern.matcher(username);
            if(matcher.find()) {
                if(age >= 18) {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("../Scenes/scene2.fxml"));
                    root = loader.load();

                    Scene2Controller scene2Controller = loader.getController();

                    scene2Controller.displayName(username);

                    stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                    scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                } else {
                    helloMessage.setText("You must have 18 years old!");
                }
            } else {
                helloMessage.setText("Username must contain minimum 2 symbols!");
            }

        }catch(NumberFormatException e) {
            helloMessage.setText("Please enter only numbers!");
        }

    }
}
