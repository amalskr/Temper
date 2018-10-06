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
import org.hamcrest.core.IsInstanceOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<SplashActivity> mActivityTestRule = new ActivityTestRule<>(SplashActivity.class);

    @Test
    public void mainActivityTest() {
        ViewInteraction linearLayout = onView(
                allOf(childAtPosition(
                        allOf(withId(R.id.item_job_date),
                                childAtPosition(
                                        withId(R.id.jobs_recyclerView),
                                        0)),
                        0),
                        isDisplayed()));
        linearLayout.perform(click());

        ViewInteraction recyclerView = onView(
                allOf(withId(R.id.inner_recycler_view),
                        childAtPosition(
                                withId(R.id.item_job_date),
                                1)));
        recyclerView.perform(actionOnItemAtPosition(1, click()));

        ViewInteraction floatingActionButton = onView(
                allOf(withId(R.id.fab_star),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                2),
                        isDisplayed()));
        floatingActionButton.perform(click());

        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.snackbar_action), withText("UNDO"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.support.design.widget.Snackbar$SnackbarLayout")),
                                        0),
                                1),
                        isDisplayed()));
        appCompatButton.perform(click());

        ViewInteraction appCompatButton2 = onView(
                allOf(withText("More"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.support.v7.widget.CardView")),
                                        0),
                                3),
                        isDisplayed()));
        appCompatButton2.perform(click());

        pressBack();

        ViewInteraction recyclerView2 = onView(
                allOf(withId(R.id.inner_recycler_view),
                        childAtPosition(
                                withId(R.id.item_job_date),
                                1)));
        recyclerView2.perform(actionOnItemAtPosition(2, click()));

        ViewInteraction appCompatImageButton = onView(
                allOf(withContentDescription("Navigate up"),
                        childAtPosition(
                                allOf(withId(R.id.toolbar),
                                        childAtPosition(
                                                withContentDescription("Sitecrew"),
                                                1)),
                                1),
                        isDisplayed()));
        appCompatImageButton.perform(click());

        ViewInteraction linearLayout2 = onView(
                allOf(childAtPosition(
                        allOf(withId(R.id.item_job_date),
                                childAtPosition(
                                        withId(R.id.jobs_recyclerView),
                                        0)),
                        0),
                        isDisplayed()));
        linearLayout2.perform(click());

        ViewInteraction linearLayout3 = onView(
                allOf(childAtPosition(
                        allOf(withId(R.id.item_job_date),
                                childAtPosition(
                                        withId(R.id.jobs_recyclerView),
                                        1)),
                        0),
                        isDisplayed()));
        linearLayout3.perform(click());

        ViewInteraction recyclerView3 = onView(
                allOf(withId(R.id.inner_recycler_view),
                        childAtPosition(
                                withId(R.id.item_job_date),
                                1)));
        recyclerView3.perform(actionOnItemAtPosition(1, click()));

        pressBack();

        ViewInteraction recyclerView4 = onView(
                allOf(withId(R.id.inner_recycler_view),
                        childAtPosition(
                                withId(R.id.item_job_date),
                                1)));
        recyclerView4.perform(actionOnItemAtPosition(2, click()));

        ViewInteraction appCompatImageButton2 = onView(
                allOf(withContentDescription("Navigate up"),
                        childAtPosition(
                                allOf(withId(R.id.toolbar),
                                        childAtPosition(
                                                withContentDescription("Hulpkok"),
                                                1)),
                                1),
                        isDisplayed()));
        appCompatImageButton2.perform(click());

        ViewInteraction textView = onView(
                allOf(withId(R.id.fee_text), withText("EUR 16.0"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                        0),
                                0),
                        isDisplayed()));
        textView.check(matches(withText("EUR -1.0")));

        ViewInteraction ratingBar = onView(
                allOf(withId(R.id.ratingBar),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                        1),
                                0),
                        isDisplayed()));
        ratingBar.check(matches(isDisplayed()));

        ViewInteraction ratingBar2 = onView(
                allOf(withId(R.id.ratingBar),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                        1),
                                0),
                        isDisplayed()));
        ratingBar2.check(matches(isDisplayed()));
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
