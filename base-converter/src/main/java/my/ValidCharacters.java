package my;

public class ValidCharacters {
    private static final String VALID_CHARACTERS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ"; // base36
    //private static final String validCharacters = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz"; // base62
    public static final int MIN_RADIX = 2;
    public static final int MAX_RADIX = VALID_CHARACTERS.length()+1;
    protected static int getValue (char ch) {
        return VALID_CHARACTERS.indexOf(ch);
    }
    protected static char getChar (int index) {
        return VALID_CHARACTERS.charAt(index);
    }
    protected static boolean contains (char ch) {
        for (int i = 0; i < VALID_CHARACTERS.length(); i++) {
            if (VALID_CHARACTERS.charAt(i) == ch){
                return true;
            }
        }
        return false;
    }
}
