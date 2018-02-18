package com.nds.pmc.placesinmycircle;

import com.nds.pmc.R;
import com.nds.pmc.views.fragments.SearchCategoryFragment;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by Namrata Shah on 2/18/2018.
 */

public class SearchCategoryFragmentTest {
    @Rule
    public FragmentTestRule<SearchCategoryFragment> mFragmentTestRule = new FragmentTestRule<>(SearchCategoryFragment.class);

    @Test
    public void display_error_fragment() {
        mFragmentTestRule.launchActivity(null);
        onView(withId(R.id.categoryRecyclerView)).check(matches(isDisplayed()));
    }
}
