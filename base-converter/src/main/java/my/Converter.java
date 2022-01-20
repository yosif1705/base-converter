package my;

import java.math.BigInteger;

class Converter {
    private static BigInteger myPow (long num , long pow){ // num to the power of pow
        BigInteger result = new BigInteger("1"); 

        for(long i = 0 ; i < pow; i++)
            result = result.multiply(new BigInteger(Long.toString(num)));

        return result;
    }

    protected static Number convertToBase10 (Number num) {
        BigInteger result = new BigInteger("0");
        String tempNumber = num.toString(); 
        int power;

        if (num.isNegative()) {
            result = result.multiply(new BigInteger("-1")); // Converting the number to positive
            power = tempNumber.length() - 2; // Getting the last power
            for(int i = 1; i < tempNumber.length(); i++) {
                BigInteger valueOfCurrChar = new BigInteger(Integer.toString(ValidCharacters.getValue(tempNumber.charAt(i)))); // The value of the current character
                BigInteger toAdd = valueOfCurrChar.multiply(myPow(num.getRadix(),power)); // Formula: currentCharacterValue * (base^currentPower) 
                result = result.add(toAdd); // Adding to the result
                power--; // Update
            }
            result = result.multiply(new BigInteger("-1")); // Back to negative
        } else {
            power = tempNumber.length() - 1; // Getting the last power
            for(int i = 0; i < tempNumber.length(); i++) {
                BigInteger valueOfCurrChar = new BigInteger(Integer.toString(ValidCharacters.getValue(tempNumber.charAt(i)))); // The value of the current character
                BigInteger toAdd = valueOfCurrChar.multiply(myPow(num.getRadix(),power)); // Formula: currentCharacterValue * (base^currentPower)
                result = result.add(toAdd); // Adding to the result
                power--; // Update
            }
        }

        return new Number(result.toString(),10);
    }

    protected static Number fromDecimalTo (Number decimal , int targetBase) {
        BigInteger tempDecimal = new BigInteger(decimal.toString()); 
        StringBuilder result = new StringBuilder();
        int remainder;

        do{
            remainder = tempDecimal.mod(BigInteger.valueOf(targetBase)).intValueExact(); // getting the remainder of the division decimal/base
            result.append(ValidCharacters.getChar(remainder));// adding the character with value=remainder  to the result
            tempDecimal = tempDecimal.divide(BigInteger.valueOf(targetBase));
        }while(tempDecimal.signum() == 1); // repeat until the number is <=0

        return new Number(result.reverse().toString(),targetBase);// return result reversed
    }
}
