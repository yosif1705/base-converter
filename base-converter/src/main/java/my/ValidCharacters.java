package my;

public class ValidCharacters {
    protected static final String validCharacters = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ"; // base36
    //protected static final String validCharacters = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz"; // base62
    public static final int MIN_RADIX = 2;
    public static final int MAX_RADIX = validCharacters.length()+1;
    protected static int getValue(char ch) {
        return validCharacters.indexOf(ch);
    }
    protected static char getChar (int index) {
        return validCharacters.charAt(index);
    }
}
