package my;

import java.math.BigInteger;

class Converter {
    private static BigInteger pow (long num , long pow){
        BigInteger result = new BigInteger("1");

        for(long i = 0 ; i < pow; i++) {
            result = result.multiply(BigInteger.valueOf(num));
        }
        return result;
    }

    protected static Number convertToBase10 (Number num) {
        if (num.getRadix() == 10){
            return num;
        }
        BigInteger result = new BigInteger("0");
        String tempNumber = num.toString();
        int power = num.isNegative() ? tempNumber.length() - 2 : tempNumber.length() - 1;
        int startingIndex = num.isNegative() ? 1 : 0;

        for(int i = startingIndex; i < tempNumber.length(); i++) {
            BigInteger valueOfCurrChar = new BigInteger(Integer.toString(ValidCharacters.getValue(tempNumber.charAt(i)))); // The value of the current character
            BigInteger toAdd = valueOfCurrChar.multiply(pow(num.getRadix(),power)); // Formula
            result = result.add(toAdd); // Adding to the result
            power--; // Update
        }

        if (num.isNegative()){
            result = result.multiply(new BigInteger("-1"));
        }

        return new Number(result.toString(),10);
    }

    protected static Number fromDecimalTo (Number decimal , int targetBase) {
        if (decimal.getRadix() == targetBase){
            return decimal;
        }
        BigInteger tempDecimal = new BigInteger(decimal.toString());
        StringBuilder result = new StringBuilder();

        if (decimal.isNegative()){
            tempDecimal = tempDecimal.multiply(BigInteger.valueOf(-1));
        }

        do{
            int remainder = tempDecimal.mod(BigInteger.valueOf(targetBase)).intValueExact(); // Dividing the number by the base and getting the remainder
            result.append(ValidCharacters.getChar(remainder)); // Adding the character of the remainder's value to the result
            tempDecimal = tempDecimal.divide(BigInteger.valueOf(targetBase)); // updating the number
        }while(tempDecimal.signum() == 1);

        if (decimal.isNegative()){
            result.append("-");
        }

        return new Number(result.reverse().toString(),targetBase);
    }

    protected static Number parse (Number number, int targetBase) {
        if (number.getRadix() == targetBase) return number;
        else return Converter.fromDecimalTo(Converter.convertToBase10(number),targetBase);
    }
}
