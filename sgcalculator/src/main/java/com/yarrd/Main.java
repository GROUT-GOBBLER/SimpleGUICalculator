package com.yarrd;

import java.util.ArrayList;
import java.util.Scanner;
import com.yarrd.Exceptions.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("\n\n\nHello world!\n");
        
        try {
            ArrayList<String> arr_list = GetUserInput();
            IsInputValid(arr_list);
            System.out.println(SolveExpression(arr_list));
        }
        catch (Exception e) {
            System.out.println(e);
        }

        System.out.println("\nExiting program.\n\n\n");
    }

    private static ArrayList<String> GetUserInput() {
        Scanner sc = new Scanner(System.in);

        String expression = sc.nextLine();
        sc.close();
        
        return ConvertUserInputToArray(expression);
    }

    private static ArrayList<String> ConvertUserInputToArray(String user_input) {       
        ArrayList<String> string_list = new ArrayList<String>();
        
        int counter = 0;

        while (counter < user_input.length()) {
            Character c = user_input.charAt(counter);

            if (Character.isDigit(c)) {
                String number_string = Character.toString(c);

                while (counter+1 < user_input.length() && Character.isDigit(user_input.charAt(counter+1))) {
                    Character next_char = user_input.charAt(counter+1);
                    number_string = number_string + Character.toString(next_char);
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
        return string_list;
    }

    private static void IsInputValid(ArrayList<String> arr_list) throws InvalidInputException {
        /* 
         *      Potential input errors. 
         *       - Invalid ordering of characters ("1++1").
         *       - Invalid characters.
         */ 

        if (IsAnOperation(arr_list.get(0)) || IsAnOperation(arr_list.getLast())) {
            throw new InvalidInputException("Operation in first/last position.");
        }

        for (int counter = 1; counter < arr_list.size() - 1; counter++) {
            String c = arr_list.get(counter);

            
            try {
                Integer.parseInt(c);    // throws an exception if c isn't an integer.
            }
            catch (NumberFormatException e) {
                if (IsAnOperation(c)) { 
                    if (c.equals(arr_list.get(counter + 1))) {  // Check for duplicate operations.
                        throw new InvalidInputException("Sequential Operations. " + c + " " + arr_list.get(counter + 1));
                    }
                }
                else { 
                    throw new InvalidInputException("Invalid character. " + c);
                }
            }
        }
    }

    private static boolean IsAnOperation(String test_value) {
        return (test_value.equals("+") || test_value.equals("-") || test_value.equals("*") || test_value.equals("/"));
    }

    private static double SolveExpression(ArrayList<String> expression) {
        ArrayList<String> expr_copy = expression;

        // Solve multiplication & division equations.
        int counter = 1;

        while (counter < expr_copy.size() - 1) {
            String counter_str = expr_copy.get(counter);
            
            if (counter_str.equals("/") || counter_str.equals("*")) {
                String prev_str = expr_copy.get(counter - 1);
                String next_str = expr_copy.get(counter + 1);

                try {
                    expr_copy.remove(counter + 1);
                    expr_copy.remove(counter);
                    expr_copy.add(counter, Double.toString(PerformOperation(prev_str, next_str, counter_str)));
                    expr_copy.remove(counter - 1);
                }
                catch (Exception e) {
                    System.out.println(e);
                }

                counter = 0;
            }

            counter++;
        }

        // Solve addition & subtraction equations.
        counter = 1;

        while (counter < expr_copy.size() - 1) {
            String counter_str = expr_copy.get(counter);

            if (counter_str.equals("+") || counter_str.equals("-")) {
                String prev_str = expr_copy.get(counter - 1);
                String next_str = expr_copy.get(counter + 1);

                try {
                    expr_copy.remove(counter + 1);
                    expr_copy.remove(counter);
                    expr_copy.add(counter, Double.toString(PerformOperation(prev_str, next_str, counter_str)));
                    expr_copy.remove(counter - 1);
                }
                catch (Exception e) {
                    System.out.println(e);
                }

                counter = 0;
            }

            counter++;
        }

        if (expr_copy.size() > 1) {
            System.out.println("ERROR! Something went wrong!!!");
            return 0.0;
        }

        return Double.parseDouble(expr_copy.get(0));
    }

    private static double PerformOperation(String num_one_str, String num_two_str, String op_str) throws InvalidOperationException {
        // Convert string input to correct format.
        double num_one_double = Double.parseDouble(num_one_str);
        double num_two_double = Double.parseDouble(num_two_str);
        char op_char = op_str.charAt(0);

        // Determine operation.
        switch (op_char) {
            case '-': {
                return num_one_double - num_two_double;
            }
            case '+': {
                return num_one_double + num_two_double;
            }
            case '/': {
                if (num_two_double == 0) {
                    throw new ArithmeticException("Cannot divide by zero.");
                }

                return num_one_double / num_two_double;
            }
            case '*': {
                return num_one_double * num_two_double;
            }
            default: {
                throw new InvalidOperationException(op_char);
            }
        }
    }
}