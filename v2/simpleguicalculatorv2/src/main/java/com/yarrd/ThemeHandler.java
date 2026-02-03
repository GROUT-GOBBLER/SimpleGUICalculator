package com.yarrd;

/*
    Yarrick Dillard
    Simple GUI Calculator v2
    ThemeHandler.java
*/

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import com.formdev.flatlaf.themes.FlatMacLightLaf;
import com.jthemedetecor.OsThemeDetector;

public class ThemeHandler {
    public static void SetThemeDefault() {
        FlatLightLaf.setup();

        // Determines if system is on Dark/Light mode.
        if (OsThemeDetector.getDetector().isDark()) {   
            try {
                UIManager.setLookAndFeel(new FlatDarkLaf());
            } catch (UnsupportedLookAndFeelException e) {
                System.out.println(e);
            }
        } 
        else {
            try {
                UIManager.setLookAndFeel(new FlatLightLaf());
            } catch (UnsupportedLookAndFeelException e) {
                System.out.println(e);
            }
        }        
    }

    enum Theme {
        SwingDefault,
        SystemDefault,
        FlatLightLaf,
        FlatDarkLaf,
        FlatIntelliJLaf,
        FlatDarculaLaf,
        FlatMacLightLaf,
        FlatMacDarkLaf
    }

    public static void SetTheme(Theme t) {
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());

            switch (t) {
                case SwingDefault: {
                    UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
                    break;
                }
                case SystemDefault: {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                    break;
                }
                case FlatLightLaf: {
                    UIManager.setLookAndFeel(new FlatLightLaf());
                    break;
                }
                case FlatDarkLaf: {
                    UIManager.setLookAndFeel(new FlatDarkLaf());
                    break;
                }
                case FlatIntelliJLaf: {
                    UIManager.setLookAndFeel(new FlatIntelliJLaf());
                    break;
                }
                case FlatDarculaLaf: {
                    UIManager.setLookAndFeel(new FlatDarculaLaf());
                    break;
                }
                case FlatMacLightLaf: {
                    UIManager.setLookAndFeel(new FlatMacLightLaf());
                    break;
                }
                case FlatMacDarkLaf: {
                    UIManager.setLookAndFeel(new FlatMacDarkLaf());
                    break;
                }
                default: {
                    UIHandler.ShowErrorDialogue("Catastrophic failure in Theme Handler.");
                }
            }

            SwingUtilities.updateComponentTreeUI(UIHandler.main_frame);
        } 
        catch (Exception e) {
            UIHandler.ShowErrorDialogue(e.toString());
        }
    }
}
