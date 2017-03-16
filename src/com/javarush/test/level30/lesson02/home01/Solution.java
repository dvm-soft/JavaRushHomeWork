package com.javarush.test.level30.lesson02.home01;


import java.math.BigInteger;

/* Конвертер систем счислений
Реализуйте логику метода convertNumberToOtherNumerationSystem, который должен переводить число number.getDigit()
из одной системы счисления(numerationSystem) в другую (expectedNumerationSystem)
бросьте NumberFormatException, если переданное число некорректно, например, число "120" с системой счисления 2
Валидация для - number.getDigit() - целое не отрицательное
Метод main не участвует в тестировании
*/
public class Solution {
    public static void main(String[] args) {
        Number number = new Number(NumerationSystemType._10, "6");
        Number result = convertNumberToOtherNumerationSystem(number, NumerationSystemType._2);
        System.out.println(result);    //expected 110

        number = new Number(NumerationSystemType._3, "21021020201000000102020102002010012010201021201200102001200210012");
        result = convertNumberToOtherNumerationSystem(number, NumerationSystemType._2);
        System.out.println(result);    //expected 1101001000000001100001001110110111111100110010101000100111011011011001001011001100011001100000111101111

        number = new Number(NumerationSystemType._16, "fffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff");
        result = convertNumberToOtherNumerationSystem(number, NumerationSystemType._2);
        System.out.println(result);
    }

    public static Number convertNumberToOtherNumerationSystem(Number number, NumerationSystem expectedNumerationSystem) {

        String inputDigit = number.getDigit();
        int inputRadix = number.getNumerationSystem().getNumerationSystemIntValue();
        BigInteger inputValue = BigInteger.ZERO;


        for(int i = inputDigit.length()-1; i >= 0; i--)
        {
            inputValue = inputValue.add(
                            BigInteger.valueOf(Integer.parseInt(inputDigit.charAt(i)+"", inputRadix)).multiply(
                                    BigInteger.valueOf(inputRadix).pow(inputDigit.length() - 1 - i)));
        }
        long outputRadix = expectedNumerationSystem.getNumerationSystemIntValue();

        String result = "";
        while (inputValue.compareTo(BigInteger.valueOf(outputRadix - 1)) > 0)
        {
            result = Long.toHexString(inputValue.remainder(BigInteger.valueOf(outputRadix)).longValue()) + result;
            inputValue = inputValue.divide(BigInteger.valueOf(outputRadix));
        }
        result = Long.toHexString(inputValue.remainder(BigInteger.valueOf(outputRadix)).longValue()) + result;

        return new Number(expectedNumerationSystem, result);
    }
}
