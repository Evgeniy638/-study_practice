import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

import static java.lang.System.out;

public class cash_machine {

    private static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        out.println("Введите выражение:");
        String expression = in.nextLine();

        if(expression.charAt(0) == '-')
            expression = "0" + expression;

        if(expression.charAt(expression.length() - 1) == '=')
            expression = expression.substring(0, expression.length() - 1);

        String[] arr = expression.split(" ");

        ArrayList<String> arrayList = getPolishNotation(arr);

       BigDecimal result = new BigDecimal(getResult(arrayList));
       out.println("Ответ: " + result.setScale(3, RoundingMode.CEILING).stripTrailingZeros());
    }

    private static double getResult(ArrayList<String> arr){
        ArrayList<Double> doubles = new ArrayList<>();

        for (int i = 0; i < arr.size(); i++){
            if(isDouble(arr.get(i))){
                doubles.add(Double.parseDouble(arr.get(i)));
            }else if(arr.get(i).equals("+")){
                double tmp = doubles.get(doubles.size() - 2) + doubles.get(doubles.size() - 1);

                doubles.remove(doubles.size() - 1);
                doubles.remove(doubles.size() - 1);

                doubles.add(tmp);
            }else if(arr.get(i).equals("-")){
                double tmp = doubles.get(doubles.size() - 2) - doubles.get(doubles.size() - 1);

                doubles.remove(doubles.size() - 1);
                doubles.remove(doubles.size() - 1);

                doubles.add(tmp);
            }else if(arr.get(i).equals("*")){
                double tmp = doubles.get(doubles.size() - 2) * doubles.get(doubles.size() - 1);

                doubles.remove(doubles.size() - 1);
                doubles.remove(doubles.size() - 1);

                doubles.add(tmp);
            }else {
                double tmp = doubles.get(doubles.size() - 2) / doubles.get(doubles.size() - 1);

                doubles.remove(doubles.size() - 1);
                doubles.remove(doubles.size() - 1);

                doubles.add(tmp);
            }
        }

        return doubles.get(0);
    }

    private static ArrayList<String> getPolishNotation(String[] arr){
        ArrayList<String> newArr = new ArrayList<>();
        Stack<String> stack = new Stack<>();

        for(int i = 0; i < arr.length; i++){
            if(isDouble(arr[i])){
                newArr.add(arr[i]);
            }else if(arr[i].equals(")")){

                while (!stack.peek().equals("(")){
                    newArr.add(stack.pop());
                }

                stack.pop();

            }else if(arr[i].equals("+") || arr[i].equals("-")) {
                while (!stack.empty() && (stack.peek().equals("+") || stack.peek().equals("-") ||
                        stack.peek().equals("*") || stack.peek().equals("/"))){
                    newArr.add(stack.pop());
                }

                stack.push(arr[i]);
            }else if(arr[i].equals("*") || arr[i].equals("/")) {
                while (!stack.empty() && (stack.peek().equals("*") || stack.peek().equals("/"))){
                    newArr.add(stack.pop());
                }

                stack.push(arr[i]);
            }else{
                stack.push(arr[i]);
            }
        }

        while (!stack.empty()){
            newArr.add(stack.pop());
        }

        return newArr;
    }

    private static boolean isDouble(String s) throws NumberFormatException {
        try {
            Double.parseDouble(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}

//123 + 12 * 10 / 6 - 1
//123 + 12 * 10 / 21 - 1
//( 1.2 + 2.1 / 3 ) * ( 13 + 5 )