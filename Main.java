import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String vvod = scanner.nextLine();
        Calc res = new Calc();
        String otvet = res.calc(vvod);

        System.out.println(otvet);

    }
    static class Calc{
        public static String calc(String input){
            int num1 = 1, num2 = 1, res = 0;
            Roma rNum1 = Roma.I, rNum2 = Roma.I;

            String znak = null;
            input = input.replace(" ", "");
            boolean romanOrArab = false;

            String[] split = input.split("[ +, \\-, *, /]");

            if (split.length == 1){
                throw new RuntimeException("строка не является математической операцией");
            }
            else if (split.length > 2 || split.length < 2){
                throw new RuntimeException("формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
            }

            if (input.contains("+")){znak = "+";}
            else if (input.contains("-")){znak = "-";}
            else if (input.contains("*")){znak = "*";}
            else if (input.contains("/")){znak = "/";}

            String strNum1 = split[0]; String strNum2 = split[1];

            try {
                num1 = Integer.parseInt(strNum1);
                num2 = Integer.parseInt(strNum2);
            } catch (NumberFormatException e){

               try {
                   rNum1 = Roma.valueOf(strNum1);
                   rNum2 = Roma.valueOf(strNum2);
                   romanOrArab = true;
               } catch (IllegalArgumentException ee){
                   throw new IllegalArgumentException("используются одновременно разные системы счисления");
               }
            }
            

            if (num1 < 1 || num1 > 10 || num2 < 1 || num2 > 10 || rNum1.number() > 10 || rNum2.number() > 10){
                throw new IllegalArgumentException("Калькулятор принимает числа от 1 до 10 включительно");
            }

            if (!romanOrArab) {
                switch (znak) {
                    case "+":
                        res = num1 + num2;
                        break;
                    case "-":
                        res = num1 - num2;
                        break;
                    case "*":
                        res = num1 * num2;
                        break;
                    case "/":
                        res = num1 / num2;
                        break;
                }
                return String.valueOf(res);
            }
            else {
                switch (znak){
                    case "+":
                        res = rNum1.number() + rNum2.number();
                        break;
                    case "-":
                        res = rNum1.number() - rNum2.number();
                        break;
                    case "*":
                        res = rNum1.number() * rNum2.number();
                        break;
                    case "/":
                        res = rNum1.number() / rNum2.number();
                        break;
                }
                if (res>1){
                     for (Roma rRes : Roma.values()) {
                        if (rRes.number() == res) {
                            return String.valueOf(rRes);
                        }
                     }
                }
                else {
                    throw new IllegalArgumentException("в римской системе нет отрицательных чисел");
                }
            }

            return null;
        }
    }
}