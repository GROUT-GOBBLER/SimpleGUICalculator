package com.yarrd;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.formdev.flatlaf.FlatLightLaf;

public class Main {
    public static void main(String[] args) {
        System.out.println("\n\n\nStarting program...\n");

        // Set theme.
        FlatLightLaf.setup();
        try {
            UIManager.setLookAndFeel( new FlatLightLaf());
        } catch (UnsupportedLookAndFeelException e) {
            System.out.println(e);
        }

        // Open application.
        UIHandler.CreateUI();
    }
}