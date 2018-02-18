package com.nds.pmc.placesinmycircle;

import com.nds.pmc.R;
import com.nds.pmc.views.fragments.FavoritePlaceListFragment;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.core.IsNot.not;

/**
 * Created by Namrata Shah on 2/18/2018.
 */

public class FavoritePlaceListFragmentTest {


    @Rule
    public FragmentTestRule<FavoritePlaceListFragment> mFragmentTestRule = new FragmentTestRule<>(FavoritePlaceListFragment.class);

    @Test
    public void display_error_fragment() {
        mFragmentTestRule.launchActivity(null);
        onView(withId(R.id.progress)).check(matches(not(isDisplayed())));
        onView(withId(R.id.noPlace)).check(matches(isDisplayed()));
        onView(withId(R.id.searchResultRecyclerView)).check(matches(not(isDisplayed())));
    }

}
