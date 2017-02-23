package com.tsystems.javaschool.tasks.calculator;

import java.text.NumberFormat;
import java.util.LinkedList;
import java.util.Locale;

public class Calculator {


    public String evaluate(String statement) {
        LinkedList<Double> value;
        try {
            String mathE = statement.replaceAll(" ", "");

            value = new LinkedList<Double>();

            LinkedList<Character> Operators = new LinkedList<Character>();


            for (int i = 0; i < mathE.length(); i++) {

                char ch = mathE.charAt(i);

                if (ch == '(') {

                    Operators.add('(');

                } else if (ch == ')') {

                    while (Operators.getLast() != '(') {

                        mathOperation(value, Operators.removeLast());

                    }

                    Operators.removeLast();

                } else if (isOperator(ch)) {

                    while (!Operators.isEmpty() && priorityOp(Operators.getLast()) >= priorityOp(ch)) {

                        mathOperation(value, Operators.removeLast());

                    }

                    Operators.add(ch);

                } else if (Character.isDigit(ch)) {

                    String operand = "";

                    while (i < mathE.length() && (mathE.charAt(i) == '.' || Character.isDigit(mathE.charAt(i))
                            || mathE.charAt(i) == ',')) {

                        if (mathE.charAt(i) == ',') {

                            return null;

                        }

                        operand += mathE.charAt(i++);

                    }
                    --i;

                    try {

                        value.add(Double.parseDouble(operand));

                    } catch (NumberFormatException e) {

                        return null;

                    }
                }
            }
            while (!Operators.isEmpty()) {

                mathOperation(value, Operators.removeLast());

            }
        } catch (Exception e) {

            return null;

        }
        try {

            NumberFormat numberFormatter = NumberFormat.getNumberInstance(Locale.US);
            numberFormatter.setMaximumFractionDigits(4);
            numberFormatter.setMinimumFractionDigits(0);

            return numberFormatter.format(value.get(0));

        } catch (Exception e) {
            return null;
        }

    }

    public boolean isOperator(char ch) {

        return ch == '+' || ch == '-' || ch == '*' || ch == '/';

    }

    public int priorityOp(char operator) {

        if (operator == '*' || operator == '/') {
            return 1;
        } else if (operator == '+' || operator == '-') {
            return 0;
        } else {
            return -1;
        }
    }

    public void mathOperation(LinkedList<Double> st, char operator) throws Exception {

        double one = st.removeLast();
        double two = st.removeLast();


        switch (operator) {
            case '+':
                st.add(two + one);
                break;
            case '-':
                st.add(two - one);
                break;
            case '*':
                st.add(two * one);
                break;
            case '/':
                if (one == 0){
                    throw new ArithmeticException();
                }
                st.add(two / one);
                break;
            default:
                System.out.println("Неверное значение параметра operator");
        }
    }
}

