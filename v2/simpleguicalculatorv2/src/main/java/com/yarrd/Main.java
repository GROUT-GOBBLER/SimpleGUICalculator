package com.yarrd;

/*
    Yarrick Dillard
    Simple GUI Calculator v2
    Main.java
*/

public class Main {
    public static void main(String[] args) {
        System.out.println("\n\n\nStarting program...\n");

        // Set theme based off system light/dark mode.
        ThemeHandler.SetThemeDefault();

        // Open application.
        UIHandler.CreateUI();
    }

    
}