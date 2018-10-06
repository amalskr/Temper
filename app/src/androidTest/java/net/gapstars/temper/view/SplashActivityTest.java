package net.gapstars.temper.view;


import android.support.test.espresso.ViewInteraction;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import net.gapstars.temper.R;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class SplashActivityTest {

    @Rule
    public ActivityTestRule<SplashActivity> mActivityTestRule = new ActivityTestRule<>(SplashActivity.class);

    @Test
    public void splashActivityTest() {
        ViewInteraction textView = onView(
                allOf(withId(R.id.job_count_textView), withText("43"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.item_job_date),
                                        0),
                                1),
                        isDisplayed()));
        textView.check(matches(withText("43")));

        ViewInteraction textView2 = onView(
                allOf(withId(R.id.job_date_textView), withText("2018-05-23"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.item_job_date),
                                        0),
                                0),
                        isDisplayed()));
        textView2.check(matches(withText("2018-05-23")));

        ViewInteraction linearLayout = onView(
                allOf(childAtPosition(
                        allOf(withId(R.id.item_job_date),
                                childAtPosition(
                                        withId(R.id.jobs_recyclerView),
                                        2)),
                        0),
                        isDisplayed()));
        linearLayout.check(matches(isDisplayed()));

        ViewInteraction linearLayout2 = onView(
                allOf(childAtPosition(
                        allOf(withId(R.id.item_job_date),
                                childAtPosition(
                                        withId(R.id.jobs_recyclerView),
                                        2)),
                        0),
                        isDisplayed()));
        linearLayout2.check(matches(isDisplayed()));
    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
