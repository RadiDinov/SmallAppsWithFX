package Controllers.Calculator;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CalculatorController {
    @FXML
    private Label labelResult;

    BigDecimal finalResult = new BigDecimal(String.format("%.2f", 0.0));
    private boolean alreadyAddedComma;
    private boolean lastSymbolIsSpecial = false;

    public void addOne() {
        lastSymbolIsSpecial = false;
        labelResult.setText(labelResult.getText() + "1");
    }

    public void addTwo() {
        lastSymbolIsSpecial = false;
        labelResult.setText(labelResult.getText() + "2");
    }

    public void addThree() {
        lastSymbolIsSpecial = false;
        labelResult.setText(labelResult.getText() + "3");
    }

    public void addFour() {
        lastSymbolIsSpecial = false;
        labelResult.setText(labelResult.getText() + "4");
    }

    public void addFive() {
        lastSymbolIsSpecial = false;
        labelResult.setText(labelResult.getText() + "5");
    }

    public void addSix() {
        lastSymbolIsSpecial = false;
        labelResult.setText(labelResult.getText() + "6");
    }

    public void addSeven() {
        lastSymbolIsSpecial = false;
        labelResult.setText(labelResult.getText() + "7");
    }

    public void addEight() {
        lastSymbolIsSpecial = false;
        labelResult.setText(labelResult.getText() + "8");
    }

    public void addNine() {
        lastSymbolIsSpecial = false;
        labelResult.setText(labelResult.getText() + "9");
    }

    public void addZero() {
        if(labelResult.getText().length() == 0) {
            labelResult.setText("0.");
            alreadyAddedComma = true;
        } else {
            lastSymbolIsSpecial = false;
            labelResult.setText(labelResult.getText() + "0");
        }
    }

    public void addComma() {
        if (!alreadyAddedComma && !lastSymbolIsSpecial) {
            labelResult.setText(labelResult.getText() + ".");
            alreadyAddedComma = true;
        }
        if(lastSymbolIsSpecial && !alreadyAddedComma) {
            lastSymbolIsSpecial = false;
        }
    }

    public void addMultiplySymbol() {
        lastSymbolIsSpecial = true;
        alreadyAddedComma = false;
        labelResult.setText(labelResult.getText() + "*");
    }

    public void addDivisionSymbol() {
        lastSymbolIsSpecial = true;
        alreadyAddedComma = false;
        labelResult.setText(labelResult.getText() + "/");
    }

    public void addSubtractionSymbol() {
        lastSymbolIsSpecial = true;
        alreadyAddedComma = false;
        labelResult.setText(labelResult.getText() + "-");
    }

    public void clearLabelText() {
        alreadyAddedComma = false;
        labelResult.setText("");
    }

    public void addSumSymbol() {
        lastSymbolIsSpecial = true;
        alreadyAddedComma = false;
        labelResult.setText(labelResult.getText() + "+");
    }

    public void removeOneChar() {
        String currentLabelResult = labelResult.getText();
        if(currentLabelResult.length() >= 1) {
            if(currentLabelResult.charAt(currentLabelResult.length() - 1) == '.') {
                alreadyAddedComma = false;
            }
            currentLabelResult = currentLabelResult.replaceFirst(".$","");
            labelResult.setText(currentLabelResult);
        }

    }




    public void printResult() {
        String result = labelResult.getText();
        if(result.contains(".")) {
            alreadyAddedComma = true;
        }



        if (result.contains("+")) {
            List<String> numbers = Stream.of(result.split("[+]")).collect(Collectors.toList());
            for (String number : numbers) {
                if (number.equals(".")) {
                    continue;
                }
                finalResult = new BigDecimal(String.format("%.2f", finalResult.doubleValue() + Double.parseDouble(number)));
                alreadyAddedComma = true;
            }
            labelResult.setText(String.valueOf(finalResult));
            finalResult = new BigDecimal(String.format("%.2f", 0.0));
            alreadyAddedComma = true;
        }




        if (result.contains("/")) {
            List<String> numbers = Stream.of(result.split("[/]")).collect(Collectors.toList());
            if(numbers.get(1).equals("0")) {
                labelResult.setText("ERR"); //TODO: rework!

            } else {
                finalResult = new BigDecimal(String.format("%.2f", Double.parseDouble(numbers.get(0)) / Double.parseDouble(numbers.get(1))));
                labelResult.setText(String.valueOf(finalResult.doubleValue()));
                finalResult = new BigDecimal(String.format("%.2f", 0.0));
                alreadyAddedComma = true;
            }
        }





        if (result.contains("*")) {
            List<String> numbers = Stream.of(result.split("[*]")).collect(Collectors.toList());
            boolean escapedMultiplyByZero = false;
            for (String number : numbers) {
                if (number.equals(".")) {
                    continue;
                }
                if(!escapedMultiplyByZero) {
                    finalResult = new BigDecimal(String.format("%.2f", 1 * Double.parseDouble(number)));
                    escapedMultiplyByZero = true;
                } else {
                    finalResult = new BigDecimal(String.format("%.2f", finalResult.doubleValue() * Double.parseDouble(number)));
                }
            }
            labelResult.setText(String.valueOf(finalResult.doubleValue()));
            finalResult = new BigDecimal(String.format("%.2f", 0.0));
            alreadyAddedComma = true;
        }






        if (result.contains("-")) {
            List<String> numbers = Stream.of(result.split("[-]")).collect(Collectors.toList());
            boolean escapedSubtractionWithZero = false;
            for (int i = 0; i < numbers.size(); i++) {
                if (numbers.get(i).equals(".")) {
                    continue;
                }
                if(!escapedSubtractionWithZero) {
                    finalResult = new BigDecimal(String.format("%.2f", Double.parseDouble(numbers.get(i)) - Double.parseDouble(numbers.get(i+1))));
                    escapedSubtractionWithZero = true;
                    i++;
                } else {
                    finalResult = new BigDecimal(String.format("%.2f", finalResult.doubleValue() - Double.parseDouble(numbers.get(i))));
                }

            }
            labelResult.setText(String.valueOf(finalResult));
            finalResult = new BigDecimal(String.format("%.2f", 0.0));
            alreadyAddedComma = true;
        }


    }

}
