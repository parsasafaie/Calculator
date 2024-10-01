import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.Math;
import java.util.Arrays;

public class Main {
    public static void main(String[] args){
        Integer frame_width = 300;
        Integer frame_height = 550;
        Integer btn_size = 75;
        Integer large_btn_width = frame_width/2;
        Integer textfield_height = 100;

        JTextField textField = new JTextField("0");
        textField.disable();
        textField.setBounds(0, 0, frame_width, textfield_height);
        textField.setFont(new Font("Arial", Font.BOLD, 30));

        ActionListener actionListener = new ActionListener() {
            Float num1 = 0f, num2 = 0f, result = 0f;
            String operation = "+";
            Boolean is_result = false;
            @Override
            public void actionPerformed(ActionEvent e) {
                String actionCommand = e.getActionCommand();
                if (actionCommand.equals("+") | actionCommand.equals("-") | actionCommand.equals("×") | actionCommand.equals("÷") | actionCommand.equals("^") | actionCommand.equals("%")){
                    try {
                        String text = textField.getText();
                        if (text.contains(".")){
                            num1 = Float.parseFloat(text);
                        } else {
                            String float_shape = text + ".0";
                            num1 = Float.parseFloat(float_shape);
                        }
                        operation = actionCommand;
                        textField.setText("0");
                    } catch (RuntimeException ex) {
                        textField.setText("oops!");
                    }

                } else if (actionCommand.equals("1") | actionCommand.equals("2") | actionCommand.equals("3") | actionCommand.equals("4") | actionCommand.equals("5") | actionCommand.equals("6")
                        | actionCommand.equals("7") | actionCommand.equals("8") | actionCommand.equals("9") | actionCommand.equals("0")) {
                    String previous_text = textField.getText();
                    if (previous_text.equals("0") | is_result) {
                        String new_text = actionCommand;
                        textField.setText(new_text);
                    } else {
                        String new_text = previous_text + actionCommand;
                        textField.setText(new_text);
                    }
                    is_result = false;
                } else if (actionCommand.equals(".")) {
                    String previous_text = textField.getText();
                    if (previous_text.contains(".")){} else{
                        textField.setText(previous_text+".");
                    }
                } else if (actionCommand.equals("±")) {
                    String text = textField.getText();
                    if (text.contains("-")){
                        String new_text = text.replace("-", "");
                        textField.setText(new_text);
                    } else if (text.equals("0")) {
                        textField.setText(text);
                    } else {
                        String new_text = "-" + text;
                        textField.setText(new_text);
                    }
                } else if (actionCommand.equals("=")) {
                    String text = textField.getText();
                    if (text.contains(".")){
                        num2 = Float.parseFloat(text);
                    } else {
                        String float_shape = text + ".0";
                        num2 = Float.parseFloat(float_shape);
                    }
                    switch (operation) {
                        case "+": {
                            result = num1 + num2;
                            break;
                        }
                        case "-": {
                            result = num1 - num2;
                            break;
                        }
                        case "×": {
                            result = num1 * num2;
                            break;
                        }
                        case "÷": {
                            result = num1 / num2;
                            break;
                        }
                        case "^": {
                            result = (float) Math.pow(num1, num2);
                            break;
                        }
                        case "%": {
                            result = num1 % num2;
                            break;
                        }
                    }
                    String text_result = result.toString();
                    if (text_result.equals("Infinity")){
                        textField.setText(text_result);
                    } else {
                        String[] result_parts = text_result.split("\\.");

                        String float_part = result_parts[1];
                        if (float_part.equals("0")){
                            textField.setText(result_parts[0]);
                        } else {
                            textField.setText(text_result);
                        }
                    }
                    is_result = true;
                } else if (actionCommand.equals("C")){
                    String previous_text = textField.getText();
                    if (previous_text.length() == 1){
                        textField.setText("0");
                    } else {
                        String new_text = previous_text.substring(0,previous_text.length()-1);
                        textField.setText(new_text);
                    }
                } else if (actionCommand.equals("CA")){
                    textField.setText("0");
                } else if (actionCommand.equals("√")) {
                    result = (float) Math.sqrt(Float.parseFloat(textField.getText()));
                    String text_result = result.toString();
                    System.out.println(text_result);
                    String[] result_parts = text_result.split("\\.");
                    System.out.println("is this: "+Arrays.toString(result_parts));
                    String float_part = result_parts[1];
                    if (float_part.equals("0")){
                        textField.setText(result_parts[0]);
                    } else {
                        textField.setText(text_result);
                    }
                    is_result = true;
                }
            }
        };

        JFrame window = new JFrame("Calculator");

        JButton abs_btn = new JButton("±");
        abs_btn.setBounds(0, frame_height-btn_size, btn_size, btn_size);
        abs_btn.addActionListener(actionListener);

        JButton zero0_btn = new JButton("0");
        zero0_btn.setBounds(btn_size, frame_height-btn_size, btn_size, btn_size);
        zero0_btn.addActionListener(actionListener);

        JButton dot_btn = new JButton(".");
        dot_btn.setBounds(2*btn_size, frame_height-btn_size, btn_size, btn_size);
        dot_btn.addActionListener(actionListener);

        JButton equal_btn = new JButton("=");
        equal_btn.setBounds(3*btn_size, frame_height-btn_size, btn_size, btn_size);
        equal_btn.addActionListener(actionListener);

        JButton one1_btn = new JButton("1");
        one1_btn.setBounds(0, frame_height-2*btn_size, btn_size, btn_size);
        one1_btn.addActionListener(actionListener);

        JButton two2_btn = new JButton("2");
        two2_btn.setBounds(btn_size, frame_height-2*btn_size, btn_size, btn_size);
        two2_btn.addActionListener(actionListener);

        JButton tree3_btn = new JButton("3");
        tree3_btn.setBounds(2*btn_size, frame_height-2*btn_size, btn_size, btn_size);
        tree3_btn.addActionListener(actionListener);

        JButton plus_btn = new JButton("+");
        plus_btn.setBounds(3*btn_size, frame_height-2*btn_size, btn_size, btn_size);
        plus_btn.addActionListener(actionListener);

        JButton four4_btn = new JButton("4");
        four4_btn.setBounds(0, frame_height-3*btn_size, btn_size, btn_size);
        four4_btn.addActionListener(actionListener);

        JButton five5_btn = new JButton("5");
        five5_btn.setBounds(btn_size, frame_height-3*btn_size, btn_size, btn_size);
        five5_btn.addActionListener(actionListener);

        JButton six6_btn = new JButton("6");
        six6_btn.setBounds(2*btn_size, frame_height-3*btn_size, btn_size, btn_size);
        six6_btn.addActionListener(actionListener);

        JButton minus_btn = new JButton("-");
        minus_btn.setBounds(3*btn_size, frame_height-3*btn_size, btn_size, btn_size);
        minus_btn.addActionListener(actionListener);

        JButton seven7_btn = new JButton("7");
        seven7_btn.setBounds(0, frame_height-4*btn_size, btn_size, btn_size);
        seven7_btn.addActionListener(actionListener);

        JButton eight8_btn = new JButton("8");
        eight8_btn.setBounds(btn_size, frame_height-4*btn_size, btn_size, btn_size);
        eight8_btn.addActionListener(actionListener);

        JButton nine9 = new JButton("9");
        nine9.setBounds(2*btn_size, frame_height-4*btn_size, btn_size, btn_size);
        nine9.addActionListener(actionListener);

        JButton multiplication_btn = new JButton("×");
        multiplication_btn.setBounds(3*btn_size, frame_height-4*btn_size, btn_size, btn_size);
        multiplication_btn.addActionListener(actionListener);

        JButton residue_btn = new JButton("%");
        residue_btn.setBounds(0, frame_height-5*btn_size, btn_size, btn_size);
        residue_btn.addActionListener(actionListener);

        JButton exponentiation_btn = new JButton("^");
        exponentiation_btn.setBounds(btn_size, frame_height-5*btn_size, btn_size, btn_size);
        exponentiation_btn.addActionListener(actionListener);

        JButton squareroot_btn = new JButton("√");
        squareroot_btn.setBounds(2*btn_size, frame_height-5*btn_size, btn_size, btn_size);
        squareroot_btn.addActionListener(actionListener);

        JButton division_btn = new JButton("÷");
        division_btn.setBounds(3*btn_size, frame_height-5*btn_size, btn_size, btn_size);
        division_btn.addActionListener(actionListener);

        JButton C_btn = new JButton("C");
        C_btn.setBounds(0, frame_height-6*btn_size, large_btn_width, btn_size);
        C_btn.addActionListener(actionListener);

        JButton CA_btn = new JButton("CA");
        CA_btn.setBounds(large_btn_width, frame_height-6*btn_size, large_btn_width, btn_size);
        CA_btn.addActionListener(actionListener);
        window.add(abs_btn);
        window.add(zero0_btn);
        window.add(dot_btn);
        window.add(equal_btn);
        window.add(one1_btn);
        window.add(two2_btn);
        window.add(tree3_btn);
        window.add(plus_btn);
        window.add(four4_btn);
        window.add(five5_btn);
        window.add(six6_btn);
        window.add(minus_btn);
        window.add(seven7_btn);
        window.add(eight8_btn);
        window.add(nine9);
        window.add(multiplication_btn);
        window.add(residue_btn);
        window.add(exponentiation_btn);
        window.add(squareroot_btn);
        window.add(division_btn);
        window.add(C_btn);
        window.add(CA_btn);
        window.add(textField);

        window.setSize(frame_width, frame_height);
        window.setLayout(null);
        window.setVisible(true);
    }
}