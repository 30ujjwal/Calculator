import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import javax.swing.*;
import javax.swing.border.LineBorder;

public class Calculator {
    int boardWidth = 360;
    int boardHeight = 540;

    Color customLightWrath = new Color(127,13,9);
    Color customMorgan = new Color(179,123,78);
    Color customCarnelianBeige = new Color(229,206,180);
    Color customBlackGrain = new Color(42,45,42);

    String[] buttonValues = {
            "AC","+/-","%","÷",
            "7","8","9","×",
            "4","5","6","-",
            "1","2","3","+",
            ".","0","√","="
    };
    String[] rightSymbols = {"÷","×","-","+","="};
    String[] topSymbols = {"AC","+/-","%"};

    JFrame frame = new JFrame("Calculator");
    JLabel displayLabel = new JLabel();
    JPanel displayPanel = new JPanel();
    JPanel buttonsPanel = new JPanel();

//A+B, A-B, A*B, A/B
    String A = "0";
    String operator = null;
    String B = null;

    Calculator() {
        // frame.setVisible(true);
        frame.setSize(boardWidth, boardHeight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        displayLabel.setBackground(customBlackGrain);
        displayLabel.setForeground(Color.white);
        displayLabel.setFont(new Font("Arial", Font.PLAIN, 80));
        displayLabel.setHorizontalAlignment(JLabel.RIGHT);
        displayLabel.setText("0");
        displayLabel.setOpaque(true);

        displayPanel.setLayout(new BorderLayout());
        displayPanel.add(displayLabel);
        frame.add(displayPanel, BorderLayout.NORTH);
        buttonsPanel.setLayout(new GridLayout(5,4));
        buttonsPanel.setBackground(customBlackGrain);
        frame.add(buttonsPanel);

        for(int i = 0; i < buttonValues.length; i++) {
            JButton button = new JButton();
            String buttonValue = buttonValues[i];
            button.setFont(new Font("Arial", Font.BOLD, 30));
            button.setText(buttonValue);
            button.setFocusable(false);
            button.setBorder(new LineBorder(customBlackGrain));
            if (Arrays.asList(topSymbols).contains(buttonValue)) {
            button.setBackground(customCarnelianBeige);
            button.setForeground(customMorgan);
        }
        else if (Arrays.asList(rightSymbols).contains(buttonValue)) {
            button.setBackground(customLightWrath);
            button.setForeground(customCarnelianBeige);
            }
        else {
            button.setBackground(customMorgan);
            button.setForeground(customBlackGrain);
            }

        buttonsPanel.add(button);

            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String buttonValue = ((JButton) e.getSource()).getText();

                    if ("AC".equals(buttonValue)) {
                        clearAll();
                        displayLabel.setText("0");
                    } else if ("+/-".equals(buttonValue)) {
                        double num = Double.parseDouble(displayLabel.getText());
                        displayLabel.setText(removeZeroDecimal(num * -1));
                    } else if ("%".equals(buttonValue)) {
                        double num = Double.parseDouble(displayLabel.getText());
                        displayLabel.setText(removeZeroDecimal(num / 100));
                    } else if ("=".equals(buttonValue)) {
                        if (operator != null) {
                            B = displayLabel.getText();
                            double numA = Double.parseDouble(A);
                            double numB = Double.parseDouble(B);
                            double result = 0;
                            if ("+".equals(operator)) result = numA + numB;
                            else if ("-".equals(operator)) result = numA - numB;
                            else if ("×".equals(operator)) result = numA * numB;
                            else if ("÷".equals(operator)) result = numA / numB;
                            displayLabel.setText(removeZeroDecimal(result));
                            clearAll();
                        }
                    } else if ("+-×÷".contains(buttonValue)) {
                        A = displayLabel.getText();
                        operator = buttonValue;
                        displayLabel.setText("0");
                    } else if ("√".equals(buttonValue)) {
                        double num = Double.parseDouble(displayLabel.getText());
                        displayLabel.setText(removeZeroDecimal(Math.sqrt(num)));
                    } else if (".".equals(buttonValue)) {
                        if (!displayLabel.getText().contains(".")) {
                            displayLabel.setText(displayLabel.getText() + ".");
                        }
                    } else {
                        if ("0".equals(displayLabel.getText())) {
                            displayLabel.setText(buttonValue);
                        } else {
                            displayLabel.setText(displayLabel.getText() + buttonValue);
                        }
                    }
                }
            });

        frame.setVisible(true);
        }
        }
    void clearAll() {
        A = "0";
        operator = null;
        B = null;
    }
    String removeZeroDecimal(double numDisplay){
        if(numDisplay % 1 == 0) {
            return Integer.toString((int) numDisplay);
        }
        return Double.toString(numDisplay);
    }

}

