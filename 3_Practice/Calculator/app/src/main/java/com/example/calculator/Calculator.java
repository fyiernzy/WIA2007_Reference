package com.example.calculator;

import java.util.ArrayList;
import java.util.Stack;

public class Calculator {
    public static double calculate(String input) {
        String[] tokens = tokenize(input);
        String[] postfix = infixToPostfix(tokens);
        double result = evaluatePostfix(postfix);
        return result;
    }

    private static String[] tokenize(String input) {
        ArrayList<String> tokens = new ArrayList<>();
        int i = 0;
        while(i < input.length()) {
            if(Character.isDigit(input.charAt(i))) {
                int j = i;
                while(j < input.length() && (Character.isDigit(input.charAt(j)) || input.charAt(j) == '.')) {
                    j++;
                }
                tokens.add(input.substring(i, j));
                i = j;
            } else {
                tokens.add(input.substring(i, i+1));
                i++;
            }
        }
        return tokens.toArray(new String[0]);
    }

    private static String[] infixToPostfix(String[] input) {
        Stack<String> s = new Stack<>();
        ArrayList<String> output = new ArrayList<>();
        for(String str : input) {
            if(isNumeric(str)) {
                output.add(str);
            } else {
                if(s.isEmpty() || str.equals("(") || precedence(str) > precedence(s.peek())) {
                    s.push(str);
                } else if (str.equals(")")) {
                    while(!s.isEmpty() && !s.peek().equals("(")) {
                        output.add(s.pop());
                    }
                    s.pop();
                }
                else {
                    while(!s.isEmpty() && precedence(s.peek()) >= precedence(str)) {
                        output.add(s.pop());
                    }
                    s.push(str);
                }
            }
        }
        while(!s.isEmpty()) {
            output.add(s.pop());
        }
        return output.toArray(new String[0]);
    }


    private static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch(NumberFormatException e) {
            return false;
        }
    }


    private static double evaluatePostfix(String[] input) {
        Stack<Double> s = new Stack<>();
        for(String str : input) {
            if(isNumeric(str)) {
                s.push(Double.parseDouble(str));
            } else {
                double b = s.pop();
                double a = s.pop();
                switch(str) {
                    case "+" : s.push(a + b); break;
                    case "-" : s.push(a - b); break;
                    case "*" : s.push(a * b); break;
                    case "/" :
                        if (b == 0) throw new ArithmeticException("Division by zero");
                        s.push(a / b);
                        break;
                }
            }
        }
        return s.pop();
    }

    private static int precedence(String str) {
        switch(str) {
            case "+" :
            case "-" : return 1;
            case "*" :
            case "/" : return 2;
            default: return 0;
        }
    }

}
