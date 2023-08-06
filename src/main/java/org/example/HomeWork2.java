package org.example;

import java.util.InputMismatchException;
import java.util.Scanner;

public class HomeWork2 {
    public static void main(String[] args) {
//        task1();
        task2();
    }

    /*
    Напишите программу, которая запрашивает у пользователя число и проверяет, является ли оно положительным.
    Если число отрицательное или равно нулю, программа должна выбрасывать исключение
    InvalidNumberException с сообщением "Некорректное число". В противном случае, программа
    должна выводить сообщение "Число корректно".
     */
    public static void task1() {
        try (Scanner sc = new Scanner(System.in)) {
            System.out.print("Введите целое число: ");
            int number = sc.nextInt();
            checkNumber(number);
        } catch (InvalidNumberException e) {
            System.out.println(e.getMessage());
        } catch (InputMismatchException e) {
            System.out.println("Некорректный ввод! Вводите целые числа!");
        }
    }

    public static void checkNumber(int value) throws InvalidNumberException {
        if (value <= 0) {
            throw new InvalidNumberException("Некорректное число!");
        } else {
            System.out.println("Число корректно.");
        }
    }

    static class InvalidNumberException extends Exception {
        public InvalidNumberException(String message) {
            super(message);
        }
    }

    /*
    Напишите программу, которая запрашивает у пользователя два числа и выполняет их деление.
    Если второе число равно нулю, программа должна выбрасывать исключение DivisionByZeroException
    с сообщением "Деление на ноль недопустимо". В противном случае, программа должна выводить
    результат деления.

    Обратите внимание, что в обоих задачах используются собственные исключения,
    которые наследуются от класса Exception. Это позволяет нам определить специфическую причину ошибки и
    передать информацию об ошибке для последующей обработки.
     */

    public static void task2(){
        try(Scanner sc = new Scanner(System.in)) {
            System.out.print("Введите 1 число: ");
            int number1 = sc.nextInt();
            System.out.print("Введите 2 число: ");
            int number2 = sc.nextInt();
            divideNumber(number1,number2);
        } catch (DivisionByZeroException e){
            System.out.println(e.getMessage());
        } catch (InputMismatchException e){
            System.out.println("Введите корректное число!");
        }

    }

    public static void divideNumber(int num1, int num2) throws DivisionByZeroException {
        if (num1 == 0 || num2 == 0){
            throw new DivisionByZeroException("Деление на ноль недопустимо");
        }else {
            System.out.println(num1/num2);
        }
    }

    static class DivisionByZeroException extends Exception {
        public DivisionByZeroException(String message) {
            super(message);
        }
    }
}