package com.nds.pmc.placesinmycircle;

import com.nds.pmc.R;
import com.nds.pmc.views.fragments.SearchResultDetailFragment;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by Namrata Shah on 2/18/2018.
 */

public class SearchResultDetailFragmentTest {
    @Rule
    public FragmentTestRule<SearchResultDetailFragment> mFragmentTestRule = new FragmentTestRule<>(SearchResultDetailFragment.class);

    @Test
    public void display_search_place_detail_fragment() {
        mFragmentTestRule.launchActivity(null);
        onView(withId(R.id.posterImage)).check(matches(isDisplayed()));
        onView(withId(R.id.placeTitle)).check(matches(isDisplayed()));
        onView(withId(R.id.placeAddress)).check(matches(isDisplayed()));
        onView(withId(R.id.resultRating)).check(matches(isDisplayed()));
        onView(withId(R.id.openSymbol)).check(matches(isDisplayed()));
        onView(withId(R.id.imageLink)).check(matches(isDisplayed()));
        onView(withId(R.id.weeklyTiming)).check(matches(isDisplayed()));
        onView(withId(R.id.reviews)).check(matches(isDisplayed()));
        onView(withId(R.id.reviewList)).check(matches(isDisplayed()));
        onView(withId(R.id.addToFavorite)).check(matches(isDisplayed()));
        onView(withId(R.id.placeLocation)).check(matches(isDisplayed()));
        onView(withId(R.id.phoneNumber)).check(matches(isDisplayed()));
        onView(withId(R.id.website)).check(matches(isDisplayed()));

    }
}
