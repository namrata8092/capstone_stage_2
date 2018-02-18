package com.nds.pmc.placesinmycircle;

import com.nds.pmc.R;
import com.nds.pmc.views.fragments.ErrorFragment;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by Namrata Shah on 2/17/2018.
 */

public class ErrorFragmentTest {

    @Rule
    public FragmentTestRule<ErrorFragment> mFragmentTestRule = new FragmentTestRule<>(ErrorFragment.class);

    @Test
    public void display_error_fragment() {
        mFragmentTestRule.launchActivity(null);
        onView(withId(R.id.container)).check(matches(isDisplayed()));

        onView(withId(R.id.errorMsg)).check(matches(isDisplayed()));

    }
}
