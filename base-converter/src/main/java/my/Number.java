package my;

public class Number {
    private String number;
    private final int radix;

    private String processString(String str) {
        String result = str;
        String leadingZerosRegex = "^0+(?!$)";
        boolean isNumNegative = false;

        if(str.charAt(0) == '-'){
            result = str.replaceFirst("-","");
            isNumNegative = true;
        }

        result = result.replaceAll(leadingZerosRegex, "");

        if(isNumNegative){
            result = "-" + result;
        }

        return result;
    }

    private void isValid(String num , int base){
        String invalidStringException = "For input string: \"" + num + "\"" + " under radix " + base;
        String invalidRadixException = "For input radix: \"" + base + "\". " + "Radix out of bounds! Min = " + ValidCharacters.MIN_RADIX + " , Max = " +ValidCharacters.MAX_RADIX;

        for (int i = 0; i < num.length(); i++) {
            if (i == 0){
                if(num.charAt(i) == '-'){
                    continue;
                }
            }
            if (!ValidCharacters.contains(num.charAt(i))) {
                throw new NumberFormatException(invalidStringException);
            }
            if (ValidCharacters.getValue(num.charAt(i)) >= base) {
                throw new NumberFormatException(invalidStringException);
            }
            if (base < ValidCharacters.MIN_RADIX || base > ValidCharacters.MAX_RADIX) {
                throw new NumberFormatException(invalidRadixException);
            }
        }
    }


    public Number parseTo (int newRadix) {
        return Converter.parse(this,newRadix);
    }

    public String toString() {
        return number;
    }

    public int getRadix() {
        return radix;
    }

    public boolean isNegative() {
        return this.number.charAt(0) == '-';
    }
    private void setNumber (String num) {
        this.number = processString(num);
    }

    public Number (String num , int base) {
        isValid(num,base);

        setNumber(num);
        this.radix = base;
    }

    public Number (Number number) {
        this.number = number.toString();
        this.radix = number.getRadix();
    }
}
