package com.example.shoping;


import com.example.shoping.UI.LoginValidator;
import com.example.shoping.UI.RegisterValidator;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.annotation.meta.When;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RegisterTest {
    LoginValidator loginValidator;

    @Mock
    RegisterValidator registerValidator = mock(RegisterValidator.class);

    @BeforeClass
    public static void startTest(){
        System.out.println("BeforeClass");
    }
    @Before
    public void beforeTest(){
        System.out.println("beforeTest");
        loginValidator = new LoginValidator();
    }
    
    
    @Test(expected =  NullPointerException.class)
    public void registerValidEmail() throws Exception {
       System.out.println("Start Test Valid Email");
       System.out.println();
       Assert.assertEquals("True", loginValidator.isValidEmail("gaza19991hotmailcom"));
       System.out.println("End Test Valid Email");
    }

    @Test
    public void registerValidPassword() throws Exception {
        System.out.println("Start Test Valid Password");
        Assert.assertEquals("True", loginValidator.isValidPassword("123-123"));
        System.out.println("End Test Valid Password");
    }


    @Test(expected =  NullPointerException.class)
    public void registerValidUserName() throws Exception {
        System.out.println("Start Test Valid User Name");
        assertTrue("True", RegisterValidator.isValidUserName("ismail&ahmed"));
        System.out.println("End Test Valid User Name");
    }

    @Test
    public void registerValidEmailWithMoc()throws Exception{
         when(registerValidator.getState()).thenReturn("true");
        System.out.println("Mock Running.....");
        Assert.assertEquals("true" , registerValidator.getState());
    }

    @After
    public void afterTest(){
        System.out.println("afterTest");
    }
    @AfterClass
    public static void afterClass(){ System.out.println("AfterClass");
    }
}
