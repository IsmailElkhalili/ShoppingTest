package com.example.shoping.UI;

import androidx.annotation.ContentView;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.example.shoping.R;

import junit.framework.TestCase;

import org.junit.*;
import org.junit.rules.TestRule;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class UILoginTest {
    @Rule
    public ActivityTestRule<Login> loginActivityTestRule = new ActivityTestRule<>(Login.class);

    @Test
    public void view_isCorrect(){
        onView(withText("WellCome")).check(matches(isDisplayed()));
        onView(withId(R.id.btn_login)).perform(click()).check(matches(isDisplayed()));
        onView(withId(R.id.Creat_Account_in_login)).check(matches(withText(" Create One")));
        onView(withId(R.id.loginTv)).check(matches(withText("Log In")));
    }
}