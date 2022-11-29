package view;

public class InputValidator {
    private static InputValidator INSTANCE = null;

    private InputValidator() {

    }

    public static InputValidator getINSTANCE(){
        if (INSTANCE == null) {
            INSTANCE = new InputValidator();
        }
        return INSTANCE;
    }

    public boolean isEmailValid(String email) {
        String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        return email.matches(regex);
    }

    public boolean isInputNotEmpty(String syote) {
        return !syote.isEmpty();
    }

    public boolean isInputLongEnough(String syote) {
        return syote.length() >= 7;
    }
}

