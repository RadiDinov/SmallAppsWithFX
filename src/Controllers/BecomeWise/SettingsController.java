package Controllers.BecomeWise;

import JDBC.JDBC;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.w3c.dom.Text;

import java.io.IOException;
import java.sql.SQLException;

public class SettingsController {
    @FXML
    TextField textQuestion;
    @FXML
    TextField textA;
    @FXML
    TextField textB;
    @FXML
    TextField textC;
    @FXML
    TextField textD;
    @FXML
    TextField textAnswer;
    @FXML
    Label labelField;


    //GUI
    private Stage stage;
    private Scene scene;
    private Parent root;
    //GUI

    public void addNewQuestion() throws SQLException {
        JDBC jdbc = new JDBC("select * from questions", "INSERT INTO questions (question, A, B, C, D, answer) VALUES (?, ?, ?, ?, ?, ?)");
        if(!textQuestion.getText().equals("") && !textA.getText().equals("") && !textB.getText().equals("") && !textC.getText().equals("") && !textD.getText().equals("") && !textAnswer.getText().equals("")) {
            jdbc.writeData.setString(1, textQuestion.getText());
            jdbc.writeData.setString(2, textA.getText());
            jdbc.writeData.setString(3, textB.getText());
            jdbc.writeData.setString(4, textC.getText());
            jdbc.writeData.setString(5, textD.getText());
            jdbc.writeData.setString(6, textAnswer.getText().toUpperCase());
            jdbc.writeData.executeUpdate();
            jdbc.writeData.close();
            labelField.setText("Успешно добавен въпрос!");
        } else {
            labelField.setText("Попълнете всичко!");
        }
    }





    public void goBackToStartStage(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/BecomeWise/startStage.fxml"));
        this.root = loader.load();
        this.stage = (Stage) (((Node) event.getSource()).getScene().getWindow());
        this.scene = new Scene(root, 637, 478);
        //Add .css file
        scene.getStylesheets().add("/CSS/BecomeWise/start.css");
        //Add .css file
        this.stage.setScene(scene);
        this.stage.centerOnScreen();
        scene.getRoot().requestFocus();
        stage.show();
    }
}
