public class Validator {

    private int minPasswordLength = 8;
    private char[] specialSymbols = {'!', '@', '*', '&', '%', '$', '?', '#'};
    private String[] topLevelDomains = {".com", ".org", ".lt", ".co.uk"};
    private String[] domains = {"gmail", "yahoo", "hotmail"};
    //private String[] prefixes

    public boolean validatePassword(String password) {

        if(password == null) {
            return false;
        }
        return checkPasswordLength(password) && checkPasswordForUppercase(password)
                && checkPasswordForSpecialCharacters(password) ;
    }

    public boolean validatePhone(String number) {

        if(number == null) {
            return false;
        }
        return checkPhoneNumberForSymbols(number) && checkPhoneNumberLength(number)
                && checkPhoneNumberPrefix(number) ;
    }

    public boolean validatEmail(String email) {

        if(email == null) {
            return false;
        }
        return checkEmailForAtSymbol(email) && checkEmailForCorrectDomain(email)
                && checkEmailForCorrectTLD(email) && checkEmailForIllegalSymbols(email);
    }


    // Password checks

    private boolean checkPasswordLength(String password) {

        if(password.length() >= this.minPasswordLength) {
            return true;
        } else {
            return false;
        }
    }

    private boolean checkPasswordForUppercase(String password) {

        char[] pass = password.toCharArray();
        for(char symbol : pass) {
            for(int i = 65; i < 91; i++) {
                if(symbol == i) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkPasswordForSpecialCharacters(String password) {

        char[] pass = password.toCharArray();
        for(char symbol : pass) {
            for(char special : specialSymbols) {
                if(symbol == special) {
                    return true;
                }
            }
        }
        return false;
    }


    // Email checks

    private boolean checkEmailForAtSymbol(String email) {

        char[] emailArr = email.toCharArray();
        int atCount = 0;
        for(char symbol : emailArr) {
            if(symbol == '@') {
                atCount++;
            }
        }
        if(atCount == 1) {
            return true;
        } else {
            return false;
        }
    }

    private boolean checkEmailForIllegalSymbols(String email) {

        char[] emailArr = email.toCharArray();
        int len = emailArr.length;

        if(emailArr[0] == '@' ) {
            return false;
        }
        if(emailArr[0] == '.' ) {
            return false;
        }
        if(emailArr[len - 1] == '.' ) {
            return false;
        }


        return true;
    }

    private boolean checkEmailForCorrectDomain(String email) {

        for(String domain : domains) {
            if(email.contains(domain)){
                return true;
            }
        }
        return false;
    }

    private boolean checkEmailForCorrectTLD(String email) {

        for(String tld : topLevelDomains) {
            if(email.contains(tld)){
                return true;
            }
        }
        return false;
    }


    // Phone number checks

    private boolean checkPhoneNumberLength(String phoneNumber) {

        char[] phone = phoneNumber.toCharArray();
        int len = phone.length;

        if(phone[0] == '+') {
            if((len - 1) == 10) {
                return true;
            }
        } else {
            if(len == 9) {
                return true;
            }
        }

        return false;

    }

    private boolean checkPhoneNumberForSymbols(String phoneNumber) {

        char[] phone = phoneNumber.toCharArray();
        int len = phone.length;

        if(phone[0] == '+') {
            for(int i = 1; i < len; i++) {
                for(char symbol : specialSymbols) {
                    if(phone[i] == symbol) {
                        return false;
                    }
                }
            }
        } else {
            for(int i = 0; i < len; i++) {
                for(char symbol : specialSymbols) {
                    if(phone[i] == symbol) {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    private boolean checkPhoneNumberPrefix(String phoneNumber) {

        if(phoneNumber.startsWith("+370") || phoneNumber.startsWith("8")) {
            return true;
        } else {
            return false;
        }
    }
}
