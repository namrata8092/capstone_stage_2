package com.nds.pmc.placesinmycircle;

import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;

import com.nds.pmc.R;
import com.nds.pmc.views.activities.CategorySearchResultActivity;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.core.AllOf.allOf;

/**
 * Created by Namrata Shah on 2/17/2018.
 */

public class CategorySearchResultActivityTest {

    @Rule
    public ActivityTestRule<CategorySearchResultActivity> mActivityTestRule = new ActivityTestRule<>(CategorySearchResultActivity.class);

    @Test
    public void categorySearchResultActivityTest() {

        ViewInteraction frameLayout = onView(
                allOf(withId(R.id.container)));
        frameLayout.perform(click());

        ViewInteraction progressBar = onView(
                allOf(withId(R.id.progress), isDisplayed()));

    }

}
