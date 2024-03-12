package com.adisastrawan.mysearchsubmission.ui.home

import android.view.KeyEvent
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.adisastrawan.mysearchsubmission.R
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class HomeFragmentTest{

    @Before
    fun setup(){
        ActivityScenario.launch(MainActivity::class.java)
    }

    @Test
    fun assertSearchUsername(){
        val username = "HarrySudana"
        Espresso.onView(ViewMatchers.withId(R.id.searchBar)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.searchView))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(com.google.android.material.R.id.open_search_view_edit_text))
            .perform(
                ViewActions.typeText(username),
        )
        Espresso.onView(ViewMatchers.withId(R.id.searchView))
            .perform(ViewActions.pressKey(KeyEvent.KEYCODE_ENTER))

        Espresso.onView(ViewMatchers.withId(R.id.rv_users))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

}