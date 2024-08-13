package sixth.model.utils;

import java.util.regex.Pattern;

public class Validation {
    public String nameValidator(String name) throws Exception {
        if(Pattern.matches("^[a-zA-Z\\s]{3,30}$", name)){
            return name;
        }else{
            throw new Exception("Invalid Name");
        }
    }

}
