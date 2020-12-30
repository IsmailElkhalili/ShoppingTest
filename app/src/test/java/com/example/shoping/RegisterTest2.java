package com.example.shoping;

import com.example.shoping.UI.LoginValidator;
import com.example.shoping.UI.RegisterValidator;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class RegisterTest2 {
    String emial,password,userName;
    boolean state;

    public RegisterTest2(String emial, String password, String userName, boolean state) {
        this.emial = emial;
        this.password = password;
        this.userName = userName;
        this.state = state;
    }

    @Test(expected =  NullPointerException.class)
    public void registerValidEmail() throws Exception {
        System.out.println("Start Test Valid Email");
        System.out.println();
        Assert.assertEquals(true, LoginValidator.isValidEmail(emial));
        System.out.println("End Test Valid Email");
    }

    @Test
    public void registerValidPassword() throws Exception {
        System.out.println("Start Test Valid Password");
        Assert.assertEquals(true, LoginValidator.isValidPassword(password));
        System.out.println("End Test Valid Password");
    }

    @Test(expected =  NullPointerException.class)
    public void registerValidUserName() throws Exception {
        System.out.println("Start Test Valid User Name");
        Assert.assertEquals(true, RegisterValidator.isValidUserName("i&s"));
        System.out.println("End Test Valid User Name");
    }

    @Parameterized.Parameters
    public static Collection parameiz(){
        return Arrays.asList(new Object[][]{
                {"gaza19991hotmail","123-123","is",false},
                {"gaza19991hotmail","123-456","ismail",false},
                {"gazahotmail","123-456","elhaliliIsmail",false}
        });
    }
}
