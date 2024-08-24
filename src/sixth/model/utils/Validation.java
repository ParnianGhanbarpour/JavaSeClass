package sixth.model.utils;

import java.util.regex.Pattern;

public class Validation {
    public String productNameValidator(String name) throws Exception {
        if(Pattern.matches("^[a-zA-Z\\s]{2,30}$", name)){
            return name;
        }else{
            throw new Exception("Invalid Name");
        }
    }
    public static String personNameValidator(String name) throws Exception {
        if(Pattern.matches("^[a-zA-Z\\s]{2,30}$", name)){
            return name;
        }else{
            throw new Exception("Invalid Name");
        }
    }

    public static String familyValidator(String family) throws Exception {
        if(Pattern.matches("^[a-zA-Z\\s]{2,30}$", family)){
            return family;
        }else{
            throw new Exception("Invalid family");
        }
    }

}
