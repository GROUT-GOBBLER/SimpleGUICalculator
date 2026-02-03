package com.yarrd;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class UIHandler {
    // GLOBAL Variables.
    private static JFrame main_frame;
    private static JPanel user_input_panel, full_panel;
    private static JTextArea text_area;
    private static JButton one_button, two_button, three_button, 
        four_button, five_button, six_button, seven_button, 
        eight_button, nine_button, zero_button, plus_button, 
        minus_button, multiply_button, divide_button, 
        clear_button, equals_button;

    private static ArrayList<JButton> buttons;
    private static final int MAX_CHAR_NUM = 29;
    private static final String ICON_URL = "pixel-art-mountain.png";

    // Fonts.
        private static final String font_name = "Arial Rounded MT";
        private static final Font TEXT_AREA_DEFAULT_FONT = new Font(font_name, Font.PLAIN, 90);
        private static final Font TEXT_AREA_ANSWER_FONT = new Font(font_name, Font.BOLD, 90);
        private static final Font BUTTON_FONT = new Font(font_name, Font.PLAIN, 50);

    // PUBLIC methods.
    public static void CreateUI() {
        SetUpTextArea();
        SetUpButtons();
        SetUpUserInputPanel();
        SetUpFullPanel();
        SetUpMainFrame();
    }

    public static void ShowErrorDialogue(String msg) {
        JOptionPane.showMessageDialog(main_frame, "Error occured.\n" + msg);
    }

    // SET-UP methods.    
    private static void SetUpTextArea() {
        text_area = new JTextArea();
        
        text_area.setFont(TEXT_AREA_DEFAULT_FONT);
        text_area.setBackground(Color.WHITE);

        text_area.setEditable(false);
        text_area.setLineWrap(true);
        text_area.setWrapStyleWord(true);
        
        text_area.setText("");
    }

    private static void SetUpButtons() {
        InstantiateButtons();
        PopulateButtonsArray();

        for (JButton b: buttons) {
            switch (b.getText()) {
                case "C": {
                    b.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            text_area.setText("");

                            if (!one_button.isEnabled()) {
                                for (JButton b: buttons) {
                                    b.setEnabled(true);
                                }
                                equals_button.setEnabled(false);
                            }

                            text_area.setFont(TEXT_AREA_DEFAULT_FONT);
                        }
                    });
                    break;
                }
                case "=": {
                    b.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            Calculator.SetUserInput(text_area.getText());
                            text_area.setText(Calculator.Solve() + "");

                            for (JButton b: buttons) {
                                b.setEnabled(false);
                            }
                            clear_button.setEnabled(true);

                            text_area.setFont(TEXT_AREA_ANSWER_FONT);
                        }
                    });
                    break;
                }
                default: {
                    b.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            if (text_area.getText().length() < MAX_CHAR_NUM) {
                                text_area.setText(text_area.getText() + b.getText());
                            }
                            if (!equals_button.isEnabled()) {
                                equals_button.setEnabled(true);
                            }
                        }
                    });
                    break;
                }
            }
            
            b.setFont(BUTTON_FONT);
        }        
    }

    private static void SetUpUserInputPanel() {
        user_input_panel = new JPanel();
        user_input_panel.setLayout(new GridLayout(4, 4, 5, 5));
        user_input_panel.add(one_button);
        user_input_panel.add(two_button);
        user_input_panel.add(three_button);
        user_input_panel.add(plus_button);
        user_input_panel.add(four_button);
        user_input_panel.add(five_button);
        user_input_panel.add(six_button);
        user_input_panel.add(minus_button);
        user_input_panel.add(seven_button);
        user_input_panel.add(eight_button);
        user_input_panel.add(nine_button);
        user_input_panel.add(multiply_button);
        user_input_panel.add(clear_button);
        user_input_panel.add(zero_button);
        user_input_panel.add(equals_button);
        user_input_panel.add(divide_button);  
    }

    private static void SetUpFullPanel() {
        full_panel = new JPanel();
        full_panel.setLayout(new GridLayout(2, 1, 5, 5));
        full_panel.add(text_area);
        full_panel.add(user_input_panel);
    }

    private static void SetUpMainFrame() {
        main_frame = new JFrame("Simple GUI Calculator");

        main_frame.add(full_panel);
        
        main_frame.setSize(800, 500);
        main_frame.setResizable(false);
        main_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        main_frame.setVisible(true);
    }

    // HELPER methods.
    private static void InstantiateButtons() {
        one_button = new JButton("1");
        two_button = new JButton("2");
        three_button = new JButton("3");
        four_button = new JButton("4");
        five_button = new JButton("5");
        six_button = new JButton("6");
        seven_button = new JButton("7");
        eight_button = new JButton("8");
        nine_button = new JButton("9");
        zero_button = new JButton("0");

        plus_button = new JButton("+");
        minus_button = new JButton("−");
        multiply_button = new JButton("×");
        divide_button = new JButton("÷");

        clear_button = new JButton("C");
            clear_button.setBackground(new Color(255, 110, 110));

        equals_button = new JButton("=");
            equals_button.setBackground(new Color(110, 255, 120));
            equals_button.setEnabled(false);
    }

    private static void PopulateButtonsArray() {
        buttons = new ArrayList<>();
        
        buttons.add(one_button); 
        buttons.add(two_button);
        buttons.add(three_button);
        buttons.add(four_button);
        buttons.add(five_button);
        buttons.add(six_button);
        buttons.add(seven_button);
        buttons.add(eight_button);
        buttons.add(nine_button);
        buttons.add(zero_button);
        buttons.add(plus_button);
        buttons.add(minus_button);
        buttons.add(multiply_button);
        buttons.add(divide_button);
        buttons.add(clear_button);
        buttons.add(equals_button );
    }
}
