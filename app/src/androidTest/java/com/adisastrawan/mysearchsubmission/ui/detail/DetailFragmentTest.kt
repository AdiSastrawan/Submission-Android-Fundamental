package com.adisastrawan.mysearchsubmission.ui.detail

import android.view.KeyEvent
import android.view.View
import androidx.test.core.app.ActivityScenario
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.adisastrawan.mysearchsubmission.ui.home.MainActivity
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import com.adisastrawan.mysearchsubmission.R
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.adisastrawan.mysearchsubmission.ui.home.UsersAdapter
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.not
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

@RunWith(AndroidJUnit4ClassRunner::class)
class DetailFragmentTest{

    @Rule
    @JvmField
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Before
    fun setup(){
        ActivityScenario.launch(MainActivity::class.java)
    }

    @Test
    fun assertGetList(){
        onView(withId(R.id.searchBar)).perform(click())
        onView(withId(R.id.searchView)).check(matches(isDisplayed()))
        onView(withId(com.google.android.material.R.id.open_search_view_edit_text)).perform(typeText("HarrySudanaa"),
        )
        onView(withId(R.id.searchView)).perform(pressKey(KeyEvent.KEYCODE_ENTER));

//        onView(withId(R.id.searchBar)).check(matches(withText("HarrySudanaa")))
        onView(withId(R.id.rv_users)).check(matches(isDisplayed()))
    }
    fun waitFor(delay : Long):ViewAction{
        return object : ViewAction {
            override fun getConstraints(): Matcher<View> = isRoot()
            override fun getDescription(): String = "wait for $delay milliseconds"
            override fun perform(uiController: UiController, v: View?) {
                uiController.loopMainThreadForAtLeast(delay)
            }
        }
    }
}