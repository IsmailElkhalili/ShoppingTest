package com.example.shoping.UI;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterValidator {
    private static final String regexU = "^(.+)-(.+)$";

    public String getState(){

        return null;
    }
    public static boolean isValidUserName(String userName){
        Pattern pattern = Pattern.compile(regexU);
        Matcher matcher = pattern.matcher(userName);
        return matcher.matches();

    }
}
