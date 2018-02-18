package com.nds.pmc.placesinmycircle;


import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.nds.pmc.R;
import com.nds.pmc.views.activities.HomeActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.core.AllOf.allOf;

@RunWith(AndroidJUnit4.class)
public class HomeActivityTest {

    @Rule
    public ActivityTestRule<HomeActivity> mActivityTestRule = new ActivityTestRule<>(HomeActivity.class);

    @Test
    public void homeActivityTest() {
        ViewInteraction drawerLayout = onView(allOf(withId(R.id.pmcDrawer)));
        drawerLayout.perform(click());

        ViewInteraction frameLayout = onView(
                allOf(withId(R.id.container)));
        frameLayout.perform(click());

//        ViewInteraction drawerList = onView(
//                allOf(withId(R.id.drawerList), isDisplayed()));
//        drawerList.perform(click());

        pressBack();
    }

}
