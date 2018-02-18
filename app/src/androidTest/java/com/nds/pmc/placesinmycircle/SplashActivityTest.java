package com.nds.pmc.placesinmycircle;

import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.nds.pmc.R;
import com.nds.pmc.views.activities.SplashActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.core.AllOf.allOf;

/**
 * Created by Namrata Shah on 2/17/2018.
 */
@RunWith(AndroidJUnit4.class)
public class SplashActivityTest {

    @Rule
    public ActivityTestRule<SplashActivity> mActivityTestRule = new ActivityTestRule<>(SplashActivity.class);

    private SplashActivity splashActivity;

    @Before
    public void setActivity() {
        splashActivity = mActivityTestRule.getActivity();
    }

    @Test
    public void launcherActivityTest() {

        ViewInteraction container = onView(allOf(withId(R.id.mainContainer), withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE), isDisplayed()));
        container.perform(click());
        pressBack();
    }

}
