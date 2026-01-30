package com.yarrd;

import java.util.ArrayList;
import java.util.Scanner;

import com.yarrd.Exceptions.InvalidOperationException;

public class Main {
    public static void main(String[] args) {
        System.out.println("\n\n\nHello world!\n");

        // User input.
        Scanner sc = new Scanner(System.in);

        String expression = sc.nextLine();
        System.out.println(expression + "\n");

        sc.close();

        // Working.
        ArrayList<String> arr_list = ConvertUserInputToArray(expression);
        System.out.println(arr_list.toString());


        try {
            System.out.println(PerformOperation(arr_list.get(0), arr_list.get(2), arr_list.get(1)));
        }
        catch (Exception e) {
            System.out.println(e);
        }

        System.out.println("\nExiting program.\n\n\n");
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