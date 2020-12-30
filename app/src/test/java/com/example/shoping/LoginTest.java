package com.example.shoping;

import com.example.shoping.UI.LoginValidator;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class LoginTest {
    String emial,password;
    boolean state;
    static LoginValidator loginValidator;

    public LoginTest(String emial, String password, boolean state) {
        this.emial = emial;
        this.password = password;
        this.state = state;
    }

    @BeforeClass
    public static void beforeTest(){
        System.out.println("beforeTest");
        loginValidator = new LoginValidator();
    }


    @Test
    public void LoginTestEmail(){
        Assert.assertEquals(state, loginValidator.isValidEmail(emial)); ;
    }

    @Test
    public void LoginTestPassword(){
        Assert.assertEquals(state, loginValidator.isValidPassword(password));
    }

    @Parameterized.Parameters
    public static Collection parameiz(){
        return Arrays.asList(new Object[][]{
                {"gaza19991@hotmail.com","123-123",true},
                {"gaza19991hotmail.com","123",false},
                {"gaza@hotmail.com","123456",true}
        });
    }
}
