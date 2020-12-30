package com.example.shoping.UI;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginValidator  {

    private final String regexE = "^(.+)@(.+)$";
    private final String regexP = "^(.+)-(.+)$";

    public boolean isValidEmail(String email){
        Pattern pattern = Pattern.compile(regexE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();

 }

    public boolean isValidPassword(String password){
        Pattern pattern = Pattern.compile(regexP);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();

    }



}
