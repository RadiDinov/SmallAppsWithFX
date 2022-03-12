package Controllers.BecomeWise;

import JDBC.JDBC;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.sql.SQLException;
import java.util.ArrayList;

public class GameController {

    @FXML
    Label labelQuestion;
    @FXML
    Label labelA;
    @FXML
    Label labelB;
    @FXML
    Label labelC;
    @FXML
    Label labelD;
    @FXML
    Label pointerOne;
    @FXML
    Label pointerTwo;
    @FXML
    Label pointerThree;
    @FXML
    Label pointerFour;
    @FXML
    Label pointerFive;
    @FXML
    Label pointerSix;
    @FXML
    Label pointerSeven;
    @FXML
    Label pointerEight;
    @FXML
    Label pointerNine;
    @FXML
    Label pointerZero;
    @FXML
    Label labelCounter;
    @FXML
    Label labelUsedFirstHint;
    @FXML
    Label labelUsedSecondHint;
    @FXML
    Label labelUsedThirdHint;

    private final ArrayList<Integer> askedQuestionsIDS = new ArrayList<>();
    JDBC auto;
    private String correctAnswer;
    private int gameProgress = 0;
    private boolean endGame = false;
    private boolean firstHint = false;
    private boolean secondHint = false;
    private boolean thirdHint = false;

    //➤
    public void initialize() throws SQLException {
        if (!endGame) {
            JDBC jdbc = new JDBC("select * from questions", "INSERT INTO questions (question, A, B, C, D, answer) VALUES (?, ?, ?, ?, ?, ?)");
            jdbc.resultSet.last();

            if (gameProgress == 0) {
                pointerZero.setText("➤");
            } else if (gameProgress == 1) {
                pointerZero.setText("");
                pointerOne.setText("➤");
            } else if (gameProgress == 2) {
                pointerOne.setText("");
                pointerTwo.setText("➤");
            } else if (gameProgress == 3) {
                pointerTwo.setText("");
                pointerThree.setText("➤");
            } else if (gameProgress == 4) {
                pointerThree.setText("");
                pointerFour.setText("➤");
            } else if (gameProgress == 5) {
                pointerFour.setText("");
                pointerFive.setText("➤");
            } else if (gameProgress == 6) {
                pointerFive.setText("");
                pointerSix.setText("➤");
            } else if (gameProgress == 7) {
                pointerSix.setText("");
                pointerSeven.setText("➤");
            } else if (gameProgress == 8) {
                pointerSeven.setText("");
                pointerEight.setText("➤");
            } else if (gameProgress == 9) {
                pointerEight.setText("");
                pointerNine.setText("➤");
            }

            //Getting idCount from database
            int jdbcIdCount = Integer.parseInt(jdbc.resultSet.getString("id")) + 1;
            //Getting idCount from database

            //Creating new random number
            int randomQuestionID = getRandomNumber(jdbcIdCount);
            //Creating new random number

            //Setting the gameProgress label
//        labelCounter.setText(String.valueOf(gameProgress));
            //Setting the gameProgress label

            //Creating new JDBC object with data from random the random number
            auto = new JDBC("select * from questions WHERE id = " + randomQuestionID, "INSERT INTO questions (question, A, B, C, D, answer) VALUES (?, ?, ?, ?, ?, ?)");
            //Creating new JDBC object with data from random the random number

            //If this is the first asked question
            if (askedQuestionsIDS.size() == 0) {
                if (auto.resultSet.next()) {

                    //Getting data from database and writing to labels
                    labelQuestion.setText(auto.resultSet.getString("question"));
                    labelA.setText(auto.resultSet.getString("A"));
                    labelB.setText(auto.resultSet.getString("B"));
                    labelC.setText(auto.resultSet.getString("C"));
                    labelD.setText(auto.resultSet.getString("D"));
                    correctAnswer = auto.resultSet.getString("answer");
                    //Getting data from database and writing to labels

                    //Adding the current question's ID to askedQuestions
                    askedQuestionsIDS.add(Integer.valueOf(auto.resultSet.getString("id")));
                    //Adding the current question's ID to askedQuestions
                }

            }
            //If this is the first asked question

            //If the question has already been asked
            else {
                boolean matchingIDs = false;
                while (true) {
                    auto = new JDBC("select * from questions WHERE id = " + randomQuestionID, "INSERT INTO questions (question, A, B, C, D, answer) VALUES (?, ?, ?, ?, ?, ?)");
                    for (Integer askedQuestionsID : askedQuestionsIDS) {
                        if (askedQuestionsID == randomQuestionID) {
                            matchingIDs = true;
                            break;
                        }
                    }

                    if (!matchingIDs) {
                        if (auto.resultSet.next()) {
                            labelQuestion.setText(auto.resultSet.getString("question"));
                            labelA.setText(auto.resultSet.getString("A"));
                            labelB.setText(auto.resultSet.getString("B"));
                            labelC.setText(auto.resultSet.getString("C"));
                            labelD.setText(auto.resultSet.getString("D"));
                            correctAnswer = auto.resultSet.getString("answer");
                            askedQuestionsIDS.add(Integer.valueOf(auto.resultSet.getString("id")));
                            break;
                        }
                    } else {
                        randomQuestionID = getRandomNumber(jdbcIdCount);
                        matchingIDs = false;
                    }
                }


            }
            //If the question has already been asked

        } else {
            System.out.println("Game ended!");
        }
    }

    public void firstHint() throws SQLException {
        if(!firstHint) {
            int randomNumber = getRandomNumber(5);
            int answer = 0;
            switch (auto.resultSet.getString("answer")) {
                case "A": {
                    answer = 1;
                    break;
                }
                case "B": {
                    answer = 2;
                    break;
                }
                case "C": {
                    answer = 3;
                    break;
                }
                case "D": {
                    answer = 4;
                    break;
                }
            }
            System.out.println(answer);
            do {
                if (randomNumber == answer) {
                    randomNumber = getRandomNumber(5);
                }
            } while (randomNumber == answer);

            firstHint = true;
            labelUsedFirstHint.setText("❌");
            switch (randomNumber) {
                case 1: {
                    labelA.setText("");
                    break;
                }
                case 2: {
                    labelB.setText("");
                    break;
                }
                case 3: {
                    labelC.setText("");
                    break;
                }
                case 4: {
                    labelD.setText("");
                    break;
                }
            }

        }
    }

    public void secondHint() throws SQLException {
        if(!secondHint) {
            int randomNumber1 = getRandomNumber(5), randomNumber2 = getRandomNumber(5);
            int answer = 0;
            switch (auto.resultSet.getString("answer")) {
                case "A": {
                    answer = 1;
                    break;
                }
                case "B": {
                    answer = 2;
                    break;
                }
                case "C": {
                    answer = 3;
                    break;
                }
                case "D": {
                    answer = 4;
                    break;
                }
            }
            do {
                if (randomNumber1 == answer) {
                    randomNumber1 = getRandomNumber(5);
                }
                if (randomNumber2 == answer) {
                    randomNumber2 = getRandomNumber(5);
                }
                while(randomNumber1 == randomNumber2) {
                    randomNumber1 = getRandomNumber(5);
                    randomNumber2 = getRandomNumber(5);
                }
            } while (randomNumber1 == answer || randomNumber2 == answer);
            secondHint = true;
            labelUsedSecondHint.setText("❌");
            switch (randomNumber1) {
                case 1: {
                    labelA.setText("");
                    break;
                }
                case 2: {
                    labelB.setText("");
                    break;
                }
                case 3: {
                    labelC.setText("");
                    break;
                }
                case 4: {
                    labelD.setText("");
                    break;
                }
            }
            switch (randomNumber2) {
                case 1: {
                    labelA.setText("");
                    break;
                }
                case 2: {
                    labelB.setText("");
                    break;
                }
                case 3: {
                    labelC.setText("");
                    break;
                }
                case 4: {
                    labelD.setText("");
                    break;
                }
            }
        }
    }

    public void thirdHint() {
        if(!thirdHint) {
            thirdHint = true;
            labelUsedThirdHint.setText("❌");
        }
    }

    public void selectedA() throws SQLException {
        if (correctAnswer.equals("A")) {
            gameProgress++;
            initialize();
        } else {
            endGame = true;
            //TODO: end game
            getGradeAfterWrongAnswer();
            setTextsToGameEnded();
        }
    }

    public void selectedB() throws SQLException {
        if (correctAnswer.equals("B")) {
            gameProgress++;
            initialize();
        } else {
            endGame = true;
            //TODO: end game
            getGradeAfterWrongAnswer();
            setTextsToGameEnded();
        }
    }

    public void selectedC() throws SQLException {
        if (correctAnswer.equals("C")) {
            gameProgress++;
            initialize();
        } else {
            endGame = true;
            //TODO: end game
            getGradeAfterWrongAnswer();
            setTextsToGameEnded();
        }
    }

    public void selectedD() throws SQLException {
        if (correctAnswer.equals("D")) {
            gameProgress++;
            initialize();
        } else {
            endGame = true;
            //TODO: end game
            getGradeAfterWrongAnswer();
            setTextsToGameEnded();
        }
    }

    public void getGrade() {
        endGame = true;
        setTextsToGameEnded();
    }

    private int getRandomNumber(int max) {
        return (int) ((Math.random() * (max - 1)) + 1);
    }

    private void getGradeAfterWrongAnswer() {
        if(gameProgress < 3) {
            System.out.println("Оценка: 2.00");
        } else if(gameProgress < 7) {
            System.out.println("Оценка: 3.00");
        } else if(gameProgress <= 9) {
            System.out.println("Оценка: 5.00");
        }
    }

    private void setTextsToGameEnded() {
        labelA.setText("Играта приключи!");
        labelB.setText("Играта приключи!");
        labelC.setText("Играта приключи!");
        labelD.setText("Играта приключи!");
        labelQuestion.setText("Играта приключи!");
    }

}
