package com.example.onlineshop;

import android.view.View;

import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

public class SplashScreenTest{
//to check if launching of SplashScreenactivity is successfull

    @Rule
    public ActivityTestRule<SplashScreen> mActivityTestRule =new ActivityTestRule<SplashScreen>(SplashScreen.class);
    private SplashScreen mActivity=null;
    //before executing a testcase
    @Before
    public void setUp() throws Exception{
        mActivity=mActivityTestRule.getActivity();
    }

    @Test
    public void testLaunch(){
        View view=mActivity.findViewById(R.id.app_slogan);
        assertNotNull(view);
    }

    @After
    public void tearDown() throws Exception{
        mActivity=null;
    }
}
