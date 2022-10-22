/*Консольный калькулятор
22.10.2022
Karetnikov E.A karetnikovlab@mail.ru
 */
import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Main {
    public static <bool> void main(String[] args)  {

        String arithmetic;
        int a = 0;
        int b = 0;

        boolean flag_arabic = false;
        boolean flag_roman = false;
        int index_operation=0;
        //---------------константы удовлетворяющие выполнению задания--------
        final String[][] digit = {{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"},
                {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"}};
        final String[] operation = {"+", "-", "*", "/"};
        //-------------------------------------------------------------------
        System.out.println("Добро пожаловать в консольный калькулятор");
        System.out.println("Используйте  числа от 1 до 10 или от I до X и арифметические знаки +, -, *, /");
        System.out.println("шаблон для ввода: [число][пробел][знак операции][пробел][число]");
        System.out.println("пример: 10 * 9 или VIII / V");
        System.out.println("----------------------------------------------------------");

        do {
            int[] index_arabic = {0,0,0};
            int[] index_roman = {0,0,0};
            flag_arabic = false;
            flag_roman = false;

            System.out.println("Введите арифметическое выражение: ");
            Scanner s = new Scanner(System.in);
            arithmetic = s.nextLine();

            //---------------шабон арабского ввода------------------
            Pattern pattern = Pattern.compile("^([1-9]|10)\s([*-/+])\s([1-9]|(10))$");
            Matcher matcher = pattern.matcher(arithmetic) ;
            boolean t = matcher.matches();

            //---------------шабон римского ввода------------------
            Pattern pattern_roman = Pattern.compile("^(I|II|III|IV|V|VI|VII|VIII|IX|X)\s([*-/+])\s(I|II|III|IV|V|VI|VII|VIII|IX|X)$");
            Matcher matcher_roman = pattern_roman.matcher(arithmetic) ;
            boolean t_roman = matcher_roman.matches();

            if (!(t)&!(t_roman)) {
                try {
                    //-----------------шаблоны для генерации исключений---------------------------------
                    Pattern pattern_operationR = Pattern.compile("^(I|II|III|IV|V|VI|VII|VIII|IX|X)\s[^+*/-]\s(I|II|III|IV|V|VI|VII|VIII|IX|X)$");
                    Matcher matcher_operationR = pattern_operationR.matcher(arithmetic) ;
                    boolean exception_5 = matcher_operationR.matches();

                    if (exception_5) {
                        try {
                            throw new IOException();
                        } catch (IOException e) {
                            System.out.println("throws Exception //т.к. т.к. формат арифметический оператор не удовлетворяет заданию   (+, -, /, *)");
                            break;
                        }
                    }

                    Pattern pattern_operationA = Pattern.compile("^([1-9]|10)\s([^+*/-])\s([1-9]|(10))$");
                    Matcher matcher_operationA = pattern_operationA.matcher(arithmetic) ;
                    boolean exception_6 = matcher_operationA.matches();

                    if (exception_6) {
                        try {
                            throw new IOException();
                        } catch (IOException e) {
                            System.out.println("throws Exception //т.к. т.к. формат арифметический оператор не удовлетворяет заданию   (+, -, /, *)");
                            break;
                        }
                    }
                    Pattern notArithmetic = Pattern.compile("^([1-9]|10)|(I|II|III|IV|V|VI|VII|VIII|IX|X)");
                    Matcher matcher_notArithmetic = notArithmetic.matcher(arithmetic) ;
                    boolean  exception_1 = matcher_notArithmetic.matches();
                    if (exception_1) {
                        try {
                            throw new IOException();
                        } catch (IOException e) {
                            System.out.println("throws Exception //т.к. строка не является математической операцией");
                            break;
                        }
                    }

                    Pattern twoSystem = Pattern.compile("^([1-9]|10)\s([*-/+])\s(I|II|III|IV|V|VI|VII|VIII|IX|X)$");
                    Matcher matcher_twoSystem = twoSystem.matcher(arithmetic) ;
                    boolean  exception_2 = matcher_twoSystem.matches();
                    if (exception_2) {
                        try {
                            throw new IOException();
                        } catch (IOException e) {
                            System.out.println("throws Exception //т.к. используются одновременно разные системы счисления");
                            break;
                        }
                    }

                    Pattern twoSystemSwap = Pattern.compile("^(I|II|III|IV|V|VI|VII|VIII|IX|X)\s([*-/+])\s([1-9]|(10))$");
                    Matcher matcher_twoSystemSwap = twoSystemSwap.matcher(arithmetic) ;
                    boolean  exception_3 = matcher_twoSystemSwap.matches();
                    if (exception_3) {
                        try {
                            throw new IOException();
                        } catch (IOException e) {
                            System.out.println("throws Exception //т.к. используются одновременно разные системы счисления");
                            break;
                        }
                    }

                    Pattern notDigits = Pattern.compile("^(1|2|3|4|5|6|7|8|9|10|I|II|III|IV|V|VI|VII|VIII|IX|X)$");
                    Matcher matcher_notDigits = notDigits.matcher(arithmetic) ;
                    boolean  exception_4 = matcher_notDigits.matches();
                    if (!exception_4) {
                        try {
                            throw new IOException();
                        } catch (IOException e) {
                            System.out.println("throws Exception //т.к. т.к. формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
                            System.out.println("необходимо использовать 1,2,3,4,5,6,7,8,9,10 или I,II,III,IV,V,VI,VII,VIII,IX,X");
                            System.out.println("шаблон для ввода: [число][пробел][знак операции][пробел][число]");
                            System.out.println("пример: 10 - 7 или X * V");
                            break;
                        }
                    }
//---------------------------------------------------------------------------------------------------------
                    throw new IOException();
                } catch (IOException e) {
                    System.out.println("throws Exception //неизвестная ошибка. Обратитесь к разработчикам");
                    break;
                }
            }

            //разбиваем строку на отдельные элементы
            String[] words = arithmetic.split(" ");

            for (int j = 0; j < 3; j++) {
                for (int i = 0; i < 10; i++) {
                    if (Objects.equals(words[j], digit[0][i])) {
                        index_arabic [j]= i;
                        flag_arabic = true;
                        //System.out.println("Найдено соответствие " + digit[0][i]);
                    } else if (Objects.equals(words[j], digit[1][i])) {
                        index_roman[j] = i;
                        flag_roman = true;
                        //System.out.println("Найдено соответствие " + digit[1][i]);
                    } else {
                        //System.out.println("Соответствий не найдено");
                        int f = 0;
                    }
                }

            }

            for (int i = 0; i < 4; i++) {
                if (Objects.equals(words[1], operation[i])) {
                    index_operation = i;
                    System.out.println("----------------------------------------------------");
                    if (flag_arabic){
                        a = Integer.parseInt(digit[0][index_arabic[0]]);
                        b = Integer.parseInt(digit[0][index_arabic[2]]);
                        //System.out.println(index_arabic[0] + operation[i] + index_arabic[2]);
                    }
                    else {
                        a = Integer.parseInt((digit[0][index_roman[0]]));
                        b = Integer.parseInt((digit[0][index_roman[2]]));
                        //System.out.println(index_roman[0] + operation[i]+ index_roman[2]);
                    }
                }
            }
        }while (flag_roman == flag_arabic); //повторяем цикл пока оба числа не будут из одной системы счисления

        switch (index_operation) {
            case 0 -> {
                if (flag_arabic) {
                    System.out.print(a + " " + operation[index_operation] + " " + b + " = " + (a + b));
                } else if(flag_roman)
                {
                    System.out.print(digit[1][a-1] + " " + operation[index_operation] + " " + digit[1][b-1] + " = " + a_to_r(a + b));
                }
            }
            case 1 -> {
                if (flag_arabic) {
                    System.out.print(a + " " + operation[index_operation] + " " + b + " = " + (a - b));
                } else if(flag_roman)
                {
                    if ((a-b)<1) {
                        try {
                            throw new IOException();
                        } catch (IOException e) {
                            System.out.println("throws Exception //т.к. в римской системе нет отрицательных чисел, и обозначения 0");
                            break;
                        }
                    }else {
                        System.out.print(digit[1][a - 1] + " " + operation[index_operation] + " " + digit[1][b - 1] + " = " + a_to_r(a - b));
                    }
                }
            }
            case 2 -> {
                if (flag_arabic) {
                    System.out.print(a + " " + operation[index_operation] + " " + b + " = " + (a * b));
                } else if(flag_roman)
                {
                    System.out.print(digit[1][a-1] + " " + operation[index_operation] + " " + digit[1][b-1] + " = " + a_to_r(a * b));
                }
            }
            case 3 -> {
                if (flag_arabic) {
                    System.out.print(a + " " + operation[index_operation] + " " + b + " = " + (a / b));
                } else if(flag_roman)
                {
                    System.out.print(digit[1][a-1] + " " + operation[index_operation] + " " + digit[1][b-1] + " = " + a_to_r(a / b));
                }
            }
        }
    }
    public static String a_to_r(int input) {
        StringBuilder s = null;
        if (input < 1 || input > 100) {
            System.out.println("Неверное число");
        } else {
            s = new StringBuilder();
            while (input >= 100) {
                s.append("C");
                input -= 100;
            }
            while (input >= 90) {
                s.append("XC");
                input -= 90;
            }
            while (input >= 50) {
                s.append("L");
                input -= 50;
            }
            while (input >= 40) {
                s.append("XL");
                input -= 40;
            }
            while (input >= 10) {
                s.append("X");
                input -= 10;
            }
            while (input >= 9) {
                s.append("IX");
                input -= 9;
            }
            while (input >= 5) {
                s.append("V");
                input -= 5;
            }
            while (input >= 4) {
                s.append("IV");
                input -= 4;
            }
            while (input >= 1) {
                s.append("I");
                input -= 1;
            }
        }
        return s.toString();
    }
}