package com.nds.pmc.placesinmycircle;

import android.content.pm.ActivityInfo;

import com.nds.pmc.R;
import com.nds.pmc.views.fragments.SearchResultListFragment;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by Namrata on 2/25/2018.
 */

public class SearchResultListFragmentTest {

    @Rule
    public FragmentTestRule<SearchResultListFragment> mFragmentTestRule = new FragmentTestRule<>(SearchResultListFragment.class);

    @Test
    public void display_place_search_list_fragment_in_portrait_mode() {
        mFragmentTestRule.launchActivity(null);
        mFragmentTestRule.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        onView(withId(R.id.searchResultRecyclerView)).check(matches(isDisplayed()));
    }

    @Test
    public void display_place_search_list_fragment_in_landscape_mode() {
        mFragmentTestRule.launchActivity(null);
        mFragmentTestRule.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        onView(withId(R.id.searchResultRecyclerView)).check(matches(isDisplayed()));
        onView(withId(R.id.detail_container)).check(matches(isDisplayed()));
    }
}
