package com.yarrd;

/*
    Yarrick Dillard
    Simple GUI Calculator v2
    Calculator.java
*/

import java.util.ArrayList;

import javax.naming.OperationNotSupportedException;

public class Calculator {
    // GLOBAL variables.
    static ArrayList<String> arr_list;

    // PUBLIC methods.
    public static void SetUserInput(String user_input) {
        ArrayList<String> string_list = new ArrayList<String>();
        
        int counter = 0;

        while (counter < user_input.length()) {
            Character c = user_input.charAt(counter);

            if (Character.isDigit(c) || c.equals('-') || c.equals('.')) {
                String number_string = Character.toString(c);
                boolean decimal_point_found = c.equals('.');

                while (counter+1 < user_input.length() && (Character.isDigit(user_input.charAt(counter+1)) || (user_input.charAt(counter+1) == '.' && !decimal_point_found))) {
                    Character next_char = user_input.charAt(counter+1);
                    number_string = number_string + Character.toString(next_char);

                    if (next_char == '.') {
                        decimal_point_found = true;
                    }

                    counter++;
                }

                string_list.add(number_string);
                counter++;
            }
            else {
                string_list.add(Character.toString(c));
                counter++;
            }
        }
        arr_list = string_list;
    }

    public static String Solve() {
        String result = "";
        
        if (IsInputValid()) {
            result = SolveExpression() + "";
            ResetArrayList();
        }      
        else {
            result = "ERROR";
        }

        return result;
    }

    // LOGICAL methods.
    private static boolean IsInputValid() { 
        if (IsAnOperation(arr_list.get(0)) || IsAnOperation(arr_list.getLast())) {
            UIHandler.ShowErrorDialogue("Operation in first/last position.");
            return false;
        }

        for (int counter = 1; counter < arr_list.size() - 1; counter++) {
            String c = arr_list.get(counter);
            
            try {
                Double.parseDouble(c);    // throws an exception if c isn't an valid #.
            }
            catch (NumberFormatException e) {
                if (IsAnOperation(c)) { 
                    if (IsAnOperation(arr_list.get(counter + 1))) {  // Check for 2 or more operations in a row.
                        UIHandler.ShowErrorDialogue("Sequential operations. " + c + " " + arr_list.get(counter + 1));
                        return false;
                    }
                }
                else { 
                    UIHandler.ShowErrorDialogue("Invalid character. " + c);
                    return false;
                }
            }
        }

        return true;
    }

    private static double SolveExpression() {
        ArrayList<String> expr_copy = arr_list;
        int counter = 1;

        // Solve multiplication, modulus, and division.
        counter = 1;

        while (counter < expr_copy.size() - 1) {
            String counter_str = expr_copy.get(counter);
            
            if (counter_str.equals("×") || counter_str.equals("÷") || counter_str.equals("%")) {
                String prev_str = expr_copy.get(counter - 1);
                String next_str = expr_copy.get(counter + 1);

                try {
                    expr_copy.remove(counter + 1);
                    expr_copy.remove(counter);
                    expr_copy.add(counter, Double.toString(PerformOperation(prev_str, next_str, counter_str)));
                    expr_copy.remove(counter - 1);
                }
                catch (ArithmeticException ae) {
                    UIHandler.ShowErrorDialogue("Cannot divide by zero.");
                    break;
                }
                catch (Exception e) {
                    UIHandler.ShowErrorDialogue(e.toString());
                    break;
                }

                counter = 0;
            }

            counter++;
        }

        // Solve addition & subtraction.
        counter = 1;

        while (counter < expr_copy.size() - 1) {
            String counter_str = expr_copy.get(counter);

            if (counter_str.equals("+") || counter_str.equals("−")) {
                String prev_str = expr_copy.get(counter - 1);
                String next_str = expr_copy.get(counter + 1);

                try {
                    expr_copy.remove(counter + 1);
                    expr_copy.remove(counter);
                    expr_copy.add(counter, Double.toString(PerformOperation(prev_str, next_str, counter_str)));
                    expr_copy.remove(counter - 1);
                }
                catch (Exception e) {
                    UIHandler.ShowErrorDialogue(e.toString());
                }

                counter = 0;
            }

            counter++;
        }

        if (expr_copy.size() > 1) {
            UIHandler.ShowErrorDialogue("A critical failure of some sort has occurred.");
            return 0.0;
        }

        return Double.parseDouble(expr_copy.get(0));
    }

    // HELPER methods.
    private static void ResetArrayList() {
        arr_list = new ArrayList<String>();
    }

    private static boolean IsAnOperation(String test_value) {
        return (test_value.equals("+") || test_value.equals("−") || test_value.equals("×") || test_value.equals("÷") || test_value.equals("%") || test_value.equals("-") || test_value.equals("."));
    }

    private static double PerformOperation(String num_one_str, String num_two_str, String op_str) throws OperationNotSupportedException, ArithmeticException {
        // Convert string input to correct format.
        double num_one_double = Double.parseDouble(num_one_str);
        double num_two_double = Double.parseDouble(num_two_str);
        char op_char = op_str.charAt(0);

        // Determine operation.
        switch (op_char) {
            case '−': {
                return num_one_double - num_two_double;
            }
            case '+': {
                return num_one_double + num_two_double;
            }
            case '÷': {
                if (num_two_double == 0) {
                    throw new ArithmeticException("Cannot divide by zero.");
                }

                return num_one_double / num_two_double;
            }
            case '×': {
                return num_one_double * num_two_double;
            }
            case '%': {
                return num_one_double % num_two_double;
            }
            default: {
                throw new OperationNotSupportedException(op_char + "");
            }
        }
    }
}
