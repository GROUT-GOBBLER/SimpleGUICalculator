package com.yarrd;

/*
    Yarrick Dillard
    Simple GUI Calculator v2
    UIHandler.java
*/

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import com.yarrd.ThemeHandler.Theme;

import java.util.ArrayList;

public class UIHandler {
    // GLOBAL Variables.
    public static JFrame main_frame;
    private static JPanel user_input_panel, full_panel;
    
    private static JMenuBar menu_bar;

    private static JScrollPane text_area_scroll_pane;
    private static JTextArea text_area;

    private static JButton one_button, two_button, three_button, four_button, five_button, 
        six_button, seven_button, eight_button, nine_button, zero_button, 
        plus_button, minus_button, multiply_button, divide_button, modulus_button,
        clear_button, backspace_button, decimal_button, negative_button, equals_button;

    private static ArrayList<JButton> buttons;

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
        SetUpMenuBar();
        SetUpFullPanel();
        SetUpMainFrame();
    }

    public static void ShowErrorDialogue(String msg) {
        JOptionPane.showMessageDialog(main_frame, msg, "ERROR", JOptionPane.ERROR_MESSAGE);

        // Disable all buttons (except for C).
        for (JButton b: buttons) {
            b.setEnabled(false);
        }
        clear_button.setEnabled(true);
    }

    // SET-UP methods.    
    private static void SetUpTextArea() {
        text_area = new JTextArea();
            text_area.setFont(TEXT_AREA_DEFAULT_FONT);
            text_area.setEditable(false);
            text_area.setLineWrap(true);
            text_area.setWrapStyleWord(true);
            text_area.setText("");

        text_area_scroll_pane = new JScrollPane(text_area);
    }

    private static void SetUpButtons() {
        InstantiateButtons();
        PopulateButtonsArray();
        AssignButtonFunctionality();
    }

    private static void SetUpUserInputPanel() {
        user_input_panel = new JPanel();
        user_input_panel.setLayout(new GridLayout(4, 5, 5, 5));
        user_input_panel.add(one_button);
        user_input_panel.add(two_button);
        user_input_panel.add(three_button);
        user_input_panel.add(backspace_button);
        user_input_panel.add(clear_button);
        user_input_panel.add(four_button);
        user_input_panel.add(five_button);
        user_input_panel.add(six_button);
        user_input_panel.add(plus_button);
        user_input_panel.add(minus_button);
        user_input_panel.add(seven_button);
        user_input_panel.add(eight_button);
        user_input_panel.add(nine_button);
        user_input_panel.add(multiply_button);
        user_input_panel.add(divide_button);
        user_input_panel.add(negative_button);
        user_input_panel.add(zero_button);
        user_input_panel.add(decimal_button);
        user_input_panel.add(equals_button);
        user_input_panel.add(modulus_button);
    }

    private static void SetUpMenuBar() {
        menu_bar = new JMenuBar();        
        JMenu theme_menu = new JMenu("Theme");
        
        JMenuItem swing_default = new JMenuItem("SwingDefault");
            swing_default.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ThemeHandler.SetTheme(Theme.SwingDefault);
                }
            });
        JMenuItem system_default = new JMenuItem("SystemDefault");
            system_default.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ThemeHandler.SetTheme(Theme.SystemDefault);
                }
            });
        JMenuItem flat_light_laf = new JMenuItem("FlatLightLaf");
            flat_light_laf.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ThemeHandler.SetTheme(Theme.FlatLightLaf);
                }
            });
        JMenuItem flat_dark_laf = new JMenuItem("FlatDarkLaf");
            flat_dark_laf.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ThemeHandler.SetTheme(Theme.FlatDarkLaf);
                }
            });
        JMenuItem flat_intellij_laf = new JMenuItem("FlatIntelliJLaf");
            flat_intellij_laf.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ThemeHandler.SetTheme(Theme.FlatIntelliJLaf);
                }
            });
        JMenuItem flat_darcula_laf = new JMenuItem("FlatDarculaLaf");
            flat_darcula_laf.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ThemeHandler.SetTheme(Theme.FlatDarculaLaf);
                }
            });
        JMenuItem flat_mac_light_laf = new JMenuItem("FlatMacLightLaf");
            flat_mac_light_laf.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ThemeHandler.SetTheme(Theme.FlatMacLightLaf);
                }
            });
        JMenuItem flat_mac_dark_laf = new JMenuItem("FlatMacDarkLaf");
            flat_mac_dark_laf.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ThemeHandler.SetTheme(Theme.FlatMacDarkLaf);
                }
            });
        

        // Add items.
        theme_menu.add(swing_default);
        theme_menu.add(system_default);
        theme_menu.add(flat_light_laf);
        theme_menu.add(flat_dark_laf);
        theme_menu.add(flat_intellij_laf);
        theme_menu.add(flat_darcula_laf);
        theme_menu.add(flat_mac_light_laf);
        theme_menu.add(flat_mac_dark_laf);



        menu_bar.add(theme_menu);
    }

    private static void SetUpFullPanel() {
        full_panel = new JPanel();
        full_panel.setLayout(new GridLayout(2, 1, 5, 5));
        full_panel.add(text_area_scroll_pane);
        full_panel.add(user_input_panel);
    }

    private static void SetUpMainFrame() {
        main_frame = new JFrame("Simple GUI Calculator v2");

        main_frame.setJMenuBar(menu_bar);
        main_frame.add(full_panel);
        
        main_frame.setSize(800, 500);
        main_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        main_frame.setVisible(true);
    }

    // HELPER methods.
    private static void InstantiateButtons() {
        // Numbers.
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

        // Operations.
        plus_button = new JButton("+");
        minus_button = new JButton("−");
        multiply_button = new JButton("×");
        divide_button = new JButton("÷");
        modulus_button = new JButton("%");

        // Text-editing.
        clear_button = new JButton("C");        
        backspace_button = new JButton("DEL");
        
        // Miscellaneous.
        decimal_button = new JButton(".");
        negative_button = new JButton("(-)");
        equals_button = new JButton("=");
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
        buttons.add(modulus_button);
        buttons.add(clear_button);
        buttons.add(backspace_button);
        buttons.add(decimal_button);
        buttons.add(negative_button);
        buttons.add(equals_button);
    }

    private static void AssignButtonFunctionality() {
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
                            if (text_area.getText().length() > 0) {
                                Calculator.SetUserInput(text_area.getText());
                                text_area.setText(Calculator.Solve() + "");
                                text_area.setFont(TEXT_AREA_ANSWER_FONT);
                            }
                        }
                    });
                    break;
                }
                case "DEL": {
                    b.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {                            
                            if (text_area.getText().length() > 0) {
                                text_area.setText(text_area.getText().substring(0, text_area.getText().length() - 1));
                            }
                        }
                    });
                    break;
                }
                case "(-)": {
                    b.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {                            
                            text_area.setText(text_area.getText() + "-");
                        }
                    });
                    
                    break;
                }
                default: {
                    b.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            text_area.setText(text_area.getText() + b.getText());
                        }
                    });
                    break;
                }
            }
            
            b.setFont(BUTTON_FONT);
        }   
    }
}
